<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<%@ taglib uri="aircrafts_tag" prefix="custom" %>
<%@ taglib uri="struts_bean_tag" prefix="bean" %>
<%@ include file="Check.jsp" %>
<jsp:include page="/banner.html"/>
<jsp:useBean id="flights"
             class="tickets.model.dat.FlightsBean"
             scope="session"/>
<jsp:setProperty name="flights" property="flightId"/>
<bean:define id="flight"
             name="flights"
             property="flight"
             type="tickets.model.dat.Flight"
             scope="session"
             toScope="page"
/>
<html>
 <head>
   <title>�� ������ - �������������� �����</title>
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
      var requiredNames = new Array("���� ������",
                                    "����� ������",
                                    "���� �������",
                                    "����� �����������",
                                    "���� 1-�� ������",
                                    "���� 2-�� ������");
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <h1>������� ��������� �����</h1>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="update">
   <input type="hidden"
          name="flightId"
          value="<jsp:getProperty name="flight" property="id"/>">
   <table cellpadding="5">
    <tr>
     <td>�����</td>
     <td><jsp:getProperty name="flight" property="id"/></td>
    </tr>
    <tr>
     <td>���� ������</td>
     <td>
      <input type="text"
             maxlength="10"
             size="10"
             name="departure_date"
             value="<jsp:getProperty name="flight" property="departureDate"/>"
             onChange="javascript:checkDate(this, '���� ������')"
      > <i>(��.��.����)</i><small>*</small>
     </td>
    </tr>
    <tr>
     <td>����� ������</td>
     <td>
      <input type="text"
             maxlength="5"
             size="5"
             name="departure_time"
             value="<jsp:getProperty name="flight" property="departureTime"/>"
             onChange="javascript:checkTime(this, '����� ������')"
      > <i>(��:��)</i><small>*</small>
     </td>
    </tr>
    <tr>
    </tr>
     <td>���� �������</td>
     <td>
      <input type="text"
             maxlength="10"
             size="10"
             name="arrival_date"
             value="<jsp:getProperty name="flight" property="arrivalDate"/>"
             onChange="javascript:checkDate(this, '���� �������')"
      > <i>(��.��.����)</i><small>*</small>
     </td>
    <tr>
    </tr>
     <td>����� �����������</td>
     <td>
      <input type="text"
             maxlength="5"
             size="5"
             name="arrival_time"
             value="<jsp:getProperty name="flight" property="arrivalTime"/>"
             onChange="javascript:checkTime(this, '����� �����������')"
      > <i>(��:��)</i><small>*</small>
     </td>
    </tr>
    <tr>
     <td>����� �����������</td>
     <td>
      <select name="departure_city">
       <sergey:cities selected="<%=flight.getIdDepartureCity()%>"/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td align>����� ��������</td>
     <td>
      <select name="arrival_city">
       <sergey:cities selected="<%=flight.getIdArrivalCity()%>"/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td>�������</td>
     <td>
      <select name="aircraft">
        <custom:aircrafts selected="<%=flight.getIdAircraft()%>"/>
      </select> <small>*</small>
     </td>
    </tr>
    <tr>
     <td>���� ������� �� ����� 1-�� ������</td>
     <td>
      <input type="text"
             size="10"
             name="price_1st_class"
             value="<jsp:getProperty name="flight" property="price1stClass"/>"
             maxlength="10"
             onChange="javascript:checkPrice(this, '���� 1-�� ������')"
      > <small>*</small>
     </td>
    <tr>
     <td>���� ������� �� ����� 2-�� ������</td>
     <td>
      <input type="text"
             size="10"
             name="price_2nd_class"
             value="<jsp:getProperty name="flight" property="price2ndClass"/>"
             maxlength="10"
             onChange="javascript:checkPrice(this, '���� 2-�� ������')"
      > <small>*</small>
     </td>
    </tr>
    </tr>
    <tr align="center">
     <td><input type="submit" value="���������"></td>
     <td><input type="reset" value="������������"></td>
    <tr>
   </table>
  </form>
  <hr>
  <a href="<%=request.getContextPath()%>/admin/flights.html">�������� ������</a>
  <br>
 <a href="<%=request.getContextPath()%>/admin/orders.html">���������� ������</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">� ������</a>
 </body>
</html>
