<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<html>
 <head>
  <title>�� ������ - ����� �����</title>
 </head>
 <body>
  <jsp:include page="/banner.html">
   <jsp:param name="sessionType" value="clientSession"/>
  </jsp:include>
  <h1>����� �����</h1>
  <form method="get" action="/Tickets/action.html">
   <table>
    <input type="hidden" name="action" value="search">
    <tr>
     <td align="right">����� ������:</td>
     <td>
      <select name="departureCity"><sergey:cities/></select>
     </td>
     <td></td><td></td>
    </tr>
    <tr>
     <td align="right">����� ����������:</td>
     <td>
      <select name="arrivalCity"><sergey:cities/></select>
     </td>
     <td></td><td></td>
    </tr>
    <tr>
     <td align="right">���� ������ (��.��.����):</td>
     <td><input type="text" name="departureDate" value="17.07.2000"  maxlength="10" size="10"></td>
     <td align="right">����� ������ (��:��):</td>
     <td>
      <select name="departureTimeCondition">
       <option value="1">��</option>
       <option value="2" selected>�����</option>
      </select>
      <input type="text" name="departureTime" maxlength="5" size="5">
     </td>
    </tr>
    <tr>
     <td align="right">���� ������� (��.��.����):</td>
     <td><input type="text" name="arrivalDate" maxlength="10" size="10"></td>
     <td align="right">����� ������� (��:��):</td>
     <td>
      <select name="arrivalTimeCondition">
       <option value="1" selected>��</option>
       <option value="2">�����</option>
      </select>
      <input type="text" name="arrivalTime" maxlength="5" size="5">
     </td>
   </table>
   <input type="submit" value="�����"> <input type="reset" value="�����">
  </form>
 </body>
</html>
