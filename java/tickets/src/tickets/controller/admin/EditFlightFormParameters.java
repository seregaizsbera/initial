package tickets.controller.admin;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Набор названий параметров, получаемых из формы редактирования рейса
 */
interface EditFlightFormParameters {
  String FLIGHT_ID = "flightId";
  String DEPARTURE_DATE = "departure_date";
  String DEPARTURE_TIME = "departure_time";
  String ARRIVAL_DATE = "arrival_date";
  String ARRIVAL_TIME = "arrival_time";
  String DEPARTURE_CITY_ID = "departure_city";
  String ARRIVAL_CITY_ID = "arrival_city";
  String AIRCRAFT_ID = "aircraft";
  String PRICE_1ST_CLASS = "price_1st_class";
  String PRICE_2ND_CLASS = "price_2nd_class";
}
