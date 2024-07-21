<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*" import="es.uco.pw.display.javabean.ReservaBean"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Listado de reservas - UCOKarts"/>
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
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoPistasController")%>">Listado de pistas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
        <li><a class="active" href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
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
    
    	<%
    	/**
    	
	private float descuento;
	private Dificultad diff;
    	
    	**/
    	%>
        <h1>Listado de reservas</h1>
        <table class="tabla">
    	<thead>
       		<tr>
       			<th>ID.</th>
       			<th>Usuario</th>
       			<th>Pista</th>
       			<th>Reserva</th>
       			<th>Canjeo</th>
       			<th>Duración</th>
       			<th>Precio</th>
       			<th>#adultos</th>
       			<th>#menores</th>
       			<th>Tipo</th>
        	</tr>
    	</thead>
    	<tbody>
    	<%
		ArrayList<ReservaBean> reservas = (ArrayList<ReservaBean>)request.getAttribute("reservas");
		for (ReservaBean rb : reservas) { %>
        	<tr>
        		<td><%=rb.getId()%></td>
        		<td><%=rb.getIdUsuario() %></td>
        		<td><a href='<%= "/UCOKarts/Pista?id=" + rb.getIdPista() %>'><%= rb.getNombrePista() %></a></td>
        		<td><%=rb.getfechaReserva() %></td>
        		<td><%=rb.getfechaCanjeo() %></td>
        		<td><%=rb.getDuracion() %></td>
        		<td><%=rb.getPrecio() %></td>
        		<td><%=rb.getNumAdultos() %></td>
        		<td><%=rb.getNumMenores() %></td>
        		<td><%=rb.getTipo()? "INDIVIDUAL" : "BONO"%></td>
        	</tr>
        <% } %>
    	</tbody>
		</table>
    </div>
</div>

</body>
</html>