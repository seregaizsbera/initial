package tickets.model.dat;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class Order {
  private int id;
  private int flightId;
  private int numberOfPlaces;
  private int classType;
  private String creditCardNumber;

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
}
