package tickets.model.dat;

import java.util.Collection;
import tickets.model.dao.OrderListDAO;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс OrdersBean содержит данные о списке заказов
 */
public class OrdersBean {
  private Collection orders;

  public OrdersBean() {
    OrderListDAO orderListDAO = OrderListDAO.getInstance();
    orders = orderListDAO.getOrders();
  }

  public Collection getOrders() {
    return orders;
  }
}
