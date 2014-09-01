package ancestris.modules.releve.model;

import ancestris.modules.releve.ConfigPanel;
import ancestris.modules.releve.file.FileBuffer;
import genj.gedcom.Gedcom;

/**
 *
 * @author Michel
 */
public class DataManager {

    private ModelBirth releveBirthModel = new ModelBirth();
    private ModelMarriage releveMarriageModel = new ModelMarriage();
    private ModelDeath releveDeathModel  = new ModelDeath();
    private ModelMisc releveMiscModel = new ModelMisc();
    private ModelAll   releveAllModel = new ModelAll();
    private CompletionProvider completionProvider  = new CompletionProvider();
    private ConfigPanel configPanel = null;

    //donnés de configuration
    boolean duplicateControlEnabled = true;
    
    // données volatiles
    private int lastRecordNo = 0;
    private String freeComment = "";

    // previous record

    public enum ModelType { birth, marriage, death, misc, all }

    public DataManager (ConfigPanel configPanel) {
        this.configPanel = configPanel;
    }

    /**
     * indique si les données ont été modifiées depuis la dernière sauvegarde
     * @return
     */
    public boolean isDirty() {
        boolean result = false;
        result |= releveBirthModel.isDirty();
        result |= releveMarriageModel.isDirty();
        result |= releveDeathModel.isDirty();
        result |= releveMiscModel.isDirty();
        result |= releveAllModel.isDirty();
        result |= configPanel.isDirty();
        return result;
    }

     /**
     * reinitialise l'indicateur des modifications
     * @return
     */
    public void resetDirty() {
        releveBirthModel.resetDirty();
        releveMarriageModel.resetDirty();
        releveDeathModel.resetDirty();
        releveMiscModel.resetDirty();
        releveAllModel.resetDirty();
        configPanel.resetDirty();
    }

    public int createRecord( ModelAbstract model) {
        Record record = model.createRecord();

        if (getCopyFreeCommentEnabled() ) {
            // je valorise le numero de photo avec la valeur par defaut
            String defaultValue = this.getDefaultFreeComment();
            record.setFreeComment(defaultValue);
        }
        
        return addRecord(record, true);
    }

    /**
     * ajoute un nouveau releve
     * le numero de releve est calculé automatiquement
     * @param record
     * @return
     */
    public int addRecord(Record record, boolean updateGui) {
        int recordIndex = 0;

        // j'ajoute le releve dans le modele correspondant
        if (record instanceof RecordBirth) {
            recordIndex = releveBirthModel.addRecord(record, updateGui);
        } else  if (record instanceof RecordMarriage) {
            recordIndex = releveMarriageModel.addRecord(record, updateGui);
        } else  if (record instanceof RecordDeath) {
            recordIndex = releveDeathModel.addRecord(record, updateGui);
        } else  if (record instanceof RecordMisc) {
            recordIndex = releveMiscModel.addRecord(record, updateGui);
        }

        releveAllModel.addRecord(record, updateGui);

        // j'attribue un numero au releve s'il n'est pas déjà renseigné
        if ( record.recordNo == 0 )  {
            record.recordNo = getNextRecordNo();
        }  else {
            // le numéro est déjà rensigné.
            // je mémorise ce numéro s'il est plus gand que ceux des relevés déjà
            // présents.
            if ( record.recordNo > lastRecordNo ) {
                setNextRecordNo(record.recordNo);
            }
        }
        //
        getCompletionProvider().addRecord(record);
        return recordIndex;
    }
   

    /**
     * ajouter les releves 
     * @param fileBuffer  buffer contenant les releves a ajouter
     * @param append  si false , vide les modeles avant d'ajouter les nouveaux relevés
     * @param defaultPlace lieu par defaut des releves
     * @param forceDefaultPlace 1=remplace les lieux des releves par le lieu par défaut, 0=n'ajoute que les releves dont le lieu est le lieu par defaut
     */
    public void addRecords(FileBuffer fileBuffer, boolean append, String defaultPlace, int forceDefaultPlace) {
        if (!append ) {
            removeAll();
        }

        // je recupere le nombre de lignes
        int firstRowBirth = releveBirthModel.getRowCount();
        int firstRowMarriage = releveMarriageModel.getRowCount();
        int firstRowDeath = releveDeathModel.getRowCount();
        int firstRowMisc = releveMiscModel.getRowCount();
        int firstRowAll = releveAllModel.getRowCount();

        // j'ajoute les releves
        for (Record record : fileBuffer.getRecords()) {
            FieldPlace fieldPlace = record.getEventPlace();
            String place = fieldPlace.getValue();
            if (!place.equals(defaultPlace)) {
                if (forceDefaultPlace == 1) {
                    // je supprime le lieu pour gagner de la place en mémoire
                    record.eventPlace = null;
                    this.addRecord(record, false);
                } else {
                    // j'ignore le releve
                }
            } else {
                // je supprime le lieu pour gagner de la place en mémoire
                record.eventPlace = null;
                this.addRecord(record, false);
            }
        }

        // si des lignes ont été ajoutées , je previens les listeners
        if (releveBirthModel.getRowCount() > firstRowBirth) {
            releveBirthModel.fireTableRowsInserted(firstRowBirth, releveBirthModel.getRowCount() - 1);
        }
        if (releveMarriageModel.getRowCount() > firstRowMarriage) {
            releveMarriageModel.fireTableRowsInserted(firstRowMarriage, releveMarriageModel.getRowCount() - 1);
        }
        if (releveDeathModel.getRowCount() > firstRowDeath) {
            releveDeathModel.fireTableRowsInserted(firstRowDeath, releveDeathModel.getRowCount() - 1);
        }
        if (releveMiscModel.getRowCount() > firstRowMisc) {
            releveMiscModel.fireTableRowsInserted(firstRowMisc, releveMiscModel.getRowCount() - 1);
        }
        if (releveAllModel.getRowCount() > firstRowAll) {
            releveAllModel.fireTableRowsInserted(firstRowMisc, releveAllModel.getRowCount() - 1);
        }

        // RAZ de l'etat du modele
        if (!append) {
            resetDirty();
        }
    }

