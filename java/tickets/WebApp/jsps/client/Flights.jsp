<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="flights_tag" prefix="sergey" %>
<html>
 <head>
  <title>АС Билеты - Список рейсов</title>
 </head>
 <body>
  <jsp:include page="/banner.html">
   <jsp:param name="sessionType" value="clientSession"/>
  </jsp:include>
  <h1>Список рейсов</h1>
  <sergey:flights/>
  <hr>
  <a href="/Tickets/client/search.html">Повоторить поиск</a><br>
  <a href="/Tickets/index.html">В начало</a>
</body>
</html>
