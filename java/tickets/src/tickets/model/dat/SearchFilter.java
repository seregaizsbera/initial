package tickets.model.dat;

import java.sql.*;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class SearchFilter {
  static public final int BEFORE = 1;
  static public final int AFTER = 2;

  private Timestamp departureTime;
  private Timestamp arrivalTime;
  private int departureCityId;
  private int arrivalCityId;
  private int departureTimeCondition;
  private int arrivalTimeCondition;

  public Timestamp getDepartureTime() {
    return departureTime;
  }

  public Timestamp getArrivalTime() {
    return arrivalTime;
  }

  public int getDepartureCityId() {
    return departureCityId;
  }

  public int getArrivalCityId() {
    return arrivalCityId;
  }

  public int getDepartureTimeCondition() {
    return departureTimeCondition;
  }

  public int getArrivalTimeCondition() {
    return arrivalTimeCondition;
  }

  public void setDepartureTime(Timestamp departureTime) {
    this.departureTime = departureTime;
  }

  public void setArrivalTime(Timestamp arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setDepartureCityId(int departureCityId) {
    this.departureCityId = departureCityId;
  }

  public void setArrivalCityId(int arrivalCityId) {
    this.arrivalCityId = arrivalCityId;
  }

  public void setDepartureTimeCondition(int departureTimeCondition) {
    this.departureTimeCondition = departureTimeCondition;
  }

  public void setArrivalTimeCondition(int arrivalTimeCondition) {
    this.arrivalTimeCondition = arrivalTimeCondition;
  }
}
