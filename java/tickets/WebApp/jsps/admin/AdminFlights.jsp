<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="struts_logic_tag" prefix="logic" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>АС Билеты - Список рейсов для администратора</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Список рейсов</h1>
  <logic:notPresent name="flights">
   <h2>В настоящее время рейсов нет.</h2>
 <a href="<%=request.getContextPath()%>/admin/new_flight.html">Создать новый</a>
  </logic:notPresent>
  <logic:present name="flights">
   <table border cellpadding="5">
    <tr>
     <th>Номер рейса</th>
     <th>Взлет</th>
     <th>Посадка</th>
     <th>Взлет</th>
     <th>Посадка</th>
     <th>Компания</th>
     <th>Цена 1-го класса</th>
     <th>Цена 2-го класса</th>
     <th>Самолет</th>
     <td nowrap>
 <a href="<%=request.getContextPath()%>/admin/new_flight.html">Создать новый</a>
     </td>
     <th></th>
    </tr>
    <logic:iterate id="flight"
                   type="tickets.model.dat.Flight"
                   property="flights"
                   name="flights">
     <tr align=right>
      <form action="<%=request.getContextPath()%>/action.html" method="post>">
       <td><jsp:getProperty name="flight" property="id"/></td>
       <td><jsp:getProperty name="flight" property="departureCity"/></td>
       <td><jsp:getProperty name="flight" property="arrivalCity"/></td>
       <td>
        <jsp:getProperty name="flight" property="departureDate"/><br>
        <jsp:getProperty name="flight" property="departureTime"/>
       </td>
       <td>
        <jsp:getProperty name="flight" property="arrivalDate"/><br>
        <jsp:getProperty name="flight" property="arrivalTime"/>
       </td>
       <td><jsp:getProperty name="flight" property="company"/></td>
       <td><jsp:getProperty name="flight" property="price1stClass"/></td>
       <td><jsp:getProperty name="flight" property="price2ndClass"/></td>
       <td><jsp:getProperty name="flight" property="airCraftModel"/></td>
       <td>
        <a href="<%=request.
             getContextPath()%>/admin/edit_flight.html?flightId=<jsp:getProperty
                 name="flight"
                 property="id"/>">Редактировать</a>
       </td>
       <td>
        <input type="hidden"
                     name="flightId"
                     value="<jsp:getProperty name="flight" property="id"/>">
        <input type="hidden" name="action" value="delete">
        <input type="submit"
               value="Удалить"
onClick="javascript:return confirm('Вы уверены, что хотите удалить рейс номер <jsp:getProperty
                         name="flight" property="id"/> и все заказы на него?')">
       </td>
      </form>
     </tr>
    </logic:iterate>
   </table>
  </logic:present>
  <hr>
 <a href="<%=request.getContextPath()%>/admin/orders.html">Заказанные билеты</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">В начало</a>
 </body>
</html>
