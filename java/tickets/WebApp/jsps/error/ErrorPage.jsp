<%@ page isErrorPage="true" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<html>
 <head>
  <title>�� ������ - ���������� ������</title>
 </head>
 <body>
  <h1>��������� ������ �������</h1>
  <br>
  <%
    if(exception==null)
      exception = (Exception)request.getAttribute("javax.servlet.error.exception");
  %>
  ��� ��������� ������ ������� ��������� ������.<br>
  <p>���������� ������ ������ � �������� <a href="/Tickets/index.html">�������</a>.
  ��� ��������� �� <a href="javascript:history.back()">���������� ��������</a>.
  </p>
  <h2><%=exception.getMessage()%></h2><br>
  <font color="red"><pre><%
    java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
    java.io.PrintWriter pw = new java.io.PrintWriter(cw, true);
    exception.printStackTrace(pw);
    out.println(cw.toString());
  %></pre></font><br>
  <p><a href="/Tickets/goodbye.html">�����</a></p>
 </body>
</html>
