package viewer.relationwizard;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.table.AbstractTableModel;
import viewer.db.Attribute;
import viewer.db.DataSource;
import viewer.util.Util;

/**
 * <p>Title: Viewer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

/**
 * RelationWizardModel class manipulates data necessary for
 * relation wizard
 */
public class RelationWizardModel extends AbstractTableModel {
  static private final String COLUMN_NAMES[] = {
    "Name",
    "Type",
    "Length"
  };
  private final DataSource data;
  private final ArrayList attributes;

  RelationWizardModel(DataSource data) {
    this.data = data;
    attributes = new ArrayList();
    attributes.add(0, new Attribute());
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  void clear() {
    attributes.clear();
    attributes.add(0, new Attribute());
  }
  void createTable(String tableName) throws SQLException {
    String sql = "create table " + tableName + " (";
    ListIterator i = attributes.listIterator();
    while(i.hasNext()) {
      Attribute attr = (Attribute)i.next();
      sql += attr.getName() + ' ' + attr.getType();
      if(attr.getSQLType() == Types.VARCHAR)
        sql += '(' + attr.getLength().toString() + ')';
      if(i.hasNext())
        sql += ", ";
    }
    sql += ')';
    data.execSQL(sql);
  }

  /**
   * add new attribute to the structure of new table
   */
  public void addAttribute() {
    int size = attributes.size();
    attributes.add(new Attribute());
    fireTableRowsInserted(size, size);
  }

  public String getColumnName(int index) {
    String name = COLUMN_NAMES[index];
    return name;
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Attribute attr = (Attribute)attributes.get(rowIndex);
    switch(columnIndex) {
      case 0:
        attr.setName((String)aValue);
        break;
      case 1:
        attr.setType((String)aValue);
        break;
      case 2:
      default:
        attr.setLength((Integer)aValue);
        break;
    }
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return true;
  }

  public Class getColumnClass(int columnIndex) {
    return getValueAt(0, columnIndex).getClass();
  }

  public int getRowCount() {
    return attributes.size();
  }

  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  public Object getValueAt(int row, int col) {
    Attribute attr = (Attribute)attributes.get(row);
    switch(col) {
      case 0:
        return attr.getName();
      case 1:
        return attr.getType();
      case 2:
      default:
        return attr.getLength();
    }
  }
}
