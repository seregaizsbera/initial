package tickets.controller.admin;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tickets.controller.AbstractDispatcher;
import tickets.controller.ActionParameters;
import tickets.controller.SessionAttributes;
import tickets.model.dao.AircraftListDAO;
import tickets.model.dao.CitiesListDAO;
import tickets.model.dao.FlightListDAO;
import tickets.model.dat.FlightsBean;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс AdminDispatcher обрабатывает запросы пользователя, полученные в режиме
 * работы с аодминистратором системы
 */
public class AdminDispatcher extends AbstractDispatcher
    implements ActionParameters {
  protected void service(HttpServletRequest request,
                         HttpServletResponse response)
      throws javax.servlet.ServletException, java.io.IOException {
    HttpSession session = request.getSession(false);
    if(session == null) {
      error("Страница устарела", request, response);
      return;
    }
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != ADMIN_SESSION) {
      error("Страница устарела", request, response);
      return;
    }

    String nextPage = ADMIN_FLIGHTS_HTML;

    String action = request.getParameter(ACTION);

    if(action == null || action.equals(LOGIN_ACTION)) {
      FlightListDAO flightListDao = FlightListDAO.getInstance();
      FlightsBean flights = flightListDao.findFlights(null);
      session.setAttribute(FLIGHTS, flights);

      CitiesListDAO citiesListDao = CitiesListDAO.getInstance();
      Collection cities = citiesListDao.getCities();
      session.setAttribute(CITIES, cities);

      AircraftListDAO aircraftsListDao = AircraftListDAO.getInstance();
      Collection aircrafts = aircraftsListDao.getAircrafts();
      session.setAttribute(AIRCRAFTS, aircrafts);
    } else if(action.equals(DELETE_FLIGHT_ACTION))
      nextPage = DELETE_FLIGHT_SERVLET;
    else if(action.equals(UPDATE_FLIGHT_ACTION))
      nextPage = UPDATE_FLIGHT_SERVLET;
    else if(action.equals(INSERT_FLIGHT_ACTION))
      nextPage = INSERT_FLIGHT_SERVLET;

    redirect(nextPage, request, response);
  }
}
