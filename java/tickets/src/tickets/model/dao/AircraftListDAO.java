package tickets.model.dao;

import java.sql.*;
import java.util.*;
import tickets.model.dat.Aircraft;
import tickets.util.*;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class AircraftListDAO extends AbstractDAO {
  static private AircraftListDAO instance = null;

  private void populate(ResultSet rs, Aircraft aircraft) throws SQLException {
    aircraft.setId(getInt(rs, "id_aircraft").intValue());
    aircraft.setCompany(getString(rs, "name_company"));
    aircraft.setModel(getString(rs, "model"));
  }

  public Collection getAircrafts() {
    Connection con = null;
    Collection result = new ArrayList();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      String query ="select" +
                    " id_aircraft," +
                    " name_company," +
                    " model" +
                    " from aircrafts" +
                    " join companies on aircrafts.id_company=companies.id_company" +
                    " order by id_aircraft";
      stmt = con.prepareStatement(query);
      rs = stmt.executeQuery();
      while(rs.next()) {
        Aircraft aircraft = new Aircraft();
        populate(rs, aircraft);
        result.add(aircraft);
      }
    } catch (SQLException e) {
      Util.debug("getFlights()", e);
      throw new DAOException(e);
    } finally {
      close(con);
    }
    return result;
  }

  static public AircraftListDAO getInstance() {
    if(instance == null)
      instance = new AircraftListDAO();
    return instance;
  }
}