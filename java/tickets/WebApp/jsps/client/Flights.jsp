<%@ page contentType="text/html; charset=windows-1251" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ taglib uri="flights_tag" prefix="sergey" %>
<html>
 <head>
  <title>�� ������ - ������ ������</title>
 </head>
 <body>
  <jsp:include page="/banner.html">
   <jsp:param name="sessionType" value="clientSession"/>
  </jsp:include>
  <h1>������ ������</h1>
  <sergey:flights/>
  <hr>
  <a href="/Tickets/client/search.html">���������� �����</a><br>
  <a href="/Tickets/index.html">� ������</a>
</body>
</html>
