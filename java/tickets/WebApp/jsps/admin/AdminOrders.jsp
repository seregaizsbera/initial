<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="admin_orders_tag" prefix="sergey" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>�� ������ - ������ �������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>���������� ������</h1>
  <sergey:adminOrders/>
  <hr>
  <a href="/Tickets/admin/flights.html">�������� ������</a><br>
  <a href="/Tickets/index.html">� ������</a>
 </body>
</html>
