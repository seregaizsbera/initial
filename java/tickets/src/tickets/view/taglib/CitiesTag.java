package tickets.view.taglib;

import java.io.*;
import java.util.*;
import javax.servlet.jsp.tagext.*;
import tickets.model.dat.City;
import tickets.util.Util;
import tickets.controller.SessionAttributes;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 */

public class CitiesTag extends TagSupport implements SessionAttributes {
  public int doStartTag() throws javax.servlet.jsp.JspException {
    try {
      Collection cities = (Collection)pageContext.getSession().getAttribute(CITIES);
      if(cities == null)
        return SKIP_BODY;
      Iterator i = cities.iterator();
      Writer w = pageContext.getOut();
      PrintWriter out = new PrintWriter(w);
      int cnt = 0;
      out.println();
      while(i.hasNext()) {
        City city = (City)i.next();
        out.print("<option value=\"" + city.getId() + "\"");
        if(++cnt == 1)
          out.print(" selected");
        out.print(">");
        out.print(city.getName());
        out.println("</option>");
      }
    }
    catch(Exception e) {
      Util.debug(e);
    }
    return SKIP_BODY;
  }
}
