package ancestris.modules.releve.dnd;

import ancestris.modules.releve.model.FieldDate;
import ancestris.modules.releve.model.FieldDead;
import ancestris.modules.releve.model.FieldPlace;
import ancestris.modules.releve.model.Record;
import ancestris.modules.releve.model.RecordBirth;
import ancestris.modules.releve.model.RecordDeath;
import ancestris.modules.releve.model.RecordMarriage;
import genj.gedcom.GedcomException;
import genj.gedcom.PropertyDate;
import genj.gedcom.time.Delta;
import genj.gedcom.time.PointInTime;


/**
 * Cette classe encapsule un objet de la classe Record
 * et ajoute des méthodes pour calculer les dates de naissance , mariage , deces
 * et des methodes pour assembler les commentaires.
 */
public class MergeRecord {

    /**
     * liste des types de releves
     */
    protected static enum RecordType {
        Birth,
        Marriage,
        Death,
        Misc
    }

    /**
     * liste des types de releves
     */
    protected static enum EventTypeTag {
        MARC,
        MARB,
        MARL,
        WILL,
        EVEN
    }

    public enum MergeParticipantType {
        participant1,
        participant2
    };

    private FieldPlace recordsInfoPlace = new FieldPlace();
    private String sourceTitle;
    private Record record;
    private MergeParticipant partipant1;
    private MergeParticipant partipant2;

    /**
     * type du releve 
     */
    private RecordType type;
    private EventTypeTag eventTypeTag;

     /**
     * constructeur
     * @param record
     */
    protected MergeRecord( FieldPlace recordsInfoPlace, String sourceTitle, Record record)  {
        this.recordsInfoPlace = recordsInfoPlace;
        if (sourceTitle != null) {
            this.sourceTitle = sourceTitle;
        } else {
            this.sourceTitle = "";
        }
        this.record = record;
        if (record instanceof RecordBirth) {
            type = RecordType.Birth;
        } else if (record instanceof RecordMarriage) {
            type = RecordType.Marriage;
        } else if (record instanceof RecordDeath) {
            type = RecordType.Death;
        } else {
            type = RecordType.Misc;
            String eventType = record.getEventType().toString().toLowerCase();
            if (eventType.equals("cm") || eventType.indexOf("mariage") != -1) {
                if ( eventType.contains("ban") || eventType.contains("publication")) {
                    eventTypeTag = EventTypeTag.MARB;
                } else if ( eventType.contains("certificat") ) {
                    eventTypeTag = EventTypeTag.MARL;
                } else{
                    eventTypeTag = EventTypeTag.MARC;
                }
            } else if (record.getEventType().toString().toLowerCase().equals("testament")) {
                eventTypeTag = EventTypeTag.WILL;
            } else {
                eventTypeTag = EventTypeTag.EVEN;
            }
        }

        partipant1 = new MergeParticipant(MergeParticipantType.participant1, record.getIndi());
        partipant2 = new MergeParticipant(MergeParticipantType.participant2, record.getWife());

    }

    ///////////////////////////////////////////////////////////////////////////
    // accesseurs
    //////////////////////////////////////////////////////////////////////////
    
    MergeParticipant getIndi() {
        return partipant1;
    }

    MergeParticipant getWife() {
        return partipant2;
    }

    MergeParticipant getParticipant(MergeParticipantType mergeParticipanType) {
        if( mergeParticipanType == MergeParticipantType.participant1) {
            return partipant1;
        } else {
            return partipant2;
        }
    }

    RecordType getType() {
        return type;
    }

    EventTypeTag getEventTypeTag() {
        return eventTypeTag;
    }
    
     public String getTag() {
        String tag;
        switch (eventTypeTag) {
            case MARB :
                tag = "MARB";
                break;
            case MARC :
                tag = "MARC";
                break;
            case MARL :
                tag = "MARL";
                break;
            case WILL : 
                tag = "WILL";
                break;
            default:
                tag = "EVEN";
                break;
        }
        return tag;
    }
     
    public String getTagLabel() {
        String tag;
        switch (eventTypeTag) {
            case MARB :
                tag = "les bans de mariage";
                break;
            case MARC :
                tag = "le contrat de mariage";
                break;
            case MARL :
                tag = "le certtificat de mariage";
                break;
            case WILL : 
                tag = "le testament";
                break;
            default:
                tag = "l'évènement";
                break;
        }
        return tag;
    }

    String getEventTypeWithDate() {
        String eventTypeWithDate = record.getEventType().toString();
        if (!recordsInfoPlace.getValue().isEmpty()) {
            if (!eventTypeWithDate.isEmpty()) {
                eventTypeWithDate += ", ";
            }
            eventTypeWithDate += recordsInfoPlace.getValue().toString();
        }
        if (!eventTypeWithDate.isEmpty()) {
            eventTypeWithDate += " (" + getEventDate().getDisplayValue() + ")";
        }

        return eventTypeWithDate;
    }
    
    String getEventSource() {
        return sourceTitle;
    }

    String getEventCote() {
        return record.getCote().toString();
    }

    String getEventPage() {
        return record.getFreeComment().toString();
    }

    PropertyDate getEventDate() {
        return record.getEventDateProperty();
    }

    String getEventDateDDMMYYYY(boolean showFrenchCalendarDate) {
        String result = record.getEventDateString();
        if (showFrenchCalendarDate) {
            try {
                String frenchCalendarDate = record.getEventDateProperty().getStart().getPointInTime(PointInTime.FRENCHR).toString();
                if (!frenchCalendarDate.isEmpty()) {
                    result += " (" + frenchCalendarDate + ")";
                }
            } catch (GedcomException ex) {
                // rien a faire
            }
        }
        return result;
    }

   PropertyDate getInsinuationDate() {
        return record.getSecondDateProperty();
    }

    String getInsinuationDateDDMMYYYY(boolean showFrenchCalendarDate) {
        String result = record.getSecondDateString();
        if (showFrenchCalendarDate) {
            try {
                String frenchCalendarDate = record.getSecondDateProperty().getStart().getPointInTime(PointInTime.FRENCHR).toString();
                if (!frenchCalendarDate.isEmpty()) {
                    result += " (" + frenchCalendarDate + ")";
                }
            } catch (GedcomException ex) {
                // rien a faire
            }
        }
        return result;
    }

    String getEventPlace() {
        return recordsInfoPlace.toString();
    }

    boolean isInsinuation() {
        return record.getSecondDateProperty() != null  && record.getSecondDateProperty().isComparable();
    }

    String getEventPlaceCityName() {
        return recordsInfoPlace.getCityName();
    }
    String getEventPlaceCityCode() {
        return recordsInfoPlace.getCityCode();
    }
    String getEventPlaceCountyName() {
        return recordsInfoPlace.getCountyName();
    }

    String getEventType() {
        return record.getEventType().toString();
    }
    String getNotary() {
        return record.getNotary().toString();
    }

    String getEventComment(boolean showFrenchCalendarDate) {
        String comment = "";
        switch (type) {
            case Birth:
                comment = makeBirthComment(showFrenchCalendarDate);
                break;
            case Marriage:
                comment = makeMarriageComment(showFrenchCalendarDate);
                break;
            case Death:
                comment = makeDeathComment(showFrenchCalendarDate);
                break;
            default:
                comment = makeMiscComment(showFrenchCalendarDate);
                break;
        }
        return comment.replaceAll(",+", ",");
    }

