<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page import="tickets.model.dat.Order" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>АС Билеты - Заказ сделан</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Заказ сделан</h1>
  <%
    Order order = (Order)request.getAttribute(SessionAttributes.ORDER);
  %>
  <p><strong>Номер Вашего заказа <b><%=order.getId()%></b>.</strong></p>
  <p>Пожалуйста, запомните его. Он понадобится при получении билетов в аэропорте.</p>
  <hr>
  <p>Благодарим Вас за пользование нашей системой.
  <p>Вы можете <a href="/Tickets/client/search.html">заказать билеты еще</a> или <a href="/Tickets/goodbye.html">завершить работу с системой</a>.
 </body>
</html>
