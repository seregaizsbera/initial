<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="java.util.*" %>
<%@ page import="tickets.model.dat.SearchFilter" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="cities_tag" prefix="sergey" %>
<%@ include file="Check.jsp" %>
<html>
 <head>
  <title>�� ������ - ����� �����</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      return checkLinked(form, "departureDate", "���� ������", "departureTime",
                         "����� ������", "����� ������") &&
             checkLinked(form, "arrivalDate", "���� �������", "arrivalTime",
                         "����� �����������", "����� �������") &&
             checkProblems();
    }
  --></script>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>����� �����</h1>
  <form method="get"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <table cellpadding="5">
    <input type="hidden" name="action" value="search">
    <tr>
     <td>����� ������:</td>
     <td colspan="3">
      <select name="departureCity">
       <sergey:cities without="�� ������"/>
      </select>
     </td>
     <td></td><td></td><td></td>
    </tr>
    <tr>
     <td>����� ����������:</td>
     <td colspan="3">
      <select name="arrivalCity">
       <sergey:cities without="�� ������"/>
      </select>
     </td>
     <td></td><td></td><td></td>
    </tr>
    <tr>
     <td>���� ������:</td>
     <td>
      <input type="text"
             name="departureDate"
             maxlength="10"
             size="10"
             onChange="javascript:checkDate(this, '���� ������')">
     </td>
     <td><i>(��.��.����)</i></td>
     <td></td>
     <td>����� ������:</td>
     <td>
      <select name="departureTimeCondition">
       <option value="1">��</option>
       <option value="2" selected>�����</option>
      </select>
      <input type="text"
             name="departureTime"
             maxlength="5"
             size="5"
             onChange="javascript:checkTime(this, '����� ������')">
     </td>
     <td><i>(��:��)</i><td>
    </tr>
    <tr>
     <td>���� �������:</td>
     <td>
      <input type="text"
             name="arrivalDate"
             maxlength="10"
             size="10"
             onChange="javascript:checkDate(this, '���� �������')">
     </td>
     <td><i>(��.��.����)</i></td>
     <td></td>
     <td>����� �������:</td>
     <td>
      <select name="arrivalTimeCondition">
       <option value="1" selected>��</option>
       <option value="2">�����</option>
      </select>
      <input type="text"
             name="arrivalTime"
             maxlength="5"
             size="5"
             onChange="javascript:checkTime(this, '����� �������')">
     </td>
     <td><i>(��:��)</i></td>
    </tr>
    <tr align="center">
     <td><input type="submit" value="�����"></td>
     <td colspan="2"><input type="reset" value="�����"></td>
    </tr>
   </table>
  </form>
 </body>
</html>
