package tickets.model.dao;

import java.math.*;
import java.sql.*;
import java.util.*;
import tickets.model.dat.Flight;
import tickets.util.Util;
import tickets.model.valueobjects.Currency;
import tickets.model.dat.SearchFilter;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class FlightListDAO extends AbstractDAO {
  static private FlightListDAO instance = null;
  private void populate(ResultSet rs, Flight flight) throws SQLException {
    flight.setDepartureDate(getTimestamp(rs, "c1"));
    flight.setArrivalDate(getTimestamp(rs, "c2"));
    flight.setDepartureCity(getString(rs, "c3"));
    flight.setArrivalCity(getString(rs, "c4"));
    flight.setPrice1stClass(new Currency(new BigDecimal(getFloat(rs, 5).doubleValue())));
    flight.setPrice2ndClass(new Currency(new BigDecimal(getFloat(rs, 6).doubleValue())));
    flight.setAirCraftModel(getString(rs, "c7"));
    flight.setCount1stClass(getInt(rs, "c8").intValue());
    flight.setCount2ndClass(getInt(rs, "c9").intValue());
    flight.setCompany(getString(rs, "c10"));
    flight.setId(getInt(rs, "c11").intValue());
  }

  private String makeSqlTimeCondition(String field, Timestamp date, int condition) {
    if(date == null)
      return "";
    String result = " and " + field;
    switch(condition) {
      case SearchFilter.BEFORE:
        result += "<=";
        break;
      case SearchFilter.AFTER:
        result += ">=";
        break;
    }
    result += '\'' + date.toString() + '\'';
    return result;
  }

  private String makeSqlWhereClause(SearchFilter filter) {
    if(filter == null)
      return "";
    String result = " where";
    result += " a.id_departure_city=" + filter.getDepartureCityId();
    result += " and a.id_arrival_city=" + filter.getArrivalCityId();
    result += makeSqlTimeCondition("a.date_departure", filter.getDepartureTime(), filter.getDepartureTimeCondition());
    result += makeSqlTimeCondition("a.date_arrival", filter.getArrivalTime(), filter.getArrivalTimeCondition());
    return result;
  }

  protected FlightListDAO() {}

  public Map FindFlights(SearchFilter filter) {
    Connection con = null;
    Map result = new HashMap();
    Statement stmt = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      stmt = con.createStatement();
      String query ="select" +
                    " a.date_departure as c1," +
                    " a.date_arrival as c2," +
                    " b.name_city as c3," +
                    " c.name_city as c4," +
                    " a.price_1_class as c5," +
                    " a.price_2_class as c6," +
                    " d.model as c7," +
                    " d.count_1_class as c8," +
                    " d.count_2_class as c9," +
                    " e.name_company as c10," +
                    " a.id_flight as c11" +
                    " from flights as a" +
                    " join cities b on a.id_departure_city=b.id_city" +
                    " join cities c on a.id_arrival_city=c.id_city" +
                    " join aircrafts d on a.id_aircraft=d.id_aircraft" +
                    " join companies e on d.id_company=e.id_company";
      query += makeSqlWhereClause(filter);
      query += " order by a.date_departure";
      Util.debug("getFlights()", query);
      rs = stmt.executeQuery(query);
      while(rs.next()) {
        Flight flight = new Flight();
        populate(rs, flight);
        result.put(new Integer(flight.getId()), flight);
      }
    } catch (SQLException e) {
      Util.debug("getFlights()", e);
      throw new DAOException(e);
    } finally {
      close(con);
    }
    return result;
  }

  static public FlightListDAO getInstance() {
    if(instance == null) {
      instance = new FlightListDAO();
    }
    return instance;
  }
}
