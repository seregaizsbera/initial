<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="admin_flights_tag" prefix="sergey" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>�� ������ - ������ ������ ��� ��������������</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>������ ������</h1>
  <sergey:adiminFlights/>
 </body>
</html>
