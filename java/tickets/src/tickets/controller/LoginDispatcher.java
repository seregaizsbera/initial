package tickets.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.util.Param;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class LoginDispatcher extends AbstractDispatcher implements LoginFormParameters{
  private Param param;

  protected void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    HttpSession session = request.getSession();
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != NEW_SESSION) {
      error("Страница устарела", request, response);
      return;
    }
    String userName = request.getParameter(USER_NAME);
    if(userName == null) {
      error("Страница устарела", request, response);
      return;
    }
    String passwd = request.getParameter(PASSWORD);
    int password = passwd == null ? 0 : passwd.hashCode();
    if(userName.equalsIgnoreCase(param.getAdminName())) {
      if(password != param.getAdminPassword()) {
        error("Неправильное имя пользователя или пароль", request, response);
        return;
      }
      session.setAttribute(SESSION_TYPE, ADMIN_SESSION);
    } else
      session.setAttribute(SESSION_TYPE, CLIENT_SESSION);
    redirect(ACTION_HTML, request, response);
  }

  public void init() throws javax.servlet.ServletException {
    param = Param.getInstance();
  }
}
