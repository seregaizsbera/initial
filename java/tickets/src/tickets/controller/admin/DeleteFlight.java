package tickets.controller.admin;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.controller.AbstractDispatcher;
import tickets.model.dao.FlightDAO;
import tickets.model.dat.Flight;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author unascribed
 * @version 1.0
 */

public class DeleteFlight extends AbstractDispatcher implements FlightsFormParameters {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    FlightDAO flightDAO = FlightDAO.getInstance();
    String flightIdStr = request.getParameter(FLIGHT_ID);
    Integer flightId = new Integer(Integer.parseInt(flightIdStr));
    Map flights = (Map)session.getAttribute(FLIGHTS);
    Flight flight = (Flight)flights.get(flightId);
    if(flight != null) {
      flightDAO.remove(flight);
      flights.remove(flightId);
    }
    String nextPage = ADMIN_FLIGHTS_HTML;
    redirect(nextPage, request, response);
  }
}