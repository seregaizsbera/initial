package tickets.model.dat;

import tickets.model.dao.FreePlacesDAO;
import java.io.Serializable;
import tickets.model.valueobjects.Currency;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс Flight содержит данные о рейсе
 */
public class Flight implements Serializable {
  private String departureDate;
  private String arrivalDate;
  private String departureTime;
  private String arrivalTime;
  private String departureCity;
  private String arrivalCity;
  private Currency price1stClass;
  private Currency price2ndClass;
  private String airCraftModel;
  private String company;
  private int count1stClass;
  private int count2ndClass;
  private int id;
  private int idDepartureCity;
  private int idArrivalCity;
  private int idAircraft;
  private int numbersOfFreePlaces[] = new int[2];

  private String correctDate(String date) {
    if(date == null)
      return null;
    String result = date;
    if(date.indexOf("/") != -1)
      result = date.substring(0, 2) + "." + date.substring(3, 5) + "." +
               date.substring(6, 10);
    if(date.indexOf("-") != -1)
      result = date.substring(8, 10) + "." + date.substring(5, 7) + "." +
               date.substring(0, 4);
    return result;
  }

  private String correctTime(String time) {
    if(time == null)
      return null;
    String result = time.substring(0, 5);
    return result;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public String getDepartureCity() {
    return departureCity;
  }

  public String getArrivalCity() {
    return arrivalCity;
  }

  public Currency getPrice1stClass() {
    return price1stClass;
  }

  public Currency getPrice2ndClass() {
    return price2ndClass;
  }

  public String getAirCraftModel() {
    return airCraftModel;
  }

  public String getCompany() {
    return company;
  }

  public int getCount1stClass() {
    return count1stClass;
  }

  public int getCount2ndClass() {
    return count2ndClass;
  }

  public int getId() {
    return id;
  }

  public int getIdDepartureCity() {
    return idDepartureCity;
  }

  public int getIdArrivalCity() {
    return idArrivalCity;
  }

  public int getIdAircraft() {
    return idAircraft;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = correctDate(departureDate);
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = correctTime(departureTime);
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = correctDate(arrivalDate);
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = correctTime(arrivalTime);
  }

  public void setDepartureCity(String departureCity) {
    this.departureCity = departureCity;
  }

  public void setArrivalCity(String arrivalCity) {
    this.arrivalCity = arrivalCity;
  }

  public void setPrice1stClass(Currency price1stClass) {
    this.price1stClass = price1stClass;
  }

  public void setPrice2ndClass(Currency price2ndClass) {
    this.price2ndClass = price2ndClass;
  }

  public void setAirCraftModel(String airCraftModel) {
    this.airCraftModel = airCraftModel;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setCount1stClass(int count1stClass) {
    this.count1stClass = count1stClass;
  }

  public void setCount2ndClass(int count2ndClass) {
    this.count2ndClass = count2ndClass;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setIdDepartureCity(int idDepartureCity) {
    this.idDepartureCity = idDepartureCity;
  }

  public void setIdArrivalCity(int idArrivalCity) {
    this.idArrivalCity = idArrivalCity;
  }

  public void setIdAircraft(int idAircraft) {
    this.idAircraft = idAircraft;
  }

  public void setClassOfFreePlaces(int placesClass) {
    FreePlacesDAO freePlacesDAO = FreePlacesDAO.getInstance();
    numbersOfFreePlaces[placesClass - 1] =
        freePlacesDAO.getNumberOfFreePlaces(placesClass, id);
  }

  public int getNumberOfFreePlaces1stClass() {
    return numbersOfFreePlaces[0];
  }

  public int getNumberOfFreePlaces2ndClass() {
    return numbersOfFreePlaces[1];
  }
}
