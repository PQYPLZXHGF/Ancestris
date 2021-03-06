/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2012 Ancestris
 * 
 * Author: Dominique Baron (lemovice-at-ancestris-dot-org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package ancestris.modules.gedcom.removetag;

import ancestris.core.actions.AbstractAncestrisContextAction;
import ancestris.modules.gedcom.utilities.GedcomUtilities;
import genj.gedcom.Context;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomException;
import genj.gedcom.UnitOfWork;
import java.awt.event.ActionEvent;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

@ActionID(id = "ancestris.modules.gedcom.removetag.RemoveTagAction", category = "Tools")
@ActionRegistration(
        displayName = "#CTL_RemoveTagAction",
        iconInMenu = true,
        lazy = false)
@ActionReference(path = "Menu/Tools/Gedcom", name = "RemoveTagAction", position = 500)
public final class RemoveTagAction extends AbstractAncestrisContextAction {

    public RemoveTagAction() {
        super();
        setImage("ancestris/modules/gedcom/removetag/RemoveTagIcon.png");
        setText(NbBundle.getMessage(RemoveTagAction.class, "CTL_RemoveTagAction"));
    }
    @Override
    protected void contextChanged() {
        setEnabled(!contextProperties.isEmpty());
        super.contextChanged();
    }

    @Override
    protected void actionPerformedImpl(ActionEvent event) {
        RemoveTagPanel removeTagPanel = new RemoveTagPanel();
        Context contextToOpen = getContext();
        if (contextToOpen != null) {
            Gedcom gedcom = contextToOpen.getGedcom();

            // Create a custom NotifyDescriptor, specify the panel instance as a parameter + other params
            NotifyDescriptor notifyDescriptor = new NotifyDescriptor(
                    removeTagPanel, // instance of your panel
                    NbBundle.getMessage(RemoveTagAction.class, "CTL_RemoveTagAction"), // title of the dialog
                    NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.QUESTION_MESSAGE, // ... of a question type => a question mark icon
                    null,
                    NotifyDescriptor.OK_OPTION // default option is "Ok"
                    );
            if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
                final String tag = removeTagPanel.getTag();
                final int selectedentity = removeTagPanel.getSelectedEntityIndex();
                try {
                    gedcom.doUnitOfWork(new UnitOfWork() {

                        @Override
                        public void perform(Gedcom gedcom) throws GedcomException {
                            GedcomUtilities.deleteTags(gedcom, tag, selectedentity);
                        }
                    }); // end of doUnitOfWork
                } catch (GedcomException ex) {
                    Exceptions.printStackTrace(ex);
                }
                DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(RemoveTagAction.class, "RemoveTagAction.done", tag, removeTagPanel.getSelectedEntityItem()), NotifyDescriptor.INFORMATION_MESSAGE));
            }
        }
    }
}
