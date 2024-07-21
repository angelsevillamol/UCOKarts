<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*" import="es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Listado de pistas - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabla.css" type="text/css">
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" type="text/css">
</head>

<body>
<jsp:include page="/include/adminACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div id="navbar">
	<ul>
  		<li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoUsuariosController")%>">Listado de usuarios</a></li>
        <li><a class="active" href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoPistasController")%>">Listado de pistas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">
    <div class="columna">
    <% 	String mensajeError = request.getParameter("mensajeError");
		if (mensajeError == null) {
			mensajeError = "";
		}
		if (!mensajeError.equals("")) { %>
			<div class="mensaje-error-on"><p><%= mensajeError %></p></div>
	<% } %>
    
        <h1>Listado de pistas</h1>
        <table class="tabla">
    	<thead>
       		<tr>
       			<th>ID.</th>
            	<th>Nombre</th>
            	<th>Disponibilidad</th>
            	<th>Dificultad</th>
            	<th>#máximo de karts</th>
            	<th>#karts asociados</th>
            	<th>Opciones</th>
        	</tr>
    	</thead>
    	<tbody>
    	<%
		ArrayList<PistaDTO> pistas = (ArrayList<PistaDTO>)request.getAttribute("pistas");
		for (PistaDTO p : pistas) { %>
        	<tr>
        		<td><%=p.getId()%></td>
            	<td><a href='<%= "/UCOKarts/Pista?id=" + p.getId() %>'><%= p.getNombre() %></a></td>
            	<td><%=(p.getDisponibilidad()? "Si" : "No")%></td>
            	<td><%=p.getDificultad()%></td>
            	<td><%=p.getMaxKarts()%></td>
            	<td><%=p.getNumKartsAsociados()%></td>
            	<td><a href='<%= "/UCOKarts/ModificarPista?id=" + p.getId() %>'>Modificar</a></td>
        	</tr>
        <% } %>
    	</tbody>
		</table>
    </div>
</div>

</body>
</html>