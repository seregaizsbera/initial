<%@ page isErrorPage="true" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<html>
 <head>
  <title>АС Билеты - Внутренняя ошибка</title>
 </head>
 <body>
  <h1>Внутрення ошибка сервера</h1>
  <br>
  <%
    if(exception==null)
      exception = (Exception)request.getAttribute("javax.servlet.error.exception");
  %>
  При обработке Вашего запроса произошла ошибка.<br>
  <p>Попробуйте начать работу с системой <a href="/Tickets/index.html">сначала</a>.
  или вернитесь на <a href="javascript:history.back()">предыдущую страницу</a>.
  </p>
  <h2><%=exception.getMessage()%></h2><br>
  <font color="red"><pre><%
    java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
    java.io.PrintWriter pw = new java.io.PrintWriter(cw, true);
    exception.printStackTrace(pw);
    out.println(cw.toString());
  %></pre></font><br>
  <p><a href="/Tickets/goodbye.html">Выход</a></p>
 </body>
</html>