    //  indi ///////////////////////////////////////////////////////////////////
//    String getIndiFirstName() {
//        return record.getIndiFirstName().toString();
//    }
//
//    String getIndiLastName() {
//        return record.getIndiLastName().toString();
//    }
//
//    int getIndiSex() {
//        return record.getIndiSex().getSex();
//    }
//    String getIndiSexString() {
//        return record.getIndiSex().getValue();
//    }
//
//    PropertyDate getIndiBirthDate() throws Exception {
//        if (IndiBirthDate == null) {
//            IndiBirthDate = calculateBirthDate(
//                record.getIndiBirthDate()!=null ? record.getIndiBirthDate().getPropertyDate() : new PropertyDate(),
//                record.getIndiAge()!=null ? record.getIndiAge().getDelta() : new Delta(0,0,0),
//                getIndiMarriedMarriageDate());
//        }
//        return IndiBirthDate;
//    }
//
//    PropertyDate getIndiDeathDate() throws Exception {
//        if (IndiDeathDate == null) {
//            if ( type == RecordType.Death) {
//                IndiDeathDate = getEventDate();
//            } else {
//                // date de deces inconnue
//                IndiDeathDate = new PropertyDate();
//            }
//        }
//        return IndiDeathDate;
//    }
//
//    /**
//     * retourne le lieu de naissance .
//     * Si c'est un acte de naissance et si IndiBirthPlace est vide
//     *      retourne IndiFatherResidence ou, à dfafaut, EventPlace
//     * @return
//     */
//    String getIndiBirthPlace() {
//        if ( record.getIndiBirthPlace() != null &&  !record.getIndiBirthPlace().isEmpty()) {
//            return record.getIndiBirthPlace().toString();
//        } else {
//            if (this.type == RecordType.Birth) {
//                if( record.getIndiFatherResidence() != null && !record.getIndiFatherResidence().isEmpty()) {
//                    return record.getIndiFatherResidence().toString();
//                } else {
//                    return recordsInfoPlace.toString();
//                }
//            } else {
//                return "";
//            }
//        }
//    }
//
//    String getIndiOccupation() {
//        if( record.getIndiOccupation() != null ) {
//            return record.getIndiOccupation().toString();
//        } else {
//            // n'est pas renseignée pour les naissances
//            return null;
//        }
//    }
//
//    String getIndiOccupationWithDate() {
//        String occupation = record.getIndiOccupation().toString();
//        if (!record.getIndiResidence().isEmpty() ) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getIndiResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//
//    String getIndiResidence() {
//        if (record.getIndiResidence()!= null ) {
//            return record.getIndiResidence().toString();
//        } else {
//            // n'est pas renseignée pour les naissances
//            return "";
//        }
//    }
//
//    //  conjoint (ou ancien conjoint) //////////////////////////////////////////
//
//    String getIndiMarriedFirstName() {
//        return record.getIndiMarriedFirstName().toString();
//    }
//
//    String getIndiMarriedLastName() {
//        return record.getIndiMarriedLastName().toString();
//    }
//
//    PropertyDate getIndiMarriedBirthDate() throws Exception {
//        if (IndiMarriedBirthDate == null) {
//            IndiMarriedBirthDate = new PropertyDate();
//            if ( record.getIndiMarriedFirstName()!= null
//                 && record.getIndiMarriedLastName()!= null
//                 && (!record.getIndiMarriedFirstName().isEmpty() ||  !record.getIndiMarriedLastName().isEmpty())
//                 && getIndiMarriedMarriageDate().isComparable()) {
//                // l'ex conjoint existe , la naissance est minMarriageYearOld le mariage avec l'individu
//                IndiMarriedBirthDate.setValue(PropertyDate.BEFORE, getYear(getIndiMarriedMarriageDate().getStart(), -MergeQuery.minMarriageYearOld), null, "naissance avant la date du mariage -"+MergeQuery.minMarriageYearOld);
//            }
//        }
//        return IndiMarriedBirthDate;
//    }
//
//    PropertyDate getIndiMarriedMarriageDate() throws Exception {
//        if (IndiMarriedMarriageDate == null) {
//            IndiMarriedMarriageDate = new PropertyDate();
//            if ( record.getIndiMarriedFirstName()!= null
//                 && record.getIndiMarriedLastName()!= null
//                 && (!record.getIndiMarriedFirstName().isEmpty() ||  !record.getIndiMarriedLastName().isEmpty())
//                 && getEventDate().isComparable()) {
//                // l'ex conjoint existe , le mariage avec l'individu est avant la date l'evenement
//                IndiMarriedMarriageDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "mariage avant la date du relevé");
//            } else if ( this.getType() == RecordType.Death ) {
//                // la date du mariage est avant le deces
//                IndiMarriedMarriageDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "mariage avant la date du relevé");
//            }
//        }
//        return IndiMarriedMarriageDate;
//    }
//
//    PropertyDate getIndiMarriedDeathDate() throws Exception {
//        if (IndiMarriedDeathDate == null) {
//            IndiMarriedDeathDate = new PropertyDate();
//            if (record.getIndiMarriedDead().getState()==true) {
//                IndiMarriedDeathDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "deces avant la date du relevé");
//            } else {
//                // je ne sais pas
//                //IndiMarriedDeathDate.setValue(PropertyDate.AFTER, getYear(getEventDate().getStart()), null, "deces aprés la date du relevé");
//            }
//        }
//        return IndiMarriedDeathDate;
//    }
//
//    String getIndiMarriedOccupation() {
//        return record.getIndiMarriedOccupation().toString();
//    }
//
//    String getIndiMarriedOccupationWithDate() {
//        String occupation = record.getIndiMarriedOccupation().toString();
//        if (!record.getIndiMarriedResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getIndiMarriedResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//        return occupation;
//    }
//
//    String getIndiMarriedResidence() {
//        return record.getIndiMarriedResidence().toString();
//    }
//
//
//    //  indi father ////////////////////////////////////////////////////////////
//
//    PropertyDate getIndiParentMarriageDate() throws Exception {
//        if (IndiParentMarriageDate == null) {
//            IndiParentMarriageDate = calculateParentMariageDate(getIndiBirthDate());
//        }
//        return IndiParentMarriageDate;
//    }
//
//    String getIndiFatherFirstName() {
//        return record.getIndiFatherFirstName().toString();
//    }
//
//    String getIndiFatherLastName() {
//        return record.getIndiFatherLastName().toString();
//    }
//
//    PropertyDate getIndiFatherBirthDate() throws Exception {
//        if (IndiFatherBirthDate == null) {
//            IndiFatherBirthDate = calculateParentBirthDate(record.getIndiFatherAge().getDelta(),getIndiBirthDate());
//        }
//        return IndiFatherBirthDate;
//    }
//
//    PropertyDate getIndiFatherDeathDate() throws Exception {
//        if (IndiFatherDeathDate == null) {
//            IndiFatherDeathDate = calculateParentDeathDate(
//                   record.getIndiFatherDead()!=null ? record.getIndiFatherDead().getState() :false,
//                   getIndiBirthDate(),
//                   9 // le pere peut être decede au plus tot apres la conception, soit 9 mois avant la naissance
//               );
//        }
//        return IndiFatherDeathDate;
//    }
//
//    String getIndiFatherOccupation() {
//        return record.getIndiFatherOccupation().toString();
//    }
//
//    String getIndiFatherResidence() {
//        return record.getIndiFatherResidence().toString();
//    }
//
//    String getIndiFatherOccupationWithDate() {
//        String occupation = record.getIndiFatherOccupation().toString();
//        if (!record.getIndiFatherResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getIndiFatherResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//        return occupation;
//    }
//
//    String getIndiMotherFirstName() {
//        return record.getIndiMotherFirstName().toString();
//    }
//
//    String getIndiMotherLastName() {
//        return record.getIndiMotherLastName().toString();
//    }
//
//    PropertyDate getIndiMotherBirthDate() throws Exception {
//        if (IndiMotherBirthDate == null) {
//            IndiMotherBirthDate = calculateParentBirthDate(record.getIndiMotherAge().getDelta(), getIndiBirthDate());
//        }
//        return IndiMotherBirthDate;
//    }
//
//    PropertyDate getIndiMotherDeathDate() throws Exception {
//        if (IndiMotherDeathDate == null) {
//            IndiMotherDeathDate = calculateParentDeathDate(
//                   record.getIndiMotherDead()!=null ? record.getIndiMotherDead().getState() : false,
//                   getIndiBirthDate(),
//                   0 // le mere peut être decedee au plus tot 0 mois avant le naissance (par opposition au pere qui peut etre decede 9 mois avant la naissance)
//               );
//        }
//        return IndiMotherDeathDate;
//    }
//
//    String getIndiMotherOccupation() {
//        return record.getIndiMotherOccupation().toString();
//    }
//
//    String getIndiMotherOccupationWithDate() {
//        String occupation = record.getIndiMotherOccupation().toString();
//        if (!record.getIndiMotherResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getIndiMotherResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//    String getIndiMotherResidence() {
//        return record.getIndiMotherResidence().toString();
//    }

