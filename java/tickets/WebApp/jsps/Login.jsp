<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<% session.setAttribute(SessionAttributes.SESSION_TYPE,
        SessionAttributes.NEW_SESSION); %>
<html>
 <head>
  <title>�� ������ - ���� � �������</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      var requiredElements = new Array("key");
      var requiredNames = new Array("��� ������������");
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems(form);
    }
  --></script>
 </head>
 <body>
  <h1>�������������, ����������.</h1>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="login">
   <table cellpadding="5">
    <tr>
     <td>���</td>
     <td><input type="text" name="key" value="�����"> <small>*</small></td>
    </tr>
    <tr>
     <td>������</td>
     <td>
      <input type="password" name="value" value=""> <small>&sup1;</small>
     </td>
    </tr>
   </table>
   <table cellpadding="5">
    <tr align="center">
     <td>
      <input type="submit" value="������ ������">
     </td>
     <td>
      <input type="reset" value="�����">
     </td>
    </tr>
   </table>
  </form>
  <hr>
  <small>* ���������� ���������� ������������ ����</small><br>
  <small>&sup1; ������ ��������� ��� ��������������</small>
 </body>
</html>
