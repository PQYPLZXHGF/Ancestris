package ancestris.modules.releve.dnd;

import ancestris.modules.releve.dnd.MergeRecord.MergeParticipantType;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertySex;
import genj.gedcom.TagPath;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbBundle;

/**
 *
 */
class MergeModelMiscWill extends MergeModel {

    private Indi currentIndi;
    
    /**
     * model factory
     * cree un liste contenant un modele comparant le releve et l'entité
     * selectionnée dans le gedcom.
     * Si selectedEntity = null, la liste contient les modeles comparant le relevé
     * avec les entités du gedcom dont les noms, prenoms, et dates de naissance,
     * mariage et décès sont compatibles avec le relevé.
     * @param mergeRecord   releve
     * @param gedcom
     * @param selectedEntity entité sélectionnée dans le gedcom
     * @return
     */
    static protected List<MergeModel> createMergeModelMiscWill (MergeRecord mergeRecord, Gedcom gedcom, Entity selectedEntity, boolean showNewParents) throws Exception {
        List<MergeModel> models = new ArrayList<MergeModel>();
        if (selectedEntity instanceof Fam) {
            // 1) Record Will : l'entité selectionnée est une famille
            Fam family = (Fam) selectedEntity;
            // j'ajoute un nouvel individu
            models.add(new MergeModelMiscWill(mergeRecord, gedcom, null, family));
            // je recherche les enfants de la famille sélectionnée compatibles avec le releve
            List<Indi> sameChildren = MergeQuery.findSameChild(mergeRecord, gedcom, family);
            // j'ajoute les enfants compatibles
            for (Indi samedIndi : sameChildren) {
                models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, samedIndi.getFamilyWhereBiologicalChild()));
            }
        } else if (selectedEntity instanceof Indi) {
            // 1) Record Will : l'entité selectionnée est un individu
            Indi selectedIndi = (Indi) selectedEntity;

            // je recherche la famille avec l'ex conjoint
            List<Fam> marriedFamilies = MergeQuery.findFamilyCompatibleWithParticipantMarried(mergeRecord, MergeRecord.MergeParticipantType.participant1, gedcom);

            // je cherche les familles des parents compatibles avec le releve
            List<Fam> parentFamilies = MergeQuery.findFamilyCompatibleWithParticipantParents(mergeRecord, MergeRecord.MergeParticipantType.participant1, gedcom);

            if (!marriedFamilies.isEmpty()) {
                for (Fam family : marriedFamilies) {
                    if (mergeRecord.getIndi().getSex() == PropertySex.MALE) {
                        Indi husband = family.getHusband();
                        if (selectedIndi.compareTo(husband) == 0) {
                            Fam husbandParentFamily = husband.getFamilyWhereBiologicalChild();
                            models.add(new MergeModelMiscWill(mergeRecord, gedcom, husband, family, husbandParentFamily));

                            if (husbandParentFamily == null) {
                                // j'ajoute un nouvel individu avec les familles compatibles
                                for (Fam parentFamily : parentFamilies) {
                                    models.add(new MergeModelMiscWill(mergeRecord, gedcom, husband, family, parentFamily));
                                }
                            }
                        }
                    } else {
                        Indi wife = family.getWife();
                        if (selectedIndi.compareTo(wife) == 0) {
                            Fam wifeParentFamily = wife.getFamilyWhereBiologicalChild();
                            models.add(new MergeModelMiscWill(mergeRecord, gedcom, wife, family, wifeParentFamily));

                            if (wifeParentFamily == null) {
                                // j'ajoute un nouvel individu avec les familles compatibles
                                for (Fam parentFamily : parentFamilies) {
                                    models.add(new MergeModelMiscWill(mergeRecord, gedcom, wife, family, parentFamily));
                                }
                            }
                        }
                    }
                }
            } else {
                // l'individu n'a pas d'ex conjoint

                // je cherche les familles compatibles avec le releve de deces
                List<Fam> families = MergeQuery.findFamilyCompatibleWithParticipantParents(mergeRecord, MergeRecord.MergeParticipantType.participant1, gedcom);

                // j'ajoute l'individu selectionné par dnd
                if (selectedIndi.getFamilyWhereBiologicalChild() != null) {
                    // j'ajoute l'individu selectionné par dnd
                    models.add(new MergeModelMiscWill(mergeRecord, gedcom, selectedIndi, selectedIndi.getFamilyWhereBiologicalChild()));
                } else {
                    models.add(new MergeModelMiscWill(mergeRecord, gedcom, selectedIndi, (Fam) null));
                    // j'ajoute l'individu selectionné par dnd avec les familles compatibles
                    for (Fam family : families) {
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, selectedIndi, family));
                    }
                }

                // je recupere les individus compatibles avec le relevé (qui portent le meme nom que le nom qui est dans le
                // releve et avec les dates de naissance compatibles et les parents compatibles)
                // en excluant l'individu selectionne s'il a deja une famille
                List<Indi> sameIndis;
                if (selectedIndi.getFamilyWhereBiologicalChild() != null) {
                    // l'individu est lié a une famille précise, je l'exclue de la recherche
                    sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, selectedIndi);
                } else {
                    // l'individu n'est pas lié a une famille précise, je l'inclue dans la recherche
                    sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, null);
                }
                // j'ajoute les individus compatibles
                for (Indi samedIndi : sameIndis) {
                    // j'ajoute les familles compatibles
                    Fam sameIndiFamily = samedIndi.getFamilyWhereBiologicalChild();
                    if (sameIndiFamily != null) {
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, sameIndiFamily));
                    } else {
                        for (Fam family : families) {
                            models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, family));
                        }
                    }
                }
            }

        } else {
            // 3) Record Will : pas d'entité selectionnee

            // j'ajoute un nouvel individu , sans famille associée
            models.add(new MergeModelMiscWill(mergeRecord, gedcom));

            // je recherche la famille avec l'ex conjoint
            List<Fam> marriedFamilies = MergeQuery.findFamilyCompatibleWithParticipantMarried(mergeRecord, MergeRecord.MergeParticipantType.participant1, gedcom);

            // je cherche les familles des parents compatibles avec le releve
            List<Fam> parentFamilies = MergeQuery.findFamilyCompatibleWithParticipantParents(mergeRecord, MergeRecord.MergeParticipantType.participant1, gedcom);

            if (!marriedFamilies.isEmpty()) {
                for (Fam family : marriedFamilies) {
                    if (mergeRecord.getIndi().getSex() == PropertySex.MALE) {
                        Indi husband = family.getHusband();
                        Fam husbandParentFamily = husband.getFamilyWhereBiologicalChild();
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, husband, family, husbandParentFamily));

                        if (husbandParentFamily == null) {
                            // j'ajoute un nouvel individu avec les familles compatibles
                            for (Fam parentFamily : parentFamilies) {
                                models.add(new MergeModelMiscWill(mergeRecord, gedcom, husband, family, parentFamily));
                            }
                        }
                    } else {
                        Indi wife = family.getWife();
                        Fam wifeParentFamily = wife.getFamilyWhereBiologicalChild();
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, wife, family, wifeParentFamily));

                        if (wifeParentFamily == null) {
                            // j'ajoute un nouvel individu avec les familles compatibles
                            for (Fam parentFamily : parentFamilies) {
                                models.add(new MergeModelMiscWill(mergeRecord, gedcom, wife, family, parentFamily));
                            }
                        }
                    }
                }

            } else {
                // il n'y a pas de famille pour de l'ex conjoint

                // je recupere les individus compatibles avec le relevé (qui portent le meme nom que le nom qui est dans le
                // releve et avec les dates de naissance compatibles et les parents compatibles)
                List<Indi> sameIndis = MergeQuery.findIndiCompatibleWithParticipant(mergeRecord, MergeParticipantType.participant1, gedcom, null);

                // j'ajoute un nouvel individu avec les familles compatibles
                for (Fam family : parentFamilies) {
                    models.add(new MergeModelMiscWill(mergeRecord, gedcom, null, family));
                }

                // j'ajoute les individus compatibles avec la famille de chacun
                for (Indi samedIndi : sameIndis) {
                    Fam sameIndiFamily = samedIndi.getFamilyWhereBiologicalChild();
                    if (sameIndiFamily != null) {
                        // j'ajoute l'individus compatible avec sa famille
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, sameIndiFamily));
                    } else {
                        // j'ajoute l'individus compatible sans famille
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, (Fam) null));
                        // j'ajoute l'individus compatible avec les familles compatibles
                        for (Fam family : parentFamilies) {
                            models.add(new MergeModelMiscWill(mergeRecord, gedcom, samedIndi, family));
                        }
                    }
                }
            }

            if (showNewParents) {
                // j'ajoute un nouvel individu en formant des couples qui ne sont pas des familles
                // mais qui pourraient être ses parents
                List<Indi> fathers = new ArrayList<Indi>();
                List<Indi> mothers = new ArrayList<Indi>();
                MergeQuery.findFatherMotherCompatibleWithBirthParticipant(mergeRecord, gedcom, parentFamilies, fathers, mothers);
                for (Indi father : fathers) {
                    for (Indi mother : mothers) {
                        models.add(new MergeModelMiscWill(mergeRecord, gedcom, father, mother));
                    }
                }
            }

        }
        return models;
    }


    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve et les
     *
     * @param indi
     * @param record
     */
    protected MergeModelMiscWill(MergeRecord record, Gedcom gedcom) throws Exception {
        this(record, gedcom, (Indi) null, (Fam) null, (Fam) null);
    }

    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve
     *
     * @param indi
     * @param record
     */
    protected MergeModelMiscWill(MergeRecord record, Gedcom gedcom, Indi indi, Fam parentfam) throws Exception {
        this(record, gedcom, indi, (Fam) null, parentfam);
    }

    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve
     *
     * @param record
     * @param gedcom
     * @param indi
     * @param marriedFamily famille avec l'ex conjoint
     * @param parentFamily  famille parent de l'individu
     */
    protected MergeModelMiscWill(MergeRecord record, Gedcom gedcom, Indi indi, Fam marriedFamily, Fam parentFamily) throws Exception {
        super(record, indi, gedcom);
        this.currentIndi = indi;
        addRowSource();
        addRowEvent(currentIndi, "WILL");
        addRowSeparator();
        addRowIndi();
        addRowMarried(marriedFamily);
        addRowParents(parentFamily);

    }
    
    /**
     * le constucteur initialise les données du modele
     * en comparant les champs du releve et les
     *
     * @param indi
     * @param record
     */
    protected MergeModelMiscWill(MergeRecord record, Gedcom gedcom, Indi father, Indi mother ) throws Exception {
        super(record, null, gedcom);
        this.currentIndi = null;
        
        addRowSource();
        addRowEvent(currentIndi, "WILL");
        addRowSeparator();
        addRowIndi();
        
        // j'affiche l'ex conjoint
        addRowMarried(null);

        // j'affiche la famille des parents de l'individu
        addRowSeparator();
        addRow(RowType.IndiParentFamily, null);
        addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), null);

        // j'affiche les parents de l'individu
        addRowFather(father);
        addRowMother(mother);
    }
    
    private void addRowIndi() throws Exception {

       if (currentIndi != null) {
           // je recherche la source d'un testament deja existant
//           Property willProperty = currentIndi.getProperty("WILL");
//            // j'affiche la date, le lieu et les commentaires du testament
//            addRow(RowType.EventDate, record.getEventDate(),   willProperty != null ? (PropertyDate)willProperty.getProperty("DATE") : null);
//            addRow(RowType.EventPlace, record.getEventPlace(),  willProperty != null ? willProperty.getPropertyValue("PLAC") : "");
//            addRow(RowType.EventComment, record.getEventComment(showFrenchCalendarDate),  willProperty != null ? willProperty.getPropertyValue("NOTE") : "");
//            addRowSeparator();

            // j'affiche les informations de l'individu
            addRow(RowType.IndiLastName, record.getIndi().getLastName(), currentIndi.getLastName(), currentIndi);
            addRow(RowType.IndiFirstName, record.getIndi().getFirstName(), currentIndi.getFirstName());
            addRow(RowType.IndiSex, record.getIndi().getSexString(), currentIndi.getPropertyValue("SEX"));
            addRow(RowType.IndiBirthDate, record.getIndi().getBirthDate() , currentIndi.getBirthDate());
            addRow(RowType.IndiBirthPlace, record.getIndi().getBirthPlace(), currentIndi.getValue(new TagPath("INDI:BIRT:PLAC"), ""));
            addRow(RowType.IndiDeathDate, record.getIndi().getDeathDate() , currentIndi.getDeathDate());
            addRow(RowType.IndiOccupation, record.getIndi().getOccupationWithDate(), MergeQuery.findOccupation(currentIndi, record.getEventDate()));

        } else {
            // selectedIndi est nul

//            // j'affiche la date, le lieu et les commentaires du testament
//            addRow(RowType.EventDate, record.getEventDate(), null);
//            addRow(RowType.EventPlace, record.getEventPlace(), "");
//            addRow(RowType.EventComment, record.getEventComment(showFrenchCalendarDate), "");
//            addRowSeparator();

            // j'affiche le nom
            addRow(RowType.IndiLastName, record.getIndi().getLastName(), "");
            addRow(RowType.IndiFirstName, record.getIndi().getFirstName(), "");
            addRow(RowType.IndiSex, record.getIndi().getSexString(), "");
            addRow(RowType.IndiBirthDate, record.getIndi().getBirthDate() , null);
            addRow(RowType.IndiBirthPlace, record.getIndi().getBirthPlace(), "");
            addRow(RowType.IndiDeathDate, record.getIndi().getDeathDate() , null);
            addRow(RowType.IndiOccupation, record.getIndi().getOccupationWithDate(), "");
        }
    }

    private void addRowParents( Fam fam) throws Exception {
        addRowSeparator();
        addRow(RowType.IndiParentFamily, fam);
        if (fam != null) {
            addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), fam.getMarriageDate());
            addRowFather(fam.getHusband());
            addRowMother(fam.getWife());
        } else {
            addRow(RowType.IndiParentMarriageDate, record.getIndi().getParentMarriageDate(), null);
            addRowFather(null);
            addRowMother(null);
        }
    }

    private void addRowFather( Indi father ) throws Exception {
        if (father != null) {
            addRow(RowType.IndiFatherLastName, record.getIndi().getFatherLastName(), father.getLastName(), father);
            addRow(RowType.IndiFatherFirstName, record.getIndi().getFatherFirstName(), father.getFirstName());
            addRow(RowType.IndiFatherBirthDate, record.getIndi().getFatherBirthDate(), father.getBirthDate());
            addRow(RowType.IndiFatherDeathDate, record.getIndi().getFatherDeathDate(), father.getDeathDate());
            addRow(RowType.IndiFatherOccupation, record.getIndi().getFatherOccupationWithDate(), MergeQuery.findOccupation(father, record.getEventDate()));
        } else {
            addRow(RowType.IndiFatherLastName, record.getIndi().getFatherLastName(), "");
            addRow(RowType.IndiFatherFirstName, record.getIndi().getFatherFirstName(), "");
            addRow(RowType.IndiFatherBirthDate, record.getIndi().getFatherBirthDate(), null);
            addRow(RowType.IndiFatherDeathDate, record.getIndi().getFatherDeathDate(), null);
            addRow(RowType.IndiFatherOccupation, record.getIndi().getFatherOccupationWithDate(), "");
        }
    }

    private void addRowMother( Indi mother ) throws Exception {
        if (mother != null) {
            addRow(RowType.IndiMotherLastName, record.getIndi().getMotherLastName(), mother.getLastName(), mother);
            addRow(RowType.IndiMotherFirstName, record.getIndi().getMotherFirstName(), mother.getFirstName());
            addRow(RowType.IndiMotherBirthDate, record.getIndi().getMotherBirthDate(), mother.getBirthDate());
            addRow(RowType.IndiMotherDeathDate, record.getIndi().getMotherDeathDate(), mother.getDeathDate());
            addRow(RowType.IndiMotherOccupation, record.getIndi().getMotherOccupationWithDate(), MergeQuery.findOccupation(mother, record.getEventDate()));
        } else {
            addRow(RowType.IndiMotherLastName, record.getIndi().getMotherLastName(), "");
            addRow(RowType.IndiMotherFirstName, record.getIndi().getMotherFirstName(), "");
            addRow(RowType.IndiMotherBirthDate, record.getIndi().getMotherBirthDate(), null);
            addRow(RowType.IndiMotherDeathDate, record.getIndi().getMotherDeathDate(), null);
            addRow(RowType.IndiMotherOccupation, record.getIndi().getMotherOccupationWithDate(), "");
        }
    }

    /**
     * affiche les informations sur l'ex conjoint
     * @param marriedFamily
     * @throws Exception
     */
    private void addRowMarried( Fam marriedFamily ) throws Exception {
        if (! record.getIndi().getMarriedLastName().isEmpty() || ! record.getIndi().getMarriedFirstName().isEmpty()) {
            // j'affiche un separateur
            addRowSeparator();
            addRow(RowType.IndiMarriedFamily, marriedFamily);

            if (marriedFamily != null) {
                addRow(RowType.IndiMarriedMarriageDate, record.getIndi().getMarriedMarriageDate(), marriedFamily.getMarriageDate());

                Indi married;
                if ( currentIndi.getSex() == PropertySex.MALE) {
                    married = marriedFamily.getWife();
                } else {
                    married = marriedFamily.getHusband();
                }
                addRow(RowType.IndiMarriedLastName, record.getIndi().getMarriedLastName(), married.getLastName(), married);
                addRow(RowType.IndiMarriedFirstName, record.getIndi().getMarriedFirstName(), married.getFirstName());
                addRow(RowType.IndiMarriedBirthDate, record.getIndi().getMarriedBirthDate(), married.getBirthDate());
                addRow(RowType.IndiMarriedDeathDate, record.getIndi().getMarriedDeathDate(), married.getDeathDate());
                addRow(RowType.IndiMarriedOccupation, record.getIndi().getMarriedOccupationWithDate(), MergeQuery.findOccupation(married, record.getEventDate()));
            } else {
                addRow(RowType.IndiMarriedMarriageDate, record.getIndi().getMarriedMarriageDate(),null);
                addRow(RowType.IndiMarriedLastName, record.getIndi().getMarriedLastName(), "", null);
                addRow(RowType.IndiMarriedFirstName, record.getIndi().getMarriedFirstName(), "");
                addRow(RowType.IndiMarriedBirthDate, record.getIndi().getMarriedBirthDate(), null);
                addRow(RowType.IndiMarriedDeathDate, record.getIndi().getMarriedDeathDate(), null);
                addRow(RowType.IndiMarriedOccupation, record.getIndi().getMarriedOccupationWithDate(), "");
            }            
        }
    }

     /**
     * retourne l'individu proposé dans le modele
     * @return 
     */
    @Override
    protected Entity getProposedEntity() {
        if (currentIndi != null)  {
            return currentIndi;
        } else {
            Entity parentFamily = getEntityObject(MergeModel.RowType.IndiParentFamily);
            if (parentFamily != null) {
                return parentFamily;
            } else {
                Entity marriedFamily = getEntityObject(RowType.IndiMarriedFamily);
                if (marriedFamily != null) {
                    return marriedFamily;
                } else {
                    return null;
                }
            }

        }
    }
    
    /**
     * retoune l'individu selectionné
     * @return individu selectionné ou null si c'est un nouvel individu
     */
    @Override
    protected Entity getSelectedEntity() {
        return currentIndi;
    }
    
    /**
     * copie les données du relevé dans l'entité
     */
    @Override
    protected Property copyRecordToEntity() throws Exception {
        
        Property resultProperty;
        
        if (currentIndi == null) {
            currentIndi = (Indi) gedcom.createEntity(Gedcom.INDI);
            currentIndi.setName(record.getIndi().getFirstName(), record.getIndi().getLastName());
            currentIndi.setSex(record.getIndi().getSex());
        } else {
            // je copie le nom du releve dans l'individu
            if (isChecked(RowType.IndiLastName)) {
                currentIndi.setName(currentIndi.getFirstName(), record.getIndi().getLastName());
            }

            // je copie le prénom du releve dans l'individu
            if (isChecked(RowType.IndiFirstName)) {
                currentIndi.setName(record.getIndi().getFirstName(), currentIndi.getLastName());
            }

            // je copie le sexe du releve dans l'individu
            if (isChecked(RowType.IndiSex)) {
                currentIndi.setSex(record.getIndi().getSex());
            }
        }
        
        // je cree la propriete de naissance si elle n'existait pas
        if (isChecked(RowType.IndiBirthDate) || isChecked(RowType.IndiBirthPlace) || isChecked(RowType.EventSource) || isChecked(RowType.EventComment)) {
            Property birthProperty = currentIndi.getProperty("BIRT");
            if (birthProperty == null) {
                birthProperty = currentIndi.addProperty("BIRT", "");
            }

            // je copie la date de naissance du releve dans l'individu
            if (isChecked(RowType.IndiBirthDate)) {
                // j'ajoute (ou remplace) la date de la naissance (le lieu de naissance n'est pas connu)
                copyBirthDate(currentIndi, getRow(RowType.IndiBirthDate), "", record);
            }

            // je copie le lieu de naissance
            if (isChecked(RowType.IndiBirthPlace)) {
                copyPlace(record.getIndi().getBirthPlace(), birthProperty);
            }
        }

        // je copie la date de décès 
        if (isChecked(RowType.IndiDeathDate)) {
            copyDeathDate(currentIndi, getRow(RowType.IndiDeathDate), record);
        }
        
        // je copie la profession de l'individu
        if (isChecked(RowType.IndiOccupation)) {
            copyOccupation(currentIndi, record.getIndi().getOccupation(), record.getIndi().getResidence(), true, record);
        }
                
        // je cree la propriete de testament 
        resultProperty = copyEvent(currentIndi);

        // je copie les donnéees du conjoint
        copyIndiMarried(record.getIndi(), currentIndi);
        
        // je copie les données des parents
        if (isChecked(RowType.IndiParentFamily)) {
            // je copie la famille des parents
            Fam family = (Fam) getEntityObject(RowType.IndiParentFamily);
            if (family == null) {
                // je cree la famille
                family = (Fam) gedcom.createEntity(Gedcom.FAM);
            }

            // j'ajoute l'enfant dans la famille si ce n'est pas déja le cas
            if (!currentIndi.isDescendantOf(family)) {
                family.addChild(currentIndi);
            }

            // je copie la date du mariage et une note indiquant l'origine de cette date
            if (isChecked(RowType.IndiParentMarriageDate)) {                
                copyMarriageDate(family, getRow(RowType.IndiParentMarriageDate), record );
            }

            // je copie les parents
            copyIndiFather(record.getIndi(), family.getHusband(), family);
            copyIndiMother(record.getIndi(), family.getWife(), family);
  
        }
        return resultProperty;
    }

    /**
     * retourne une chaine de caracteres contenant le nom, prénom et la date du relevé
     * Cette chaine sert de titre a la fenetre principale
     * @return
     */
    @Override
    protected String getTitle() {
        String message = record.getIndi().getFirstName() + " "+ record.getIndi().getLastName()+ " " + record.getEventDate().getDisplayValue();
        return MessageFormat.format(NbBundle.getMessage(MergeDialog.class, "MergeModel.title.miscWill"), message);
    }

    /**
     * retourne un resumé du modele
     * Cette chaine sert de commentaire dans la liste des modeles
     * @return
     */
    @Override
    public String getSummary(Entity selectedEntity) {
        String summary;
        if ( currentIndi == null ) {
                summary = "Nouvel individu" + " - ";
                if (getEntityObject(MergeModel.RowType.IndiParentFamily) != null) {
                    summary += getEntityObject(MergeModel.RowType.IndiParentFamily).toString(false);
                } else {
                    if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null
                            || getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                        summary += "Nouveau couple:" + " ";
                        if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiFatherLastName).toString(true);
                        } else {
                            summary += "Nouveau père";
                        }
                        summary += " , ";
                        if (getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiMotherLastName).toString(true);
                        } else {
                            summary += "Nouvelle mère";
                        }
                    } else {
                        if( isChecked(MergeModel.RowType.IndiParentFamily)) {
                            summary += "Nouveaux parents";
                        } else {
                            summary += "Sans parents";
                        }
                    }
                }
        } else {
            if (selectedEntity instanceof Fam) {
                summary = "Nouvel enfant de la famille sélectionnée";
            } else {
                summary = "Modifier " + currentIndi.toString(true) + ", ";

                if (getEntityObject(MergeModel.RowType.IndiParentFamily) != null) {
                    summary += getEntityObject(MergeModel.RowType.IndiParentFamily).toString(false);
                } else {
                    if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null
                            || getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                        summary += "Nouveau couple:" + " ";
                        if (getEntityObject(MergeModel.RowType.IndiFatherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiFatherLastName).toString(true);
                        } else {
                            summary += "Nouveau père";
                        }
                        summary += ", ";
                        if (getEntityObject(MergeModel.RowType.IndiMotherLastName) != null) {
                            summary += getEntityObject(MergeModel.RowType.IndiMotherLastName).toString(true);
                        } else {
                            summary += "Nouvelle mère";
                        }
                    } else {
                        if( isChecked(MergeModel.RowType.IndiParentFamily)) {
                            summary += "Nouveaux parents";
                        } else {
                            summary += "Sans parents";
                        }
                    }
                }
            }
        }
        return summary;
    }

}
