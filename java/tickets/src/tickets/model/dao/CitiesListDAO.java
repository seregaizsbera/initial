package tickets.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import tickets.model.dat.City;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс CitiesListDAO позволяет получить список городов из
 * базы данных
 */
public class CitiesListDAO extends AbstractDAO {
  private final String ID_CITY = "ID_CITY";
  private final String NAME_CITY = "NAME_CITY";
  static private CitiesListDAO instance = null;

  private void populate(ResultSet rs, City city) throws SQLException {
    city.setId(getInt(rs, ID_CITY).intValue());
    city.setName(getString(rs, NAME_CITY));
  }

  protected CitiesListDAO() {}

  public Collection getCities() {
    Connection con = null;
    ArrayList result = new ArrayList();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      String query = "select ID_CITY as " + ID_CITY +
                     ", NAME_CITY as " + NAME_CITY +
                     " from cities order by NAME_CITY";
      con = getConnection();
      stmt = con.prepareStatement(query);
      rs = stmt.executeQuery();
      while(rs.next()) {
        City city = new City();
        populate(rs, city);
        result.add(city);
      }
    } catch (SQLException e) {
      Util.debug("getCities()", e);
      throw new DAOException(e);
    } finally {
      close(rs);
      close(stmt);
      close(con);
    }
    return result;
  }

  static public CitiesListDAO getInstance() {
    if(instance == null) {
      instance = new CitiesListDAO();
    }
    return instance;
  }
}
