<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<% session.invalidate(); %>
<html>
 <head>
  <title>АС Билеты - До свидания</title>
 </head>
 <body>
  <h1>Работа с системой завершена.</h1>
  <p>Спасибо за визит.
   Приходите <a href="<%=request.getContextPath()%>/index.html">еще</a>.
  </p>
 </body>
</html>
