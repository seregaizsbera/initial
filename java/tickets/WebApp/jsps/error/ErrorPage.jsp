<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page import="tickets.util.Util" %>
<%@ page isErrorPage="true" %>
<html>
 <head>
  <title>АС Билеты - Внутренняя ошибка</title>
 </head>
 <body>
  <h1>Внутрення ошибка сервера</h1>
  <br>
  <%
    if(exception==null)
      exception =
          (Exception)request.getAttribute("javax.servlet.error.exception");
    Util.debug(exception);
  %>
  При обработке Вашего запроса произошла ошибка.<br>
  <p>Попробуйте начать работу с системой
   <a href="<%=request.getContextPath()%>/index.html">сначала</a>.
   или вернитесь на <a href="javascript:history.back()">предыдущую страницу</a>.
  </p>
  <h2>
   <%=(exception.getMessage() == null ? "" : exception.getMessage())%>
  </h2>
  <br>
  <p><a href="<%=request.getContextPath()%>/goodbye.html">Выход</a></p>
 </body>
</html>
