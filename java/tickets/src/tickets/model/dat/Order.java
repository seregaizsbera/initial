package tickets.model.dat;

import tickets.model.valueobjects.Currency;
/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс Order содержит данные о заказе
 */
public class Order {
  private int id;
  private int flightId;
  private int numberOfPlaces;
  private int classType;
  private String creditCardNumber;
  private Currency price;

  public int getFlightId() {
    return flightId;
  }

  public int getNumberOfPlaces() {
    return numberOfPlaces;
  }

  public int getClassType() {
    return classType;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public int getId() {
    return id;
  }

  public String getClassTypeName() {
    if(classType == 1)
      return "Первый";
    else
      return "Второй";
  }

  public void setFlightId(int flightId) {
    this.flightId = flightId;
  }

  public void setNumberOfPlaces(int numberOfPlaces) {
    this.numberOfPlaces = numberOfPlaces;
  }

  public void setClassType(int classType) {
    this.classType = classType;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setPrice(Currency price) {
    this.price = price;
  }

  public Currency getPrice() {
    return price;
  }
}
