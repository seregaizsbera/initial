<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<html>
 <head>
  <title>АС Билеты - Поиск рейса</title>
 </head>
 <body>
  <jsp:include page="/banner.html">
   <jsp:param name="sessionType" value="clientSession"/>
  </jsp:include>
  <h1>Поиск рейса</h1>
  <form method="get" action="/Tickets/action.html">
   <table>
    <input type="hidden" name="action" value="search">
    <tr>
     <td align="right">Город вылета:</td>
     <td>
      <select name="departureCity"><sergey:cities/></select>
     </td>
     <td></td><td></td>
    </tr>
    <tr>
     <td align="right">Город назначения:</td>
     <td>
      <select name="arrivalCity"><sergey:cities/></select>
     </td>
     <td></td><td></td>
    </tr>
    <tr>
     <td align="right">День вылета (дд.мм.гггг):</td>
     <td><input type="text" name="departureDate" value="17.07.2000"  maxlength="10" size="10"></td>
     <td align="right">Время вылета (чч:мм):</td>
     <td>
      <select name="departureTimeCondition">
       <option value="1">До</option>
       <option value="2" selected>После</option>
      </select>
      <input type="text" name="departureTime" maxlength="5" size="5">
     </td>
    </tr>
    <tr>
     <td align="right">День прилета (дд.мм.гггг):</td>
     <td><input type="text" name="arrivalDate" maxlength="10" size="10"></td>
     <td align="right">Время посадки (чч:мм):</td>
     <td>
      <select name="arrivalTimeCondition">
       <option value="1" selected>До</option>
       <option value="2">После</option>
      </select>
      <input type="text" name="arrivalTime" maxlength="5" size="5">
     </td>
   </table>
   <input type="submit" value="Поиск"> <input type="reset" value="Сброс">
  </form>
 </body>
</html>
