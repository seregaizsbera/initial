<%@ page import="java.util.*" %>
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page import="tickets.model.dat.Flight" %>
<%@ page import="tickets.controller.client.OrderFormParameters" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>АС Билеты - Заказ</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Введите параметры заказа</h1>
  <%
    Map flights = (Map)session.getAttribute(SessionAttributes.FLIGHTS);
    Integer flightId = new Integer(Integer.parseInt(request.getParameter(OrderFormParameters.FLIGHT_ID)));
    Flight flight = (Flight)flights.get(flightId);
  %>
  <h2>Рейс номер <%=flight.getId()%></h2>
  <form method="post" action="/Tickets/action.html">
   <input type="hidden" name="action" value="order">
   <input type="hidden" name="flight_id" value="<%=flightId%>">
   <table>
    <tr>
     <td align="right"><p>Класс:</td>
     <td>
      <select name="class">
       <option value="1" selected>Первый</option>
       <option value="2">Второй</option>
      </select>
     </td>
    </tr>
    <tr>
     <td align="right"><p>Количество мест:</td>
     <td>
      <input type="text" name="number_of_places" maxlength="3" size="3" value="1">
     </td>
    <tr>
     <td align="right"><p>Номер кредитной карточки:</td>
     <td>
      <input type="text" name="credit_card" maxlength="20" size="20">
     </td>
    </tr>
   </table>
   <input type="submit" value="Сделать заказ">
   <input type="reset" value="Сброс">
  </form>
  <hr>
  <a href="/Tickets/client/search.html">Повоторить поиск</a><br>
  <a href="/Tickets/index.html">В начало</a>
 </body>
</html>
