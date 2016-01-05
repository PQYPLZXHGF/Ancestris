/*
 * Ancestris - http://www.ancestris.org
 * 
 * Copyright 2016 Ancestris
 * 
 * Author: Frédéric Lapeyre (frederic@ancestris.org).
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package ancestris.modules.editors.standard.tools;

import genj.gedcom.Indi;
import genj.gedcom.PropertySex;
import java.awt.FontMetrics;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author frederic
 */
class AssoWithTableModel extends AbstractTableModel {

    private static int NBCOLUMNS = 7;
        
    private String[] columnNames = {
        NbBundle.getMessage(getClass(), "COL_event"),
        NbBundle.getMessage(getClass(), "COL_rela"),
        NbBundle.getMessage(getClass(), "COL_Indi"),
        NbBundle.getMessage(getClass(), "COL_Lastname"),
        NbBundle.getMessage(getClass(), "COL_Firstname"),
        NbBundle.getMessage(getClass(), "COL_Sex"),
        NbBundle.getMessage(getClass(), "COL_Occupation")
    };
    private Object[][] data;

    public AssoWithTableModel(List<AssoWrapper> assoSet) {
        if (assoSet == null || assoSet.isEmpty()) {
            data = new Object[1][NBCOLUMNS];
            return;
        }
        data = new Object[assoSet.size()][NBCOLUMNS];
        int i = 0;
        for (AssoWrapper asso : assoSet) {
            data[i][0] = asso.targetEvent;
            data[i][1] = asso.assoTxt;
            data[i][2] = asso.assoIndi;
            data[i][3] = asso.assoLastname;
            data[i][4] = asso.assoFirstname;
            data[i][5] = PropertySex.getImage(asso.assoSex);
            data[i][6] = asso.assoOccupation;
            i++;
        }
    }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c) != null ? getValueAt(0, c).getClass() : String.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        public int getMaxWidth(FontMetrics fm, int column) {
            int ret = fm.stringWidth(getColumnName(column));
            int rows = getRowCount();
            String str = "";
            for (int i = 0; i < rows; i++) {
                Object o = getValueAt(i, column);
                if (o == null) {
                    return 50;
                }
                switch (column) {
                    case 0:
                        str = ((EventWrapper)o).eventLabel.getLongLabel() + "MM";  // add size of an icon
                        break;
                    case 1:
                        str = ((String)o);
                        break;
                    case 2:
                        str = ((Indi)o).toString();
                        break;
                    case 3:
                        str = ((String)o);
                        break;
                    case 4:
                        str = ((String)o);
                        break;
                    case 5:
                        str = "MM"; // size of a sex icon
                        break;
                    case 6:
                        str = ((String)o);
                        break;
                    default:
                        str = o.toString();
                        break;
                }
                int width = fm.stringWidth(str);
                if (width > ret) {
                    ret = width;
                }
            }
            return ret;
    }



}