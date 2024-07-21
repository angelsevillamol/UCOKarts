<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";

if (customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado")) {
	nextPage = application.getInitParameter("loginView");
	%>
	<jsp:forward page="<%=nextPage%>"></jsp:forward>
	<%
} else if (customer.getRol().equals("Usuario")) { 
	nextPage = application.getInitParameter("adminACLErrorView");
	%>
	<jsp:forward page="<%=nextPage%>"></jsp:forward>
	<%
} %>
		