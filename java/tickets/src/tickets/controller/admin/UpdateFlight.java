package tickets.controller.admin;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.controller.AbstractDispatcher;
import tickets.model.dao.FlightDAO;
import tickets.model.dat.Flight;
import tickets.model.valueobjects.Currency;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class UpdateFlight extends AbstractDispatcher implements EditFlightFormParameters {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String flightIdStr = request.getParameter(FLIGHT_ID);
    String departureDate = request.getParameter(DEPARTURE_DATE);
    String departureTime = request.getParameter(DEPARTURE_TIME);
    String arrivalDate = request.getParameter(ARRIVAL_DATE);
    String arrivalTime = request.getParameter(ARRIVAL_TIME);
    String departureCityIdStr = request.getParameter(DEPARTURE_CITY_ID);
    String arrivalCityIdStr = request.getParameter(ARRIVAL_CITY_ID);
    String aircraftIdStr = request.getParameter(AIRCRAFT_ID);
    String price1stClassStr = request.getParameter(PRICE_1ST_CLASS);
    String price2ndClassStr = request.getParameter(PRICE_2ND_CLASS);
    if(Util.isEmpty(departureCityIdStr) ||
       Util.isEmpty(departureDate) ||
       Util.isEmpty(departureTime) ||
       Util.isEmpty(arrivalDate) ||
       Util.isEmpty(arrivalTime) ||
       Util.isEmpty(departureCityIdStr) ||
       Util.isEmpty(arrivalCityIdStr) ||
       Util.isEmpty(aircraftIdStr) ||
       Util.isEmpty(price1stClassStr) ||
       Util.isEmpty(price2ndClassStr)) {
      error("Ќеправильно заданы параметры рейса", request, response);
      return;
    }
    int flightId = Integer.parseInt(flightIdStr);
    int departureCityId = Integer.parseInt(departureCityIdStr);
    int arrivalCityId = Integer.parseInt(arrivalCityIdStr);
    int aircraftId = Integer.parseInt(aircraftIdStr);
    Currency price1stClass = new Currency(new BigDecimal(price1stClassStr));
    Currency price2ndClass = new Currency(new BigDecimal(price2ndClassStr));

    Map flights = (Map)request.getSession().getAttribute(FLIGHTS);
    Flight flight = (Flight)flights.get(new Integer(flightId));

    flight.setDepartureDate(departureDate);
    flight.setDepartureTime(departureTime);
    flight.setArrivalDate(arrivalDate);
    flight.setArrivalTime(arrivalTime);
    flight.setIdDepartureCity(departureCityId);
    flight.setIdArrivalCity(arrivalCityId);
    flight.setIdAircraft(aircraftId);
    flight.setPrice1stClass(price1stClass);
    flight.setPrice2ndClass(price2ndClass);

    FlightDAO flightDao = FlightDAO.getInstance();
    flightDao.update(flight);

    String nextPage = ADMIN_FLIGHTS_HTML;
    redirect(nextPage, request, response);
    response.getWriter().print("<h1>UpdateFlight</h1>");
  }
}