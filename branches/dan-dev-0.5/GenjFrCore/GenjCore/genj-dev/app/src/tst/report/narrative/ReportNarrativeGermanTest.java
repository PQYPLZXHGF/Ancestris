package report.narrative;

import java.io.IOException;
import java.util.Locale;

/**
 * Test for ReportNarrative in German.
 */
public class ReportNarrativeGermanTest extends ReportNarrativeTest {

  public void testDescendantsEn() throws IOException {
    testDescendants(Locale.GERMAN);
  }


  public void testAncestorsDe() throws IOException {
    testAncestors(Locale.GERMAN);
    }
}
