<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<%@ taglib uri="aircrafts_tag" prefix="custom" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>АС Билеты - Новый рейс</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      var requiredElements = new Array("departure_date",
                                       "departure_time",
                                       "arrival_date",
                                       "arrival_time",
                                       "price_1st_class",
                                       "price_2nd_class"
                                       );
      var requiredNames = new Array("День вылета",
                                    "Время взлета",
                                    "День прилета",
                                    "Время приземления",
                                    "Цена 1-го класса",
                                    "Цена 2-го класса");
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Введите параметры рейса</h1>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="insert">
   <table cellpadding="5">
    <tr>
     <td>Номер</td>
     <td><i>(Задается автоматически)</i></td>
    </tr>
    <tr>
     <td>День вылета</td>
     <td>
      <input type="text"
             maxlength="10"
             size="10"
             name="departure_date"
             onChange="javascript:checkDate(this, 'День вылета')"
      > <i>(дд.мм.гггг)</i><small>*</small>
     </td>
    </tr>
    <tr>
     <td>Время взлета</td>
     <td>
      <input type="text"
             maxlength="5"
             size="5"
             name="departure_time"
             onChange="javascript:checkTime(this, 'Время взлета')"
      > <i>(чч:мм)</i><small>*</small>
     </td>
    </tr>
    <tr>
    </tr>
     <td>День прилета</td>
     <td>
      <input type="text"
             maxlength="10"
             size="10"
             name="arrival_date"
             onChange="javascript:checkDate(this, 'День прилета')"
      > <i>(дд.мм.гггг)</i><small>*</small>
     </td>
    <tr>
    </tr>
     <td>Время приземления</td>
     <td>
      <input type="text"
             maxlength="5"
             size="5"
             name="arrival_time"
             onChange="javascript:checkTime(this, 'Время приземления')"
      > <i>(чч:мм)</i><small>*</small>
     </td>
    </tr>
    <tr>
     <td>Город отправления</td>
     <td>
      <select name="departure_city">
       <sergey:cities/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td>Город прибытия</td>
     <td>
      <select name="arrival_city">
       <sergey:cities/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td>Самолет</td>
     <td>
      <select name="aircraft">
        <custom:aircrafts/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td>Цена билетов на места 1-го класса</td>
     <td>
      <input type="text"
             name="price_1st_class"
             size="10"
             maxlength="10"
             onChange="javascript:checkPrice(this, 'Цена 1-го класса')"
      > <small>*</small>
     </td>
    <tr>
     <td>Цена билетов на места 2-го класса</td>
     <td>
      <input type="text"
             name="price_2nd_class"
             size="10"
             maxlength="10"
             onChange="javascript:checkPrice(this, 'Цена 2-го класса')"
      > <small>*</small>
     </td>
    </tr>
    </tr>
    <tr align="center">
     <td><input type="submit" value="Сохранить"></td>
     <td><input type="reset" value="Восстановить"></td>
    <tr>
   </table>
  </form>
  <hr>
  <a href="<%=request.getContextPath()%>/admin/flights.html">Просмотр рейсов</a>
  <br>
 <a href="<%=request.getContextPath()%>/admin/orders.html">Заказанные билеты</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">В начало</a>
 </body>
</html>
