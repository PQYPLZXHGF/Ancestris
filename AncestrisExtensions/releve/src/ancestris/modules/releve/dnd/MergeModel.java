package ancestris.modules.releve.dnd;

import static ancestris.modules.releve.dnd.MergeQuery.isCompatible;
import ancestris.modules.releve.dnd.MergeRecord.MergeParticipant;
import ancestris.modules.releve.dnd.MergeRecord.MergeParticipantType;
import ancestris.modules.releve.dnd.MergeRecord.RecordType;
import genj.gedcom.Entity;
import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.Indi;
import genj.gedcom.Property;
import genj.gedcom.PropertyDate;
import genj.gedcom.PropertyEvent;
import genj.gedcom.PropertyPlace;
import genj.gedcom.PropertySex;
import genj.gedcom.PropertySource;
import genj.gedcom.PropertyXRef;
import genj.gedcom.Source;
import genj.gedcom.TagPath;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 * cette classe gère les lignes affichées dans la fenetre du comparateur
 *
 * @author Michel
 */
public abstract class MergeModel extends AbstractTableModel implements java.lang.Comparable<MergeModel> {

    protected MergeRecord record;
    protected Gedcom gedcom;
    // type de participant 
    protected MergeParticipantType participantType;
    protected MergeParticipant mainParticipant;
    protected boolean showFrenchCalendarDate = true;
    private int nbMatch = 0;
    private int nbMatchMax = 0;
    private PropertyEvent proposedEvent = null;
    private PropertyDate proposedEventDate = null;
    private PropertyPlace proposedEventPlace = null;
    private Property proposedEventComment = null;
    private PropertyEvent proposedInsinuation = null;
    private PropertyDate proposedInsinuationDate = null;
    private PropertyPlace proposedInsinuationPlace = null;
    private Property proposedInsinuationComment = null;

    MergeRowList mergeRowList = new MergeRowList();

    static protected List<MergeModel> createMergeModel(MergeRecord mergeRecord, Gedcom gedcom, Entity selectedEntity) throws Exception {
        return createMergeModel(mergeRecord, gedcom, selectedEntity, false);
    }

    /**
     * model factory 
     * cree un liste contenant un modele comparant le releve et
     * l'entité selectionnée dans le gedcom. Si selectedEntity = null, la liste
     * contient les modeles comparant le relevé avec les entités du gedcom dont
     * les noms, prenoms, et dates de naissance, mariage et décès sont
     * compatibles avec le relevé.
     *
     * @param mergeRecord releve
     * @param gedcom
     * @param selectedEntity entité sélectionnée dans le gedcom
     * @return
     */
    static protected List<MergeModel> createMergeModel(MergeRecord mergeRecord, Gedcom gedcom, Entity selectedEntity, boolean showNewParents) throws Exception {
        List<MergeModel> models;
        if (mergeRecord.getType() == MergeRecord.RecordType.Birth) {
            models = MergeModelBirth.createMergeModelBirth(mergeRecord, gedcom, selectedEntity, showNewParents);
        } else if (mergeRecord.getType() == MergeRecord.RecordType.Marriage) {
            models = MergeModelMarriage.createMergeModelMarriage(mergeRecord, gedcom, selectedEntity, showNewParents);
        } else if (mergeRecord.getType() == MergeRecord.RecordType.Death) {
            models = MergeModelDeath.createMergeModelDeath(mergeRecord, gedcom, selectedEntity, showNewParents);
        } else if (mergeRecord.getType() == MergeRecord.RecordType.Misc) {
            if (mergeRecord.getEventTypeTag() == MergeRecord.EventTypeTag.MARB
                    || mergeRecord.getEventTypeTag() == MergeRecord.EventTypeTag.MARC
                    || mergeRecord.getEventTypeTag() == MergeRecord.EventTypeTag.MARL) {
                // Contrat de mariage ou bans de mariage
                models = MergeModelMiscMarc.createMergeModelMiscMarc(mergeRecord, gedcom, selectedEntity, showNewParents);
            } else if (mergeRecord.getEventTypeTag() == MergeRecord.EventTypeTag.WILL) {
                // Testament
                models = MergeModelMiscWill.createMergeModelMiscWill(mergeRecord, gedcom, selectedEntity, showNewParents);
            } else {
                // Autre evenement (quittance, obligation, emancipation, enregistrement, insinuation ...;
                models = MergeModelMiscOther.createMergeModelMiscOther(mergeRecord, gedcom, selectedEntity, showNewParents);
            }
        } else {
            // je retourne une liste de modeles vide.
            models = new ArrayList<MergeModel>();
        }

        // je trie les modeles par ordre décroissant du nombre de champs egaux entre le relevé et l'entité du gedcom
        Collections.sort(models);

        return models;
    }

    ///////////////////////////////////////////////////////////////////////////
    // constructeurs
    ///////////////////////////////////////////////////////////////////////////
    MergeModel(MergeRecord record, Entity proposedEntity, Gedcom gedcom) {
        this(record, MergeParticipantType.participant1, proposedEntity, gedcom);
    }

