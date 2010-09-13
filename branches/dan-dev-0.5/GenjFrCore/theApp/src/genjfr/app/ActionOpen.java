/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genjfr.app;

import genj.app.Images;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.util.Registry;
import genjfr.util.GedcomDirectory;
import genj.util.Resources;
import genj.util.swing.Action2;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

public class ActionOpen extends Action2 {

    private Resources RES = Resources.get("genj.app");
    private URL url;

    /** constructor - good for button or menu item */
    public ActionOpen() {
        setTip(RES, "cc.tip.open_file");
        setText(RES, "cc.menu.open");
        setImage(Images.imgOpen);
    }

    public void actionPerformed(ActionEvent event) {
        Context context;
        if (url != null) {
            context = App.workbenchHelper.openGedcom(url);
        } else {
            context = App.workbenchHelper.openGedcom();
        }
    }

} // ActionOpen

