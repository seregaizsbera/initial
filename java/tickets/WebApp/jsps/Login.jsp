<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%
  session.setAttribute(SessionAttributes.SESSION_TYPE, SessionAttributes.NEW_SESSION);
%>
<html>
 <head>
  <title>�� ������ - �����������</title>
 </head>
 <body>
  <h1>�������������, ����������.</h1>
  <form method="post" action="/Tickets/action.html">
   <input type="hidden" name="action" value="login">
   <table>
    <tr>
     <td>���</td>
     <td><input type=text name="key" value="�����"></td>
    </tr>
    <tr>
     <td>������</td>
     <td><input type=password name="value" value=""></td>
    </tr>
   </table>
   <table>
    <tr>
     <td>
      <input type=submit value="������ ������">
     </td>
     <td>
      <input type=reset value="�����">
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
