<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<html>
 <head>
  <title>АС Билеты - Ошибка</title>
 </head>
 <body>
  <h1>
   К сожалению, страница не может быть отображена из-за возникшей ошибки.
  </h1>
  <p>Попробуйте начать работу с системой
   <a href="<%=request.getContextPath()%>/index.html">сначала</a>.
   или вернитесь на <a href="javascript:history.back()">предыдущую страницу</a>.
  </p>
  <%
    String errorMessage =
        (String)request.getAttribute(SessionAttributes.ERROR_MESSAGE);
    if(errorMessage != null) {
  %>
  <hr>
  <h2><%=errorMessage%></h2>
  <%
    }
  %>
  <p><a href="<%=request.getContextPath()%>/goodbye.html">Выход</a></p>
 </body>
</html>
