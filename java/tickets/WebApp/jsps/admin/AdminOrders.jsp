<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="admin_orders_tag" prefix="sergey" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>АС Билеты - Список заказов</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Заказанные билеты</h1>
  <sergey:adminOrders/>
  <hr>
  <a href="/Tickets/admin/flights.html">Просмотр рейсов</a><br>
  <a href="/Tickets/index.html">В начало</a>
 </body>
</html>