    public void removeRecord(Record record) {

        // je libere le numero de record si c'est le dernier
        setPreviousRecordNo(record.recordNo);
        getCompletionProvider().removeRecord(record);

        if (record instanceof RecordBirth) {
            releveBirthModel.removeRecord(record);
        } else  if (record instanceof RecordMarriage) {
            releveMarriageModel.removeRecord(record);
        } else  if (record instanceof RecordDeath) {
            releveDeathModel.removeRecord(record);
        } else  if (record instanceof RecordMisc) {
            releveMiscModel.removeRecord(record);
        }

        releveAllModel.removeRecord(record);
    }

    public void removeAll() {
        getCompletionProvider().removeAll();
        releveBirthModel.removeAll();
        releveMarriageModel.removeAll();
        releveDeathModel.removeAll();
        releveMiscModel.removeAll();
        releveAllModel.removeAll();
        lastRecordNo = 0;
        resetDirty();
    }

    public void addGedcomCompletion ( Gedcom gedcom) {
        completionProvider.addGedcomCompletion(gedcom);
    }

    public void removeGedcomCompletion ( Gedcom gedcom) {
        completionProvider.removeGedcomCompletion(gedcom);
    }


    ///////////////////////////////////////////////////////////////////////////
    // accesseurs aux options
    ///////////////////////////////////////////////////////////////////////////

    public boolean getDuplicateControlEnabled() {
        return configPanel.getDuplicateControl();
    }

    public boolean getCopyFreeCommentEnabled() {
        return configPanel.getCopyFreeComment();
    }

    public boolean getNewValueControlEnabled() {
        return configPanel.getNewValueControlEnabled();
    }

    ///////////////////////////////////////////////////////////////////////////
    // accesseurs numero de page
    ///////////////////////////////////////////////////////////////////////////

    public String getDefaultFreeComment() {
        return freeComment;
    }

    public void setDefaultFreeComment(String text) {
        freeComment = text;
    }

    ///////////////////////////////////////////////////////////////////////////
    // accesseurs aux modeles
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @return the releveBirthModel
     */
    public ModelAbstract getModel( ModelType modelType) {
        switch (modelType) {
            case birth:
                return releveBirthModel;
            case marriage:
                return releveMarriageModel;
            case death:
                return releveDeathModel;
            case misc:
                return releveMiscModel;
            case all:
                return releveAllModel;
            default:
                return null;
        }

    }

    /**
     * @return the releveBirthModel
     */
    public ModelBirth getReleveBirthModel() {
        return releveBirthModel;
    }

    /**
     * @return the releveMarriageModel
     */
    public ModelMarriage getReleveMarriageModel() {
        return releveMarriageModel;
    }

    /**
     * @return the releveDeathModel
     */
    public ModelDeath getReleveDeathModel() {
        return releveDeathModel;
    }

    /**
     * @return the releveMiscModel
     */
    public ModelMisc getReleveMiscModel() {
        return releveMiscModel;
    }

    /**
     * @return the releveMiscModel
     */
    public ModelAll getReleveAllModel() {
        return releveAllModel;
    }

    public CompletionProvider getCompletionProvider() {
        return completionProvider;
    }

    ///////////////////////////////////////////////////////////////////////////
    //gestion du numero de dernier releve
    ///////////////////////////////////////////////////////////////////////////

    /**
     * incremente puis retourne le numero de releve
     * @return
     */
    protected int getNextRecordNo() {
        return ++lastRecordNo;
    }

    /**
     * libere le numero de record si c'est le dernier
     * @param recordNo
     */
    public void setPreviousRecordNo(int recordNo) {
        if ( recordNo == lastRecordNo) {
            lastRecordNo--;
        }
    }

    /**
     * impose le numero de dernier releve s'il est superieur a la valeur actuelle
     * @param recordNo
     */
    void setNextRecordNo(int recordNo) {
        if ( recordNo > lastRecordNo) {
            lastRecordNo = recordNo;
        }
    }

}