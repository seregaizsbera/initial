package tickets.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tickets.controller.AbstractDispatcher;
import tickets.model.dao.FlightDAO;
import tickets.model.dat.Flight;
import tickets.model.dat.FlightsBean;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 *  ласс DeleteFlight обрабатывает запрос пользовател€ на удаление рейса
 */
public class DeleteFlight extends AbstractDispatcher
    implements FlightsFormParameters {
  protected void service(HttpServletRequest request,
                         HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null) {
      error("—траница устарела", request, response);
      return;
    }
    FlightDAO flightDAO = FlightDAO.getInstance();
    String flightIdStr = request.getParameter(FLIGHT_ID);
    int flightId = Integer.parseInt(flightIdStr);
    FlightsBean flights = (FlightsBean)session.getAttribute(FLIGHTS);
    flights.setFlightId(flightId);
    Flight flight = (Flight)flights.getFlight();
    if(flight != null) {
      flightDAO.remove(flight);
      flights.removeFlight(flightId);
    }
    String nextPage = ADMIN_FLIGHTS_HTML;
    redirect(nextPage, request, response);
  }
}