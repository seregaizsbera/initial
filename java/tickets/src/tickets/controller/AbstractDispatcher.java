package tickets.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс AbstractDispatcher предоставляет полезные методы сервлетам,
 * которые его наследуют
 */
abstract public class AbstractDispatcher extends HttpServlet
    implements SessionAttributes {
  protected void error(String message,
                       HttpServletRequest request,
                       HttpServletResponse response)
      throws IOException, ServletException {
    request.setAttribute(ERROR_MESSAGE, message);
    redirect(ERROR_HTML, request, response);
  }

  protected void error(String message,
                       int status,
                       HttpServletRequest request,
                       HttpServletResponse response)
      throws IOException, ServletException {
    response.sendError(status, message);
  }

  protected void redirect(String nextPage,
                          HttpServletRequest request,
                          HttpServletResponse response)
    throws IOException, ServletException {
    request.getRequestDispatcher(nextPage).forward(request, response);
  }
}
