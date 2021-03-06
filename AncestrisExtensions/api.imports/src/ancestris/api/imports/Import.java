/**
 * Reports are Freeware Code Snippets
 *
 * This report is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.
 *
 * sur une base de gedrepohr.pl (Patrick TEXIER) pour la correction des REPO Le
 * reste des traitements par Daniel ANDRE
 */
package ancestris.api.imports;

import ancestris.modules.console.Console;
import genj.gedcom.Gedcom;
import genj.gedcom.TagPath;
import genj.io.GedcomEncodingSniffer;
import genj.io.PropertyReader;
import java.io.BufferedInputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;

/**
 * The import function foreign gedcom. This abstract class must be used to
 * create a gecdcom importer whic can then be proposed in the Inport wizard.
 * <p>
 * The import process has two main steps:
 * <ul><li/>First step is some sot of filter which process the input file and
 * produce another text file in a tmp directory. This filter process is done in
 * two passes: analyzing and writing.
 * <li/>Then this file is opened as an ordinary gedcom file in ancestris memory
 * and corrected to make the whole gedcom data as compliant as possible.
 * </ul>
 * <p/>
 * Note: The file produced in filter step is deleted by the caller to force the
 * user to save the new gedcom as a new file.
 *
 */
public abstract class Import {

    protected static final String typerepo = "REPO"; // Debut de la cle REPO dans le
    // gedcom
    protected static String EOL = System.getProperty("line.separator");
    // static Pattern pattern = Pattern.compile("^1 REPO (.*)");
    protected static final String INDI_TAG_YES = "BIRT|CHR|" + "DEAT|BURI|CREM|"
            + "ADOP|BAPM|BARM|BASM|BLES|CHRA|CONF|FCOM|ORDN|NATU|EMIG|IMMI|"
            + "CENS|PROB|WILL|" + "GRAD|RETI|";
    protected static final String FAM_TAG_YES = "ANUL|CENS|DIV|DIVF|"
            + "ENGA|MARR|MARB|MARC|" + "MARL|MARS";
    protected static String GEDCOM_VERSION = "";
    protected static final String GEDCOM55_TAG = "ABBR|ADDR|ADR1|ADR2|ADOP|AFN|AGE|AGNC|ALIA|ANCE|ANCI|ANUL|ASSO|AUTH|"
            + "BAPL|BAPM|BARM|BASM|BIRT|BLES|BLOB|BURI|CALN|CAST|CAUS|CENS|CHAN|CHAR|"
            + "CHIL|CHR|CHRA|CITY|CONC|CONF|CONL|CONT|COPR|CORP|CREM|CTRY|DATA|DATE|"
            + "DEAT|DESC|DESI|DEST|DIV|DIVF|DSCR|EDUC|EMIG|ENDL|ENGA|EVEN|FAM|FAMC|"
            + "FAMF|FAMS|FCOM|FILE|FORM|GEDC|GIVN|GRAD|HEAD|HUSB|IDNO|IMMI|INDI|LANG|"
            + "LEGA|MARB|MARC|MARL|MARR|MARS|MEDI|NAME|NATI|NATU|NCHI|NICK|NMR|NOTE|"
            + "NPFX|NSFX|OBJE|OCCU|ORDI|ORDN|PAGE|PEDI|PHON|PLAC|POST|PROB|PROP|PUBL|"
            + "QUAY|REFN|RELA|RELI|REPO|RESI|RESN|RETI|RFN|RIN|ROLE|SEX|SLGC|SLGS|"
            + "SOUR|SPFX|SSN|STAE|STAT|SUBM|SUBN|SURN|TEMP|TEXT|TIME|TITL|TRLR|TYPE|"
            + "VERS|WIFE|WILL";
    protected static final String GEDCOM551_TAG = "|EMAIL|FAX|FACT|FONE|ROMN|WWW|MAP|LATI|LONG|";
    protected static Pattern tag_y = Pattern.compile("(" + INDI_TAG_YES + FAM_TAG_YES
            + ")");
    protected static Pattern tag55_valid = Pattern.compile("(" + GEDCOM55_TAG + ")");
    protected static Pattern tag551_valid = Pattern.compile("(" + GEDCOM55_TAG + GEDCOM551_TAG + ")");
    protected static Pattern gedcom_line = Pattern.compile("^(\\d) (_*\\w+)(.*)");
    private static HashMap<String, ImportIndi> hashIndis;
    private static HashMap<String, ImportFam> hashFams;
    /**
     * our files
     */
    protected GedcomFileReader input;
    protected GedcomFileWriter output;
    protected Console console;

    // protected boolean handleYesTag = true;
    // protected boolean handleInvalidTag = true;
    // protected boolean handleMissingEntities = true;
    /**
     * Constructor
     */
    public Import() {
    }

    /**
     * Gives back output file name
     */
    protected abstract String getImportComment();

    public void setTabName(String tabName) {
        console = new Console(tabName);
    }

