package tickets.controller.admin;

import java.util.*;
import javax.servlet.http.*;
import tickets.controller.AbstractDispatcher;
import tickets.controller.SessionAttributes;
import tickets.model.dao.*;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class AdminDispatcher extends AbstractDispatcher {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    HttpSession session = request.getSession();
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != ADMIN_SESSION) {
      error("Страница устарела", request, response);
      return;
    }

    FlightListDAO flightListDao = FlightListDAO.getInstance();
    Map flights = flightListDao.FindFlights(null);
    session.setAttribute(FLIGHTS, flights);

    String nextPage = ADMIN_FLIGHTS_HTML;
    redirect(nextPage, request, response);
  }
}
