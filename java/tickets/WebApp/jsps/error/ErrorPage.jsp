<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page import="tickets.util.Util" %>
<%@ page isErrorPage="true" %>
<html>
 <head>
  <title>�� ������ - ���������� ������</title>
 </head>
 <body>
  <h1>��������� ������ �������</h1>
  <br>
  <%
    if(exception==null)
      exception =
          (Exception)request.getAttribute("javax.servlet.error.exception");
    Util.debug(exception);
  %>
  ��� ��������� ������ ������� ��������� ������.<br>
  <p>���������� ������ ������ � ��������
   <a href="<%=request.getContextPath()%>/index.html">�������</a>.
   ��� ��������� �� <a href="javascript:history.back()">���������� ��������</a>.
  </p>
  <h2>
   <%=(exception.getMessage() == null ? "" : exception.getMessage())%>
  </h2>
  <br>
  <p><a href="<%=request.getContextPath()%>/goodbye.html">�����</a></p>
 </body>
</html>