    //  wife ///////////////////////////////////////////////////////////////////

//    String getWifeFirstName() {
//        return record.getWifeFirstName().toString();
//    }
//
//    String getWifeLastName() {
//        return record.getWifeLastName().toString();
//    }
//
//    int getWifeSex() {
//        return record.getWifeSex().getSex();
//    }
//
//    PropertyDate getWifeBirthDate() throws Exception {
//        if (WifeBirthDate == null) {
//            WifeBirthDate = calculateBirthDate(
//                record.getWifeBirthDate()!=null ? record.getWifeBirthDate().getPropertyDate() : new PropertyDate(),
//                record.getWifeAge()!=null ? record.getWifeAge().getDelta() : new Delta(0,0,0),
//                getWifeMarriedMarriageDate() );
//        }
//        return WifeBirthDate;
//    }
//
//    PropertyDate getWifeDeathDate() throws Exception {
//        if (WifeDeathDate == null) {
//            if ( type == RecordType.Death) {
//                IndiDeathDate = getEventDate();
//            } else {
//                IndiDeathDate = new PropertyDate();
//            }
//        }
//        return WifeDeathDate;
//    }
//
//    String getWifeBirthPlace() {
//        if( record.getWifeBirthPlace() != null) {
//            return record.getWifeBirthPlace().toString();
//        } else {
//            return "";
//        }
//    }
//
//    String getWifeOccupation() {
//        return record.getWifeOccupation().toString();
//    }
//
//    String getWifeOccupationWithDate() {
//        String occupation = record.getWifeOccupation().toString();
//        if (!record.getWifeResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getWifeResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//    String getWifeResidence () {
//        return record.getWifeResidence().toString();
//    }
//
//    //  conjoint (ou ancien conjoint) //////////////////////////////////////////
//    String getWifeMarriedFirstName() {
//        return record.getWifeMarriedFirstName().toString();
//    }
//
//    String getWifeMarriedLastName() {
//        return record.getWifeMarriedLastName().toString();
//    }
//
//    PropertyDate getWifeMarriedBirthDate() throws Exception {
//        if (WifeMarriedBirthDate == null) {
//            WifeMarriedBirthDate = new PropertyDate();
//            if ( record.getWifeMarriedFirstName()!= null
//                 && record.getWifeMarriedLastName()!= null
//                 && (!record.getWifeMarriedFirstName().isEmpty() ||  !record.getWifeMarriedLastName().isEmpty())
//                 && getWifeMarriedMarriageDate().isComparable()) {
//                // l'ex conjoint existe , la naissance est minMarriageYearOld le mariage avec l'individu
//                WifeMarriedBirthDate.setValue(PropertyDate.BEFORE, getYear(getWifeMarriedMarriageDate().getStart(), -MergeQuery.minMarriageYearOld), null, "naissance avant la date du mariage -"+MergeQuery.minMarriageYearOld);
//            }
//        }
//        return WifeMarriedBirthDate;
//    }
//
//    PropertyDate getWifeMarriedMarriageDate() throws Exception {
//        if (WifeMarriedMarriageDate == null) {
//            WifeMarriedMarriageDate = new PropertyDate();
//            if ( record.getWifeMarriedFirstName()!= null  && record.getWifeMarriedLastName() != null
//                    && (!record.getWifeMarriedFirstName().isEmpty() ||  !record.getWifeMarriedLastName().isEmpty())
//                    && getEventDate().isComparable() ) {
//                // le mariage avec l'ex conjoit
//                //WifeMarriedMarriageDate.setValue(String.format("BEF %d",getEventDate().getStart().getYear()));
//                WifeMarriedMarriageDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "mariage avant la date du relevé");
//            }
//        }
//        return WifeMarriedMarriageDate;
//    }
//
//    PropertyDate getWifeMarriedDeathDate() throws Exception {
//        if (WifeMarriedDeathDate == null) {
//            WifeMarriedDeathDate = new PropertyDate();
//            if ( record.getWifeMarriedDead()!= null && record.getWifeMarriedDead().getState() ) {
//                WifeMarriedDeathDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "décès avant la date du relevé");
//             } else {
//                // je ne sais pas
//                //WifeMarriedDeathDate.setValue(PropertyDate.AFTER, getYear(getEventDate().getStart()), null, "deces aprés la date du relevé");
//            }
//        }
//        return WifeMarriedDeathDate;
//    }
//
//    String getWifeMarriedOccupation() {
//        return record.getWifeFatherOccupation().toString();
//    }
//
//    String getWifeMarriedOccupationWithDate() {
//        String occupation = record.getWifeMarriedOccupation().toString();
//        if (!record.getWifeMarriedResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getWifeMarriedResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//
//
//    //  wife father ////////////////////////////////////////////////////////////
//
//    PropertyDate getWifeParentMarriageDate() throws Exception {
//        if (WifeParentMarriageDate == null) {
//            WifeParentMarriageDate = calculateParentMariageDate(getWifeBirthDate());
//        }
//        return WifeParentMarriageDate;
//    }
//
//    String getWifeFatherFirstName() {
//        if ( record.getWifeFatherFirstName() == null) {
//            return "";
//        } else {
//            return record.getWifeFatherFirstName().toString();
//        }
//    }
//
//    String getWifeFatherLastName() {
//        if ( record.getWifeFatherLastName() == null ) {
//            return "";
//        } else {
//            return record.getWifeFatherLastName().toString();
//        }
//    }
//
//    PropertyDate getWifeFatherBirthDate() throws Exception {
//        if (WifeFatherBirthDate == null) {
//            WifeFatherBirthDate = calculateParentBirthDate(record.getWifeFatherAge().getDelta(), getWifeBirthDate());
//        }
//        return WifeFatherBirthDate;
//    }
//
//    PropertyDate getWifeFatherDeathDate() throws Exception {
//        if (WifeFatherDeathDate == null) {
//             WifeFatherDeathDate = calculateParentDeathDate(
//                   record.getWifeFatherDead()!=null ? record.getWifeFatherDead().getState() :false,
//                   getWifeBirthDate(),
//                   9 // le pere peut être decede au plus tot apres la conception, soit 9 mois avant la naissance
//               );
//        }
//        return WifeFatherDeathDate;
//    }
//
//    String getWifeFatherOccupation() {
//        if ( record.getWifeFatherOccupation() == null ) {
//            return "";
//        } else {
//            return record.getWifeFatherOccupation().toString();
//        }
//    }
//
//    String getWifeFatherResidence() {
//        return record.getWifeFatherResidence().toString();
//    }
//
//    String getWifeFatherOccupationWithDate() {
//        String occupation = getWifeFatherOccupation();
//        if (!getWifeFatherResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += getWifeFatherResidence();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//    String getWifeMotherFirstName() {
//        if( record.getWifeMotherFirstName() == null) {
//            return "";
//        } else {
//            return record.getWifeMotherFirstName().toString();
//        }
//    }
//
//    String getWifeMotherLastName() {
//        if ( record.getWifeMotherLastName() == null) {
//            return "";
//        } else {
//            return record.getWifeMotherLastName().toString();
//        }
//    }
//
//    PropertyDate getWifeMotherBirthDate() throws Exception {
//        if (WifeMotherBirthDate == null) {
//            WifeMotherBirthDate = calculateParentBirthDate(record.getWifeMotherAge().getDelta(), getWifeBirthDate());
//        }
//        return WifeMotherBirthDate;
//    }
//
//    PropertyDate getWifeMotherDeathDate() throws Exception {
//        if (WifeMotherDeathDate == null) {
//            WifeMotherDeathDate = calculateParentDeathDate(
//                   record.getWifeMotherDead()!=null ? record.getWifeMotherDead().getState() : false,
//                   getWifeBirthDate(),
//                   0 // le mere peut être decedee 0 mois avant le naissance (= juste apres la naissance)
//               );
//        }
//        return WifeMotherDeathDate;
//    }
//
//    String getWifeMotherOccupation() {
//        if ( record.getWifeMotherOccupation() == null ) {
//            return "";
//        } else {
//            return record.getWifeMotherOccupation().toString();
//        }
//    }
//
//    String getWifeMotherOccupationWithDate() {
//        String occupation = getWifeMotherOccupation();
//        if (!record.getWifeMotherResidence().isEmpty()) {
//            if( !occupation.isEmpty()) {
//                occupation += ", ";
//            }
//            occupation += record.getWifeMotherResidence().toString();
//        }
//        if (!occupation.isEmpty()) {
//            occupation += " (" + getEventDate().getDisplayValue()+")";
//        }
//
//        return occupation;
//    }
//
//    /**
//     *
//     * @return
//     */
//    String getWifeMotherResidence() {
//        String residence;
//        if (this.type == RecordType.Birth && record.getWifeMotherResidence().isEmpty() && ! record.getWifeFatherResidence().isEmpty()) {
//            residence = record.getWifeFatherResidence().toString();
//        } else {
//            residence = record.getWifeMotherResidence().toString();
//        }
//        return residence;
//    }
//

