package tickets.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tickets.model.dat.Flight;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс FlightDAO позволяет получить доступ к рейсу на уровне базы данных
 */
public class FlightDAO extends AbstractDAO {
  static private FlightDAO instance = null;

  private void populate(ResultSet rs, Flight flight)  throws SQLException {
    flight.setId(getInt(rs, "c1").intValue());
    flight.setDepartureCity(getString(rs, "c2"));
    flight.setArrivalCity(getString(rs, "c3"));
    flight.setAirCraftModel(getString(rs, "c4"));
    flight.setCount1stClass(getInt(rs, "c5").intValue());
    flight.setCount2ndClass(getInt(rs, "c6").intValue());
    flight.setCompany(getString(rs, "c7"));
    flight.setDepartureTime(getString(rs, "c8"));
    flight.setArrivalTime(getString(rs, "c9"));
  }

  private void loadLinkedData(Connection con,
                              Flight flight,
                              String whereFlightId) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      String query = "select" +
                     " a.id_flight as c1," +
                     " b.name_city as c2," +
                     " c.name_city as c3," +
                     " d.model as c4," +
                     " d.count_1_class as c5," +
                     " d.count_2_class as c6," +
                     " e.name_company as c7," +
                     " time(a.date_departure) as c8," +
                     " time(a.date_arrival) as c9" +
                     " from flights as a" +
                     " join cities as b on a.id_departure_city=b.id_city" +
                     " join cities as c on a.id_arrival_city=c.id_city" +
                     " join aircrafts as d on a.id_aircraft=d.id_aircraft" +
                     " join companies as e on d.id_company=e.id_company" +
                     " where a.id_flight=" + whereFlightId;
      stmt = con.prepareStatement(query);
      rs = stmt.executeQuery();
      rs.next();
      populate(rs, flight);
    }
    finally {
      close(rs);
      close(stmt);
    }
  }

  protected FlightDAO() {}

  public void create(Flight flight) {
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = getConnection();
      String query =
          "insert into flights" +
          " (date_departure" +
          ", date_arrival" +
          ", id_departure_city" +
          ", id_arrival_city" +
          ", id_aircraft" +
          ", price_1_class" +
          ", price_2_class)"+
          " values (" +
          " Timestamp(cast(? as varchar(14)), cast(? as varchar(14)))," +
          " Timestamp(cast(? as varchar(14)), cast(? as varchar(14)))," +
          " ?, ?, ?, ?, ?)";
      stmt = con.prepareStatement(query);
      setString(stmt, 1, flight.getDepartureDate());
      setString(stmt, 2, flight.getDepartureTime());
      setString(stmt, 3, flight.getArrivalDate());
      setString(stmt, 4, flight.getArrivalTime());
      setInt(stmt, 5, new Integer(flight.getIdDepartureCity()));
      setInt(stmt, 6, new Integer(flight.getIdArrivalCity()));
      setInt(stmt, 7, new Integer(flight.getIdAircraft()));
      setCurrency(stmt, 8, flight.getPrice1stClass());
      setCurrency(stmt, 9, flight.getPrice2ndClass());
      stmt.executeUpdate();
      stmt.close();
      loadLinkedData(con, flight, "(select max(id_flight) from flights)");
    } catch (SQLException e) {
      Util.debug("FlightDAO::insert()", e);
      throw new DAOException(e);
    } finally {
      close(stmt);
      close(con);
    }
  }

  public void remove(Flight flight) {
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = getConnection();
      String query = "delete from flights" +
                     " where" +
                     " (id_flight=?)";
      stmt = con.prepareStatement(query);
      setInt(stmt, 1, new Integer(flight.getId()));
      stmt.executeUpdate();
    } catch (SQLException e) {
      Util.debug("FlightDAO::remove()", e);
      throw new DAOException(e);
    } finally {
      close(stmt);
      close(con);
    }
  }

  public void update(Flight flight) {
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = getConnection();
      String query = "update flights set" +
                     "  date_departure=Timestamp(cast(? as varchar(14))," +
                                                   " cast(? as varchar(14)))" +
                     ", date_arrival=Timestamp(cast(? as varchar(14))," +
                                                   " cast(? as varchar(14)))" +
                     ", id_departure_city=?" +
                     ", id_arrival_city=?" +
                     ", id_aircraft=?" +
                     ", price_1_class=?" +
                     ", price_2_class=?" +
                     " where (id_flight=?)";
      stmt = con.prepareStatement(query);
      setString(stmt, 1, flight.getDepartureDate());
      setString(stmt, 2, flight.getDepartureTime());
      setString(stmt, 3, flight.getArrivalDate());
      setString(stmt, 4, flight.getArrivalTime());
      setInt(stmt, 5, new Integer(flight.getIdDepartureCity()));
      setInt(stmt, 6, new Integer(flight.getIdArrivalCity()));
      setInt(stmt, 7, new Integer(flight.getIdAircraft()));
      setCurrency(stmt, 8, flight.getPrice1stClass());
      setCurrency(stmt, 9, flight.getPrice2ndClass());
      setInt(stmt, 10, new Integer(flight.getId()));
      stmt.executeUpdate();
      loadLinkedData(con, flight, new Integer(flight.getId()).toString());
    } catch (SQLException e) {
      Util.debug("FlightDAO::update()", e);
      throw new DAOException(e);
    } finally {
      close(stmt);
      close(con);
    }
  }

  static public FlightDAO getInstance() {
    if(instance == null)
      instance = new FlightDAO();
    return instance;
  }
}
