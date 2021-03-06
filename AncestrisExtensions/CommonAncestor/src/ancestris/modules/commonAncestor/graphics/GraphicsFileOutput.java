/**
 * Reports are Freeware Code Snippets
 *
 * This report is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package ancestris.modules.commonAncestor.graphics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Writes report output to a file in an appropriate format.
 * A GraphicsWriter object is used to draw the content.
 *
 * @author Przemek Wiech <pwiech@losthive.org>
 */
public abstract class GraphicsFileOutput implements IGraphicsOutput {

  /**
   * Destination file.
   */
  private File file;

  /**
   * Sets the file to write to.
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * Writes the family tree to the output file.
   */
  public void output(IGraphicsRenderer renderer) throws IOException {
    OutputStream out = new FileOutputStream(file);
    write(out, renderer);
    out.close();
  }

  /**
   * Displays the generated file.
   */
  public Object result() {
    return file;
  }

  /**
   * Writes the drawing to the output stream.
   * @param out  destination output stream
   * @param renderer this object renders the drawing
   */
  public abstract void write(OutputStream out, IGraphicsRenderer renderer) throws IOException;


}