    /**
     * genere le commentaire de la naissance
     * composé de :
     *     commentaire de l'individu ,
     *     commentaire genéral
     *     commentaire photo
     *     commentaire père
     *     commentaire mère
     *     noms, prénom et commentaires des parrain, marraine et témoins
     *
     * @return
     */
    private String makeBirthComment(boolean showFrenchCalendarDate) {

        String comment = "";
        comment = "Date de l'acte: "+getEventDateDDMMYYYY(showFrenchCalendarDate);       
        comment = appendComment(comment, "Nouveau né", makeIndiComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Père", makeIndiFatherComment());
        comment = appendComment(comment, "Mère", makeIndiMotherComment());

        String godFather = appendValue(
                record.getWitness1FirstName().toString() + " " + record.getWitness1LastName().toString(),
                record.getWitness1Occupation().toString(),
                record.getWitness1Comment().toString());
        if (!godFather.isEmpty()) {
            if (!comment.isEmpty() && comment.charAt(comment.length()-1)!= '\n') {
                comment += "\n";
            }
            comment += "Parrain/témoin" + ": " + godFather;
        }
        String godMother = appendValue(
                record.getWitness2FirstName().toString() + " " + record.getWitness2LastName().toString(),
                record.getWitness2Occupation().toString(),
                record.getWitness2Comment().toString());
        if (!godMother.isEmpty()) {
            if (!comment.isEmpty() && comment.charAt(comment.length()-1)!= '\n') {
                comment += "\n";
            }
            comment += "Marraine/témoin" + ": " + godMother;
        }

        String witness = appendValue(
                record.getWitness3FirstName().toString() + " " + record.getWitness3LastName().toString(),
                record.getWitness3Occupation().toString(),
                record.getWitness3Comment().toString(),
                record.getWitness4FirstName().toString() + " " + record.getWitness4LastName().toString(),
                record.getWitness4Occupation().toString(),
                record.getWitness4Comment().toString());
        comment = appendComment(comment, "Témoin(s)",witness);

        comment = appendComment(comment, "Commentaire général",record.getGeneralComment().toString());
        comment = appendComment(comment, "Photo",record.getFreeComment().toString());

        return comment;
    }

    /**
     * genere le commentaire de la naissance
     * en concatenant :
     *     commentaire general,
     *     commentaire libre,
     *     commentaire de l'epoux
     *     commentaire de l'epouse
     *     noms, prénom et commentaires des témoins
     *
     * @return
     */
    private String makeMarriageComment(boolean showFrenchCalendarDate) {

        String comment="";
        comment = "Date de l'acte: "+getEventDateDDMMYYYY(showFrenchCalendarDate);

        comment = appendComment(comment, "Epoux", makeIndiComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Ex conjoint époux", makeIndiMarriedComment());
        comment = appendComment(comment, "Père époux", makeIndiFatherComment());
        comment = appendComment(comment, "Mère époux", makeIndiMotherComment());

        comment = appendComment(comment, "Epouse", makeWifeComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Ex conjoint épouse", makeWifeMarriedComment());
        comment = appendComment(comment, "Père épouse", makeWifeFatherComment());
        comment = appendComment(comment, "Mère épouse", makeWifeMotherComment());

        comment = appendComment(comment, "Témoin(s)", makeWitnessComment());
        comment = appendComment(comment, "Commentaire général",record.getGeneralComment().toString());
        comment = appendComment(comment, "Photo",record.getFreeComment().toString());

        return comment;
    }

    /**
     * genere le commentaire de la deces
     * composé de :
     *     commentaire de l'individu ,
     *     commentaire genéral
     *     commentaire photo
     *     commentaire père
     *     commentaire mère
     *     noms, prénom et commentaires des parrain, marraine et témoins
     *
     * @return
     */
    private String makeDeathComment(boolean showFrenchCalendarDate) {
        String comment = "";
        comment = "Date de l'acte: "+getEventDateDDMMYYYY(showFrenchCalendarDate);

        comment = appendComment(comment, "Défunt", makeIndiComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Conjoint", makeIndiMarriedComment());
        comment = appendComment(comment, "Père", makeIndiFatherComment());
        comment = appendComment(comment, "Mère", makeIndiMotherComment());

        comment = appendComment(comment, "Témoin(s)", makeWitnessComment());
        comment = appendComment(comment, "Commentaire général",record.getGeneralComment().toString());
        comment = appendComment(comment, "Photo",record.getFreeComment().toString());

        return comment;
    }

    /**
     * genere le commentaire d'un evenement divers
     * en concatenant :
     *     commentaire de l'individu
     *     commentaire du pere de l'individu
     *     commentaire de la mere de l'individu
     *     noms, prénom et commentaires des témoins
     *     commentaire general
     *     commentaire libre
     *
     * @return
     */
    private String makeMiscComment(boolean showFrenchCalendarDate) {
        String comment="";
        comment = "Date de l'acte: "+getEventDateDDMMYYYY(showFrenchCalendarDate);

        // commentaire de l'insinuation
        if( isInsinuation()) {
            String insinuationComment = appendValue(
                    "Insinuation de l'acte ''" + getEventType() + "'' du " + getEventDateDDMMYYYY(showFrenchCalendarDate),
                    record.getNotary().isEmpty() ? "" : "retenu par " + getNotary());

            if (!insinuationComment.isEmpty()) {
                if (!comment.isEmpty() && comment.charAt(comment.length() - 1) != '\n') {
                    comment += "\n";
                }
                comment += insinuationComment;
            }
        }
        
        comment = appendComment(comment, "Intervenant 1", makeIndiComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Conjoint intervenant 1", makeIndiMarriedComment());
        comment = appendComment(comment, "Père intervenant 1", makeIndiFatherComment());
        comment = appendComment(comment, "Mère intervenant 1", makeIndiMotherComment());

        comment = appendComment(comment, "Intervenant 2", makeWifeComment(showFrenchCalendarDate));
        comment = appendComment(comment, "Conjoint intervenant 2", makeWifeMarriedComment());
        comment = appendComment(comment, "Père intervenant 2", makeWifeFatherComment());
        comment = appendComment(comment, "Mère intervenant 2", makeWifeMotherComment());

        comment = appendComment(comment, "Témoin(s)", makeWitnessComment());
        comment = appendComment(comment, "Commentaire général",record.getGeneralComment().toString());
        comment = appendComment(comment, "Photo",record.getFreeComment().toString());

        return comment;
    }

    //
    private String makeIndiComment(boolean showFrenchCalendarDate) {
        String comment = appendValue(
                record.getIndiFirstName() + " " + record.getIndiLastName(),
                record.getIndiAge()==null ? "" :record.getIndiAge().toString(),
                makeParticipantBirthComment(record.getIndiBirthDate(), showFrenchCalendarDate, record.getIndiBirthPlace()),
                record.getIndiOccupation()==null ? "" : record.getIndiOccupation().toString(),
                appendPrefixValue("domicile", record.getIndiResidence()== null ? "" :record.getIndiResidence().toString()),
                record.getIndiComment().toString()
                );
        return comment;
    }

    private String makeIndiMarriedComment(  ) {
           String comment = appendValue(
                record.getIndiMarriedFirstName() + " " + record.getIndiMarriedLastName(),
                record.getIndiMarriedDead().toString(),
                record.getIndiMarriedOccupation().toString(),
                appendPrefixValue("domicile", record.getIndiMarriedResidence().toString()),
                record.getIndiMarriedComment().toString()
                );

        return comment;
    }

    private String makeIndiFatherComment(  ) {
        String comment = appendValue(
                record.getIndiFatherFirstName() + " " + record.getIndiFatherLastName(),
                record.getIndiFatherAge().toString(),
                record.getIndiFatherDead().toString(),
                record.getIndiFatherOccupation().toString(),
                appendPrefixValue("domicile", record.getIndiFatherResidence().toString()),
                record.getIndiFatherComment().toString()
                );
        return comment;
    }

    private String makeIndiMotherComment(  ) {
         String comment = appendValue(
                record.getIndiMotherFirstName() + " " + record.getIndiMotherLastName(),
                record.getIndiMotherAge().toString(),
                record.getIndiMotherDead().toString(),
                record.getIndiMotherOccupation().toString(),
                appendPrefixValue("domicile", record.getIndiMotherResidence().toString()),
                record.getIndiMotherComment().toString()
                );
        return comment;
    }

    private String makeWifeComment( boolean showFrenchCalendarDate ) {
        String comment = appendValue(
                record.getWifeFirstName() + " " + record.getWifeLastName(),
                record.getWifeAge().toString(),
                makeParticipantBirthComment(record.getWifeBirthDate(), showFrenchCalendarDate, record.getWifeBirthPlace()),
                record.getWifeOccupation().toString(),
                appendPrefixValue("domicile", record.getWifeResidence().toString()),
                record.getWifeComment().toString()
                );
        return comment;
    }

    private String makeWifeMarriedComment(  ) {
         String comment = appendValue(
                record.getWifeMarriedFirstName() + " " + record.getWifeMarriedLastName(),
                record.getWifeMarriedDead().toString(),
                record.getWifeMarriedOccupation().toString(),
                appendPrefixValue("domicile", record.getWifeMarriedResidence().toString()),
                record.getWifeMarriedComment().toString()
                );
        return comment;
    }

    private String makeWifeFatherComment(  ) {
         String comment = appendValue(
                record.getWifeFatherFirstName() + " " + record.getWifeFatherLastName(),
                record.getWifeFatherAge().toString(),
                record.getWifeFatherDead().toString(),
                record.getWifeFatherOccupation().toString(),
                appendPrefixValue("domicile", record.getWifeFatherResidence().toString()),
                record.getWifeFatherComment().toString()
                );
        return comment;
    }

    private String makeWifeMotherComment(  ) {
        String comment = appendValue(
                record.getWifeMotherFirstName() + " " + record.getWifeMotherLastName(),
                record.getWifeMotherAge().toString(),
                record.getWifeMotherDead().toString(),
                record.getWifeMotherOccupation().toString(),
                appendPrefixValue("domicile", record.getWifeMotherResidence().toString()),
                record.getWifeMotherComment().toString()
                );
        return comment;
    }

     private String makeWitnessComment(  ) {
        String comment = appendValue(
                record.getWitness1FirstName().toString() + " " + record.getWitness1LastName().toString(),
                record.getWitness1Occupation().toString(),
                record.getWitness1Comment().toString(),
                record.getWitness2FirstName().toString() + " " + record.getWitness2LastName().toString(),
                record.getWitness2Occupation().toString(),
                record.getWitness2Comment().toString(),
                record.getWitness3FirstName().toString() + " " + record.getWitness3LastName().toString(),
                record.getWitness3Occupation().toString(),
                record.getWitness3Comment().toString(),
                record.getWitness4FirstName().toString() + " " + record.getWitness4LastName().toString(),
                record.getWitness4Occupation().toString(),
                record.getWitness4Comment().toString());
        return comment;
    }


    /**
     * genere le commentaire mentionnant la reference à une insinuation 
     * @return
     */
    protected String makeInsinuationReferenceComment(boolean showFrenchCalendarDate) {
        String comment = "";

        // commentaire de l'insinuation
        comment = "Voir l'insinuation de l'acte ''" + getEventType() + "''"
                + " du " + getInsinuationDateDDMMYYYY(showFrenchCalendarDate)
                + " (" + getEventPlaceCityName() + ")"
                + (getNotary().isEmpty() ? "" : " retenu par " + getNotary());

        return comment;
    }

        
    private String makeParticipantBirthComment(FieldDate birthDate, boolean showFrenchCalendarDate, FieldPlace birthPlace) {
        String comment = "";
        if (birthDate != null && !birthDate.isEmpty()) {
            comment = "né le" + " " + birthDate.getValueDDMMYYYY();
            if( showFrenchCalendarDate) {
                String frenchCalendarDate = birthDate.getFrenchCalendarValue();
                if (!frenchCalendarDate.isEmpty()) {
                    comment += " (" + frenchCalendarDate + ")";
                }
            }
        }

        if (birthPlace != null && !birthPlace.isEmpty()) {
            if( comment.isEmpty() ) {
                comment = "né à" + " " + birthPlace.toString();
            } else {
                comment += " "+ "à" + " " +birthPlace.toString();
            }
        }
        return comment;
    }
    
    protected String makeEventPage() {
        String eventPage = "";
        eventPage = record.getCote().toString();
        if( !record.getFreeComment().isEmpty() ) {
            if ( !eventPage.isEmpty()) {
                eventPage += ", ";
            }
            eventPage += record.getFreeComment().toString();
        }
        return eventPage;
    }


    /**
     * concatene plusieurs commentaires dans une chaine , séparés par une virgule
     */
    private String appendComment(String comment, String label, String newComment) {
        if (!newComment.isEmpty()) {
            if (!comment.isEmpty() && comment.charAt(comment.length()-1)!= '\n') {
                comment += "\n";
            }
            comment += label+": "+newComment;
        }
        return comment;
    }

    /**
     * concatene plusieurs commentaires dans une chaine , séparés par une virgule
     */
    private String appendValue(String... otherValues) {
        StringBuilder sb = new StringBuilder();
        for (String otherValue : otherValues) {
            // j'ajoute les valeurs supplémentaires séparées par des virgules
            if (!otherValue.trim().isEmpty()) {
                // je concantene les valeurs en inserant une virgule dans
                // si la valeur précedente n'est pas vide
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(otherValue.trim());
            }
        }

        return sb.toString();
    }
    
    /**
     * concatene plusieurs commentaires dans une chaine , séparés par une virgule
     */
    private String appendPrefixValue(String prefix, String... otherValues) {
        StringBuilder sb = new StringBuilder();
        for (String otherValue : otherValues) {
            // j'ajoute les valeurs supplémentaires séparées par des virgules
            if (!otherValue.trim().isEmpty()) {
                // je concantene les valeurs en inserant une virgule dans
                // si la valeur précedente n'est pas vide
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(otherValue.trim());
            }
        }
        if (sb.length() > 0 ) {
            sb.insert(0, prefix + " ");
        }

        return sb.toString();
    }



    
    static private PointInTime getYear(PointInTime pit) throws GedcomException {
        PointInTime gregorianPit = pit.getPointInTime(PointInTime.GREGORIAN);
        return new PointInTime(PointInTime.UNKNOWN,PointInTime.UNKNOWN,gregorianPit.getYear());
    }
    static private PointInTime getYear(PointInTime pit, int shiftYear) throws GedcomException {
        PointInTime gregorianPit = pit.getPointInTime(PointInTime.GREGORIAN);
        return new PointInTime(PointInTime.UNKNOWN,PointInTime.UNKNOWN,gregorianPit.getYear()+shiftYear);
    }

    static protected PointInTime getYear(PointInTime refPit, Delta age) throws GedcomException {
        PointInTime gregorianPit = refPit.getPointInTime(PointInTime.GREGORIAN);
        PointInTime pit = new PointInTime(gregorianPit.getDay(), gregorianPit.getMonth(),gregorianPit.getYear());

        pit.add( -age.getDays(), -age.getMonths(), -age.getYears() );

        if ( age.getMonths() ==0 && age.getDays() ==0 ) {
            // si le nombre de mois n'est pas précisé, j'arrondis à l'année
            pit.set(PointInTime.UNKNOWN, PointInTime.UNKNOWN, pit.getYear());
        } else if ( age.getDays() ==0 ) {
            // si le nombre de jours n'est pas précisé, j'arrondis au mois
            pit.set(PointInTime.UNKNOWN, pit.getMonth(), pit.getYear());
        }
        
        return pit;
    }

    /**
     * calcule la date de naissance de l'individu ou de l'epouse
     *  si record=RecordBirth :
     *    retourne la date de naissance de l'individu ou a defaut date de l'evenement (c'est souvent la meme sauf si l'evenement est un bapteme qui a lieu apres la naissance)
     *  si record=RecordMarriage :
     *    retourne la date de naissance de l'individu ou a defaut dateBirth = date de son mariage -  minMarriageYearOld
     *  si record=RecordDeath ou RecordMisc
     *    retourne la date "avant" de la levenement arrondi a l'année. 
     * 
     * @param birthDate date de naissance de l'individu ou de l'epouse
     * @param age       age de l'individu ou de l'epouse
     * @param marriedMarriageDate     date de mariage avec l'ex conjoint (null si pas d'ex conjoint
     * @return
     */
    private PropertyDate calculateBirthDate(PropertyDate recordBirthDate, Delta age, PropertyDate marriedMarriageDate) throws Exception {
        PropertyDate resultDate = new PropertyDate();
        resultDate.setValue(recordBirthDate.getValue());
        if (type == RecordType.Birth) {
            if ( !recordBirthDate.isComparable() ) {
                resultDate.setValue(getEventDate().getValue());
            }
        } else if (type == RecordType.Marriage  
                || (type == RecordType.Misc && (eventTypeTag == EventTypeTag.MARB || eventTypeTag == EventTypeTag.MARC || eventTypeTag == EventTypeTag.MARL) )) {

            if ( ! recordBirthDate.isComparable() &&  record.getEventDateProperty().isComparable()) {
                // la date de naissance n'est pas valide, 
                // je calcule la naissance a partir de l'age ou de la date de mariage
                if (age.getYears() != 0 || age.getMonths() != 0 || age.getDays() != 0 ) {
                    // l'age est valide,
                    if (age.getYears() != 0 && age.getMonths() != 0 && age.getDays() != 0) {
                        // l'age est precis au jour près
                        // date de naissance = date de mariage - age
                        //birthDate.setValue(record.getEventDateProperty().getValue());
                        //birthDate.getStart().add(-age.getDays(), -age.getMonths(), -age.getYears());
                        PointInTime start = null;
                        if ( record.getEventDateProperty().getStart()!= null) {
                            start = new PointInTime();
                            start.set(record.getEventDateProperty().getStart());
                            start.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        PointInTime end = null;
                        if ( record.getEventDateProperty().getEnd()!= null) {
                            end = new PointInTime();
                            end.set(record.getEventDateProperty().getEnd());
                            end.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        resultDate.setValue(record.getEventDateProperty().getFormat(),
                                start, end , "Date naissance = date du releve - age");

                    } else {
                        // l'age n'est pas précis au jour près.
                        // date de naissance = date de mariage (arrondi a l'année) - age (arrondi à l'année)
                        ///birthDate.setValue(String.format("CAL %d", record.getEventDateProperty().getStart().getYear() -age.getYears()));
                        resultDate.setValue(PropertyDate.CALCULATED, getYear(getEventDate().getStart(),age), null, "date naissance=date du releve - age");
                    }
                } else {
                    // l'age n'est pas valide

                    // date de naissance maximale BEF = date de mariage -  minMarriageYearOld
                    //birthDate= calulateDateBeforeMinusShift(record.getEventDateProperty(), MergeQuery.minMarriageYearOld, "date 15 ans avant le mariage");
                    resultDate= calulateDateBeforeMinusShift(getEventDate(), MergeQuery.minMarriageYearOld, "=date mariage -"+ MergeQuery.minMarriageYearOld );
                }
            }
        } else if (type == RecordType.Death) {
            if ( ! recordBirthDate.isComparable() &&  record.getEventDateProperty().isComparable()) {
                // la date de naissance n'est pas valide,
                // je calcule la naissance a partir de l'age
                if (age.getYears() != 0 || age.getMonths() != 0 || age.getDays() != 0 ) {
                    // l'age est valide,
                    if (age.getYears() != 0 && age.getMonths() != 0 && age.getDays() != 0) {
                        // l'age est precis au jour près
                        // date de naissance = date de mariage - age
                        ///birthDate.setValue(record.getEventDateProperty().getValue());
                        ///birthDate.getStart().add(-age.getDays(), -age.getMonths(), -age.getYears());
                        PointInTime start = null;
                        if ( record.getEventDateProperty().getStart()!= null) {
                            start = new PointInTime();
                            start.set(record.getEventDateProperty().getStart());
                            start.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        PointInTime end = null;
                        if ( record.getEventDateProperty().getEnd()!= null) {
                            end = new PointInTime();
                            end.set(record.getEventDateProperty().getEnd());
                            end.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        resultDate.setValue(record.getEventDateProperty().getFormat(),
                                start, end , "Date naissance = date du releve - age");
                    } else {
                        // l'age n'est pas précis au jour près.
                        // date de naissance = date de mariage (arrondi a l'année) - age (arrondi à l'année)
                        ///birthDate.setValue(String.format("CAL %d", record.getEventDateProperty().getStart().getYear() - age.getYears()));
                        resultDate.setValue(PropertyDate.CALCULATED, getYear(getEventDate().getStart(),age), null, "date naissance=date du releve - age");
                        
                    }
                } else {
                    // l'age n'est pas valide
                    if (marriedMarriageDate != null && marriedMarriageDate.isComparable()) {
                        // la naissance est minMarriageYearOld avant le mariage avec l'ex conjoint
                        ///birthDate.setValue(String.format("BEF %d", marriedMarriageDate.getStart().getYear()-MergeQuery.minMarriageYearOld));
                        resultDate.setValue(PropertyDate.BEFORE, getYear(marriedMarriageDate.getStart(),-MergeQuery.minMarriageYearOld), null, "date naissance= avant date de mariage avec ex conjoint -"+ MergeQuery.minMarriageYearOld);
                    } else {
                        // la naissance est avant le deces
                        ///birthDate.setValue(String.format("BEF %d", getEventDate().getStart().getYear()));
                        resultDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "date naissance= avant date du releve");
                    }
                }
            }

        } else {
            // releve divers
            if ( ! recordBirthDate.isComparable() &&  record.getEventDateProperty().isComparable()) {
                // la date de naissance n'est pas valide,
                // je calcule la naissance a partir de l'age
                if (age.getYears() != 0 ) {
                    // l'age est valide,
                    if (age.getYears() != 0 && age.getMonths() != 0 && age.getDays() != 0) {
                        // l'age est precis au jour près
                        // date de naissance = date de mariage - age
                        ///birthDate.setValue(record.getEventDateProperty().getValue());
                        ///birthDate.getStart().add(-age.getDays(), -age.getMonths(), -age.getYears());
                        PointInTime start = null;
                        if ( record.getEventDateProperty().getStart()!= null) {
                            start = new PointInTime();
                            start.set(record.getEventDateProperty().getStart());
                            start.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        PointInTime end = null;
                        if ( record.getEventDateProperty().getEnd()!= null) {
                            end = new PointInTime();
                            end.set(record.getEventDateProperty().getEnd());
                            end.add(-age.getDays(), -age.getMonths(), -age.getYears());
                        }
                        resultDate.setValue(record.getEventDateProperty().getFormat(),
                                start, end , "Date naissance = date du releve - age");
                    } else {
                        // l'age n'est pas précis au jour près.
                        // date de naissance = date de mariage (arrondi a l'année) - age (arrondi à l'année)
                        ///birthDate.setValue(String.format("CAL %d", record.getEventDateProperty().getStart().getYear() - age.getYears()));
                        resultDate.setValue(PropertyDate.CALCULATED, getYear(record.getEventDateProperty().getStart(),age), null, "date naissance=date du releve - age");
                    }
                } else {
                    // l'age n'est pas valide
                    if (marriedMarriageDate != null && marriedMarriageDate.isComparable()) {
                        // la naissance est minMarriageYearOld avant le mariage avec l'ex conjoint
                        ///birthDate.setValue(String.format("BEF %d", marriedMarriageDate.getStart().getYear()-MergeQuery.minMarriageYearOld));
                        resultDate.setValue(PropertyDate.BEFORE, getYear(marriedMarriageDate.getStart(),-MergeQuery.minMarriageYearOld), null, "date naissance= avant date de mariage avec ex conjoint -"+ MergeQuery.minMarriageYearOld);
                    } else {
                        // il n'y a pas d'ex conjoint
                        // la naissance est avant l'evenement qui concerne l'individu
                        ///birthDate.setValue(String.format("BEF %d", record.getEventDateProperty().getStart().getYear()));
                        resultDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "date naissance= avant date du releve");
                    }
                }
            }
        }

        return resultDate;
    }

/**
     * calcule la date de deces de l'individu ou de l'epouse
     *  si record=RecordBirth :
     *    retourne "apres" la date de l'évènement arrondie à l'année.
     *  si record=RecordMarriage :
     *    retourne "apres" la date de mariage l'évènement arrondie à l'année
     *  si record=RecordDeath 
     *    retourne la date l'évènement.
     *  si record=RecordMisc
     *    retourne la date "a partir de" la date de l'évènement arrondie a l'année. 
     * 
     * @param birthDate date de naissance de l'individu ou de l'épouse
     * @param age       age de l'individu ou de l'épouse
     * @param marriedMarriageDate     date de mariage avec l'ex conjoint (null si pas d'ex conjoint
     * @return
     */
    private PropertyDate calculateDeathDate() throws Exception {
        PropertyDate resultDate = new PropertyDate();
        if (type == RecordType.Death) {
            resultDate.setValue(getEventDate().getValue());
        } else {            
            resultDate.setValue(PropertyDate.FROM, getYear(getEventDate().getStart()), null, "date décès= à partir de la date de l'évènement");            
        }

        return resultDate;
    }

    
    /**
     * calcule la date de naissance d'un parent a partir 
     *  - soit de l'age du parent s'il est precisé dans le releve
     *  - soit de la date de naissance de l'enfant si elle est precisée dans le relevé
     *  - soit de la date de mariage de l'enfant (seulement pour le releve de mariage)
     * @param requiredBirth
     * @return
     */
    private PropertyDate calculateParentBirthDate(Delta parentAge, PropertyDate childBirthDate) throws Exception {

        PropertyDate parentBirthDate = new PropertyDate();
        // j'initialise la date de naissance du parent avec la date de naissance de l'individu
        // puis je la decrementerai en fonction des autres donnees du releve.
        ///parentBirthDate.setValue(childBirthDate.getValue());

        if ( parentAge.getYears()!=0 && record.getEventDateProperty().isComparable() ) {
            // l'age du parent est valide
            // parentBirthDate = eventDate - parentAge
            if( parentAge.getYears()!=0 && parentAge.getMonths()!=0 &&  parentAge.getDays()!=0) {
                //l'age est precis au jour pres.
                ///parentBirthDate.getStart().add(-parentAge.getDays(), -parentAge.getMonths(), -parentAge.getYears());
                PointInTime start = null;
                if ( getEventDate().getStart()!= null) {
                    start = new PointInTime();
                    start.set(getEventDate().getStart());
                    start.add(-parentAge.getDays(), -parentAge.getMonths(), -parentAge.getYears());
                }
                PointInTime end = null;
                if ( getEventDate().getEnd()!= null) {
                    end = new PointInTime();
                    end.set(getEventDate().getEnd());
                    end.add(-parentAge.getDays(), -parentAge.getMonths(), -parentAge.getYears());
                }
                parentBirthDate.setValue(getEventDate().getFormat(),
                        start, end , "Date naissance = date du releve - age");
            } else {
                // date arrondie à l'année car l'age n'est pas précis au jour près.
                ///parentBirthDate.setValue(String.format("CAL %d", parentBirthDate.getStart().add(0, 0, -parentAge.getYears()).getYear() ));
                parentBirthDate.setValue(PropertyDate.CALCULATED, getYear(getEventDate().getStart(),parentAge), null, "date naissance=date du releve - age");
            }
        } else {
            // l'age du parent n'est pas valide
            // je calcule la date maximale à partir de la date de mariage ou la date de naissance de l'enfant
            if ( childBirthDate.isComparable()) {
                // le parent doit être né  "minParentYearOld" années avant la naissance de l'individu
                // parentBirthDate = eventDat - minParentYearOld
                ///parentBirthDate.setValue(String.format("BEF %d", parentBirthDate.getStart().add(0, 0, -MergeQuery.minParentYearOld).getYear()));
                parentBirthDate.setValue(PropertyDate.BEFORE, getYear(childBirthDate.getStart(),-MergeQuery.minParentYearOld), null, "date naissance= avant naissance de l'enfant -"+MergeQuery.minParentYearOld);
            } else {
                // je ne sais pas calculer la date de naissance
                parentBirthDate = new PropertyDate();
            }
        }

        return parentBirthDate;
    }

    /**
     * calcule la date de deces d'un parent a partir de la date du relevé et
     * la date de naissance d'un enfant.
     *  
     * enfant
     * @param record
     * @param dead      true si le parent est decede avant la date de l'evenement
     * @param childBirthDate date de naissance de l'enfant
     * @param monthBeforeBirth nombre de mois du deces avant la naissance de l'enfant (9 mois pour le pere, 0 mois pour la mere)
     * @return date de deces du parent
     */
    private PropertyDate calculateParentDeathDate(FieldDead.DeadState dead, PropertyDate childBirthDate, int monthBeforeBirth) throws Exception {

        PropertyDate parentDeathDate = new PropertyDate();

        if (getEventDate().isComparable()) {
            // la naissance de l'individu n'est utilisable pour determiner la date de minimale de deces du pere
            // que si c'est une date exacte ou une date avec une borne inferieure
            // J'ignore la date si c'est une date maximale (BEF) ou estimée
            PointInTime childBirthPit = new PointInTime();
            boolean birthDateUsefull;
            if (childBirthDate.isValid()) {
                if (childBirthDate.getFormat() == PropertyDate.DATE
                        || childBirthDate.getFormat() == PropertyDate.AFTER
                        || childBirthDate.getFormat() == PropertyDate.BETWEEN_AND
                        || childBirthDate.getFormat() == PropertyDate.CALCULATED) {
                    birthDateUsefull = true;
                    childBirthPit.set(childBirthDate.getStart());
                    childBirthPit.add(0, -monthBeforeBirth, 0);
                } else {
                    birthDateUsefull = false;
                }
            } else {
                birthDateUsefull = false;
            }

            if (dead == FieldDead.DeadState.dead) {
                if (birthDateUsefull) {
                    // le parent est decede entre la date de naissance et la date du releve
                    // parentDeathDate.setValue(String.format("BEF %d", getEventDate().getStart().getYear()));
                    // parentDeathDate.setValue(String.format("BET %d AND %d", birthPit.getYear(), getEventDate().getStart().getYear()));
                    if (monthBeforeBirth == 0) {
                        parentDeathDate.setValue(PropertyDate.BETWEEN_AND, getYear(childBirthPit), getYear(getEventDate().getStart()), "date deces= entre la naissance de l'enfant et la date du releve");
                    } else {
                        parentDeathDate.setValue(PropertyDate.BETWEEN_AND, getYear(childBirthPit), getYear(getEventDate().getStart()), "date deces= entre la conceptionde l'enfant et la date du releve");
                    }
                } else {
                    // le parent est decede avant la date du releve
                    parentDeathDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "date deces= avant la date du releve");
                }
            } else if (dead == FieldDead.DeadState.alive) {
                // le parent est decede apres la date du releve
                parentDeathDate.setValue(PropertyDate.AFTER, getYear(getEventDate().getStart()), null, "date deces= apres la date du releve");
            } else {
                if (birthDateUsefull) {
                    // le parent est decede apres la naissance 
                    parentDeathDate.setValue(String.format("AFT %d", childBirthPit.getYear()));
                    if (monthBeforeBirth == 0) {
                        parentDeathDate.setValue(PropertyDate.AFTER, getYear(childBirthPit), null, "date deces= apres la naissance de l'enfant");
                    } else {
                        parentDeathDate.setValue(PropertyDate.AFTER, getYear(childBirthPit), null, "date deces= apres la conception de l'enfant");
                    }
                } else {
                    // date inconnue, rien a faire
                }
            }
        } else {
            // date inconnue, rien a faire
        }

        return parentDeathDate;
    }

    /**
     * calcule la date de mariage des parents
     * @param record
     * @return
     */
    private PropertyDate calculateParentMariageDate(PropertyDate childBirthDate ) throws Exception {
        PropertyDate parentMariageDate;
        if (record instanceof RecordMarriage) {
            // le mariage des parents est
            //   - avant la naissance de l'epoux
            //   - avant le mariage du marié dminué de minParentYearOld
            // date de mariage = BEF date de naissance de l'epoux
            PropertyDate beforeChildBirth = calulateDateBeforeMinusShift(childBirthDate, 0, "Date mariage= avant naissance époux(se)");
            // date de mariage = EventDate - minMarriageYearOld
            PropertyDate beforeChildMarriage = calulateDateBeforeMinusShift(getEventDate(), MergeQuery.minMarriageYearOld, "Date mariage = dete du releve -"+MergeQuery.minMarriageYearOld);
            // je retiens la date la plus petite
            boolean beforeChildBirthComparable = beforeChildBirth.isComparable();
            boolean beforeChildMarriageComparable = beforeChildMarriage.isComparable();
            if (beforeChildBirthComparable && beforeChildMarriageComparable) {
                // les dates sont comparables , je retiens la plus petite
                if (MergeQuery.getMostAccurateDate(beforeChildBirth, beforeChildMarriage) == beforeChildBirth) {
                    parentMariageDate = beforeChildBirth;
                } else {
                    parentMariageDate = beforeChildMarriage;
                }
            } else if (beforeChildBirthComparable) {
                parentMariageDate = beforeChildBirth;
            } else {
                parentMariageDate = beforeChildMarriage;
            }
        } else {
            // le mariage des parents est avant la naissance de l'enfant arrondie a l'année
            parentMariageDate = calulateDateBeforeMinusShift(childBirthDate, 0, "Date mariage = avant la naissance de l'enfant");
        }
        return parentMariageDate;
    }

    /**
     * calcule la date de mariage a partir de la date du contrat de mariage
     *
     * @param record
     * @return
     */
    protected PropertyDate calculateMariageDateFromMarc(PropertyDate marcDate) {

        PropertyDate marriageDate = new PropertyDate();
        if (marcDate.getFormat() == PropertyDate.DATE) {
            try {
                marriageDate.setValue(PropertyDate.ABOUT, getYear(marcDate.getStart(), 0), null, "date mariage=environ la date du contrat ");
            } catch (GedcomException ex) {
                // j'ignore l'eeereur et je retourne une date de mariage nulle
            }
        }
        return marriageDate;

    }


    /**
     * retourne une date arrondie à l'année immediatement inferieure ou egale
     * a la date donnée, dimminée de yearShift
     * @param birthDate
     * @param yearShift
     * @return
     */
    static private PropertyDate calulateDateBeforeMinusShift(PropertyDate birthDate, int yearShift, String phrase) throws GedcomException {
        // le mariage des parents est avant la naissance de l'enfant arrondie a l'année
        PropertyDate marriageDate = new PropertyDate();
        if (birthDate.getFormat() == PropertyDate.BETWEEN_AND || birthDate.getFormat() == PropertyDate.FROM_TO) {
            //marriageDate.setValue(String.format("BEF %d", birthDate.getEnd().getYear() - yearShift));
            marriageDate.setValue(PropertyDate.BEFORE, getYear(birthDate.getEnd(), -yearShift), null, phrase);
        } else if (birthDate.getFormat() == PropertyDate.FROM || birthDate.getFormat() == PropertyDate.AFTER) {
            // Unknwon
            marriageDate.setValue("");
        } else if (birthDate.getFormat() == PropertyDate.TO || birthDate.getFormat() == PropertyDate.BEFORE) {
            //marriageDate.setValue(String.format("BEF %d", birthDate.getStart().getYear() - yearShift));
            marriageDate.setValue(PropertyDate.BEFORE, getYear(birthDate.getStart(), -yearShift), null, phrase);
        } else if (birthDate.getFormat() == PropertyDate.ABOUT || birthDate.getFormat() == PropertyDate.ESTIMATED) {
            //marriageDate.setValue(String.format("BEF %d", birthDate.getStart().getYear() - yearShift));
            marriageDate.setValue(PropertyDate.BEFORE, getYear(birthDate.getStart(), -yearShift), null, phrase);
        } else {
            //marriageDate.setValue(String.format("BEF %d", birthDate.getStart().getYear() - yearShift));
            marriageDate.setValue(PropertyDate.BEFORE, getYear(birthDate.getStart(), -yearShift), null, phrase);
        }
        return marriageDate;
    }

    /**
     *
     */
    protected class MergeParticipant  {

        MergeParticipantType participantType;
        Record.Participant participant;

        // memorise les dates calculees (pour éviter de les recalculer a chaque consultation)
        private PropertyDate BirthDate = null;
        private PropertyDate DeathDate = null;
        private PropertyDate FatherBirthDate = null;
        private PropertyDate FatherDeathDate = null;
        private PropertyDate MotherBirthDate = null;
        private PropertyDate MotherDeathDate = null;
        private PropertyDate ParentMarriageDate = null;
        private PropertyDate MarriedBirthDate = null;
        private PropertyDate MarriedDeathDate = null;
        private PropertyDate MarriedMarriageDate = null;

        MergeParticipant ( MergeParticipantType participantType, Record.Participant participant) {
            this.participantType = participantType;
            this.participant = participant;
        }

        String getFirstName() {
            return participant.getFirstName().toString();
        }

        String getLastName() {
            return participant.getLastName().toString();
        }

        int getSex() {
            return participant.getSex().getSex();
        }

        String getSexString() {
            return participant.getSex().getValue();
        }

        PropertyDate getBirthDate() throws Exception {
            if (BirthDate == null) {
                BirthDate = calculateBirthDate(
                        participant.getBirthDate() != null ? participant.getBirthDate().getPropertyDate() : new PropertyDate(),
                        participant.getAge() != null ? participant.getAge().getDelta() : new Delta(0, 0, 0),
                        getMarriedMarriageDate());
            }
            return BirthDate;
        }

        PropertyDate getDeathDate() throws Exception {
            if (DeathDate == null) {
                DeathDate = calculateDeathDate();                
            }
            return DeathDate;
        }

        /**
         * retourne le lieu de naissance .
         * Si c'est un acte de naissance et si IndiBirthPlace est vide
         *      retourne IndiFatherResidence ou, à défaut, EventPlace
         * @return
         */
        String getBirthPlace() {
            if (participant.getBirthPlace() != null && !participant.getBirthPlace().isEmpty()) {
                return participant.getBirthPlace().toString();
            } else {
                if ( type == RecordType.Birth) {
                    if (participant.getResidence() != null && !participant.getResidence().isEmpty()) {
                        return participant.getResidence().toString();
                    } else {
                        if (participantType == MergeParticipantType.participant1) {
                            if (participant.getFatherResidence() != null && !participant.getFatherResidence().isEmpty()) {
                                return participant.getFatherResidence().toString();
                            } else {
                                return recordsInfoPlace.toString();
                            }
                        } else {
                            return "";
                        }
                    }
                } else {
                    return "";
                }
            }
        }

        /**
         * retourne le lieu de naissance .
         * Si c'est un acte de naissance et si IndiBirthPlace est vide
         *      retourne IndiFatherResidence ou, à défaut, EventPlace
         * @return
         */
        String getDeathPlace() {
                if (participant.getResidence() != null && !participant.getResidence().isEmpty()) {
                    return participant.getResidence().toString();
                } else {
                    return recordsInfoPlace.toString();
                }
        }
        
        String getOccupation() {
            if (participant.getOccupation() != null) {
                return participant.getOccupation().toString();
            } else {
                // n'est pas renseignée pour les naissances
                return null;
            }
        }

        String getOccupationWithDate() {
            String occupation = participant.getOccupation().toString();
            if (!participant.getResidence().isEmpty()) {
                if (!occupation.isEmpty()) {
                    occupation += ", ";
                }
                occupation += participant.getResidence().toString();
            }
            if (!occupation.isEmpty()) {
                occupation += " (" + getEventDate().getDisplayValue() + ")";
            }

            return occupation;
        }

        String getResidence() {
            if (participant.getResidence() != null) {
                return participant.getResidence().toString();
            } else {
                // n'est pas renseignée pour les naissances
                return "";
            }
        }

        //  conjoint (ou ancien conjoint) //////////////////////////////////////////
        String getMarriedFirstName() {
            return participant.getMarriedFirstName().toString();
        }

        String getMarriedLastName() {
            return participant.getMarriedLastName().toString();
        }

        PropertyDate getMarriedBirthDate() throws Exception {
            if (MarriedBirthDate == null) {
                MarriedBirthDate = new PropertyDate();
                if (participant.getMarriedFirstName() != null
                        && participant.getMarriedLastName() != null
                        && (!participant.getMarriedFirstName().isEmpty() || !participant.getMarriedLastName().isEmpty())
                        && getMarriedMarriageDate().isComparable()) {
                    // l'ex conjoint existe , la naissance est minMarriageYearOld le mariage avec l'individu
                    MarriedBirthDate.setValue(PropertyDate.BEFORE, getYear(getMarriedMarriageDate().getStart(), -MergeQuery.minMarriageYearOld), null, "naissance avant la date du mariage -" + MergeQuery.minMarriageYearOld);
                }
            }
            return MarriedBirthDate;
        }

        PropertyDate getMarriedMarriageDate() throws Exception {
            if (MarriedMarriageDate == null) {
                MarriedMarriageDate = new PropertyDate();
                if (participant.getMarriedFirstName() != null
                        && participant.getMarriedLastName() != null
                        && (!participant.getMarriedFirstName().isEmpty() || !participant.getMarriedLastName().isEmpty())
                        && getEventDate().isComparable()) {
                    // l'ex conjoint existe , le mariage avec l'individu est avant la date l'evenement
                    MarriedMarriageDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "mariage avant la date du relevé");
                } else if (participantType == MergeParticipantType.participant1 && getType() == RecordType.Death) {
                    // la date du mariage est avant le deces
                    // non car il n'est pas forcément marié
                    //MarriedMarriageDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "mariage avant la date du relevé");
                }
            }
            return MarriedMarriageDate;
        }

        PropertyDate getMarriedDeathDate() throws Exception {
            if (MarriedDeathDate == null) {
                MarriedDeathDate = new PropertyDate();

                switch (participant.getMarriedDead().getState()) {
                    case dead:
                        MarriedDeathDate.setValue(PropertyDate.BEFORE, getYear(getEventDate().getStart()), null, "deces avant la date du relevé");
                        break;
                    case alive:
                        MarriedDeathDate.setValue(PropertyDate.AFTER, getYear(getEventDate().getStart()), null, "deces apres la date du relevé");
                        break;
                    default:
                        // je ne sais pas
                        break;
                }
            }
            return MarriedDeathDate;
        }

        String getMarriedOccupation() {
            return participant.getMarriedOccupation().toString();
        }

        String getMarriedOccupationWithDate() {
            String occupation = participant.getMarriedOccupation().toString();
            if (!participant.getMarriedResidence().isEmpty()) {
                if (!occupation.isEmpty()) {
                    occupation += ", ";
                }
                occupation += participant.getMarriedResidence().toString();
            }
            if (!occupation.isEmpty()) {
                occupation += " (" + getEventDate().getDisplayValue() + ")";
            }
            return occupation;
        }

        String getMarriedResidence() {
            return participant.getMarriedResidence().toString();
        }

        //  indi father ////////////////////////////////////////////////////////////
        PropertyDate getParentMarriageDate() throws Exception {
            if (ParentMarriageDate == null) {
                ParentMarriageDate = calculateParentMariageDate(getBirthDate());
            }
            return ParentMarriageDate;
        }

        String getFatherFirstName() {
            return participant.getFatherFirstName().toString();
        }

        String getFatherLastName() {
            return participant.getFatherLastName().toString();
        }

        PropertyDate getFatherBirthDate() throws Exception {
            if (FatherBirthDate == null) {
                FatherBirthDate = calculateParentBirthDate(participant.getFatherAge().getDelta(), getBirthDate());
            }
            return FatherBirthDate;
        }

        PropertyDate getFatherDeathDate() throws Exception {
            if (FatherDeathDate == null) {
                FatherDeathDate = calculateParentDeathDate(
                        participant.getFatherDead() != null ? participant.getFatherDead().getState() : FieldDead.DeadState.unknown,
                        getBirthDate(),
                        9 // le pere peut être decede au plus tot apres la conception, soit 9 mois avant la naissance
                        );
            }
            return FatherDeathDate;
        }

        String getFatherOccupation() {
            return participant.getFatherOccupation().toString();
        }

        String getFatherResidence() {
            return participant.getFatherResidence().toString();
        }

        String getFatherOccupationWithDate() {
            String occupation = participant.getFatherOccupation().toString();
            if (!participant.getFatherResidence().isEmpty()) {
                if (!occupation.isEmpty()) {
                    occupation += ", ";
                }
                occupation += participant.getFatherResidence().toString();
            }
            if (!occupation.isEmpty()) {
                occupation += " (" + getEventDate().getDisplayValue() + ")";
            }
            return occupation;
        }

        String getMotherFirstName() {
            if (participant.getMotherFirstName() == null) {
                return "";
            } else {
                return participant.getMotherFirstName().toString();
            }
        }

        String getMotherLastName() {
            if (participant.getMotherLastName() == null) {
                return "";
            } else {
                return participant.getMotherLastName().toString();
            }
        }

        PropertyDate getMotherBirthDate() throws Exception {
            if (MotherBirthDate == null) {
                MotherBirthDate = calculateParentBirthDate(participant.getMotherAge().getDelta(), getBirthDate());
            }
            return MotherBirthDate;
        }

        PropertyDate getMotherDeathDate() throws Exception {
            if (MotherDeathDate == null) {
                MotherDeathDate = calculateParentDeathDate(
                        participant.getMotherDead() != null ? participant.getMotherDead().getState() : FieldDead.DeadState.unknown,
                        getBirthDate(),
                        0 // le mere peut être decedee au plus tot 0 mois avant le naissance (par opposition au pere qui peut etre decede 9 mois avant la naissance)
                        );
            }
            return MotherDeathDate;
        }

        String getMotherOccupation() {
            if (participant.getMotherOccupation() == null) {
                return "";
            } else {
                return participant.getMotherOccupation().toString();
            }
        }

        String getMotherOccupationWithDate() {
            String occupation = participant.getMotherOccupation().toString();
            if (!participant.getMotherResidence().isEmpty()) {
                if (!occupation.isEmpty()) {
                    occupation += ", ";
                }
                occupation += participant.getMotherResidence().toString();
            }
            if (!occupation.isEmpty()) {
                occupation += " (" + getEventDate().getDisplayValue() + ")";
            }

            return occupation;
        }

        String getMotherResidence() {
            return participant.getMotherResidence().toString();
        }

    }
}
