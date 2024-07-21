<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<link rel="stylesheet" href="/UCOKarts/css/header.css">

<!-- Cabecera de la página -->
<header>
<a href="/UCOKarts/Home" class="logo">UCOKARTS</a>
<%
	// Usuario no logado -> Se invoca al controlador de la funcionalidad
	if (customer == null || customer.getEmail()=="") { %>
		<div class="dropdown" style="float:right;">
  			<a href="${pageContext.request.contextPath}<%=application.getInitParameter("loginView")%>"><button class="dropbtn">Iniciar sesión</button></a>
		</div>
	<% } else { %>
	<div class="dropdown" style="float:right;">
  		<button class="dropbtn"><%=customer.getNombre() + " " + customer.getApellidos() %></button>
  		<div class="dropdown-content">
  			<a href="${pageContext.request.contextPath}<%=application.getInitParameter("perfilController")%>">Ver perfil</a>
  			<a href="${pageContext.request.contextPath}<%=application.getInitParameter("editarPerfilView")%>">Editar perfil</a>
  			<% if (customer.getRol().equals("Administrador")) { %>
	        	<a href="${pageContext.request.contextPath}<%=application.getInitParameter("panelAdminView")%>">Panel de administración</a>
	        <%  } %>
  			<a href="${pageContext.request.contextPath}<%=application.getInitParameter("logoutController")%>">Cerrar sesión</a>
  		</div>
	</div>
	<% } %>
	
</header>