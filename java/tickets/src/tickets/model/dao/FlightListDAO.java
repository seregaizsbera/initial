package tickets.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tickets.model.dat.Flight;
import tickets.model.dat.FlightsBean;
import tickets.model.valueobjects.Currency;
import tickets.model.dat.SearchFilter;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс FlightListDAO предоставляет доступ к списку рейсов на уровне базы
 * данных
 */
public class FlightListDAO extends AbstractDAO {
  static private FlightListDAO instance = null;

  private void populate(ResultSet rs, Flight flight) throws SQLException {
    flight.setDepartureDate(getString(rs, "c1"));
    flight.setArrivalDate(getString(rs, "c2"));
    flight.setDepartureCity(getString(rs, "c3"));
    flight.setArrivalCity(getString(rs, "c4"));
    flight.setPrice1stClass(
        new Currency(new BigDecimal(getFloat(rs, "c5").doubleValue())));
    flight.setPrice2ndClass(
        new Currency(new BigDecimal(getFloat(rs, "c6").doubleValue())));
    flight.setAirCraftModel(getString(rs, "c7"));
    flight.setCount1stClass(getInt(rs, "c8").intValue());
    flight.setCount2ndClass(getInt(rs, "c9").intValue());
    flight.setCompany(getString(rs, "c10"));
    flight.setId(getInt(rs, "c11").intValue());
    flight.setIdDepartureCity(getInt(rs, "c12").intValue());
    flight.setIdArrivalCity(getInt(rs, "c13").intValue());
    flight.setIdAircraft(getInt(rs, "c14").intValue());
    flight.setDepartureTime(getString(rs, "c15"));
    flight.setArrivalTime(getString(rs, "c16"));
  }

  private String getSqlBooleanOperator(int condition) {
    switch(condition) {
      case SearchFilter.BEFORE:
        return "<=";
      case SearchFilter.AFTER:
        return ">=";
      default:
        return "=";
    }
  }

  private String makeSqlWhereClause(SearchFilter filter) {
    if(filter == null)
      return "";
    String result = " where (1=1)";
    if(filter.getDepartureCityId() != SearchFilter.NOT_SPECIFIED)
      result += " and (id_departure_city=?)";
    if(filter.getArrivalCityId() != SearchFilter.NOT_SPECIFIED)
      result += " and (id_arrival_city=?)";
    if(filter.getDepartureDate() != null)
      result += " and (Date(date_departure)=?)";
    if(filter.getDepartureTime() != null)
      result += " and (Time(date_departure)" +
                getSqlBooleanOperator(filter.getDepartureTimeCondition()) +
                "?)";
    if(filter.getArrivalDate() != null)
      result += " and (Date(date_arrival)=?)";
    if(filter.getArrivalTime() != null)
      result += " and (Time(date_arrival)" +
                getSqlBooleanOperator(filter.getArrivalTimeCondition()) + "?)";
    return result;
  }

  private void makeStatementParameters(PreparedStatement stmt,
                                       SearchFilter filter)
      throws SQLException {
    if(filter == null)
      return;
    int index = 0;
    if(filter.getDepartureCityId() != SearchFilter.NOT_SPECIFIED)
      setInt(stmt, ++index, new Integer(filter.getDepartureCityId()));
    if(filter.getArrivalCityId() != SearchFilter.NOT_SPECIFIED)
      setInt(stmt, ++index, new Integer(filter.getArrivalCityId()));
    if(filter.getDepartureDate() != null)
      setString(stmt, ++index, filter.getDepartureDate());
    if(filter.getDepartureTime() != null)
      setString(stmt, ++index, filter.getDepartureTime());
    if(filter.getArrivalDate() != null)
      setString(stmt, ++index, filter.getArrivalDate());
    if(filter.getArrivalTime() != null)
      setString(stmt, ++index, filter.getArrivalTime());
  }

  protected FlightListDAO() {}

  public FlightsBean findFlights(SearchFilter filter) {
    Connection con = null;
    FlightsBean result = new FlightsBean();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      String query ="select" +
                    " date(a.date_departure) as c1," +
                    " date(a.date_arrival) as c2," +
                    " b.name_city as c3," +
                    " c.name_city as c4," +
                    " a.price_1_class as c5," +
                    " a.price_2_class as c6," +
                    " d.model as c7," +
                    " d.count_1_class as c8," +
                    " d.count_2_class as c9," +
                    " e.name_company as c10," +
                    " a.id_flight as c11," +
                    " a.id_departure_city as c12," +
                    " a.id_arrival_city as c13," +
                    " a.id_aircraft as c14," +
                    " time(a.date_departure) as c15," +
                    " time(a.date_arrival) as c16" +
                    " from flights as a" +
                    " join cities b on a.id_departure_city=b.id_city" +
                    " join cities c on a.id_arrival_city=c.id_city" +
                    " join aircrafts d on a.id_aircraft=d.id_aircraft" +
                    " join companies e on d.id_company=e.id_company";
      query += makeSqlWhereClause(filter);
      stmt = con.prepareStatement(query);

      makeStatementParameters(stmt, filter);

      rs = stmt.executeQuery();
      while(rs.next()) {
        Flight flight = new Flight();
        populate(rs, flight);
        result.setNewFlight(flight);
      }
    } catch (SQLException e) {
      Util.debug("getFlights()", e);
      throw new DAOException(e);
    } finally {
      close(rs);
      close(stmt);
      close(con);
    }
    if(result.isEmpty())
      result = null;
    return result;
  }

  static public FlightListDAO getInstance() {
    if(instance == null)
      instance = new FlightListDAO();
    return instance;
  }
}
