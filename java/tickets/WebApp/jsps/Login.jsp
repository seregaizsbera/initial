<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%
  session.setAttribute(SessionAttributes.SESSION_TYPE, SessionAttributes.NEW_SESSION);
%>
<html>
 <head>
  <title>АС Билеты - Регистрация</title>
 </head>
 <body>
  <h1>Представьтесь, пожалуйста.</h1>
  <form method="post" action="/Tickets/action.html">
   <input type="hidden" name="action" value="login">
   <table>
    <tr>
     <td>Имя</td>
     <td><input type=text name="key" value="Гость"></td>
    </tr>
    <tr>
     <td>Пароль</td>
     <td><input type=password name="value" value=""></td>
    </tr>
   </table>
   <table>
    <tr>
     <td>
      <input type=submit value="Начать работу">
     </td>
     <td>
      <input type=reset value="Сброс">
     </td>
    </tr>
   </table>
  </form>
 </body>
</html>
