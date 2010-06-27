/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app.tools.webbook.creator;

import genj.gedcom.Indi;
import genjfr.app.tools.webbook.WebBook;
import genjfr.app.tools.webbook.WebBookParams;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author frederic
 */
public class WebHome extends WebSection {

    /**
     * Constructor
     */
    public WebHome(boolean generate, WebBook wb, WebBookParams wp, WebHelper wh) {
        super(generate, wb, wp, wh);
    }

    /**
     * Section's entry point
     */
    @Override
    public void create() {

        File dir = wh.createDir(wh.getDir().getAbsolutePath(), true);
        File file = wh.getFileForName(dir, indexFile);
        PrintWriter out = wh.getWriter(file, UTF8);

        // Calculate statistics
        GedcomStats stats = new GedcomStats(wp, wh);

        // HEAD
        printOpenHTML(out, "", this);

        // START OF PAGE ------------------
        exportIndex(out, stats);
        // END OF PAGE ------------------

        // TAIL
        printCloseHTML(out);

        // done
        out.close();
        wh.log.write(indexFile + trs("EXEC_DONE"));
    }

    /**
     * Exports index.html
     */
    private void exportIndex(PrintWriter out, GedcomStats stats) {

        out.println("<hr />");
        out.println("<div class=\"contindex\">");      // conteneur

        //
        // MENU side of the index page
        //
        out.println("<div class=\"menu\">");


        // Individuals
        out.println("<p>" + trs("TXT_menu_individuals") + "</p>");
        out.println("<ul>");
        if (wb.sectionLastnames.toBeGenerated) {
            out.println("<li><a href=\"" + wb.sectionLastnames.sectionLink + "\">" + htmlText(wb.sectionLastnames.sectionName) + "</a></li>");
        }
        if (wb.sectionIndividuals.toBeGenerated) {
            out.println("<li><a href=\"" + wb.sectionIndividuals.sectionLink + "\">" + htmlText(wb.sectionIndividuals.sectionName) + "</a></li>");
        }
        if (wb.sectionIndividualsDetails.toBeGenerated) {
            out.println("<li><a href=\"" + wb.sectionIndividualsDetails.sectionLink + "\">" + htmlText(wb.sectionIndividualsDetails.sectionName) + "</a></li>");
        }
        out.println("</ul>");


        // Documents
        if (wp.param_media_GeneSources.equals("1") || wp.param_media_GeneMedia.equals("1")) {
            out.println("<p>" + trs("TXT_menu_documents") + "</p>");
            out.println("<ul>");
        if (wb.sectionSources.toBeGenerated && wp.param_media_GeneSources.equals("1")) {
            out.println("<li><a href=\"" + wb.sectionSources.sectionLink + "\">" + htmlText(wb.sectionSources.sectionName) + "</a></li>");
        }
//        if (pagesMedia && displayMediaSec) {
//            out.println("<li><a href=\"" + sectionMedia.sectionLink + "\">" + htmlText(sectionMedia.sectionName) + "</a></li>");
//        }
            out.println("</ul>");
        }

        // Locations
        if (wp.param_media_GeneMap.equals("1")) {
            out.println("<p>" + htmlText(trs("TXT_menu_locations")) + "</p>");
            out.println("<ul>");
//        out.println("<li><a href=\"" + sectionMap.sectionLink + "\">" + htmlText(sectionMap.sectionName) + "</a></li>");
//        out.println("<li><a href=\"" + sectionCities.sectionLink + "\">" + htmlText(sectionCities.sectionName) + "</a></li>");
//        out.println("<li><a href=\"" + sectionCitiesDetails.sectionLink + "\">" + htmlText(sectionCitiesDetails.sectionName) + "</a></li>");
            out.println("</ul>");
        }

        // Dates
        out.println("<p>" + htmlText(trs("TXT_menu_days")) + "</p>");
        out.println("<ul>");
//        out.println("<li><a href=\"" + sectionDays.sectionLink + "\">" + htmlText(sectionDays.sectionName) + "</a></li>");
//        out.println("<li><a href=\"" + sectionDaysDetails.sectionLink + "\">" + htmlText(sectionDaysDetails.sectionName) + "</a></li>");
        out.println("</ul>");

        // Statistics
        if (true) {
            out.println("<p>" + htmlText(trs("TXT_menu_statistics")) + "</p>");
            out.println("<ul>");
//        if (pagesStatsFrequent) {
//            out.println("<li><a href=\"" + sectionStatsFrequent.sectionLink + "\">" + htmlText(sectionStatsFrequent.sectionName) + "</a></li>");
//        }
//        if (pagesStatsImplex) {
//            out.println("<li><a href=\"" + sectionStatsImplex.sectionLink + "\">" + htmlText(sectionStatsImplex.sectionName) + "</a></li>");
//        }
            out.println("</ul>");
        }

        // Structured lists
        if (wp.param_dispAncestors.equals("1")) {
            out.println("<p>" + htmlText(trs("TXT_menu_structuredlist")) + "</p>");
            out.println("<ul>");
//        }
//        if (pagesRepSosa && displayRepSosa) {
//            out.println("<li><a href=\"" + sectionRepSosa.sectionLink + "\">" + htmlText(sectionRepSosa.sectionName) + "</a></li>");
//        }
            out.println("</ul>");
        }

        // Tools
        out.println("<p>" + htmlText(trs("TXT_menu_tools")) + "</p>");
        out.println("<ul>");
//        if (pagesSearch) {
//            out.println("<li><a href=\"" + sectionSearch.sectionLink + "\">" + htmlText(sectionSearch.sectionName) + "</a></li>");
//        }
        out.println("</ul></div>");



        //
        // Right hand side of the index page
        //
        out.println("<div class=\"intro\">");

        // Dynamic message
        if (wp.param_dispMsg.equals("1")) {
            out.println(wp.param_message);
            out.println("<br /><br /><hr /><br />");
        }

        // Static message
        out.println(trs("TXT_text_sosa", " <a href=\"" + getLink(stats.indiDeCujus) + "\">" + getNameShort(stats.indiDeCujus) + "</a>", stats.nbAncestors, stats.nbGen) + "<br />");

        out.println(trs("TXT_text_old", "<a href=\"" + getLink(stats.indiOlder) + "\">" + getName(stats.indiOlder) + "</a>", 
                stats.olderBirthDate == null ? trs("TXT_text_unknown_date") : stats.olderBirthDate) + "<br />");

        if (wp.param_dispStatAncestor.equals("1")) {
            stats.calcLonguestLine(stats.indiDeCujus);
            out.println("<br />");
            if (stats.indiDeCujus == stats.longIndiG) {
                if (stats.indiDeCujus == stats.longIndiA) {
                    out.println(trs("TXT_text_longuest1") + "<br />");
                    out.println(trs("TXT_text_largest1", trs("TXT_text_largest1too")) + "<br />");
                } else {
                    out.println(trs("TXT_text_longuest1") + "<br />");
                    out.println(trs("TXT_text_largest2", "<a href=\"" + getLink(stats.longIndiA) + "\">" + getNameShort(stats.longIndiA) + "</a>", stats.nbAncestorsA) + "<br />");
                }
            } else {
                if (stats.indiDeCujus == stats.longIndiA) {
                    out.println(trs("TXT_text_largest1", trs("TXT_text_largest1too") + SPACE) + "<br />");
                    out.println(trs("TXT_text_longuest2", "<a href=\"" + getLink(stats.longIndiG) + "\">" + getNameShort(stats.longIndiG) + "</a>", stats.nbGenG) + "<br />");
                } else {
                    if (stats.longIndiG == stats.longIndiA) {
                        out.println(trs("TXT_text_longuest2", "<a href=\"" + getLink(stats.longIndiG) + "\">" + getNameShort(stats.longIndiG) + "</a>", stats.nbGenG) + "<br />");
                        out.println(trs("TXT_text_largest1", trs("TXT_text_largest1too") + SPACE) + "<br />");
                    } else {
                        out.println(trs("TXT_text_longuest2", "<a href=\"" + getLink(stats.longIndiG) + "\">" + getNameShort(stats.longIndiG) + "</a>", stats.nbGenG) + "<br />");
                        out.println(trs("TXT_text_largest2", "<a href=\"" + getLink(stats.longIndiA) + "\">" + getNameShort(stats.longIndiA) + "</a>", stats.nbAncestorsA) + "<br />");
                    }
                }
            }
        }
        out.println("<br /><hr /><br />");
        if (stats.place.length() > 0) {
            out.println(trs("TXT_text_place", stats.place) + "<br />");
        }
        out.println(trs("TXT_text_stats", stats.nbIndis, stats.nbFams, stats.nbNames, stats.nbPlaces) + "<br />");
        out.println(trs("TXT_text_cousins", stats.nbAscendants, stats.nbCousins, stats.nbOthers) + "<br />");
        out.println(trs("TXT_text_family", stats.nbFams, stats.nbFamsWithKids, stats.avgKids) + "<br />");
        out.println("<br /><hr /><br />");

        // Author
        out.println(trs("WebBookVisualPanel1.jLabel3.text") + ":" + SPACE + wp.param_author + "<br />");
        out.println(trs("WebBookVisualPanel1.jLabel4.text") + ":" + SPACE + wp.param_address + "<br />" + trs("WebBookVisualPanel1.jLabel5.text") + ":" + SPACE + wp.param_phone + "<br />");
        if (wp.param_dispEmailButton.equals("1")) {
            out.println("<a href=\"mailto:" + wp.param_email + "?subject=" + trs("TXT_idx_email_subject") + "&amp;body=" + trs("TXT_idx_email_dear")
                    + "%20" + wp.param_author + ",%0a%0a" + trs("TXT_idx_email_body") + " \">" + trs("TXT_idx_email_link") + "</a><br /><br />");
        }
        out.println("<hr /><br />");

        // Footer
        out.println("<p class=\"legal\">" + trs("TXT_text_pages", "<a href=\"http://www.ancestris.com\">Ancestris WebBook</a>", new Date()) + "</p>");
        out.println("</div>");

        out.println("<div class=\"spacer\">" + SPACE + "</div>");



        // conteneur
        out.println("</div>");

    }

    public String getLink(Indi indi) {
        return wb.sectionIndividualsDetails.sectionDir + SEP + wb.sectionIndividualsDetails.getPagesMap().get(indi.getId()) + "#" + indi.getId();
    }

    public String getName(Indi indi) {
        String name = indi.getFirstName() + " " + wh.getLastName(indi, DEFCHAR);
        if (wh.isPrivate(indi)) {
            name = "... ...";
        } else {
            // add sosa number
            String sosa = wh.getSosa(indi);
            if (sosa != null && sosa.length() != 0) {
                name += " (" + sosa + ")";
            }
        }
        return name;
    }

    public String getNameShort(Indi indi) {
        String name = indi.getFirstName() + " " + wh.getLastName(indi, DEFCHAR);
        if (wh.isPrivate(indi)) {
            name = "... ...";
        }
        return name;
    }
}