    MergeModel(MergeRecord record, MergeParticipantType participantType, Entity proposedEntity, Gedcom gedcom) {
        this.record = record;
        this.gedcom = gedcom;
        this.participantType = participantType;
        this.mainParticipant = record.getParticipant(participantType);

        if (proposedEntity != null) {
            if (record.isInsinuation()) {
                String recordEventInsinuationType = record.getInsinuationType();
                PropertyDate recordInsinuationDate = record.getInsinuationDate();
                // je cherche un evenement d'insinuation avec le meme type et une date compatible
                if (recordInsinuationDate.isComparable() ) {
                    for (Property iterationEvent : proposedEntity.getProperties("EVEN")) {

                        String entityEventType = iterationEvent.getPropertyValue("TYPE").trim();
                        if (!recordEventInsinuationType.equalsIgnoreCase(entityEventType)) {
                            continue;
                        }

                        // je recherche les dates meme si elles ne sont pas valides
                        for (Property iterationProperty : iterationEvent.getProperties("DATE", false)) {
                            PropertyDate iterationDate = (PropertyDate) iterationProperty;
                            if (isCompatible(recordInsinuationDate, iterationDate)) {
                                proposedInsinuation = (PropertyEvent) iterationEvent;
                                proposedInsinuationDate = iterationDate;
                                proposedInsinuationPlace = (PropertyPlace) iterationEvent.getProperty("PLAC", false);
                                proposedInsinuationComment = iterationEvent.getProperty("NOTE", false);
                                break;
                            }
                        }
                        if (proposedInsinuation != null) {
                            break;
                        }
                    }
                }
            }

            // je cherche l'evenement dans l'entité
            PropertyDate recordEventDate = record.getEventDate();
            int dateMarge;
            if (record.getType() == RecordType.Birth) {
                // je prends une marge de 1 jout pour les naissances
                dateMarge = 1;
            } else {
                dateMarge = 0;
            }
            for (Property iterationEvent : proposedEntity.getProperties(record.getEventTag())) {
                // je recherche les dates meme si elles ne sont pas valides
                for (Property iterationProperty : iterationEvent.getProperties("DATE", false)) {
                    PropertyDate iterationDate = (PropertyDate) iterationProperty;
                    if (isCompatible(recordEventDate, iterationDate, dateMarge)) {
                        proposedEvent = (PropertyEvent) iterationEvent;
                        proposedEventDate = iterationDate;
                        proposedEventPlace = (PropertyPlace) iterationEvent.getProperty("PLAC", false);
                        proposedEventComment = iterationEvent.getProperty("NOTE", false);
                        break;
                    }
                }
                if (proposedEvent != null) {
                    break;
                }
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // ajout de lignes dans le modele
    ///////////////////////////////////////////////////////////////////////////
    /**
     * ajoute une ligne dans le modele
     *
     * @param rowType
     * @param label
     * @param recordValue
     * @param entityValue
     */
    void addRow(RowType rowType, String recordValue, String entityValue) {
        addRow(rowType, recordValue, entityValue, null);
    }

    /**
     * ajoute une ligne dans le modele pour comparer un champ de type String
     * (nom , prénom, commentaire )
     *
     * @param rowType
     * @param label
     * @param recordValue
     * @param entityValue
     */
    void addRow(RowType rowType, String recordValue, String entityValue, Entity entity) {
        MergeRow mergeRow = new MergeRow(rowType);
        mergeRowList.put(mergeRow);
        mergeRow.rowType = rowType;
        mergeRow.label = getRowTypeLabel(rowType);
        mergeRow.recordValue = recordValue;
        mergeRow.entityValue = entityValue;
        mergeRow.setEntityObject(entity);
        if (isRowParentApplicable(rowType)) {
            if (recordValue.isEmpty()) {
                mergeRow.merge = false;
                mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
            } else {
                if (rowType == RowType.IndiLastName
                        || rowType == RowType.IndiMarriedLastName
                        || rowType == RowType.IndiFatherLastName
                        || rowType == RowType.IndiMotherLastName
                        || rowType == RowType.WifeLastName
                        || rowType == RowType.WifeMarriedLastName
                        || rowType == RowType.WifeFatherLastName
                        || rowType == RowType.WifeMotherLastName) {
                    if (entityValue.isEmpty()) {
                        mergeRow.merge = !recordValue.equals(entityValue);
                        mergeRow.compareResult = mergeRow.merge ? CompareResult.COMPATIBLE : CompareResult.EQUAL;
                    } else {
                        mergeRow.merge = false;
                        // il suffit que l'un des noms soit identique 
                        String[] names1 = recordValue.split(",");
                        String[] names2 = entityValue.split(",");
                        boolean result = false;
                        for (String name1 : names1) {
                            for (String name2 : names2) {
                                result |= name1.trim().equals(name2.trim());
                            }
                        }
                        mergeRow.compareResult = !result ? CompareResult.CONFLIT : CompareResult.EQUAL;
                    }

                } else if (rowType == RowType.IndiFirstName
                        || rowType == RowType.IndiMarriedFirstName
                        || rowType == RowType.IndiFatherFirstName
                        || rowType == RowType.IndiMotherFirstName
                        || rowType == RowType.WifeFirstName
                        || rowType == RowType.WifeMarriedFirstName
                        || rowType == RowType.WifeFatherFirstName
                        || rowType == RowType.WifeMotherFirstName) {
                    if (entityValue.isEmpty()) {
                        mergeRow.merge = !recordValue.equals(entityValue);
                        mergeRow.compareResult = mergeRow.merge ? CompareResult.COMPATIBLE : CompareResult.EQUAL;
                    } else {
                        mergeRow.merge = false;
                        mergeRow.compareResult = !recordValue.equals(entityValue) ? CompareResult.CONFLIT : CompareResult.EQUAL;
                    }

                } else if (rowType == RowType.EventComment) {
                    // merge actif si le commentaire existant dans l'entité ne contient pas deja le commentaire du relevé.
                    mergeRow.merge = !entityValue.contains(recordValue);
                    mergeRow.compareResult = !entityValue.equals(recordValue) ? CompareResult.COMPATIBLE : CompareResult.EQUAL;
                } else {
                    mergeRow.merge = !recordValue.equals(entityValue);
                    mergeRow.compareResult = mergeRow.merge ? CompareResult.COMPATIBLE : CompareResult.EQUAL;
                }
            }
        } else {
            // ligne parent NOT_APPLICABLE
            mergeRow.merge = false;
            mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        }

        // j'incremente le compteur de champs egaux
        if ((mergeRow.compareResult == CompareResult.EQUAL || mergeRow.compareResult == CompareResult.CONFLIT)
                && !recordValue.isEmpty() && !entityValue.isEmpty()) {
            nbMatch++;
        }
        nbMatchMax++;
        mergeRow.merge_initial = mergeRow.merge;

    }

    /**
     * ajoute une ligne dans le modele pour comparer un champ date
     *
     * @param rowType
     * @param label
     * @param recordValue
     * @param entityValue
     */
    void addRow(RowType rowType, PropertyDate recordValue, PropertyDate entityValue) {
        MergeRow mergeRow = new MergeRow(rowType);
        mergeRowList.put(mergeRow);
        mergeRow.rowType = rowType;
        mergeRow.label = getRowTypeLabel(rowType);
        mergeRow.entityValue = entityValue;
        // je clone la valeur , y compris la phrase
        PropertyDate cloneDate = new PropertyDate();
        cloneDate.setValue(recordValue.getFormat(), recordValue.getStart(), recordValue.getEnd(), recordValue.getPhrase());
        mergeRow.recordValue = cloneDate;

        if (isRowParentApplicable(rowType)) {
            // je compare les valeurs par defaut du releve et de l'entite
            if (!recordValue.isComparable()) {
                mergeRow.merge = false;
                mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
            } else if (entityValue == null || !entityValue.isComparable()) {
                // j'active Merge seulement si la date du releve est comparable
                mergeRow.merge = recordValue.isComparable();
                mergeRow.compareResult = mergeRow.merge ? CompareResult.COMPATIBLE : CompareResult.EQUAL;
            } else {
                if (recordValue.getValue().equals(entityValue.getValue())) {
                    // les valeurs sont egales, pas besoin de merger
                    mergeRow.merge = false;
                    mergeRow.compareResult = CompareResult.EQUAL;
                } else {
                    PropertyDate bestDate = MergeQuery.getMostAccurateDate(recordValue, entityValue);
                    if (bestDate == null) {
                        mergeRow.merge = false;
                        mergeRow.compareResult = CompareResult.CONFLIT;
                    } else if (bestDate == entityValue) {
                        mergeRow.merge = false;
                        mergeRow.compareResult = MergeQuery.isCompatible(recordValue, entityValue) ? CompareResult.COMPATIBLE : CompareResult.CONFLIT;
                    } else {
                        // je propose une date plus precise que celle du releve
                        cloneDate.setValue(bestDate.getFormat(), bestDate.getStart(), bestDate.getEnd(), bestDate.getPhrase());
                        mergeRow.merge = true;
                        mergeRow.compareResult = CompareResult.COMPATIBLE;
                    }
                }
            }
        } else {
            // ligne parent NOT_APPLICABLE
            mergeRow.merge = false;
            mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        }

        // j'incremente le compteur des champs egaux
        if (mergeRow.compareResult == CompareResult.EQUAL) {
            nbMatch++;
        }
        nbMatchMax++;
        mergeRow.merge_initial = mergeRow.merge;
    }

    /**
     * ajoute une ligne dans le modele pour comparer le type d'evenement
     *
     * @param rowType
     * @param label
     * @param recordValues
     * @param entityValues
     */
    void addRow(RowType rowType, String recordValue, Property eventProperty) {
        MergeRow mergeRow = new MergeRow(rowType);
        mergeRowList.put(mergeRow);
        mergeRow.rowType = rowType;
        mergeRow.label = getRowTypeLabel(rowType);
        mergeRow.recordValue = recordValue;
        mergeRow.entityValue = eventProperty;
        mergeRow.setEntityObject(null);

        if (isRowParentApplicable(rowType)) {

            if (eventProperty != null) {
                if ( !recordValue.isEmpty()) {
                    if (eventProperty instanceof PropertyEvent) {
                        // pour PropertyEvent, je compare avec la propriété fille "TYPE"
                        if ( eventProperty.getTag().equals("EVEN")) {
                            // pour PropertyEvent tag=EVEN, je compare avec la propriété fille "TYPE"
                            if (eventProperty.getPropertyValue("TYPE").equals(recordValue)) {
                                mergeRow.merge = false;
                                mergeRow.compareResult = CompareResult.EQUAL;
                            } else {
                                mergeRow.merge = true;
                                mergeRow.compareResult = CompareResult.COMPATIBLE;
                            }
                        } else {
                            // je compare le tag
                            if ( eventProperty.getTag().equals(record.getEventTag())) {
                                mergeRow.merge = false;
                                if ( Gedcom.getName(eventProperty.getTag()).equals(recordValue)) {
                                    // les tags sont égaux et les libellés sont égaux
                                    mergeRow.compareResult = CompareResult.EQUAL;
                                } else {
                                    // les tags sont égaux, mais pas les libellés 
                                    mergeRow.compareResult = CompareResult.COMPATIBLE;
                                }
                            } else {
                                mergeRow.merge = true;
                                mergeRow.compareResult = CompareResult.COMPATIBLE;
                            }
                            
                        }
                    } else {
                         // pour les autres je compare avec la valeur de la propriete
                        if (eventProperty.getValue().contains(recordValue)) {
                            mergeRow.merge = false;
                            if (eventProperty.getValue().equals(recordValue)) {
                                mergeRow.compareResult = CompareResult.EQUAL;
                            } else {
                                mergeRow.compareResult = CompareResult.COMPATIBLE;
                            }
                        } else {
                            mergeRow.merge = true;
                            mergeRow.compareResult = CompareResult.COMPATIBLE;
                        }
                    }
                } else {
                     mergeRow.merge = false;
                     mergeRow.compareResult = CompareResult.COMPATIBLE;
                }
            } else {
                mergeRow.merge = !recordValue.isEmpty();
                mergeRow.compareResult = mergeRow.merge ? CompareResult.COMPATIBLE : CompareResult.NOT_APPLICABLE;
            }
        } else {
            // ligne parent NOT_APPLICABLE
            mergeRow.merge = false;
            mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        }
        mergeRow.merge_initial = mergeRow.merge;
    }

    /**
     * ajoute une ligne dans le modele pour comparer la famille
     *
     * @param rowType
     * @param label
     * @param recordValues
     * @param entityValues
     */
    void addRow(RowType rowType, Fam family) {
        MergeRow mergeRow = new MergeRow(rowType);
        mergeRowList.put(mergeRow);
        mergeRow.rowType = rowType;
        mergeRow.label = getRowTypeLabel(rowType);
        mergeRow.recordValue = null;
        mergeRow.entityValue = family;
        mergeRow.setEntityObject(family);
        if (isRowParentApplicable(rowType)) {

            if (family != null) {
                mergeRow.merge = true;
                mergeRow.compareResult = CompareResult.EQUAL;
            } else {
                if ((rowType == RowType.IndiParentFamily && record.getIndi().getFatherLastName().isEmpty() && record.getIndi().getMotherLastName().isEmpty())
                        || (rowType == RowType.WifeParentFamily && record.getWife().getFatherLastName().isEmpty() && record.getWife().getMotherLastName().isEmpty())) {
                    // j'interdis la creation d'un nouvelle famille si le nom du pere et de la mere sont vide.
                    mergeRow.merge = false;
                    mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
                } else {
                    mergeRow.merge = true;
                    mergeRow.compareResult = CompareResult.COMPATIBLE;
                }
            }
        } else {
            // ligne parent NOT_APPLICABLE
            mergeRow.merge = false;
            mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        }

        // j'incremente le compteur des champs egaux
        if (mergeRow.compareResult == CompareResult.EQUAL) {
            nbMatch++;
        }
        nbMatchMax++;
        mergeRow.merge_initial = mergeRow.merge;

    }

    /**
     * ajoute une ligne dans le modele servant de separateur
     *
     * @param rowType
     * @param label
     * @param recordValues
     * @param entityValues
     */
    void addRowSeparator() {
        MergeRow mergeRow = new MergeRow(RowType.Separator);
        mergeRowList.put(mergeRow);
        mergeRow.rowType = RowType.Separator;
        mergeRow.label = "";
        mergeRow.entityValue = null;
        mergeRow.recordValue = null;
        mergeRow.merge = false;
        mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        mergeRow.merge_initial = mergeRow.merge;
    }

    protected final void addRowEvent(Entity entity, String eventTag) {
        if (record.isInsinuation()) {
            // j'affiche l'insinuation 
            addRow(RowType.EventInsinuationType, record.getInsinuationType(), proposedInsinuation);
            addRow(RowType.EventInsinuationDate, record.getInsinuationDate(), proposedInsinuationDate);
            addRow(RowType.EventInsinuationPlace, record.getEventPlace(), proposedInsinuationPlace);
            addRow(RowType.EventInsinuationComment, record.getEventComment(showFrenchCalendarDate), proposedInsinuationComment);
        }

        // j'affiche l'evenement
        addRow(RowType.EventType, record.getEventType(), proposedEvent);
        addRow(RowType.EventDate, record.getEventDate(), proposedEventDate);
        addRow(RowType.EventPlace, record.isInsinuation() ? "" : record.getEventPlace(), proposedEventPlace);
        addRow(RowType.EventComment, 
                record.isInsinuation() ? record.makeInsinuationReferenceComment(showFrenchCalendarDate) :record.getEventComment(showFrenchCalendarDate),
                proposedEventComment);
    }

    protected final void addRowSource() {
        addRowSource(null);
    }

    protected final void addRowSource(Source requiredSource) {
        PropertyEvent entityEventProperty;
        if (participantType == MergeParticipantType.participant1) {
            if (record.isInsinuation()) {
                entityEventProperty = proposedInsinuation;
            } else {
                entityEventProperty = proposedEvent;
            }
        } else {
            return;
        }

        // je cree la ligne
        MergeRow mergeRow = new MergeRow(MergeModel.RowType.EventSource);
        mergeRowList.put(mergeRow);
        mergeRow.label = getRowTypeLabel(mergeRow.rowType);
        mergeRow.recordValue = record.getEventSourceTitle();

        String recordSourceTitle;
        if (requiredSource != null) {
            recordSourceTitle = requiredSource.getTitle();
        } else {
            recordSourceTitle = record.getEventSourceTitle();
        }

        // je cherche la source et la page dans la propriété de l'entité
        Source entityEventSource = null;
        String entityEventPage = null;
        if (entityEventProperty != null) {
            Property[] sourceProperties = entityEventProperty.getProperties("SOUR", false);
            for (Property sourcePropertie : sourceProperties) {
                // remarque : verification de classe PropertySource avant de faire le cast en PropertySource pour eliminer
                // les cas anormaux , par exemple une source "multiline"
                if (sourcePropertie instanceof PropertySource) {
                    Source source = (Source) ((PropertySource) sourcePropertie).getTargetEntity();
                    if (recordSourceTitle.compareTo(source.getTitle()) == 0) {
                        entityEventSource = source;
                        // je verifie si elle contient le meme numero de page ou la meme cote
                        for (Property pageProperty : sourcePropertie.getProperties("PAGE")) {
                            if ((!record.getEventCote().isEmpty() && pageProperty.getValue().contains(record.getEventCote()))
                                    || (!record.getEventPage().isEmpty() && pageProperty.getValue().contains(record.getEventPage()))) {
                                entityEventPage = pageProperty.getValue();
                                break;
                            }
                        }
                    }
                }
                if (entityEventSource != null && entityEventPage != null) {
                    break;
                }
            }

        }

        if (isRowParentApplicable(mergeRow.rowType)) {
            if (entityEventSource != null) {
                // la source existe dans l'entité
                mergeRow.entityValue = entityEventSource;
                mergeRow.setEntityObject(entityEventSource);
                mergeRow.merge = false;
                mergeRow.compareResult = CompareResult.EQUAL;
            } else {
                // la source n'existe pas dans l'entité                                

                // je cherche la source dans le gedcom
                Entity gedcomSource = null;
                Entity[] sources = gedcom.getEntities("SOUR", "SOUR:TITL");
                for (Entity source : sources) {
                    if (((Source) source).getTitle().equals(recordSourceTitle)) {
                        gedcomSource = source;
                        break;
                    }
                }

                if (gedcomSource != null) {
                    // la source indiquée dans le releve existe dans le gedcom
                    // je propose de l'ajouter
                    mergeRow.entityValue = gedcomSource;
                    mergeRow.setEntityObject(gedcomSource);
                    mergeRow.merge = true;
                    mergeRow.compareResult = CompareResult.COMPATIBLE;
                } else {
                    // la source indiquée dans le releve n'existe pas dans le gedcom
                    mergeRow.entityValue = null;
                    mergeRow.setEntityObject(null);
                    mergeRow.merge = false;
                    mergeRow.compareResult = CompareResult.NOT_APPLICABLE;

                }
            }
        } else {
            // ligne parent NOT_APPLICABLE
            mergeRow.merge = false;
            mergeRow.compareResult = CompareResult.NOT_APPLICABLE;
        }
        mergeRow.merge_initial = mergeRow.merge;

        addRow(RowType.EventPage, record.makeEventPage(), entityEventPage);

        addRowSeparator();
    }

    ///////////////////////////////////////////////////////////////////////////
    // accesseurs
    ///////////////////////////////////////////////////////////////////////////
    public MergeParticipantType getParticipantType() {
        return participantType;
    }

    /**
     * retoune le gedcom associé au modele
     *
     * @return gedcom
     */
    protected Gedcom getGedcom() {
        return gedcom;
    }

    /**
     * retourne une ligne en fonction du type
     *
     * @param rowType
     * @return
     */
    protected MergeRow getRow(RowType rowType) {
        return mergeRowList.get(rowType);
    }

    /**
     * retourne une ligne en fonction du type
     *
     * @param rowType
     * @return
     */
    protected Entity getEntityObject(RowType rowType) {
        MergeRow mergeRow = getRow(rowType);
        if (mergeRow != null) {
            return mergeRow.getEntityObject();
        } else {
            return null;
        }
    }

    /**
     * retourne une ligne en fonction numero
     *
     * @param row
     * @return
     */
    MergeRow getRow(int row) {
        return mergeRowList.get(row);
    }

    /**
     * retourne le resultat de comparaison de la ligne
     *
     * @param row
     * @return
     */
    protected CompareResult getCompareResult(int row) {
        return mergeRowList.get(row).compareResult;
    }

    /**
     * retourne le nombre de champs egaux entre le releve et l'entité
     *
     * @param rowType
     * @return
     */
    protected int getNbMatch() {
        return nbMatch;
    }

    /**
     * nombre total de champs comparables
     *
     * @return
     */
    protected int getNbMatchMax() {
        return nbMatchMax;
    }

    /**
     * compare le nombre de champs egaux du modele avec celui d'un autre modele
     * pour savoir quel est le modele qui contient l'entité la plus proche du
     * relevé.
     *
     * @param object
     * @return
     */
    @Override
    public int compareTo(MergeModel object) {
        if (!(object instanceof MergeModel)) {
            return 1;
        }
        int nombre1 = object.nbMatch;
        int nombre2 = this.nbMatch;
        if (nombre2 > nombre1) {
            return -1;
        } else if (nombre1 == nombre2) {
            return 0;
        } else {
            return 1;
        }
    }

    // methodes abstraites
    protected abstract Property copyRecordToEntity() throws Exception;

    protected abstract String getTitle();

    public abstract String getSummary(Entity selectedEntity);

    protected abstract Entity getSelectedEntity();

    protected abstract Entity getProposedEntity();

    /**
     * crée une association entre associatedProperty et l'entité sélectionné
     * dans le modele
     *
     * @param associatedProperty1
     * @throws Exception
     */
    protected void copyAssociation(Property associatedProperty1, Property associatedProperty2) throws Exception {
        PropertyXRef asso = (PropertyXRef) associatedProperty2.addProperty("ASSO", '@' + associatedProperty1.getEntity().getId() + '@');
        TagPath anchor = associatedProperty1.getPath(true);

        asso.addProperty("RELA", anchor == null ? "Présent" : "Présent" + '@' + anchor.toString());

        // je cree le lien à l'autre extermite de l'association
        try {
            asso.link();
        } catch (GedcomException e) {
            associatedProperty1.delProperty(asso);
            throw e;
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    // focntions de gestion de la JTable
    ///////////////////////////////////////////////////////////////////////////
    private String[] columnNames = {
        "",
        NbBundle.getMessage(MergeModel.class, "MergePanel.title.recordColumn"),
        "=>",
        NbBundle.getMessage(MergeModel.class, "MergePanel.title.gedcomColumn"),
        "Identifiant"
    };
    private Class[] columnClass = {String.class, Object.class, Boolean.class, Object.class, Entity.class};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return mergeRowList.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return columnClass[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return mergeRowList.get(row).label;
            case 1:
                return mergeRowList.get(row).recordValue;
            case 2:
                return mergeRowList.get(row).merge;
            case 3:
                return mergeRowList.get(row).entityValue;
            case 4:
                return mergeRowList.get(row).getEntityObject();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 2:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 1:
                mergeRowList.get(row).recordValue = value;
                break;
            case 2:
                check(row, (Boolean) value);
                break;
            case 3:
                mergeRowList.get(row).recordValue = value;
                break;
            default:
                break;
        }
        fireTableCellUpdated(row, col);
    }

    /**
     * coche ou décoche une ligne en fonction de son numéro d'ordre
     *
     * @param rowNum
     * @param state
     */
    void check(int rowNum, boolean state) {
        MergeRow mergeRow = mergeRowList.get(rowNum);

        mergeRow.merge = state;
        fireTableCellUpdated(rowNum, 2);
        fireTableCellUpdated(rowNum, 3);

        // je mets a jour les lignes filles
        for (int i = 0; i < mergeRowList.size(); i++) {
            MergeRow mergeRowChild = mergeRowList.get(i);
            MergeRow parentRow = getParentRow(mergeRowChild.rowType);
            if (parentRow != null && parentRow.rowType == mergeRow.rowType) {
                if (state == true) {
                    // je restaure l'etat initial de la ligne fille
                    mergeRowChild.merge = mergeRowChild.merge_initial;
                } else {
                    mergeRowChild.merge = false;
                }
                fireTableCellUpdated(i, 2);
                fireTableCellUpdated(i, 3);
            }
        }

        // je mets a jour la ligne parent
        // seulement si elle était décochée et que la fille vient d'etre cochee
        MergeRow parentRow = getParentRow(mergeRow.rowType);
        if (state == true && parentRow != null) {
            parentRow.merge = true;
            int parentIndex = mergeRowList.indexOf(parentRow.rowType);
            fireTableCellUpdated(parentIndex, 2);
            fireTableCellUpdated(parentIndex, 3);
        }
    }

    /**
     * retourne l'etat coché ou décoché d'une ligne
     *
     * @param rowType
     * @return
     */
    boolean isChecked(RowType rowType) {
        MergeRow mergeRow = mergeRowList.get(rowType);
        if (mergeRow == null) {
            return false;
        } else {
            return mergeRowList.get(rowType).merge;
        }
    }

    /**
     * retourne l'état coché ou décoché de plusieurs lignes
     *
     * @param rowType
     * @return true si au moins une ligne est cochée, sinon false
     */
    boolean isCheckedOneOf(RowType... rowTypes) {
        boolean result = false;
        for (RowType rowType : rowTypes) {
            MergeRow mergeRow = mergeRowList.get(rowType);
            if (mergeRow != null) {
                result |= mergeRowList.get(rowType).merge;
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////
    // utilitaires
    ////////////////////////////////////////////////////////////////////////////
    protected void copyIndiFather(MergeParticipant participant, Indi father, Fam parentfamily) throws Exception {
        if (isCheckedOneOf(RowType.IndiFatherFirstName, RowType.IndiFatherLastName, RowType.IndiFatherBirthDate, RowType.IndiFatherDeathDate, RowType.IndiFatherOccupation)) {
            // je copie le nom et le prenom du pere de l'epoux
            if (father == null) {
                // je cree le pere
                father = (Indi) gedcom.createEntity(Gedcom.INDI);
                father.setName(participant.getFatherFirstName(), participant.getFatherLastName());
                father.setSex(PropertySex.MALE);
                parentfamily.setHusband(father);
            } else {
                if (isChecked(RowType.IndiFatherFirstName)) {
                    father.setName(participant.getFatherFirstName(), father.getLastName());
                }
                if (isChecked(RowType.IndiFatherLastName)) {
                    father.setName(father.getFirstName(), participant.getFatherLastName());
                }
            }

            // je copie la date de naissance du pere de l'epoux
            if (isChecked(RowType.IndiFatherBirthDate)) {
                copyBirthDate(father, getRow(RowType.IndiFatherBirthDate), "", record);
            }

            //je copie la date de décès du pere de l'epoux
            if (isChecked(RowType.IndiFatherDeathDate)) {
                copyDeathDate(father, getRow(RowType.IndiFatherDeathDate), record);
            }
            
            // je copie la profession du pere
            if (isChecked(RowType.IndiFatherOccupation)) {
                copyOccupation(father, participant.getFatherOccupation(), participant.getFatherResidence(), true, record);
            }
        }

    }

    protected void copyIndiMother(MergeParticipant participant, Indi mother, Fam parentfamily) throws Exception {
        if (isCheckedOneOf(RowType.IndiMotherFirstName, RowType.IndiMotherLastName, RowType.IndiMotherBirthDate, RowType.IndiMotherDeathDate, RowType.IndiMotherOccupation)) {
            // je copie le nom et le prenom de la mere de l'epoux
            if (mother == null) {
                // je cree le pere
                mother = (Indi) gedcom.createEntity(Gedcom.INDI);
                mother.setName(participant.getMotherFirstName(), participant.getMotherLastName());
                mother.setSex(PropertySex.FEMALE);
                parentfamily.setWife(mother);
            } else {
                if (isChecked(RowType.IndiMotherFirstName)) {
                    mother.setName(participant.getMotherFirstName(), mother.getLastName());
                }
                if (isChecked(RowType.IndiMotherLastName)) {
                    mother.setName(mother.getFirstName(), participant.getMotherLastName());
                }
            }

            // je copie la date de naissance de la mere de l'epoux
            if (isChecked(RowType.IndiMotherBirthDate)) {
                copyBirthDate(mother, getRow(RowType.IndiMotherBirthDate), "", record);
            }

            // je copie la date de décès de la mere de l'epoux
            if (isChecked(RowType.IndiMotherDeathDate)) {
                copyDeathDate(mother, getRow(RowType.IndiMotherDeathDate), record);
            }

            // je copie la profession de la mere de l'epoux
            if (isChecked(RowType.IndiMotherOccupation)) {
                copyOccupation(mother, participant.getMotherOccupation(), participant.getMotherResidence(), true, record);
            }
        }
    }

    protected void copyIndiMarried(MergeParticipant participant, Indi currentIndi) throws Exception {
        if (isChecked(RowType.IndiMarriedFamily)) {
            Indi exSpouse = (Indi) getRow(RowType.IndiMarriedLastName).getEntityObject();
            if (exSpouse == null) {
                // je cree l'individu
                exSpouse = (Indi) gedcom.createEntity(Gedcom.INDI);
                exSpouse.setName(participant.getMarriedFirstName(), participant.getMarriedLastName());
                exSpouse.setSex(currentIndi.getSex() == PropertySex.MALE ? PropertySex.FEMALE : PropertySex.MALE);
            } else {
                // je copie le nom de l'ex conjoint
                if (isChecked(RowType.IndiMarriedLastName)) {
                    exSpouse.setName(exSpouse.getFirstName(), participant.getMarriedLastName());
                }

                // je copie le prénom de l'ex conjoint
                if (isChecked(RowType.IndiMarriedFirstName)) {
                    exSpouse.setName(participant.getMarriedFirstName(), exSpouse.getLastName());
                }
            }

            // je copie la date, le lieu et commentaire de naissance de l'ex conjoint
            if (isChecked(RowType.IndiMarriedBirthDate)) {
                copyBirthDate(exSpouse, getRow(RowType.IndiMarriedBirthDate), "", record);
            }

            // je copie la date, le lieu et commentaire de deces de l'ex conjoint
            if (isChecked(RowType.IndiMarriedDeathDate)) {
                copyDeathDate(exSpouse, getRow(RowType.IndiMarriedDeathDate), record);
            }

            // je copie la profession de l'ex conjoint
            if (isChecked(RowType.IndiMarriedOccupation)) {
                copyOccupation(exSpouse, participant.getMarriedOccupation(), participant.getMarriedResidence(), true, record);
            }

            // je copie la famille avec l'ex conjoint
            Fam family = (Fam) getRow(RowType.IndiMarriedFamily).getEntityObject();
            if (family == null) {
                // je cree la famille
                family = (Fam) gedcom.createEntity(Gedcom.FAM);
                // j'ajoute les epoux
                if (currentIndi.getSex() == PropertySex.MALE) {
                    family.setHusband(currentIndi);
                    family.setWife(exSpouse);
                } else {
                    family.setHusband(exSpouse);
                    family.setWife(currentIndi);
                }
            }

            // je copie la date du mariage avec l'ex conjoint et une note indiquant l'origine de cette date
            if (isChecked(RowType.IndiMarriedMarriageDate)) {
                copyMarriageDate(family, getRow(RowType.IndiMarriedMarriageDate), record);
            }
        }

    }

    protected void copyWifeFather(MergeParticipant participant, Indi father, Fam parentfamily) throws Exception {
        if (isCheckedOneOf(RowType.WifeFatherFirstName, RowType.WifeFatherLastName, RowType.WifeFatherBirthDate, RowType.WifeFatherDeathDate, RowType.WifeFatherOccupation)) {
            // je copie le nom et le prenom du pere de l'epouse
            if (father == null) {
                // je cree le pere
                father = (Indi) gedcom.createEntity(Gedcom.INDI);
                father.setName(participant.getFatherFirstName(), participant.getFatherLastName());
                father.setSex(PropertySex.MALE);
                parentfamily.setHusband(father);
            } else {
                if (isChecked(RowType.WifeFatherFirstName)) {
                    father.setName(participant.getFatherFirstName(), father.getLastName());
                }
                if (isChecked(RowType.WifeFatherLastName)) {
                    father.setName(father.getFirstName(), participant.getFatherLastName());
                }
            }

            // je copie la date de naissance du pere de l'epouse
            if (isChecked(RowType.WifeFatherBirthDate)) {
                copyBirthDate(father, getRow(RowType.WifeFatherBirthDate), "", record);
            }

            //je copie la date de décès du pere de l'epouse
            if (isChecked(RowType.WifeFatherDeathDate)) {
                copyDeathDate(father, getRow(RowType.WifeFatherDeathDate), record);
            }

            // je copie la profession du pere de l'epouse
            if (isChecked(RowType.WifeFatherOccupation)) {
                copyOccupation(father, participant.getFatherOccupation(), participant.getFatherResidence(), true, record);
            }
        }

    }

    protected void copyWifeMother(MergeParticipant participant, Indi mother, Fam parentfamily) throws Exception {
        // je copie les linformations de la mere de l'épouse
        if (isCheckedOneOf(RowType.WifeMotherFirstName, RowType.WifeMotherLastName, RowType.WifeMotherBirthDate, RowType.WifeMotherDeathDate, RowType.WifeMotherOccupation)) {
            // je copie le nom et le prenom de la mere de l'epouse
            if (mother == null) {
                // je cree le pere
                mother = (Indi) gedcom.createEntity(Gedcom.INDI);
                mother.setName(participant.getMotherFirstName(), participant.getMotherLastName());
                mother.setSex(PropertySex.FEMALE);
                parentfamily.setWife(mother);
            } else {
                if (isChecked(RowType.WifeMotherFirstName)) {
                    mother.setName(participant.getMotherFirstName(), mother.getLastName());
                }
                if (isChecked(RowType.WifeMotherLastName)) {
                    mother.setName(mother.getFirstName(), participant.getMotherLastName());
                }
            }

            // je copie la date de naissance de la mere e l'epouse
            if (isChecked(RowType.WifeMotherBirthDate)) {
                copyBirthDate(mother, getRow(RowType.WifeMotherBirthDate), "", record);
            }

            // je copie la date de décès de la mere de l'epouse
            if (isChecked(RowType.WifeMotherDeathDate)) {
                copyDeathDate(mother, getRow(RowType.WifeMotherDeathDate), record);
            }

            // je copie la profession de la mere de l'epouse
            if (isChecked(RowType.WifeMotherOccupation)) {
                copyOccupation(mother, participant.getMotherOccupation(), participant.getMotherResidence(), true, record);
            }
        }

    }

    /**
     * ajoute un lieu a une propriete
     *
     * @param place
     * @param eventProperty
     */
    static protected void copyPlace(String place, Property eventProperty) {
        PropertyPlace propertyPlace = (PropertyPlace) eventProperty.getProperty("PLAC");
        if (propertyPlace == null) {
            // je cree le lieu .
            propertyPlace = (PropertyPlace) eventProperty.addProperty("PLAC", "");
        }
        propertyPlace.setValue(place);
    }

    /**
     * ajoute un evenement
     *
     * @param source
     * @param eventProperty
     * @param record
     * @throws Exception
     */
    protected Property copyEvent(Entity currentEntity) throws Exception {
        Property resultProperty;
        if (!record.isInsinuation()) {
            // je crée la propriété de l'évènement
            Property eventProperty = (Property) getRow(RowType.EventType).entityValue;
            if (eventProperty == null || !eventProperty.isContained(currentEntity)) {
                // je recupere la date du releve                
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventDate).recordValue;
                eventProperty = currentEntity.addProperty(record.getEventTag(), "", getPropertyBestPosition(currentEntity, propertyDate));
            }

            resultProperty = eventProperty;

            // je copie le type d'évènement
            if (isChecked(RowType.EventType) && eventProperty.getTag().equals("EVEN")) {
                // j'ajoute la propriete TYPE si c'est un tag EVEN (par besoin pour WILL, MARR, etc.) 
                Property propertyType = eventProperty.getProperty("TYPE");
                if (propertyType == null) {
                    propertyType = eventProperty.addProperty("TYPE", "");
                }
                propertyType.setValue(record.getEventType());
            }
            
            // je copie la date de l'évènement
            if (isChecked(RowType.EventDate)) {
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventDate).entityValue;
                if (propertyDate == null ) {
                    propertyDate = (PropertyDate) eventProperty.addProperty("DATE", "");
                }
                propertyDate.setValue(record.getEventDate().getValue());
            }

            // je copie la source du releve de l'évènement
            if (isChecked(RowType.EventSource) || isChecked(RowType.EventPage)) {
                copySource((Source) getEntityObject(RowType.EventSource), eventProperty, isChecked(RowType.EventPage), record);
            }

            // je copie le lieu de l'évènement
            if (isChecked(RowType.EventPlace)) {
                Property propertyPlace = (Property) getRow(RowType.EventPlace).entityValue;
                if (propertyPlace == null || !propertyPlace.isContained(eventProperty)) {
                    // je cree le lieu.
                    propertyPlace = eventProperty.addProperty("PLAC", "");
                }
                propertyPlace.setValue(record.getEventPlace());
            }

            // je copie le commentaire de l'évènement
            if (isChecked(RowType.EventComment)) {
                Property propertyComment = (Property) getRow(RowType.EventComment).entityValue;
                if (propertyComment == null) {
                    // je cree une note .
                    propertyComment = eventProperty.addProperty("NOTE", "");
                }

                // j'ajoute le commentaire au debut de la note existante.
                String value = propertyComment.getValue();
                String comment = record.getEventComment(showFrenchCalendarDate);
                if (!comment.isEmpty()) {
                    if (!value.isEmpty()) {
                        comment += "\n";
                    }
                    comment += value;
                    propertyComment.setValue(comment);
                }
            }
        } else {
            // c'est une insinuation

            // je crée la propriété EVEN de l'insinuation
            Property insinuationProperty = (Property) getRow(RowType.EventInsinuationType).entityValue;
            if (insinuationProperty == null) {
                // je recupere la date du releve                
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventInsinuationDate).recordValue;
                insinuationProperty = currentEntity.addProperty("EVEN", "", getPropertyBestPosition(currentEntity, propertyDate));
            }
            resultProperty = insinuationProperty;

            // je copie le type de l'insinuation
            if (isChecked(RowType.EventInsinuationType)) {
                Property propertyType = insinuationProperty.getProperty("TYPE");
                if (propertyType == null) {
                    propertyType = insinuationProperty.addProperty("TYPE", "");
                }
                propertyType.setValue((String)getRow(RowType.EventInsinuationType).recordValue);
            }

            // je copie la date de l'insinuation
            if (isChecked(RowType.EventInsinuationDate)) {
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventInsinuationDate).entityValue;
                if (propertyDate == null) {
                    propertyDate = (PropertyDate) insinuationProperty.addProperty("DATE", "");
                }
                propertyDate.setValue(record.getInsinuationDate().getValue());
            }

            // je copie la source du releve de l'insinuation
            if (isChecked(RowType.EventSource) || isChecked(RowType.EventPage)) {
                copySource((Source) getEntityObject(RowType.EventSource), insinuationProperty, isChecked(RowType.EventPage), record);
            }

            // je copie le lieu de l'insinuation
            if (isChecked(RowType.EventInsinuationPlace)) {
                Property propertyPlace = (Property) getRow(RowType.EventInsinuationPlace).entityValue;
                if (propertyPlace == null) {
                    // je cree le lieu .
                    propertyPlace = insinuationProperty.addProperty("PLAC", "");
                }
                propertyPlace.setValue(record.getEventPlace());
            }

            // je copie le commentaire de l'insinuation
            if (isChecked(RowType.EventInsinuationComment)) {
                Property propertyComment = (Property) getRow(RowType.EventInsinuationComment).entityValue;
                if (propertyComment == null) {
                    // je cree une note .
                    propertyComment = insinuationProperty.addProperty("NOTE", "");
                }

                // j'ajoute le commentaire de l'insinuation au debut de la note existante.
                String oldComment = propertyComment.getValue();
                String newComment = (String) getRow(RowType.EventInsinuationComment).recordValue;
                if (!newComment.isEmpty()) {
                    if (!oldComment.isEmpty()) {
                        newComment += "\n";
                        newComment += oldComment;
                    }
                    propertyComment.setValue(newComment);
                }
            }

            // je crée la propriété de l'évènement insinué
            Property eventProperty = (Property) getRow(RowType.EventType).entityValue;
            if (eventProperty == null) {
                // je recupere la date du releve
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventDate).recordValue;
                eventProperty = currentEntity.addProperty(record.getEventTag(), "", getPropertyBestPosition(currentEntity, propertyDate));
            }
            
            // je copie le type d'évènement
            if (isChecked(RowType.EventType)) {
                Property propertyType = eventProperty.getProperty("TYPE");
                if (propertyType == null) {
                    propertyType = eventProperty.addProperty("TYPE", "");
                }
                propertyType.setValue(record.getEventType());
            }
            
            // je copie la date de de l'évènement insinué
            if (isChecked(RowType.EventDate)) {
                PropertyDate propertyDate = (PropertyDate) getRow(RowType.EventDate).entityValue;
                if (propertyDate == null) {
                    propertyDate = (PropertyDate) eventProperty.addProperty("DATE", "");
                }
                propertyDate.setValue(record.getEventDate().getValue());
            }

            // je copie le commentaire du contrat de mariage.
            if (isChecked(RowType.EventComment)) {
                Property propertyComment = (Property) getRow(RowType.EventComment).entityValue;
                if (propertyComment == null) {
                    // je cree une note .
                    propertyComment = eventProperty.addProperty("NOTE", "");
                }

                // j'ajoute le commentaire du contrat de mariage au debut de la note existante.
                String oldComment = propertyComment.getValue();
                String newComment = (String) getRow(RowType.EventComment).recordValue;
                if (!newComment.isEmpty()) {
                    if (!oldComment.isEmpty()) {
                        newComment += "\n";
                        newComment += oldComment;
                    }
                    propertyComment.setValue(newComment);
                }
            }
        }

        return resultProperty;

    }

    /**
     * ajoute une source a une propriete
     *
     * @param source
     * @param eventProperty
     * @param record
     * @throws Exception
     */
    static protected void copySource(Source source, Property eventProperty, boolean pageIsChecked, MergeRecord record) throws Exception {
        PropertyXRef sourcexref = null;
        if (source != null) {
            // je verifie si la source est déjà associée à la naissance
            boolean found = false;
            // je copie les sources de l'entité
            Property[] sourceProperties = eventProperty.getProperties("SOUR", false);
            for (Property sourcePropertie : sourceProperties) {
                Source eventSource = (Source) ((PropertySource) sourcePropertie).getTargetEntity();
                if (source.compareTo(eventSource) == 0) {
                    found = true;
                    // je memorise le lien vers la source pour ajouter la page
                    sourcexref = (PropertyXRef) sourcePropertie;
                    break;
                }
            }
            if (found == false) {
                try {
                    // je relie la reference de la source du releve à la propriété de naissance
                    sourcexref = (PropertyXRef) eventProperty.addProperty("SOUR", "@" + source.getId() + "@");
                    sourcexref.link();
                } catch (GedcomException ex) {
                    throw new Exception(String.format("Link source=%s error=% ", source.getTitle(), ex.getMessage()));
                }
            }
        } else {
            // je cree une nouvelle source et je la relie à l'entité
            Source newSource = (Source) eventProperty.getGedcom().createEntity(Gedcom.SOUR);
            newSource.addProperty("TITL", record.getEventSourceTitle());
            try {
                // je relie la source du releve à l'entité
                sourcexref = (PropertyXRef) eventProperty.addProperty("SOUR", "@" + newSource.getId() + "@");
                sourcexref.link();
            } catch (GedcomException ex) {
                throw new Exception(String.format("Link source=%s error=% ", source == null ? source : source.getTitle(), ex.getMessage()));
            }
        }

        // j'ajoute la page
        if (sourcexref != null && pageIsChecked) {
            String coteValue = record.getEventCote();
            String pageValue = record.getEventPage();

            // je cherche les tags PAGE déjà existant contenat la cote ou la page
            Property foundPage = null;
            Property[] entityPageList = sourcexref.getProperties("PAGE");
            for (Property entityPage : entityPageList) {
                if ((!coteValue.isEmpty() && entityPage.getValue().contains(coteValue))
                        || (!pageValue.isEmpty() && entityPage.getValue().contains(pageValue))) {
                    foundPage = entityPage;
                }
            }
            if (foundPage == null) {
                // je cree une nouvelle propriété PAGE
                sourcexref.addProperty("PAGE", record.makeEventPage());
            } else {
                // je modifie le propriété PAGE
                foundPage.setValue(record.makeEventPage());
            }
        }
    }

    /**
     * ajoute la date et le lieu de naissance et une note pour indiquer la
     * source de la naissance dans la propriete BIRT d'un individu
     *
     * @param indi individu
     * @param birthDate date de naissance
     * @param place lieu de naissance
     * @param record releve servant a renseigner la note
     */
    protected void copyBirthDate(Indi indi, MergeRow mergeRow, String place, MergeRecord record) {
        Property birthProperty = indi.getProperty("BIRT");
        if (birthProperty == null) {
            birthProperty = indi.addProperty("BIRT", "");
        }
        // j'ajoute (ou remplace ) la date de la naissance
        PropertyDate propertyDate = indi.getBirthDate(true);
        PropertyDate birthDate = (PropertyDate) mergeRow.recordValue;
        propertyDate.setValue(birthDate.getValue());

        // j'ajoute le lieu
        if (!place.isEmpty()) {
            PropertyPlace propertyPlace = (PropertyPlace) birthProperty.getProperty("PLAC");
            if (propertyPlace == null) {
                propertyPlace = (PropertyPlace) birthProperty.addProperty("PLAC", "");
            }
            propertyPlace.setValue(place);
        }

        // j'ajoute une note indiquant l'origine de la date de naissance
        copyReferenceNote(birthProperty, record);
    }

    /**
     * ajoute la date et le lieu de naissance et une note pour indiquer la
     * source de la naissance dans la propriete DEATH d'un individu
     *
     * @param indi individu
     * @param deathDate date de naissance
     * @param place lieu de naissance
     * @param record releve servant a renseigner la note
     */
    protected void copyDeathDate(Indi indi, MergeRow mergeRow, MergeRecord record) {
        Property deathProperty = indi.getProperty("DEAT");
        if (deathProperty == null) {
            deathProperty = indi.addProperty("DEAT", "");
        }
        // j'ajoute (ou remplace ) la date de la deces
        PropertyDate propertyDate = indi.getDeathDate(true);
        PropertyDate deathDate = (PropertyDate) mergeRow.recordValue;
        propertyDate.setValue(deathDate.getValue());

        // j'ajoute une note indiquant l'origine de la date du deces
        copyReferenceNote(deathProperty, record);
    }

    /**
     * ajoute la date de marriage et une note pour indiquer la source dans la
     * propriete MARR d'une famille
     *
     * @param family famille de mariés
     * @param marriageDate date de marriage
     * @param occupationDate date du releve
     * @param record releve servant a renseigner la note
     */
    protected void copyMarriageDate(Fam family, MergeRow mergeRow, MergeRecord record) {
        // j'ajoute (ou remplace) la date du mariage des parents
        // je crée la propriété MARR
        Property marriageProperty = family.getProperty("MARR");
        if (marriageProperty == null) {
            marriageProperty = family.addProperty("MARR", "");
        }
        // j'ajoute (ou remplace ) la date de la naissance
        PropertyDate propertyDate = (PropertyDate) marriageProperty.getProperty("DATE", false);
        if (propertyDate == null) {
            propertyDate = (PropertyDate) marriageProperty.addProperty("DATE", "");
        }
        PropertyDate marriageDate = (PropertyDate) mergeRow.recordValue;
        propertyDate.setValue(marriageDate.getValue());

        // j'ajoute une note indiquant l'origine de la date de naissance
        copyReferenceNote(marriageProperty, record);
    }

    /**
     * ajoute la profession a un individu Si la profession existe deja a la meme
     * date, l'invidu n'est pas modifié.
     *
     * @param indi individu
     * @param occupation profession
     * @param occupationDate date du releve
     * @param record releve servant a renseigner la note de la profession
     */
    protected void copyOccupation(Indi indi, String occupation, String residence, boolean createResidence, MergeRecord record) throws GedcomException {
        PropertyDate occupationDate = record.getEventDate();
        // je cherche si l'individu a deja un tag OCCU a la meme date
        Property occupationProperty = null;
        // j'ajoute la profession ou la residence
        if (!occupation.isEmpty()) {
            occupationProperty = indi.addProperty("OCCU", "", getPropertyBestPosition(indi, occupationDate));
            occupationProperty.setValue(occupation);
        } else if (createResidence && !residence.isEmpty()) {
            occupationProperty = indi.addProperty("RESI", "", getPropertyBestPosition(indi, occupationDate));
        }

        if (occupationProperty != null) {
            // j'ajoute la date
            PropertyDate date = (PropertyDate) occupationProperty.addProperty("DATE", "");
            date.setValue(occupationDate.getValue());
            // j'ajoute le lieu
            if (!residence.isEmpty()) {
                PropertyPlace place = (PropertyPlace) occupationProperty.addProperty("PLAC", "");
                place.setValue(residence);
            }

            // j'ajoute une note indiquant la source
            copyReferenceNote(occupationProperty, record);
        }
    }

    /**
     * ajoute une NOTE a un evenement La note contient contenant les
     * informations de reference du relevé a une propriete.
     *
     * @param eventProperty
     * @param record
     */
    protected void copyReferenceNote(Property eventProperty, MergeRecord mergeRecord) {
        String noteText;
        PropertyDate propertyDate = (PropertyDate) eventProperty.getProperty("DATE");

        String targetTagName = eventProperty.getTag();
        if (targetTagName.equals("BIRT")) {
            noteText = "Date de naissance {0} déduite de";
        } else if (targetTagName.equals("DEAT")) {
            noteText = "Date de décès {0} déduite de";
        } else if (targetTagName.equals("MARR")) {
            noteText = "Date de mariage {0} déduite de";
        } else if (targetTagName.equals("OCCU")) {
            noteText = "Profession indiquée dans";
        } else if (targetTagName.equals("RESI")) {
            noteText = "Domicile indiqué dans";
        } else {
            noteText = "Information indiquée dans";
        }

        switch (record.getType()) {
            case Birth:
                noteText = MessageFormat.format(noteText + " " + "l''acte de naissance de {1} {2} le {3} ({4})",
                        propertyDate.getDisplayValue(),
                        record.getIndi().getFirstName(),
                        record.getIndi().getLastName(),
                        record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                        record.getEventPlaceCityName());
                break;
            case Marriage:
                noteText = MessageFormat.format(noteText + " " + "l''acte de mariage de {1} {2} et {3} {4} le {5} ({6})",
                        propertyDate.getDisplayValue(),
                        record.getIndi().getFirstName(),
                        record.getIndi().getLastName(),
                        record.getWife().getFirstName(),
                        record.getWife().getLastName(),
                        record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                        record.getEventPlaceCityName());
                break;
            case Death:
                noteText = MessageFormat.format(noteText + " " + "l''acte de décès de {1} {2} le {3} ({4})",
                        propertyDate.getDisplayValue(),
                        record.getIndi().getFirstName(),
                        record.getIndi().getLastName(),
                        record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                        record.getEventPlaceCityName());
                break;
            default:
                switch (record.getEventTypeTag()) {
                    case WILL:
                        noteText = MessageFormat.format(noteText + " " + "l''acte de {1} de {2} {3} le {4} ({5})",
                                propertyDate.getDisplayValue(),
                                record.getEventType(),
                                record.getIndi().getFirstName(),
                                record.getIndi().getLastName(),
                                record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                                record.getEventPlaceCityName() + (record.getNotary().isEmpty() ? "" : ", " + record.getNotary()));
                        break;
                    case MARB:
                        noteText = MessageFormat.format(noteText + " " + "l''acte de bans de mariage entre {2} {3} et {4} {5} le {6} ({7})",
                                propertyDate.getDisplayValue(),
                                record.getEventType(),
                                record.getIndi().getFirstName(),
                                record.getIndi().getLastName(),
                                record.getWife().getFirstName(),
                                record.getWife().getLastName(),
                                record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                                record.getEventPlaceCityName() + (record.getNotary().isEmpty() ? "" : ", " + record.getNotary()));
                        break;
                    case MARC:
                        noteText = MessageFormat.format(noteText + " " + "l''acte de contrat de mariage entre {2} {3} et {4} {5} le {6} ({7})",
                                propertyDate.getDisplayValue(),
                                record.getEventType(),
                                record.getIndi().getFirstName(),
                                record.getIndi().getLastName(),
                                record.getWife().getFirstName(),
                                record.getWife().getLastName(),
                                record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                                record.getEventPlaceCityName() + (record.getNotary().isEmpty() ? "" : ", " + record.getNotary()));
                        break;
                    case MARL:
                        noteText = MessageFormat.format(noteText + " " + "l''acte de certificat de mariage entre {2} {3} et {4} {5} le {6} ({7})",
                                propertyDate.getDisplayValue(),
                                record.getEventType(),
                                record.getIndi().getFirstName(),
                                record.getIndi().getLastName(),
                                record.getWife().getFirstName(),
                                record.getWife().getLastName(),
                                record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                                record.getEventPlaceCityName() + (record.getNotary().isEmpty() ? "" : ", " + record.getNotary()));
                        break;
                    default:
                        noteText = MessageFormat.format(noteText + " " + "l''acte ''{1}'' entre {2} {3} et {4} {5} le {6} ({7})",
                                propertyDate.getDisplayValue(),
                                record.getEventType(),
                                record.getIndi().getFirstName(),
                                record.getIndi().getLastName(),
                                record.getWife().getFirstName(),
                                record.getWife().getLastName(),
                                record.getEventDateDDMMYYYY(showFrenchCalendarDate),
                                record.getEventPlaceCityName() + (record.getNotary().isEmpty() ? "" : ", " + record.getNotary()));
                        break;
                }
                break;
        }

        // je recherche une NOTE deja presente dans la propriete qui
        // contiendrait deja le texte à ajouter
        Property[] notes = eventProperty.getProperties("NOTE");
        boolean found = false;
        for (Property note : notes) {
            if (note.getValue().contains(noteText)) {
                found = true;
                break;
            }
        }

        // J'ajoute le commentaire si aucune note existe deja avec le texte
        if (!found) {
            Property propertyNote = eventProperty.getProperty("NOTE");
            if (propertyNote == null) {
                // je cree une note .
                propertyNote = eventProperty.addProperty("NOTE", "");
            }

            // j'ajoute le commentaire a la fin de la note existante.
            String value = propertyNote.getValue();
            if (!noteText.isEmpty()) {
                if (!value.isEmpty()) {
                    value += "\n";
                }
                value += noteText;
                propertyNote.setValue(value);
            }
        }
    }

    /**
     * retourne la position du propriété - apres BIRT - avant DEATH
     *
     * @param entity
     * @param propertyDate
     */
    static protected int getPropertyBestPosition(Entity entity, PropertyDate propertyDate) {
        int resultPosition = -1;
        if ( entity != null && propertyDate != null) {
            int birthPosition;
            int position = 0;
            for (Property child : entity.getProperties()) {
                if (child.getTag().equals("BIRT")) {
                    birthPosition = position;
                    resultPosition = birthPosition + 1;
                } else if (child.getTag().equals("DEAT")) {
                    // rien à faire
                } else {
                    Property[] childDates = child.getProperties("DATE");
                    if (childDates.length > 0) {
                        try {
                            if (!MergeQuery.isRecordBeforeThanDate(propertyDate, (PropertyDate) childDates[0], 0, 0)) {
                                resultPosition = position + 1;
                            }
                        } catch (GedcomException ex) {
                            // rien a faire
                        }
                    }
                }

                position++;
            }
        }

        return resultPosition;
    }

    /**
     * concatene plusieurs commentaires dans une chaine , séparés par une
     * virgule
     */
    public String appendValue(String value, String... otherValues) {
        int fieldSize = value.length();
        StringBuilder sb = new StringBuilder();
        sb.append(value.trim());
        for (String otherValue : otherValues) {
            // j'ajoute les valeurs supplémentaires séparées par des virgules
            if (!otherValue.trim().isEmpty()) {
                // je concantene les valeurs en inserant une virgule dans
                // si la valeur précedente n'est pas vide
                if (fieldSize > 0) {
                    sb.append(", ");
                }
                sb.append(otherValue.trim());
                fieldSize += otherValue.length();
            }
        }
        return sb.toString();
    }

    /**
     * retourne false si la ligne parent existe et est NON_APPLICABLE sinon
     * retoune true
     *
     * @param rowType
     * @return
     */
    private boolean isRowParentApplicable(RowType rowType) {
        MergeRow parent = getParentRow(rowType);
        if (parent == null) {
            return true;
        } else {
            return parent.compareResult != CompareResult.NOT_APPLICABLE;
        }
    }

    /**
     * retourne le libellé d'un type de ligne donnée le libellé est indenté si
     * la ligne a une ligne parent
     *
     * @param rowType
     * @return
     */
    private String getRowTypeLabel(RowType rowType) {
        String label = "";
        if (getParentRow(rowType) != null) {
            label = "  ";
        }
        if (record.getType() == RecordType.Misc && rowType == RowType.MarriageFamily) {
            // j'affiche le type d'évènement présent dans le relevé
            label += record.getEventType();
        } else if (rowType == RowType.EventType && record.getType() == RecordType.Misc) {
            if ( record.isInsinuation()) {
                label += NbBundle.getMessage(MergeModel.class, "MergeModel.EventInsinuated");
            } else {
                label += NbBundle.getMessage(MergeModel.class, "MergeModel." + rowType.toString());
            }
        } else if (rowType == RowType.IndiMarriedFamily || rowType == RowType.WifeMarriedFamily) {
            // j'affiche le type d'évènement présent dans le relevé
            if (record.getType() == RecordType.Marriage
                    || (record.getType() == RecordType.Misc
                    && (record.getEventTypeTag() == MergeRecord.EventTypeTag.MARB
                    || record.getEventTypeTag() == MergeRecord.EventTypeTag.MARC
                    || record.getEventTypeTag() == MergeRecord.EventTypeTag.MARL))) {
                label += NbBundle.getMessage(MergeModel.class, "MergeModel." + rowType.toString() + "Ex");
            } else {
                label += NbBundle.getMessage(MergeModel.class, "MergeModel." + rowType.toString());
            }
        } else {
            // j'affiche le libellé par défaut
            label += NbBundle.getMessage(MergeModel.class, "MergeModel." + rowType.toString());
        }

        return label;
    }

    /**
     * retourne la ligne parent d'une ligne donnée
     *
     * @param rowType
     * @return
     */
    MergeRow getParentRow(RowType rowType) {
        MergeRow parent;
        switch (rowType) {
            case IndiParentMarriageDate:
            case IndiFatherLastName:
            case IndiFatherFirstName:
            case IndiFatherBirthDate:
            case IndiFatherDeathDate:
            case IndiFatherOccupation:
            case IndiMotherFirstName:
            case IndiMotherLastName:
            case IndiMotherBirthDate:
            case IndiMotherDeathDate:
            case IndiMotherOccupation:
                parent = getRow(RowType.IndiParentFamily);
                break;

            case IndiMarriedFirstName:
            case IndiMarriedLastName:
            case IndiMarriedBirthDate:
            case IndiMarriedDeathDate:
            case IndiMarriedOccupation:
            case IndiMarriedMarriageDate:
                parent = getRow(RowType.IndiMarriedFamily);
                break;

            case WifeParentMarriageDate:
            case WifeFatherFirstName:
            case WifeFatherLastName:
            case WifeFatherBirthDate:
            case WifeFatherDeathDate:
            case WifeFatherOccupation:
            case WifeMotherFirstName:
            case WifeMotherLastName:
            case WifeMotherBirthDate:
            case WifeMotherDeathDate:
            case WifeMotherOccupation:
                parent = getRow(RowType.WifeParentFamily);
                break;

            case WifeMarriedFirstName:
            case WifeMarriedLastName:
            case WifeMarriedBirthDate:
            case WifeMarriedDeathDate:
            case WifeMarriedOccupation:
            case WifeMarriedMarriageDate:
                parent = getRow(RowType.WifeMarriedFamily);
                break;

            case EventPage:
                parent = getRow(RowType.EventSource);
                break;

            case EventDate:
            case EventPlace:
            case EventComment:
                parent = getRow(RowType.EventType);
                break;

            case EventInsinuationDate:
            case EventInsinuationPlace:
            case EventInsinuationComment:
                parent = getRow(RowType.EventInsinuationType);
                break;

            default:
                parent = null;

        }
        return parent;
    }

    /**
     * liste des types de ligne
     */
    static protected enum RowType {

        Separator,
        EventType,
        EventSource,
        EventPage,
        EventDate,
        EventPlace,
        EventComment,
        EventInsinuationType,
        EventInsinuationDate,
        EventInsinuationPlace,
        EventInsinuationComment,
        MarriageFamily,
        MarriageDate,
        //  indi ///////////////////////////////////////////////////////////////////
        IndiFirstName,
        IndiLastName,
        IndiSex,
        IndiAge,
        IndiBirthDate,
        IndiDeathDate,
        IndiBirthPlace,
        IndiResidence,
        IndiOccupation,
        //  conjoint (ou ancien conjoint) //////////////////////////////////////////
        IndiMarriedFamily,
        IndiMarriedMarriageDate,
        IndiMarriedFirstName,
        IndiMarriedLastName,
        IndiMarriedBirthDate,
        IndiMarriedDeathDate,
        IndiMarriedOccupation,
        //  indi father ////////////////////////////////////////////////////////////
        IndiParentFamily,
        IndiParentMarriageDate,
        IndiFatherFirstName,
        IndiFatherLastName,
        IndiFatherBirthDate,
        IndiFatherDeathDate,
        IndiFatherOccupation,
        IndiMotherFirstName,
        IndiMotherLastName,
        IndiMotherBirthDate,
        IndiMotherDeathDate,
        IndiMotherOccupation,
        //  wife ///////////////////////////////////////////////////////////////////
        WifeFirstName,
        WifeLastName,
        WifeSex,
        //wifeDead,
        wifeAge,
        WifeBirthDate,
        WifeDeathDate,
        WifePlace,
        WifeOccupation,
        //  wifeMarried ///////////////////////////////////////////////////////////
        WifeMarriedFamily,
        WifeMarriedMarriageDate,
        WifeMarriedFirstName,
        WifeMarriedLastName,
        WifeMarriedBirthDate,
        WifeMarriedDeathDate,
        WifeMarriedOccupation,
        //  wifeFather ///////////////////////////////////////////////////////////
        WifeParentFamily,
        WifeParentMarriageDate,
        WifeFatherFirstName,
        WifeFatherLastName,
        WifeFatherBirthDate,
        WifeFatherDeathDate,
        WifeFatherOccupation,
        WifeMotherFirstName,
        WifeMotherLastName,
        WifeMotherBirthDate,
        WifeMotherDeathDate,
        WifeMotherOccupation,
    }

    protected class MergeRow {

        RowType rowType;
        String label;
        Object recordValue;
        Object entityValue;
        boolean merge;
        boolean merge_initial;
        CompareResult compareResult;
        private Entity entityObject = null;

        MergeRow(RowType rowType) {
            this.rowType = rowType;
        }

        @Override
        public String toString() {
            return rowType.name() + " " + recordValue + " " + entityValue;
        }

        /**
         * @return the entityObject
         */
        public Entity getEntityObject() {
            return entityObject;
        }

        /**
         * @param entityObject the entityObject to set
         */
        public void setEntityObject(Entity entityObject) {
            this.entityObject = entityObject;
        }
    }

    protected enum CompareResult {

        EQUAL,
        COMPATIBLE,
        CONFLIT,
        NOT_APPLICABLE
    }

    /**
     * liste a deux entrées ( index et RowType)
     */
    private class MergeRowList {

        private final EnumMap<RowType, Integer> dataMap = new EnumMap<RowType, Integer>(RowType.class);
        private final List<MergeRow> dataList = new ArrayList<MergeRow>();

        void put(MergeRow mergeRow) {
            if (mergeRow.rowType == RowType.Separator) {
                // le separateur n'est pas inedxe dans dataMap
                // il peut y avoir plusieurs sperateurs
                dataList.add(mergeRow);
            } else {
                Integer index = dataMap.get(mergeRow.rowType);
                if (index == null) {
                    dataList.add(mergeRow);
                    dataMap.put(mergeRow.rowType, new Integer(dataList.size() - 1));
                } else {
                    dataList.set(index, mergeRow);
                }
            }

        }

        MergeRow get(int index) {
            return dataList.get(index);
        }

        MergeRow get(RowType rowType) {
            Integer index = dataMap.get(rowType);
            if (index != null) {
                return dataList.get(index);
            } else {
                return null;
            }
        }

        private int size() {
            return dataList.size();
        }

        /**
         * retourne l'index
         *
         * @param rowType
         * @return the index of the first occurrence of the specified element in
         * this list, or -1 if this list does not contain the element
         */
        private int indexOf(RowType rowType) {
            Integer index = dataMap.get(rowType);
            if (index != null) {
                return index;
            } else {
                return -1;
            }
        }

    }
}
