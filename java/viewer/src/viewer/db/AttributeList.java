package viewer.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import viewer.util.Util;

/**
 * <p>Title: Viewer</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class AttributeList extends ArrayList {
  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  /**
   * initialize list with data from ResultSetMetaData object
   *
   * @param rsmd source ResultSetMetaData object
   * @exception SQLException if a database access error occurs
   */
  public void fillWith(ResultSetMetaData rsmd) throws SQLException {
    clear();
    int columnCount = rsmd.getColumnCount();
    for(int i = 1; i <= columnCount; i++) {
      Attribute attribute = new Attribute();
      attribute.fillWith(rsmd, i);
      add(attribute);
    }
  }

  /**
   * initialize list with data from ResultSet object
   *
   * @param rsmd source ResultSet object
   * @exception SQLException if a database access error occurs
   */
  public void fillWith(ResultSet rsmd) throws SQLException {
    clear();
    while (rsmd.next()) {
      Attribute attribute = new Attribute();
      attribute.fillWith(rsmd);
      add(attribute);
    }
  }

  public Attribute getAttribute(int index) {
    return (Attribute)get(index - 1);
  }
}