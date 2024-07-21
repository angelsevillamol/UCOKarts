<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-escalable=no, initial-scale=1, maximum-scale=1. minimum-scale=1 "/>
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Poppins" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<link rel="icon" href="${pageContext.request.contextPath}/img/logo.png" type="image/png">
<title>${param.title}</title>

<% if (customer == null || customer.getEmail() == "") {
	customer = new es.uco.pw.display.javabean.CustomerBean();
	session.setAttribute("customer", customer);
} %>
