/**
 * Reports are Freeware Code Snippets
 *
 * This report is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

package ancestris.report.svgtree.graphics;

import genj.report.Report;
import ancestris.core.actions.AbstractAncestrisAction;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Creates classes that write report output. This can be a file type or the screen.
 *
 * @author Przemek Wiech <pwiech@losthive.org>
 */
public class GraphicsOutputFactory {

    /**
     * Output type.
     */
    public int output_type = 0;

    public String[] output_types = null;

    private Map<String, GraphicsOutput> outputs = new LinkedHashMap<String, GraphicsOutput>();
    public List<GraphicsOutput> outputList = new ArrayList<GraphicsOutput>();

    /**
     * Creates the object
     */
    public GraphicsOutputFactory()
    {
        add("svg", new SvgWriter());
        add("pdf", new PdfWriter());
        add("png", new PngWriter());
        add("screen", new ScreenOutput());
    }

    /**
     * Creates the output class for the given type.
     *
     * @param type output type
     * @param report Containing report. Used to show dialogs and translate strings.
     */
    public GraphicsOutput createOutput(Report report)
    {
        GraphicsOutput output = outputList.get(output_type);

        if (output == null)
            return null;

        if (output instanceof GraphicsFileOutput)
        {
            GraphicsFileOutput fileOutput = (GraphicsFileOutput)output;
            String extension = fileOutput.getFileExtension();

            // Get filename from users
            File file = report.getFileFromUser(report.translate("output.file"),
                    AbstractAncestrisAction.TXT_OK, true, extension);
            if (file == null)
                return null;

            // Add appropriate file extension
            String suffix = "." + extension;
            if (!file.getPath().endsWith(suffix))
                file = new File(file.getPath() + suffix);
            fileOutput.setFile(file);
        }

        return output;
    }

    public void add(String name, GraphicsOutput output)
    {
        outputs.put(name, output);
        outputList.add(output);
        output_types = outputs.keySet().toArray(new String[0]);
    }
}
