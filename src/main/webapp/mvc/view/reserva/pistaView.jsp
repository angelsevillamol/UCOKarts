<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp"%>
<%@ page import="es.uco.pw.business.pistas.Dificultad"%>
<%@ page import="es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE html>
<html>

<%
PistaDTO pista = (PistaDTO)request.getAttribute("pista");
if (pista == null) { %>
	<jsp:forward page="/include/errorPage.jsp"/>
<% } %>

<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value='<%= pista.getNombre() + " - UCOKarts"%>'/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
   	<script src="${pageContext.request.contextPath}/js/validarPista.js"></script>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<!-- Información de la pista -->
<div class="columnas">
    <!-- Columna izquierda -->
    <div class="col-izquierda">
        <!-- información de contacto -->
        <div class="caja">
            <table class="tab-informacion">
                <tr><td><span class="etiqueta">Nombre: </span> <span class="valor-etiqueta"><%= pista.getNombre() %></span></td></tr>
                <tr><td><span class="etiqueta">Identificador: </span> <span class="valor-etiqueta"><%= pista.getId() %></span></td></tr>
                <tr><td><span class="etiqueta">Dificultad: </span> <span class="valor-etiqueta"><%= pista.getDificultad() %></span></td></tr>
                <tr><td><span class="etiqueta">Maximo de Karts: </span> <span class="valor-etiqueta"><%= pista.getMaxKarts() %></span></td></tr>
                <tr><td><span class="etiqueta">Karts asociados: </span> <span class="valor-etiqueta"><%= pista.getNumKartsAsociados() %></span></td></tr>
                <%
				if (pista.getDificultad() == Dificultad.FAMILIAR) {%>
					<tr><td><span class="etiqueta">Karts infantiles disponibles: </span> <span class="valor-etiqueta"><%= pista.getNumKartsInfantilesDisponibles() %></span></td></tr>
					<tr><td><span class="etiqueta">Karts adultos disponibles: </span> <span class="valor-etiqueta"><%= pista.getNumKartsAdultosDisponibles() %></span></td></tr>
				<%}
				else {%>
					<tr><td><span class="etiqueta">Karts disponibles: </span> <span class="valor-etiqueta"><%= pista.getNumKartsDisponibles() %></span></td></tr>
				<%}%>
                
                <tr><td><span class="etiqueta">Disponibilidad: </span> <span class="valor-etiqueta"><%= (pista.getDisponibilidad()? "Si" : "No") %></span></td></tr>
            </table>
            
             <% if (pista.getDisponibilidad()) {
            	if (pista.getNumKartsDisponibles() == 0) { %>
            		<a href='#' onclick="return alertaNoKartsDisponibles(this);"><input type="submit" value="Reservar"></a>
            	<%}
            	else if (pista.getDisponibilidad())  {%>
            		<a href='<%= "/UCOKarts/CrearReserva?id=" + pista.getId() %>'><input type="submit" value="Reservar"></a> 
            	<%}
             } else { %>
        		<input type="submit" value="Reservar" disabled>
        	<% } %>
        </div>
    </div>

    <!-- Columna central -->
    <div class="col-central">
        <h1><%= pista.getNombre() %></h1>
            <div class="texto">
                <div>
                    <p><%= pista.getDescripcion() %></p>
                </div>
            </div>
    </div>
</div>


<jsp:include page="/include/footer.jsp"/>
</body>
</html>