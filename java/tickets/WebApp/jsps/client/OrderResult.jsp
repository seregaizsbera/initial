<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page import="tickets.model.dat.Order" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>�� ������ - ����� ������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
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
