<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<% session.setAttribute(SessionAttributes.SESSION_TYPE,
        SessionAttributes.NEW_SESSION); %>
<html>
 <head>
  <title>АС Билеты - Вход в систему</title>
  <script language="javascript"
          src="<%=request.getContextPath()%>/validate_form.js">
  </script>
  <script language="javascript"><!--
    function validateForm(form) {
      var requiredElements = new Array("key");
      var requiredNames = new Array("Имя пользователя");
      return requireValues(form, requiredElements, requiredNames) &&
             checkProblems(form);
    }
  --></script>
 </head>
 <body>
  <h1>Представьтесь, пожалуйста.</h1>
  <form method="post"
        action="<%=request.getContextPath()%>/action.html"
        onSubmit="javascript:return validateForm(this)">
   <input type="hidden" name="action" value="login">
   <table cellpadding="5">
    <tr>
     <td>Имя</td>
     <td><input type="text" name="key" value="Гость"> <small>*</small></td>
    </tr>
    <tr>
     <td>Пароль</td>
     <td>
      <input type="password" name="value" value=""> <small>&sup1;</small>
     </td>
    </tr>
   </table>
   <table cellpadding="5">
    <tr align="center">
     <td>
      <input type="submit" value="Начать работу">
     </td>
     <td>
      <input type="reset" value="Сброс">
     </td>
    </tr>
   </table>
  </form>
  <hr>
  <small>* Звездочкой обозначены обязательные поля</small><br>
  <small>&sup1; Пароль необходим для администратора</small>
 </body>
</html>
