<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 3.2//EN">
<%@ page import="tickets.model.dat.Order" %>
<%@ page errorPage="/error/error_page.html" %>
<%@ page contentType="text/html; charset=windows-1251" %>
<%@ include file="Check.jsp" %>
<jsp:useBean id="order" class="tickets.model.dat.Order" scope="request"/>
<html>
 <head>
  <title>АС Билеты - Заказ сделан</title>
 </head>
 <body>
  <jsp:include page="/banner.html"/>
  <h1>Заказ сделан</h1>
  <p><strong>Номер Вашего заказа
   <b><jsp:getProperty name="order" property="id"/></b>.</strong>
  </p>
  <p>Пожалуйста, запомните его.
   Он понадобится при получении билетов в аэропорте.
  </p>
  <p>Со счета Вашей банковской карты будет произведен платеж на сумму
   <strong><jsp:getProperty name="order" property="price"/></strong>
  </p>
  <hr>
  <p>Благодарим Вас за пользование нашей системой.</p>
  <p>Вы можете
   <a href="<%=request.getContextPath()%>/client/search.html">заказать
    билеты еще
   </a> или
   <a href="<%=request.getContextPath()%>/goodbye.html">завершить работу с
    системой
   </a>.
  </p>
 </body>
</html>
