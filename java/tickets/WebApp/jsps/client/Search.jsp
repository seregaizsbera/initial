<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="java.util.*" %>
<%@ page import="tickets.model.dat.SearchFilter" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>АС Билеты - Поиск рейса</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      return checkLinked(form, "departureDate", "День вылета", "departureTime",
                         "Время взлета", "время вылета") &&
             checkLinked(form, "arrivalDate", "День прилета", "arrivalTime",
                         "Время приземления", "время прилета") &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Поиск рейса</h1>
  <form method="get"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <table cellpadding="5">
    <input type="hidden" name="action" value="search">
    <tr>
     <td>Город вылета:</td>
     <td colspan="3">
      <select name="departureCity">
       <sergey:cities without="Не указан"/>
      </select>
     </td>
     <td></td><td></td><td></td>
    </tr>
    <tr>
     <td>Город назначения:</td>
     <td colspan="3">
      <select name="arrivalCity">
       <sergey:cities without="Не указан"/>
      </select>
     </td>
     <td></td><td></td><td></td>
    </tr>
    <tr>
     <td>День вылета:</td>
     <td>
      <input type="text"
             name="departureDate"
             maxlength="10"
             size="10"
             onChange="javascript:checkDate(this, 'День вылета')">
     </td>
     <td><i>(дд.мм.гггг)</i></td>
     <td></td>
     <td>Время вылета:</td>
     <td>
      <select name="departureTimeCondition">
       <option value="1">До</option>
       <option value="2" selected>После</option>
      </select>
      <input type="text"
             name="departureTime"
             maxlength="5"
             size="5"
             onChange="javascript:checkTime(this, 'Время взлета')">
     </td>
     <td><i>(чч:мм)</i><td>
    </tr>
    <tr>
     <td>День прилета:</td>
     <td>
      <input type="text"
             name="arrivalDate"
             maxlength="10"
             size="10"
             onChange="javascript:checkDate(this, 'День прилета')">
     </td>
     <td><i>(дд.мм.гггг)</i></td>
     <td></td>
     <td>Время посадки:</td>
     <td>
      <select name="arrivalTimeCondition">
       <option value="1" selected>До</option>
       <option value="2">После</option>
      </select>
      <input type="text"
             name="arrivalTime"
             maxlength="5"
             size="5"
             onChange="javascript:checkTime(this, 'Время посадки')">
     </td>
     <td><i>(чч:мм)</i></td>
    </tr>
    <tr align="center">
     <td><input type="submit" value="Поиск"></td>
     <td colspan="2"><input type="reset" value="Сброс"></td>
    </tr>
   </table>
  </form>
 </body>
</html>
