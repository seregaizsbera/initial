package tickets.controller.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import tickets.model.dat.Order;
import tickets.model.dao.OrderDAO;
import tickets.controller.AbstractDispatcher;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class MakeOrder extends AbstractDispatcher implements OrderFormParameters {
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession();
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != CLIENT_SESSION) {
      error("Страница устарела", request, response);
      return;
    }
    String classStr = request.getParameter(CLASS);
    String flightIdStr = request.getParameter(FLIGHT_ID);
    String numberOfPlacesStr = request.getParameter(NUMBER_OF_PLACES);
    String creditCard  = request.getParameter(CREDIT_CARD);
    if(isEmpty(classStr) || isEmpty(flightIdStr) ||
       isEmpty(numberOfPlacesStr) || isEmpty(creditCard)) {
      error("Указаны не все параметры заказа", request, response);
      return;
    }
    int classType = 0;
    int flightId = 0;
    int numberOfPlaces = 0;
    try {
      classType = Integer.parseInt(classStr);
      flightId = Integer.parseInt(flightIdStr);
      numberOfPlaces = Integer.parseInt(numberOfPlacesStr);
    }
    catch(NumberFormatException e) {
      error("Параметры запроса указаны неверно", request, response);
      return;
    }
    if(creditCard.length() != 20) {
      error("Параметры запроса указаны неверно", request, response);
      return;
    }
    Order order = new Order();
    order.setClassType(classType);
    order.setCreditCardNumber(creditCard);
    order.setFlightId(flightId);
    order.setNumberOfPlaces(numberOfPlaces);

    OrderDAO.getInstance().create(order);
    request.setAttribute(ORDER, order);

    redirect(ORDER_RESULT_HTML, request, response);
  }
}
