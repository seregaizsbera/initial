<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<html>
 <head>
  <title>�� ������ - ������</title>
 </head>
 <body>
  <h1>� ���������, �������� �� ����� ���� ���������� ��-�� ��������� ������.</h1>
  <p>���������� ������ ������ � �������� <a href="/Tickets/index.html">�������</a>.
  ��� ��������� �� <a href="javascript:history.back()">���������� ��������</a>.
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
  <p><a href="/Tickets/goodbye.html">�����</a></p>
 </body>
</html>
