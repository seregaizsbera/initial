package tickets.view.taglib;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import tickets.controller.SessionAttributes;
import tickets.model.dat.Flight;


/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class FlightsTag extends TagSupport implements SessionAttributes {
  private void printFlight(Flight flight, PrintWriter out) {
    int id = flight.getId();
    out.println("<tr align=\"right\">");
    out.println("<td>" + id + "</td>");
    out.println("<td>" + flight.getDepartureCity() + "</td>");
    out.println("<td>" + flight.getArrivalCity() + "</td>");
    out.println("<td>" + flight.getDepartureDate() + "</td>");
    out.println("<td>" + flight.getArrivalDate() + "</td>");
    out.println("<td>" + flight.getCompany() + "</td>");
    out.println("<td>" + flight.getPrice1stClass() + "</td>");
    out.println("<td>" + flight.getPrice2ndClass() + "</td>");
    out.println("<td>" + flight.getAirCraftModel() + "</td>");
    out.println("<td><a href=\"/Tickets" + ORDER_HTML + "?flight_id=" + id + "\">" + "������� �����" + "</a></td>");
    out.println("</tr>");
  }

  public int doStartTag() throws JspException {
    HttpSession session = pageContext.getSession();

    Writer w = pageContext.getOut();
    PrintWriter out = new PrintWriter(w);

    Map flights = (Map)session.getAttribute(FLIGHTS);
    if(flights == null || flights.isEmpty()) {
      out.println("<h2>");
      out.println("������, ��������������� ����� ��������, �� �������.");
      out.println("</h2>");
      return SKIP_BODY;
    }
    out.println("<table>");
    out.println("<th>����� �����</th>");
    out.println("<th>�����</th>");
    out.println("<th>�������</th>");
    out.println("<th>�����</th>");
    out.println("<th>�������</th>");
    out.println("<th>��������</th>");
    out.println("<th>���� 1-�� ������</th>");
    out.println("<th>���� 2-�� ������</th>");
    out.println("<th>�������</th>");
    out.println("<th></th>");
    for(Iterator i = flights.values().iterator(); i.hasNext(); ) {
      Flight flight = (Flight)i.next();
      printFlight(flight, out);
    }
    out.println("</table>");
    return SKIP_BODY;
  }
}
