<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page import="tickets.model.dat.Order" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<html>
 <head>
  <title>�� ������ - ����� ������</title>
 </head>
 <body>
  <jsp:include page="/banner.html">
   <jsp:param name="sessionType" value="clientSession"/>
  </jsp:include>
  <h1>����� ������</h1>
  <%
    Order order = (Order)request.getAttribute(SessionAttributes.ORDER);
  %>
  <p><strong>����� ������ ������ <b><%=order.getId()%></b>.</strong></p>
  <p>����������, ��������� ���. �� ����������� ��� ��������� ������� � ���������.</p>
  <hr>
  <p>���������� ��� �� ����������� ����� ��������.
  <p>�� ������ <a href="/Tickets/client/search.html">�������� ������ ���</a> ��� <a href="/Tickets/goodbye.html">��������� ������ � ��������</a>.
 </body>
</html>
