package tickets.view.taglib;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import tickets.model.dat.City;
import tickets.model.dat.SearchFilter;
import tickets.util.Util;
import tickets.controller.SessionAttributes;

/**
 * <p>Title: Tickets</p>
 * <p>Description: Tickets Ordering System</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Sberbank</p>
 * @author Sergey Bogdanov
 * @version 1.0
 *
 * Класс CitiesTag обрабатывает пользовательский тэг cities
 */
public class CitiesTag extends TagSupport implements SessionAttributes {
  private int selected = SearchFilter.NOT_SPECIFIED;
  private String without = null;

  public void setSelected(Integer value) {
    selected = value.intValue();
  }

  public void setSelected(int value) {
    selected = value;
  }

  public void setSelected(String value) {
    selected = Integer.parseInt(value);
  }

  public void setWithout(String value) {
    without = value;
  }

  public int doStartTag() throws JspException {
    try {
      HttpSession session = pageContext.getSession();
      Collection cities = (Collection)session.getAttribute(CITIES);
      if(cities == null)
        return SKIP_BODY;
      Iterator i = cities.iterator();
      JspWriter out = pageContext.getOut();
      out.println();
      if(!Util.isEmpty(without)) {
        out.print("<option value=" + SearchFilter.NOT_SPECIFIED);
        out.print(selected == SearchFilter.NOT_SPECIFIED ? " selected>" : ">");
        out.print(without);
        out.println("</option>");
      }
      while(i.hasNext()) {
        City city = (City)i.next();
        int cityId = city.getId();
        out.print("<option value=\"" + cityId + "\"");
        if(cityId == selected)
          out.print(" selected");
        out.print(">");
        out.print(city.getName());
        out.println("</option>");
      }
    }
    catch(IOException e) {
      Util.debug(e);
    }
    return SKIP_BODY;
  }
}
