package tickets.controller;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Ќабор значений параметра ACTION, передаваемых в класс FrontController
 */
public interface ActionParameters {
  String ACTION = "action";
  String LOGIN_ACTION = "login";
  String SEARCH_ACTION = "search";
  String ORDER_ACTION = "order";
  String DELETE_FLIGHT_ACTION = "delete";
  String UPDATE_FLIGHT_ACTION = "update";
  String INSERT_FLIGHT_ACTION = "insert";
}
