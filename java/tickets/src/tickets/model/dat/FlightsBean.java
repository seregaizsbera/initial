package tickets.model.dat;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс FlightsBean содержит данные о списке рейсов и позволяет выбрать
 * конкретный рейс по идентификатору рейса
 */
public class FlightsBean {
  private Map flights;
  private int flightId;

  public FlightsBean() {
    flights = new TreeMap();
    flightId = 0;
  }

  public Collection getFlights() {
    return flights.values();
  }

  public void setNewFlight(Flight flight) {
    flights.put(new Integer(flight.getId()), flight);
  }

  public void removeFlight(int flightId) {
    flights.remove(new Integer(flightId));
  }

  public void setFlightId(int flightId) {
    this.flightId = flightId;
  }

  public Flight getFlight() {
    return (Flight)flights.get(new Integer(flightId));
  }

  public boolean isEmpty() {
    return flights.isEmpty();
  }
}
