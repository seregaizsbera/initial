<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="struts_logic_tag" prefix="logic" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>�� ������ - ������ ������ ��� ��������������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>������ ������</h1>
  <logic:notPresent name="flights">
   <h2>� ��������� ����� ������ ���.</h2>
 <a href="<%=request.getContextPath()%>/admin/new_flight.html">������� �����</a>
  </logic:notPresent>
  <logic:present name="flights">
   <table border cellpadding="5">
    <tr>
     <th>����� �����</th>
     <th>�����</th>
     <th>�������</th>
     <th>�����</th>
     <th>�������</th>
     <th>��������</th>
     <th>���� 1-�� ������</th>
     <th>���� 2-�� ������</th>
     <th>�������</th>
     <td nowrap>
 <a href="<%=request.getContextPath()%>/admin/new_flight.html">������� �����</a>
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
                 property="id"/>">�������������</a>
       </td>
       <td>
        <input type="hidden"
                     name="flightId"
                     value="<jsp:getProperty name="flight" property="id"/>">
        <input type="hidden" name="action" value="delete">
        <input type="submit"
               value="�������"
onClick="javascript:return confirm('�� �������, ��� ������ ������� ���� ����� <jsp:getProperty
                         name="flight" property="id"/> � ��� ������ �� ����?')">
       </td>
      </form>
     </tr>
    </logic:iterate>
   </table>
  </logic:present>
  <hr>
 <a href="<%=request.getContextPath()%>/admin/orders.html">���������� ������</a>
  <br>
  <a href="<%=request.getContextPath()%>/index.html">� ������</a>
 </body>
</html>
