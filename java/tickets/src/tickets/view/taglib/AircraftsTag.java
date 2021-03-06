package tickets.view.taglib;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import tickets.model.dat.Aircraft;
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
 * ����� AircraftsTag ������������ ���������������� ��� aircrafts
 */
public class AircraftsTag extends TagSupport implements SessionAttributes {
  private int selected = 0;

  public void setSelected(Integer value) {
    selected = value.intValue();
  }

  public void setSelected(String value) {
    selected = Integer.parseInt(value);
  }

  public void setSelected(int value) {
    selected = value;
  }

  public int doStartTag() throws JspException {
    try {
      HttpSession session = pageContext.getSession();
      Collection aircrafts = (Collection)session.getAttribute(AIRCRAFTS);
      if(aircrafts == null)
        return SKIP_BODY;
      Iterator i = aircrafts.iterator();
      JspWriter out = pageContext.getOut();
      out.println();
      while(i.hasNext()) {
        Aircraft aircraft = (Aircraft)i.next();
        int aircraftId = aircraft.getId();
        out.print("<option value=\"" + aircraftId + "\"");
        if(aircraftId == selected)
          out.print(" selected");
        out.print(">");
        out.print(aircraft.getDescription());
        out.println("</option>");
      }
    }
    catch(IOException e) {
      Util.debug(e);
    }
    return SKIP_BODY;
  }
}

