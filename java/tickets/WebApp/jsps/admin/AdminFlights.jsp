<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="admin_flights_tag" prefix="sergey" %>
<%@ include file="Check.jsp"%>
<html>
 <head>
  <title>АС Билеты - Список рейсов для администратора</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Список рейсов</h1>
  <sergey:adiminFlights/>
 </body>
</html>
