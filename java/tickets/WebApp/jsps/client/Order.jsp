<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="struts_bean_tag" prefix="bean" %>
<%@ include file="Check.jsp" %>
<jsp:useBean id="flights"
             class="tickets.model.dat.FlightsBean"
             scope="session"
/>
<jsp:setProperty name="flights" property="flightId"/>
<bean:define id="flight"
             name="flights"
             property="flight"
             type="tickets.model.dat.Flight"
             scope="session"
             toScope="page"
/>
<jsp:setProperty name="flight" property="classOfFreePlaces" value="1"/>
<jsp:setProperty name="flight" property="classOfFreePlaces" value="2"/>
<html>
 <head>
  <title>АС Билеты - Заказ</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      var freePlaces = new Array(
         <jsp:getProperty name="flight" property="numberOfFreePlaces1stClass"/>,
         <jsp:getProperty name="flight" property="numberOfFreePlaces2ndClass"/>
      );
      var requiredElements = new Array("number_of_places", "credit_card");
      var requiredNames = new Array("Количество мест", "Банковская карта");
      var classRadio = form["class"];
      var placesClass = 0;
      for(var i = 0; i < classRadio.length; i++)
        if(classRadio[i].checked) {
          placesClass = i;
          break;
        }
      checkPlaces(form["number_of_places"],
                  "Количество мест", freePlaces[placesClass]);
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Введите параметры заказа</h1>
  <h2>Рейс номер <jsp:getProperty name="flight" property="id"/></h2>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="order">
   <input type="hidden"
          name="flightId"
          value="<jsp:getProperty name="flight" property="id"/>">
   <p><b>Класс cалона:</b><small>*</small><br>
    <table cellpadding="5">
     <tr>
      <td><input type="radio" name="class" value="1" checked>Первый</td>
      <td>
       свободных мест - <jsp:getProperty name="flight"
                                         property="numberOfFreePlaces1stClass"/>
      </td>
      <td>цена <jsp:getProperty name="flight" property="price1stClass"/></td>
     </tr>
     <tr>
      <td><input type="radio" name="class" value="2">Второй</td>
      <td>
       свободных мест - <jsp:getProperty name="flight"
                                         property="numberOfFreePlaces2ndClass"/>
      </td>
      <td>цена <jsp:getProperty name="flight" property="price2ndClass"/></td>
     </tr>
    </table>
   </p>
   <p><b>Количество мест:</b><small>*</small><br>
    <table cellpadding="5">
     <tr>
      <td>
       <input type="text"
              name="number_of_places"
              maxlength="3"
              size="10"
              value="1">
      </td>
     </tr>
    </table>
   </p>
   <p><b>Номер банковской карты:</b><small>*</small><br>
    <table cellpadding="5">
     <tr>
      <td>
       <input type="text"
              name="credit_card"
              maxlength="20"
              size="20"
              onChange="javascript:checkCreditCard(this, 'Банковская карта')">
       </td>
      <td align="right"><i>(20 цифр)</i></td>
     </tr>
    </table>
   </p>
   <input type="submit" value="Сделать заказ">
   <input type="reset" value="Сброс">
  </form>
  <hr>
  <a
   href="<%=request.getContextPath()%>/client/search.html">Повоторить поиск</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">В начало</a>
 </body>
</html>
