package ancestris.app;

import ancestris.core.actions.AbstractAncestrisAction;
import ancestris.core.pluginservice.AncestrisPlugin;
import ancestris.gedcom.GedcomFileListener;
import ancestris.view.SelectionDispatcher;
import genj.gedcom.Context;
import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.GedcomListener;
import genj.gedcom.Property;
import genj.util.swing.GraphicsHelper;
import genj.util.swing.PopupWidget;
import genj.view.SelectionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.openide.util.actions.Presenter;

public final class ActionHistory implements Presenter.Toolbar {

    private static HistoryCombo history;

    @Override
    public java.awt.Component getToolbarPresenter() {
        if (history == null) {
            history = new HistoryCombo();
        }
        return history;
    }

    private static class HistoryCombo extends JPanel {

        private final Icon POPUP = GraphicsHelper.getIcon(Color.BLACK, 0, 0, 8, 0, 4, 4);
        private List<Entity> history = new ArrayList<Entity>();
        private int index = -1;
        private EventHandler events = new EventHandler();
        private Back back = new Back();
        private Forward forward = new Forward();
        private Popup pick = new Popup();

        /**
         * Constructor
         */
        HistoryCombo() {
            setLayout(new java.awt.GridBagLayout());
            add(back);
            add(forward);
            add(pick);
        }

        public Component add(Action a) {
            JButton b = new JButton(a);
            b.setFocusable(false);
            return add(b);
        }

        private void fireSelection(Entity e) {
            SelectionDispatcher.fireSelection(new Context(e));
        }

        private void update() {
            forward.setEnabled(index < history.size() - 1);
            forward.setTip(getEntityText(index + 1));

            back.setEnabled(index > 0);
            back.setTip(getEntityText(index - 1));

            pick.setEnabled(history.size() > 1);
        }

        private String getEntityText(int i) {
            try {
                Entity e = history.get(i);
                return e.toString();
            } catch (Exception e) {
            }
            return null;
        }

        /** back */
        private class Popup extends PopupWidget {

            Popup() {
                super(POPUP);
            }

            @Override
            public void showPopup() {
                removeItems();
                for (int i = 0; i < history.size(); i++) {
                    JMenuItem item = new JMenuItem(new Jump(i));
                    if (index == i) {
                        item.setFont(item.getFont().deriveFont(Font.BOLD));
                    }
                    addItem(item);
                }
                super.showPopup();
            }
        }

        private class Jump extends AbstractAncestrisAction {

            private int i;

            public Jump(int i) {
                this.i = i;
                Entity entity = history.get(i);
                setImage(entity.getImage());
                setText(entity.toString());
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                index = i;
                update();
                fireSelection(history.get(i));
            }
        }

        /** back */
        private class Back extends AbstractAncestrisAction {

            public Back() {
                setImage(new ImageIcon(ActionHistory.class.getResource("Back.png")));
                //FIXME: should be installed with @ActionReference
//                install(HistoryCombo.this, "alt LEFT");
            }

            public void actionPerformed(ActionEvent evt) {
                if (index < 1) {
                    return;
                }
                index--;
                update();
                fireSelection(history.get(index));
            }
        }

        /** forward */
        private class Forward extends AbstractAncestrisAction {

            public Forward() {
                //FIXME: should be installed with @ActionReference
//                install(HistoryCombo.this, "alt RIGHT");
                setImage(new ImageIcon(ActionHistory.class.getResource("Forward.png")));
            }

            public void actionPerformed(ActionEvent evt) {
                if (index == history.size() - 1) {
                    return;
                }
                index++;
                update();
                fireSelection(history.get(index));
            }
        }

        private class EventHandler implements SelectionListener, GedcomFileListener, GedcomListener {

            EventHandler() {
                AncestrisPlugin.register(this);
            }

            @Override
            public void gedcomClosed(Gedcom gedcom) {
                history.clear();
                index = -1;
                update();
                gedcom.removeGedcomListener(this);
            }

            @Override
            public void gedcomOpened(Gedcom gedcom) {
                gedcom.addGedcomListener(this);
            }

            public void commitRequested(Context context) {
            }

            @Override
            public void setContext(Context context) {

                Entity e = context.getEntity();
                if (e == null) {
                    return;
                }

                // don't add twice to tail
                if (!history.isEmpty() && history.get(index) == e) {
                    return;
                }

                // add
                while (history.size() > index + 1) {
                    history.remove(history.size() - 1);
                }
                history.add(++index, e);

                // trim
                while (history.size() > 16) {
                    index--;
                    history.remove(0);
                }

                update();

                // show
            }

            @Override
            public void gedcomEntityAdded(Gedcom gedcom, Entity entity) {
            }

            @Override
            public void gedcomEntityDeleted(Gedcom gedcom, Entity entity) {

                // affects history?
                int i = history.indexOf(entity);
                if (i < 0) {
                    return;
                }

                // remove it
                history.remove(i);
                if (index >= i) {
                    index--;
                }

                // go to previous (or next if only thing available)
                i--;
                if (i < 0 && history.size() > 0) {
                    i++;
                }
                if (i >= 0) {
                    fireSelection(history.get(i));
                }

                // fallback to first available person
                if (i < 0) {
                    Entity first = gedcom.getFirstEntity(Gedcom.INDI);
                    if (first != null) {
                        fireSelection(first);
                    }
                }

                update();
            }

            @Override
            public void gedcomPropertyAdded(Gedcom gedcom, Property property, int pos, Property added) {
            }

            @Override
            public void gedcomPropertyChanged(Gedcom gedcom, Property property) {
            }

            @Override
            public void gedcomPropertyDeleted(Gedcom gedcom, Property property, int pos, Property deleted) {
            }
        }
    }
}
