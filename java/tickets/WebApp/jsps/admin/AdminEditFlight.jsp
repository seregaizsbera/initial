<%@ page import="java.util.*" %>
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page import="tickets.model.dat.Flight" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<%@ taglib uri="aircrafts_tag" prefix="custom" %>
<%@ include file="Check.jsp"%>
<%
  boolean isNew = false;
  Flight flight = null;
  Map flights = (Map)session.getAttribute(SessionAttributes.FLIGHTS);
  String flightIdStr = request.getParameter("flight_id");
  if(flightIdStr != null)
    flight = (Flight)flights.get(new Integer(Integer.parseInt(flightIdStr)));
  if(flight == null) {
    isNew = true;
    flight = new Flight();
  }
%>
<html>
 <head>
  <% if(isNew) { %>
   <title>АС Билеты - Новый рейс</title>
  <% } else { %>
   <title>АС Билеты - Редактирование рейса</title>
  <% } %>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Введите параметры рейса</h1>

  <form method="post" action="/Tickets/action.html">
   <input type="hidden" name="action" value=<%=isNew?"new":"update"%>>
   <input type="hidden" name="flight_id" value=<%=flight.getId()%>>
   <table cellpadding=5>
    <tr>
     <td align="right">Номер</td>
     <td><%=isNew?"<small>(Задается автоматически)</small>":flight.getId()+""%></td>
    </tr>
    <tr>
     <td align="right">День взлета (дд.мм.гггг)</td>
     <td><input type="text" maxlength="10" size="10" name="departure_date" value=<%=isNew?"":flight.getDepartureDate()%>></td>
    </tr>
    <tr>
     <td align="right">Время взлета (чч:мм)</td>
     <td><input type="text" maxlength="5" size="5" name="departure_time" value=<%=isNew?"":flight.getDepartureTime()%>></td>
    </tr>
    <tr>
    </tr>
     <td align="right">День посадки (дд.мм.гггг)</td>
     <td><input type="text" maxlength="10" size="10" name="arrival_date" value=<%=isNew?"":flight.getArrivalDate()%>></td>
    <tr>
    </tr>
     <td align="right">Время посадки (чч:мм)</td>
     <td><input type="text" maxlength="5" size="5" name="arrival_time" value=<%=isNew?"":flight.getArrivalTime()%>></td>
    </tr>
    <tr>
     <td align="right">Город отправления</td>
     <td>
      <select name="departure_city">
       <sergey:cities selected="<%=flight.getIdDepartureCity()%>"/>
      </select>
     </td>
    </tr>
    <tr>
     <td align="right">Город прибытия</td>
     <td>
      <select name="arrival_city">
       <sergey:cities selected="<%=flight.getIdArrivalCity()%>"/>
      </select>
     </td>
    </tr>
    <tr>
     <td align="right">Самолет</td>
     <td>
      <select name="aircraft">
        <custom:aircrafts selected="<%=flight.getIdAircraft()%>"/>
      </select>
     </td>
    </tr>
    <tr>
     <td>Цена билетов на места 1-го класса</td>
     <td><input type=text name="price_1st_class" value="<%=isNew?"0.00":flight.getPrice1stClass().toString()%>" size=10></td>
    <tr>
     <td>Цена билетов на места 2-го класса</td>
     <td><input type=text name="price_2nd_class" value="<%=isNew?"0.00":flight.getPrice2ndClass().toString()%>" size=10></td>
    </tr>
    </tr>
    <tr align=center>
     <td><input type=submit value="Сохранить"></td>
     <td><input type=reset value="Восстановить"></td>
    <tr>
   </table>
  </form>
  <hr>
  <a href="/Tickets/admin/flights.html">Просмотр рейсов</a><br>
  <a href="/Tickets/admin/orders.html">Заказанные билеты</a><br>
  <a href="/Tickets/index.html">В начало</a>
 </body>
</html>
