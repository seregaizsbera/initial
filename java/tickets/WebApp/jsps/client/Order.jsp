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
  <title>�� ������ - �����</title>
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
      var requiredNames = new Array("���������� ����", "���������� �����");
      var classRadio = form["class"];
      var placesClass = 0;
      for(var i = 0; i < classRadio.length; i++)
        if(classRadio[i].checked) {
          placesClass = i;
          break;
        }
      checkPlaces(form["number_of_places"],
                  "���������� ����", freePlaces[placesClass]);
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>������� ��������� ������</h1>
  <h2>���� ����� <jsp:getProperty name="flight" property="id"/></h2>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="order">
   <input type="hidden"
          name="flightId"
          value="<jsp:getProperty name="flight" property="id"/>">
   <p><b>����� c�����:</b><small>*</small><br>
    <table cellpadding="5">
     <tr>
      <td><input type="radio" name="class" value="1" checked>������</td>
      <td>
       ��������� ���� - <jsp:getProperty name="flight"
                                         property="numberOfFreePlaces1stClass"/>
      </td>
      <td>���� <jsp:getProperty name="flight" property="price1stClass"/></td>
     </tr>
     <tr>
      <td><input type="radio" name="class" value="2">������</td>
      <td>
       ��������� ���� - <jsp:getProperty name="flight"
                                         property="numberOfFreePlaces2ndClass"/>
      </td>
      <td>���� <jsp:getProperty name="flight" property="price2ndClass"/></td>
     </tr>
    </table>
   </p>
   <p><b>���������� ����:</b><small>*</small><br>
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
   <p><b>����� ���������� �����:</b><small>*</small><br>
    <table cellpadding="5">
     <tr>
      <td>
       <input type="text"
              name="credit_card"
              maxlength="20"
              size="20"
              onChange="javascript:checkCreditCard(this, '���������� �����')">
       </td>
      <td align="right"><i>(20 ����)</i></td>
     </tr>
    </table>
   </p>
   <input type="submit" value="������� �����">
   <input type="reset" value="�����">
  </form>
  <hr>
  <a
   href="<%=request.getContextPath()%>/client/search.html">���������� �����</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">� ������</a>
 </body>
</html>
