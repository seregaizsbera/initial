package tickets.controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tickets.model.dao.OrderDAO;
import tickets.model.dat.Flight;
import tickets.model.dat.FlightsBean;
import tickets.model.dat.Order;
import tickets.controller.AbstractDispatcher;
import tickets.controller.SessionAttributes;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 *  ласс MakeOrder обрабатывает запрос пользовател€ на создание нового заказа
 */
public class MakeOrder extends AbstractDispatcher
    implements OrderFormParameters {
  protected void service(HttpServletRequest request,
                         HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if(session == null) {
      error("—траница устарела", request, response);
      return;
    }
    String sessionType = (String)session.getAttribute(SESSION_TYPE);
    if(sessionType != CLIENT_SESSION) {
      error("—траница устарела", request, response);
      return;
    }
    String classStr = request.getParameter(CLASS);
    String flightIdStr = request.getParameter(FLIGHT_ID);
    String numberOfPlacesStr = request.getParameter(NUMBER_OF_PLACES);
    String creditCard  = request.getParameter(CREDIT_CARD);
    if(Util.isEmpty(classStr) ||
       Util.isEmpty(flightIdStr) ||
       Util.isEmpty(numberOfPlacesStr) ||
       Util.isEmpty(creditCard)) {
      error("”казаны не все параметры заказа", request, response);
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
      error("ѕараметры запроса указаны неверно", request, response);
      return;
    }
    if(creditCard.length() != 20 || numberOfPlaces <= 0) {
      error("ѕараметры запроса указаны неверно", request, response);
      return;
    }

    FlightsBean flights =
        (FlightsBean)session.getAttribute(SessionAttributes.FLIGHTS);
    flights.setFlightId(flightId);
    Flight flight = flights.getFlight();
    int numberOfFreePlaces = (classType == 1) ?
                             flight.getNumberOfFreePlaces1stClass() :
                             flight.getNumberOfFreePlaces2ndClass();
    if(numberOfPlaces > numberOfFreePlaces) {
      error("Ќедостаточно свободных мест дл€ выполнени€ заказа",
            request, response);
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
