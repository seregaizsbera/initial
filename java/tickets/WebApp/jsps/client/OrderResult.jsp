<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.model.dat.Order" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ include file="Check.jsp" %>
<jsp:useBean id="order" class="tickets.model.dat.Order" scope="request"/>
<html>
 <head>
  <title>�� ������ - ����� ������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>����� ������</h1>
  <p><strong>����� ������ ������
   <b><jsp:getProperty name="order" property="id"/></b>.</strong>
  </p>
  <p>����������, ��������� ���.
   �� ����������� ��� ��������� ������� � ���������.
  </p>
  <p>�� ����� ����� ���������� ����� ����� ���������� ������ �� �����
   <strong><jsp:getProperty name="order" property="price"/></strong>
  </p>
  <hr>
  <p>���������� ��� �� ����������� ����� ��������.</p>
  <p>�� ������
   <a href="<%=request.getContextPath()%>/client/search.html">��������
    ������ ���
   </a> ���
   <a href="<%=request.getContextPath()%>/goodbye.html">��������� ������ �
    ��������
   </a>.
  </p>
 </body>
</html>
