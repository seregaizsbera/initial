package tickets.view.taglib;

import java.io.*;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import tickets.model.dao.OrderListDAO;
import tickets.model.dat.Order;
import tickets.util.Util;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class AdminOrdersTag extends TagSupport {
  public int doStartTag() throws javax.servlet.jsp.JspException {
    try {
      OrderListDAO orderListDAO = OrderListDAO.getInstance();
      Collection orders = orderListDAO.getOrders();
      JspWriter out = pageContext.getOut();
      if(orders.isEmpty()) {
        out.println("Заказов нет");
        return SKIP_BODY;
      }
      out.println("<table border cellpadding=5>");
      out.println("<tr><th>Номер заказа</th>");
      out.println("<th>Номер рейса</th>");
      out.println("<th>Класс</th>");
      out.println("<th>Количество мест</th>");
      out.println("</tr>");
      for(Iterator i = orders.iterator(); i.hasNext();) {
        Order order = (Order)i.next();
        out.println("<tr align=\"right\">");
        out.println("<td>" + order.getId() + "</td>");
        out.println("<td>" + order.getFlightId() + "</td>");
        out.println("<td>" + order.getClassTypeName() + "</td>");
        out.println("<td>" + order.getNumberOfPlaces() + "</td>");
        out.println("</tr>");
      }
      out.println("</table>");
    }
    catch(IOException e) {
      Util.debug(e);
    }
    return SKIP_BODY;
  }
}
