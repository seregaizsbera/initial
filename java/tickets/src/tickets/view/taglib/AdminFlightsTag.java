package tickets.view.taglib;

import java.io.*;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.*;
import tickets.util.Util;
import tickets.model.dat.Flight;
import tickets.controller.SessionAttributes;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class AdminFlightsTag extends TagSupport implements SessionAttributes {
  private void printFlight(Flight flight, JspWriter out) throws IOException {
    out.println("<tr align=right>");
    out.println("<form action=/Tickets/action.html method=post>");
    out.println("<td>" + flight.getId() + "</td>");
    out.println("<td>" + flight.getDepartureCity() + "</td>");
    out.println("<td>" + flight.getArrivalCity() + "</td>");
    out.println("<td>" + flight.getDepartureDate() + "<br>" + flight.getDepartureTime() + "</td>");
    out.println("<td>" + flight.getArrivalDate() + "<br>" + flight.getArrivalTime() + "</td>");
    out.println("<td>" + flight.getCompany() + "</td>");
    out.println("<td>" + flight.getPrice1stClass() + "</td>");
    out.println("<td>" + flight.getPrice2ndClass() + "</td>");
    out.println("<td>" + flight.getAirCraftModel() + "</td>");
    out.println("<td>");
    out.println("<input type=hidden name=flight_id value=" + flight.getId() + ">");
    out.println("<input type=hidden name=action value=delete>");
    out.println("<input type=submit value=Удалить onClick=\"return confirm('Вы уверены?')\">");
    out.println("</td>");
    out.println("<td><a href=/Tickets/admin/edit_flight.html?flight_id=" + flight.getId() + ">Редактировать</a></td>");
    out.println("</form>");
    out.println("</tr>");
  }

  public int doStartTag() throws JspException {
    HttpSession session = pageContext.getSession();
    try {
      JspWriter out = pageContext.getOut();
      Map flights = (Map)session.getAttribute(FLIGHTS);
      if(flights == null || flights.isEmpty()) {
        out.println("<h2>");
        out.println("Рейсов, удовлетворяющих Вашим условиям, не нашлось.");
        out.println("</h2>");
        return SKIP_BODY;
      }
      out.println("<table border cellpadding=5>");
      out.println("<th>Номер рейса</th>");
      out.println("<th>Взлет</th>");
      out.println("<th>Посадка</th>");
      out.println("<th>Взлет</th>");
      out.println("<th>Посадка</th>");
      out.println("<th>Компания</th>");
      out.println("<th>Цена 1-го класса</th>");
      out.println("<th>Цена 2-го класса</th>");
      out.println("<th>Самолет</th>");
      out.println("<th></th>");
      out.println("<td><a href=/Tickets/admin/edit_flight.html>Создать новый</a></td>");
      for(Iterator i = flights.values().iterator(); i.hasNext(); ) {
        Flight flight = (Flight)i.next();
        printFlight(flight, out);
      }
      out.println("</table>");
    }
    catch(IOException e) {
      Util.debug(e);
    }
    return SKIP_BODY;
  }
}
