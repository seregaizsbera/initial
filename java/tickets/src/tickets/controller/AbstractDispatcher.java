package tickets.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

abstract public class AbstractDispatcher extends HttpServlet implements SessionAttributes {
  static protected boolean isEmpty(String str) {
    return str == null || str.equals("");
  }

  protected void error(String message, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.getSession().setAttribute(ERROR_MESSAGE, message);
    redirect(ERROR_HTML, request, response);
  }

  protected void error(String message, int status, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.sendError(status, message);
  }

  protected void redirect(String nextPage, HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    request.getRequestDispatcher(nextPage).forward(request, response);
  }
}
