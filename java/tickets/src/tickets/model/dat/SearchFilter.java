package tickets.model.dat;

import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс SearchFilter содержит данные о поисковом фильтре
 */
public class SearchFilter {
  static public final int BEFORE = 1;
  static public final int AFTER = 2;
  static public final int NOT_SPECIFIED = 0;

  private String departureTime;
  private String arrivalTime;
  private String departureDate;
  private String arrivalDate;
  private int departureCityId;
  private int arrivalCityId;
  private int departureTimeCondition;
  private int arrivalTimeCondition;

  public String getDepartureTime() {
    return departureTime;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public String getArrivalDate() {
    return arrivalDate;
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

  public void setDepartureTime(String departureTime) {
    this.departureTime = Util.isEmpty(departureTime) ? null : departureTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = Util.isEmpty(arrivalTime) ? null : arrivalTime;
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

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = Util.isEmpty(arrivalDate) ? null : arrivalDate;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = Util.isEmpty(departureDate) ? null : departureDate;
  }
}
