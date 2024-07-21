<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";

// Si el usuario no ha iniciado sesión.
if (customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado")) {
	nextPage = application.getInitParameter("loginrView");
	%>
	<jsp:forward page="<%=nextPage%>"></jsp:forward>
<% } %>