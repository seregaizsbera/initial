package viewer.navigator;

import java.sql.ResultSet;
import java.sql.SQLException;
import viewer.db.DataSource;
import viewer.util.StringList;
import viewer.util.Util;

/**
 * Title:        Viewer
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Sberbank
 * @author Sergey Bogdanov
 * @version 1.0
 */

class NavigatorModel {
  private final DataSource data;

  NavigatorModel(DataSource data) {
    this.data = data;
  }

  protected void finalize() throws java.lang.Throwable {
    Util.debug(getClass().getName() + ".finalize(" + hashCode() +')');
    super.finalize();
  }

  StringList getTables() {
    StringList res = new StringList();
    try {
      ResultSet rs = data.getTables();
      while(rs.next())
        res.addStrLast(rs.getString("TABLE_NAME"));
    } catch(SQLException e) {
      Util.showException(e);
    }
    return res;
  }
}
