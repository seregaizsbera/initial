package tickets.model.dat;

import java.io.Serializable;
import java.sql.*;
import tickets.model.valueobjects.Currency;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class Flight implements Serializable {
  private Timestamp departureDate;
  private Timestamp arrivalDate;
  private String departureCity;
  private String arrivalCity;
  private Currency price1stClass;
  private Currency price2ndClass;
  private String airCraftModel;
  private String company;
  private int count1stClass;
  private int count2ndClass;
  private int id;

  public Timestamp getDepartureDate() {
    return departureDate;
  }

  public Timestamp getArrivalDate() {
    return arrivalDate;
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

  public void setDepartureDate(Timestamp departureDate) {
    this.departureDate = departureDate;
  }

  public void setArrivalDate(Timestamp arrivalDate) {
    this.arrivalDate = arrivalDate;
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
}
