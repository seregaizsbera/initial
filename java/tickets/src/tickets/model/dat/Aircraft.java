package tickets.model.dat;

import java.io.Serializable;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс Aircraft содержит данные о рейсе
 */
public class Aircraft implements Serializable {
  private int id;
  private String model;
  private String company;

  public int getId() {
    return id;
  }

  public String getDescription() {
    return "No. " + id + " " + model + " от \"" + company + "\"";
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setModel(String model) {
    this.model = model;
  }
}
