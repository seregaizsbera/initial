package tickets.controller.client;

import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import tickets.controller.AbstractDispatcher;
import tickets.controller.ActionParameters;
import tickets.model.dat.SearchFilter;
import tickets.model.dao.FlightListDAO;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class SearchFlight extends AbstractDispatcher
                   implements ActionParameters, SearchFormParameters {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    HttpSession session = request.getSession();
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    String action = request.getParameter(ACTION);
    if(sessionType != CLIENT_SESSION || action == null || !action.equals(SEARCH_ACTION)) {
      error("Страница устарела", request, response);
      return;
    }

    String departureCity = request.getParameter(DEPARTURE_CITY);
    String departureDate = request.getParameter(DEPARTURE_DATE);
    String departureTime = request.getParameter(DEPARTURE_TIME);
    String departureTimeCondition = request.getParameter(DEPARTURE_TIME_CONDITION);
    String arrivalCity = request.getParameter(ARRIVAL_CITY);
    String arrivalDate = request.getParameter(ARRIVAL_DATE);
    String arrivalTime = request.getParameter(ARRIVAL_TIME);
    String arrivalTimeCondition = request.getParameter(ARRIVAL_TIME_CONDITION);
    if((Util.isEmpty(departureCity) || departureCity.equals("0")) &&
       (Util.isEmpty(arrivalCity) || arrivalCity.equals("0")) &&
       Util.isEmpty(departureTime) &&
       Util.isEmpty(arrivalTime) &&
       Util.isEmpty(departureDate) &&
       Util.isEmpty(arrivalDate)) {
      error("Неправильно указаны параметры поиска.", request, response);
      return;
    }

    Locale locale = (Locale)session.getAttribute(LOCALE);

    SearchFilter searchFilter = new SearchFilter();
    searchFilter.setDepartureCityId(Integer.parseInt(departureCity));
    searchFilter.setDepartureDate(departureDate);
    searchFilter.setDepartureTime(departureTime);
    searchFilter.setDepartureTimeCondition(Integer.parseInt(departureTimeCondition));
    searchFilter.setArrivalCityId(Integer.parseInt(arrivalCity));
    searchFilter.setArrivalDate(arrivalDate);
    searchFilter.setArrivalTime(arrivalTime);
    searchFilter.setArrivalTimeCondition(Integer.parseInt(arrivalTimeCondition));

    FlightListDAO flightListDao = FlightListDAO.getInstance();
    Map flights = flightListDao.FindFlights(searchFilter);
    session.setAttribute(FLIGHTS, flights);

    String nextPage = FLIGHTS_HTML;
    redirect(nextPage, request, response);
  }
}
