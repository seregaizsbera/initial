<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.controller.SessionAttributes" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<html>
 <head>
  <title>�� ������ - ������</title>
 </head>
 <body>
  <h1>
   � ���������, �������� �� ����� ���� ���������� ��-�� ��������� ������.
  </h1>
  <p>���������� ������ ������ � ��������
   <a href="<%=request.getContextPath()%>/index.html">�������</a>.
   ��� ��������� �� <a href="javascript:history.back()">���������� ��������</a>.
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
  <p><a href="<%=request.getContextPath()%>/goodbye.html">�����</a></p>
 </body>
</html>
