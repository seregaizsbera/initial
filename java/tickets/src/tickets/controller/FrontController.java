package tickets.controller;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 *  ласс FrontController обрабатывает все пользовательские запросы,
 * перенаправл€€ их куда следует
 */
public class FrontController extends AbstractDispatcher
                             implements ActionParameters {
  protected void service(HttpServletRequest request,
                         HttpServletResponse response)
      throws ServletException, IOException {
    String path = request.getServletPath();
    if(!path.endsWith(HTML_SUFFIX)) {
      error("—траница не найдена", HttpServletResponse.SC_NOT_FOUND,
            request, response);
      return;
    }

    HttpSession session = request.getSession(true);
    if(session.isNew())
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
}
