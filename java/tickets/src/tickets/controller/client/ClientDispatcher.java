package tickets.controller.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.controller.AbstractDispatcher;
import tickets.controller.ActionParameters;
import tickets.model.dao.CitiesListDAO;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class ClientDispatcher extends AbstractDispatcher implements ActionParameters {
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != CLIENT_SESSION) {
      error("Страница устарела", request, response);
      return;
    }
    String nextPage = SEARCH_HTML;
    String action = request.getParameter(ACTION);
    if(action == null || action.equals(LOGIN_ACTION)) {
      CitiesListDAO citiesListDAO = CitiesListDAO.getInstance();
      session.setAttribute(CITIES, citiesListDAO.getCities());
    } else if(action.equals(SEARCH_ACTION))
      nextPage = SEARCH_FLIGHT_SERVLET;
    else if(action.equals(ORDER_ACTION))
      nextPage = MAKE_ORDER_SERVLET;
    redirect(nextPage, request, response);
  }
}
