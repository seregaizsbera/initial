package tickets.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.model.dao.ConnectionSource;
import tickets.model.dao.ContainerConnectionSource;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class FrontController extends AbstractDispatcher
                             implements ActionParameters {
  private ConnectionSource connectionSource;
  private Connection connection;

  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getServletPath();
    if(!path.endsWith(HTML_SUFFIX)) {
      error("Страница не найдена", HttpServletResponse.SC_NOT_FOUND, request, response);
      return;
    }

    HttpSession session = request.getSession(true);
    session.setMaxInactiveInterval(TIMEOUT);
    String sessionType = (String)session.getAttribute(SESSION_TYPE);

    Locale locale = (Locale)session.getAttribute(LOCALE);
    if(locale == null) {
      locale = request.getLocale();
      if(locale == null)
        locale = Locale.getDefault();
      session.setAttribute(LOCALE, locale);
    }
    String nextPage = LOGIN_DISPATCHER;

    if(sessionType == NEW_SESSION || sessionType == null)
      nextPage = LOGIN_DISPATCHER;
    else if(sessionType == ADMIN_SESSION)
      nextPage = ADMIN_DISPATCHER;
    else if(sessionType == CLIENT_SESSION)
      nextPage = CLIENT_DISPATCHER;
     redirect(nextPage, request, response);
  }

  public void init() throws javax.servlet.ServletException {
    connectionSource = ContainerConnectionSource.getInstance();
    connection = connectionSource.getConnection();
  }

  public void destroy() {
    connectionSource.close(connection);
  }
}
