<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp"%>
<%@ page import="es.uco.pw.business.pistas.Dificultad"%>
<%@ page import="java.util.*, es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Inicio - UCOKarts"/>
   	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/circuito.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<!-- Información del home -->
<div class="columnas">
    <!-- Columna izquierda -->
    <div class="col-izquierda">
   	 	<!-- Caja de filtro  -->
   		<div class="caja">
    	<form action="/UCOKarts/Home" method="GET" accept-charset="UTF-8">
        	<h2>Filtrado</h2>
        	<h3>Tipo</h3>
        	<label class="contenedor-cm">Circuitos adultos<input type="checkbox" name="adultos" value="tipag"><span class="checkmark"></span></label>
        	<label class="contenedor-cm">Circuitos infantiles<input type="checkbox" name="infantiles" value="tipat"><span class="checkmark"></span></label>
        	<label class="contenedor-cm">Circuitos familiares<input type="checkbox" name="familiares" value="tipai"><span class="checkmark"></span></label>

        	<h3>Número de karts disponibles</h3>
        	<input type="number" placeholder="Número de karts disponibles" name="numKartsDisponibles" min="0">
        
        <input type="submit" value="Aplicar filtro">
        </form>
        </div>
    </div>


    <!-- Columna central -->
    <div class="col-central">
        <!-- Pistas  -->
        <%
		ArrayList<PistaDTO> pistas = (ArrayList<PistaDTO>)request.getAttribute("pistas");
		for (PistaDTO p : pistas) { %>
		<div class="caja">
            <!-- Elemento Pista -->
            <div class="circuito">
                <div>
                	<span class="id"><%= p.getId() %></span>
                    <span class="nombre"><a href='<%= "/UCOKarts/Pista?id=" + p.getId() %>'><%= p.getNombre() %></a></span>
                </div>
                <div>
                <span class="tipo"><%= p.getDificultad() %></span>
                <span class="tipo"><%= p.getMaxKarts() %> karts máximo</span>
                <span class="tipo"><%= p.getNumKartsDisponibles() %> karts disponibles</span>
                </div>
                <div class="descripcion"><%= p.getDescripcion() %></div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>