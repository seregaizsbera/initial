<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="struts_logic_tag" prefix="logic" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>�� ������ - ������ �������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <jsp:useBean id="orders" class="tickets.model.dat.OrdersBean" scope="page"/>
  <h1>���������� ������</h1>
  <logic:notPresent name="orders">
   ������� ���.
  </logic:notPresent>
  <logic:present name="orders">
    <table border cellpadding="5">
    <tr>
     <th>����� ������</th>
     <th>����� �����</th>
     <th>�����</th>
     <th>���������� ����</th>
    </tr>
   <logic:iterate id="order"
                  type="tickets.model.dat.Order"
                  name="orders"
                  property="orders">
    <tr align="right">
     <td><jsp:getProperty name="order" property="id"/></td>
     <td><jsp:getProperty name="order" property="flightId"/></td>
     <td><jsp:getProperty name="order" property="classTypeName"/></td>
     <td><jsp:getProperty name="order" property="numberOfPlaces"/></td>
    </tr>
   </logic:iterate>
   </table>
  </logic:present>
  <hr>
  <a href="<%=request.getContextPath()%>/admin/flights.html">�������� ������</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">� ������</a>
 </body>
</html>