    /**
     * First import step as a file filter.
     *
     * @param fileIn Gedcom to import
     * @param fileOut Temporary Gedcom fle created
     * @return true if conversion is successful
     */
    public boolean run(File fileIn, File fileOut) {
        hashIndis = new HashMap<String, ImportIndi>();
        hashFams = new HashMap<String, ImportFam>();
        // Read pass. No writing is made
        try {
            EOL = getEOL(fileIn);
            input = GedcomFileReader.create(fileIn);
            try {
                /*
                 * readLine is a bit quirky : it returns the content of a line
                 * MINUS the newline. it returns null only for the END of the
                 * stream. it returns an empty String if two newlines appear in
                 * a row.
                 */
                while ((input.getNextLine(true)) != null) {
                    firstPass();
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "file.not.found", fileIn.getName()));
            //Exceptions.printStackTrace(e1);
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "file.read.error", fileIn.getName()));
            //Exceptions.printStackTrace(ex);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "error.unknown"));
            //Exceptions.printStackTrace(e);
            return false;
        }

        // maintenant on effectue toutes les transformations
        try {
            output = new GedcomFileWriter(fileOut, input.getCharset(), getEOL(fileIn));
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "file.create.error", fileOut.getName()));
            return false;
        }
        try {
            console.println("Gedcom version = " +  GEDCOM_VERSION);
            console.println("=============================");
            input = GedcomFileReader.create(fileIn);
            try {
                while (input.getNextLine(true) != null) {
                    if ((input.getLevel() == 0) && (input.getTag().equals("HEAD"))) {
                        output.writeLine(input);
                        console.println("Updating Header Note");
                        console.println("=============================");
                        output.writeLine(1, "NOTE", getImportComment());
                        continue;
                    }
                    if (process()) {
                        continue;
                    }

                    if (input.getTag().equals("TRLR")) {
                        finalise();
                        output.writeLine(0, "TRLR", null);
                        continue;
                    }
                    output.writeLine(input);
                }
            } finally {
                input.close();
                output.close();
            }

        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "file.not.found", fileIn.getName()));
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "file.read.error", fileIn.getName()));
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, NbBundle.getMessage(Import.class, "error.unknown"));
            return false;
        }

        console.println("=========== Completed =============");
        return true;
    }

    /**
     * This is the second step of import process. The gedcom file generated in
     * step one as been loaded in memory and can be manipulated using all
     * ancestris core functionnalities.
     *
     * @param gedcom
     * @return
     */
    public boolean fixGedcom(Gedcom gedcom) {
        return true;
    }

    protected void finalise() throws IOException {
        finaliseMissingEntities();
    }

    protected void firstPass() {
        if ((input.getLevel() == 2) && input.getTag().equals("VERS")) {
            GEDCOM_VERSION = input.getValue();
        }
        firstPassMissingEntities();
    }

    protected boolean process() throws IOException {
        if (processYesTag()) {
            return true;
        }
        if (processInvalidTag()) {
            return true;
        }
        return false;
    }

    private void firstPassMissingEntities() {
        if (input.getTag().equals("INDI")) {
            String xref = "@" + input.getXref() + "@";
            if (!hashIndis.containsKey(xref)) {
                hashIndis.put(xref, new ImportIndi());
            }
            hashIndis.get(xref).seen = true;
        }
        if (input.getTag().equals("CHIL")) {
            if (!hashIndis.containsKey(input.getValue())) {
                hashIndis.put(input.getValue(), new ImportIndi());
            }
        }
        if (input.getTag().equals("FAM")) {
            String xref = "@" + input.getXref() + "@";
            if (!hashFams.containsKey(xref)) {
                hashFams.put(xref, new ImportFam());
            }
            hashFams.get(xref).seen = true;
        }
        if (input.getTag().equals("FAMS")) {
            if (!hashFams.containsKey(input.getValue())) {
                hashFams.put(input.getValue(), new ImportFam());
            }
        }

    }

    private void finaliseMissingEntities() throws IOException {
        for (String k : hashIndis.keySet()) {
            if (!hashIndis.get(k).seen) {
                output.writeLine(0, k, "INDI", null);
            }
        }
        for (String k : hashFams.keySet()) {
            if (!hashFams.get(k).seen) {
                output.writeLine(0, k, "FAM", null);
            }
        }

    }

    /**
     * Normallize YES_TAGS.
     * Convert all "YES_TAGS" (eg BIRT, EVEN, ...) where value is not null 
     * and different from "Y" from
     * <pre>
     * n TAG value</pre>
     * to 
     * <pre>
     * n TAG
     * n+1 NOTE value</pre>
     * @return
     * @throws IOException 
     */
    public boolean processYesTag() throws IOException {
        Matcher matcher = tag_y.matcher(input.getTag());
        if (matcher.matches()) {
            String result = null;
            String tag = input.getTag();
            int level = input.getLevel();
            String line = input.getLine();
            if (input.getValue().length() != 0) {
                if (input.getValue().equalsIgnoreCase("y")){
                    output.writeLine(input);
                } else {
                    result = output.writeLine(level, tag, null);
                    result += "\n"+output.writeLine(level+1, "NOTE", input.getValue());
                }
            } else {
                String temp = input.getNextLine(false);
                if ((temp != null) && (input.getLevel() == level + 1)) {
                    output.writeLine(level, tag, null);
                } else {
                    result = output.writeLine(level, tag, "Y");
                }
            }
            if (result != null){
                console.println(line);
                console.println("==> " + result);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean processInvalidTag() throws IOException {
        // C'est un tag perso: on ecrit telque
        if (input.getTag().startsWith("_")) {
            return false;
        }
        // le tag n'est pas valide: on le prefixe par _
        Pattern tag_valid = GEDCOM_VERSION.startsWith("5.5.1") ? tag551_valid : tag55_valid;
        if (!tag_valid.matcher(input.getTag()).matches()) {
            String result = output.writeLine(input.getLevel(), "_" + input.getTag(), input.getValue());
            console.println(input.getLine());
            console.println("==> " + result);
            return true;
        }
        return false;
    }

    public String getEOL(File input) {

        String eolMark = System.getProperty("line.separator");

        try {
            FileReader fr = new FileReader(input);
            char[] buffer = new char[200];
            fr.read(buffer);

            String line = new String(buffer);
            if (line.contains("\r\n")) {
                eolMark = "\r\n";
            } else if (line.contains("\n")) {
                eolMark = "\n";
            } else if (line.contains("\r")) {
                eolMark = "\r";
            }
        } catch (IOException e) {
        }

        return eolMark;
    }

    protected static class GedcomFileReader extends PropertyReader {

        private String theLine = "";
        private TagPath path = null;
        private Charset charset = null;

        public TagPath getPath() {
            return path;
        }

        static GedcomFileReader create(File fileIn) {
            GedcomEncodingSniffer sniffer;
            Charset charset;
            try {
                sniffer = new GedcomEncodingSniffer(new BufferedInputStream(new FileInputStream(fileIn)));
                charset = sniffer.getCharset();
            } catch (IOException ex) {
                return null;
            }
            GedcomFileReader reader = new GedcomFileReader(new InputStreamReader(sniffer, charset));
            reader.setCharset(charset);
            return reader;
        }

        private GedcomFileReader(Reader in) {
            super(in, null, false);
        }

        public Charset getCharset() {
            return charset;
        }

        public void setCharset(Charset charset) {
            this.charset = charset;
        }

        public String getValue() {
            return value;
        }

        public String getXref() {
            return xref;
        }

        public int getLevel() {
            return level;
        }

        public String getNextLine(boolean consume) throws IOException {
            readLine(false, false);
            theLine = line;
            if (level <= 0) {
                path = new TagPath(tag);
            } else {
                path = new TagPath(new TagPath(path, level), tag);
            }
            if (consume) {
                line = null;
            }
            return theLine;
        }

        public void close() throws IOException {
            in.close();
        }

        public String getTag() {
            return tag;
        }

        public String getLine() {
            return theLine;
        }
    }

    protected class GedcomFileWriter extends BufferedWriter {

        String EOL = System.getProperty("line.separator");
        private int levelShift = 0;
        private int shiftedLevel = -1;

        public GedcomFileWriter(File fileOut, Charset charset, String eol) throws UnsupportedEncodingException, FileNotFoundException {
            super(new OutputStreamWriter(new FileOutputStream(fileOut), charset));
            EOL = eol;
        }

        public String writeLine(int level, String tag, String value) throws IOException {
            return writeLine(level, null, tag, value);
        }

        public String writeLine(GedcomFileReader input) throws IOException {
            return writeLine(input.getLevel(), input.getXref(), input.getTag(),
                    input.getValue());
        }

        public String writeLine(int level, String xref, String tag, String value)
                throws IOException {

            if (level <= shiftedLevel) {
                shiftedLevel = -1;
                levelShift = 0;
            }
            String result = Integer.toString(level + levelShift) + " ";

            if (xref != null && xref.length() > 0) {
                result += "@" + xref + "@ ";
            }
            result += tag;

            // Value
            if (value != null && value.length() > 0) {
                result += " " + value;
            }
            write(result + EOL);
            return result;
        }

        public String shiftLine(GedcomFileReader input) throws IOException {
            return shiftLine(input.getLevel(), input.getXref(), input.getTag(),
                    input.getValue());
        }

        public String shiftLine(int level, String xref, String tag, String value) throws IOException {
            if (levelShift == 0) {
                String result = writeLine(level + 1, xref, tag, value);
                levelShift = 1;
                shiftedLevel = level;
                return result;
            }
            return null;
        }
    }

    private class ImportIndi {

        protected boolean seen = false;
    }

    private class ImportFam {

        protected boolean seen = false;
        protected String husb = "";
        protected String wife = "";
        protected String[] child = new String[10];
    }
}
