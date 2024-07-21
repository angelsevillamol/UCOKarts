<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="es.uco.pw.business.pistas.Dificultad"%>
<%@ page import="es.uco.pw.business.pistas.PistaDTO"%>

<head>
	<% request.setCharacterEncoding("UTF-8"); %>
    <jsp:include page="/include/commonhead.jsp">
			<jsp:param name="title" value="Modificar pista - UCOKarts"/>
	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customselect.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" type="text/css">
</head>

<%
PistaDTO pista = (PistaDTO)request.getAttribute("pista");
if (pista == null) { %>
	<jsp:forward page="/include/errorPage.jsp"/>
<% } %>

<body>
<jsp:include page="/include/adminACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div id="navbar">
	<ul>
  		<li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoUsuariosController")%>">Listado de usuarios</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoPistasController")%>">Listado de pistas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">
    <!-- Columna central -->
    <div class="columna">
    <h1>Modificar pista</h1>
    
    <% 	String mensajeError = request.getParameter("mensajeError");
	if (mensajeError == null) {
		mensajeError = "";
	}
	if (!mensajeError.equals("")) { %>
		<div class="mensaje-error-on"><p><%= mensajeError %></p></div>
	<% } %>
    
    <form action="/UCOKarts/ModificarPista" method="POST" accept-charset="UTF-8">
        <div class="caja">
        	<h3>Nombre</h3>
            <input type="text"  placeholder="Nombre" id="nombre" name="nombre" value='<%= pista.getNombre() %>' readonly="readonly"/>
            
            <h3>Tipo de pista</h3>
            <input type="text"  placeholder="Dificultad" id="dificultad" name="dificultad" value='<%= pista.getDificultad() %>' disabled/>
            
            <h3>Disponibilidad</h3>
            <div class="custom-select">
            <select name="disponibilidad" id="disponibilidad">
            	<option value='<%= pista.getDisponibilidad()? "true" : "false" %>'><%= pista.getDisponibilidad()? "Disponible" : "No disponible" %></option>
            	<option value="true">Disponible</option>
            	<option value="false">No disponible</option>
            </select>
            </div>
            
            <h3>Número máximo de karts</h3>
            <input type="number" placeholder="Número máximo de karts" id="maxKarts" name="maxKarts" min="1" value='<%= pista.getMaxKarts() %>' disabled/>
            
            <h3>Descripción</h3>
            <div class="contenedor-txtarea"><textarea placeholder="Descripción" id="descripcion" name="descripcion" cols="" rows="" disabled><%= pista.getDescripcion() %></textarea></div>

            <div><input type="submit" value="Aceptar" id="submit-button"/></div>
        </div>
       </form>
   </div>
</div>

<script src="${pageContext.request.contextPath}/js/customselect.js"></script>
</body>