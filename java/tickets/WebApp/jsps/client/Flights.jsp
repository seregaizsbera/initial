<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="struts_logic_tag" prefix="logic" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>АС Билеты - Список рейсов</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <logic:notPresent name="flights">
   <h2>Рейсов, удовлетворяющих Вашим условиям, не нашлось.</h2>
  </logic:notPresent>
  <logic:present name="flights">
   <h1>Список рейсов</h1>
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
     <th></th>
    </tr>
    <logic:iterate id="flight"
                   type="tickets.model.dat.Flight"
                   property="flights"
                   name="flights">
     <tr align="right">
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
      <td nowrap>
<a
 href="<%=request.getContextPath()%>/client/order.html?flightId=<jsp:getProperty
     name="flight"
     property="id"/>">Сделать заказ</a>
      </td>
     </tr>
    </logic:iterate>
   </table>
  </logic:present>
  <hr>
 <a href="<%=request.getContextPath()%>/client/search.html">Повоторить поиск</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">В начало</a>
 </body>
</html>
