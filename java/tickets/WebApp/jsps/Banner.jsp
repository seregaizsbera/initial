<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page errorPage="/error/error_page.html" %>
<table>
 <tr>
  <td align="left" width="90%"><h1>��� ����, � � - � ���������!</h1></td>
  <td align="right" width="10%"><a href="/Tickets/goodbye.html">�����</a></td>
 </tr>
</table>
<%
  String sessionTypeParam = request.getParameter(SessionAttributes.SESSION_TYPE);
  String sessionType = (String)session.getAttribute(SessionAttributes.SESSION_TYPE);
  if(sessionType == null) {
    response.sendError(500, "�������� ��������");
    return;
  }
  if(!sessionTypeParam.equals(sessionType)) {
    response.sendError(500, "�������� ��������");
    return;
  }
%>
<hr>
