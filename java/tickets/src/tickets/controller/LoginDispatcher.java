package tickets.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tickets.util.Param;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 *  ласс LoginDispatcher обрабатывает запросы пользовател€, полученные до
 * определени€ режима работы с системой
 */
public class LoginDispatcher extends AbstractDispatcher
    implements LoginFormParameters{
  private Param param;

  protected void service(HttpServletRequest request,
                         HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null) {
      error("—траница устарела", request, response);
      return;
    }
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != NEW_SESSION) {
      error("—траница устарела", request, response);
      return;
    }
    String userName = request.getParameter(USER_NAME);
    if(userName == null) {
      error("—траница устарела", request, response);
      return;
    }
    String passwd = request.getParameter(PASSWORD);
    int password = passwd == null ? 0 : passwd.hashCode();
    if(userName.equalsIgnoreCase(param.getAdminName())) {
      if(password != param.getAdminPassword()) {
        error("Ќеправильное им€ пользовател€ или пароль", request, response);
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
