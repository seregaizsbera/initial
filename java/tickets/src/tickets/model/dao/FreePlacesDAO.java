package tickets.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс FreePlacesDAO позволяет получить количество свободных мест в рейсе из
 * базы данных
 */
public class FreePlacesDAO extends AbstractDAO {
  static private FreePlacesDAO instance = null;

  protected FreePlacesDAO() {}

  public int getNumberOfFreePlaces(int placesClass, int flightId) {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int result = 0;
    try {
      con = getConnection();
      String placesClassStr = "count_" +
                              Integer.toString(placesClass) + "_class";
      String query = "select" +
                     " c." + placesClassStr + "-sum(count)" +
                     " from orders as a" +
                     " join flights as b on a.id_flight=b.id_flight" +
                     " join aircrafts as c on b.id_aircraft=c.id_aircraft" +
                     " group by a.id_flight, a.class, c." + placesClassStr +
                     " having a.class=? and a.id_flight=?";
      stmt = con.prepareStatement(query);
      setInt(stmt, 1, new Integer(placesClass));
      setInt(stmt, 2, new Integer(flightId));
      rs = stmt.executeQuery();
      if(!rs.next()) {
        query = "select b." + placesClassStr +
                " from flights as a" +
                " join aircrafts as b on a.id_aircraft=b.id_aircraft" +
                " where a.id_flight=?";
        close(rs);
        close(stmt);
        stmt = con.prepareStatement(query);
        setInt(stmt, 1, new Integer(flightId));
        rs = stmt.executeQuery();
        rs.next();
      }
      result = getInt(rs, 1).intValue();
    } catch (SQLException e) {
      Util.debug("getNumberOfFreePlaces()", e);
      throw new DAOException(e);
    } finally {
      close(rs);
      close(stmt);
      close(con);
    }
    return result;
  }

  static public FreePlacesDAO getInstance() {
    if(instance == null)
      instance = new FreePlacesDAO();
    return instance;
  }
}

