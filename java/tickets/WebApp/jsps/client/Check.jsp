<%@ page import="tickets.controller.SessionAttributes" %>
<%
  String sessionType = (String)session.getAttribute(SessionAttributes.SESSION_TYPE);
  if(sessionType == null) {
    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Страница устарела");
    return;
  }
  if(!sessionType.equals(SessionAttributes.CLIENT_SESSION)) {
    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Страница устарела");
    return;
  }
%>
