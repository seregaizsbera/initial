package tickets.controller.client;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Набор названий параметров, получаемых из формы заказа билетов
 */
interface OrderFormParameters {
  String FLIGHT_ID = "flightId";
  String CLASS = "class";
  String NUMBER_OF_PLACES = "number_of_places";
  String CREDIT_CARD = "credit_card";
}