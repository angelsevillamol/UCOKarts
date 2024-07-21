<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<jsp:setProperty property="id" value="0" name="customer"/>
<jsp:setProperty property="nombre" value="" name="customer"/>
<jsp:setProperty property="apellidos" value="" name="customer"/>
<jsp:setProperty property="email" value="" name="customer"/>
<jsp:setProperty property="edad" value="0" name="customer"/>
<jsp:setProperty property="antiguedad" value="0" name="customer"/>
<jsp:setProperty property="rol" value="Invitado" name="customer"/>
<% 
request.getSession().removeAttribute("customer");
String nextPage = application.getInitParameter("index");
%>
<jsp:forward page="<%=nextPage%>"/>
