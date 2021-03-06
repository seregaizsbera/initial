package tickets.controller;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * ����� ������������ ��������� ������ � �� �������� ��������.
 * URI ���� ������������ � ���������� ��������� � JSP.
 */
public interface SessionAttributes {
  String NEW_SESSION = "newSession";
  String CLIENT_SESSION = "clientSession";
  String ADMIN_SESSION = "adminSession";

  String ADMIN_DISPATCHER = "/dispatchers/admin.html";
  String CLIENT_DISPATCHER = "/dispatchers/client.html";
  String LOGIN_DISPATCHER = "/dispatchers/login.html";
  String SEARCH_FLIGHT_SERVLET = "/client/search_flight.html";
  String MAKE_ORDER_SERVLET = "/client/make_order.html";
  String DELETE_FLIGHT_SERVLET = "/admin/delete_flight.html";
  String UPDATE_FLIGHT_SERVLET = "/admin/update_flight.html";
  String INSERT_FLIGHT_SERVLET = "/admin/insert_flight.html";

  String HTML_SUFFIX = ".html";
  String ACTION_HTML = "/action.html";
  String ERROR_HTML = "/error/error.html";
  String ADMIN_FLIGHTS_HTML = "/admin/flights.html";
  String SEARCH_HTML = "/client/search.html";
  String FLIGHTS_HTML = "/client/flights.html";
  String ORDER_RESULT_HTML = "/client/order_result.html";
  String ORDER_HTML = "/client/order.html";

  String SESSION_TYPE = "sessionType";
  String ERROR_MESSAGE = "Error message";
  String LOCALE = "locale";
  String CITIES = "cities";
  String FLIGHTS = "flights";
  String AIRCRAFTS = "aircrafts";
  String ORDER = "order";

  int TIMEOUT = 600;
}
