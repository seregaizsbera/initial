<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<html>
 <head>
  <title>АС Билеты - Ошибка</title>
 </head>
 <body>
  <h1>К сожалению, страница не может быть отображена из-за возникшей ошибки.</h1>
  <p>Попробуйте начать работу с системой <a href="/Tickets/index.html">сначала</a>.
  или вернитесь на <a href="javascript:history.back()">предыдущую страницу</a>.
  <%
    String errorMessage = (String)session.getAttribute(SessionAttributes.ERROR_MESSAGE);
    if(errorMessage != null) {
  %>
  <hr>
  <h2><%=errorMessage%></h2>
  <%
    }
    session.setAttribute(SessionAttributes.ERROR_MESSAGE, null);
  %>
  <p><a href="/Tickets/goodbye.html">Выход</a></p>
 </body>
</html>
