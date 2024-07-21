<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="es.uco.pw.business.pistas.Dificultad,es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE htlm>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp">
		<jsp:param name="title" value="Portal de Reservas - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reserva.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customselect.css" type="text/css">
	<script src="${pageContext.request.contextPath}/js/validarPista.js"></script>
</head>

<%
PistaDTO pista = (PistaDTO)request.getAttribute("pista");
if (pista == null) { %>
	<jsp:forward page="/include/errorPage.jsp"/>
<% } %>

<body>
<jsp:include page="/include/usuarioACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <!-- Columna central -->
    <div class="columna">
	<h1>Página de reserva</h1>
	<form action="/UCOKarts/CrearReserva" method="POST" accept-charset="UTF-8">
		<div class="caja">
			<h3>Pista a reservar</h3>
            <input type="text" placeholder="Nombre" id="nombre" name="nombre" value='<%= pista.getNombre() %>' readonly="readonly"/>
		
			<p>¿Desea realizar el canjeo de un bono de descuento para realizar esta reserva?</p>
			<div class="custom-select">
            <select name="bono" id="bono">
            	<option value="no">No utilizar bono</option>
            	<option value="no">No utilizar bono</option>
            	<option value="si">Utilizar un bono</option>
            </select>
            </div>
			<p><em>¿No tiene bono? Reserve uno <a href="${pageContext.request.contextPath}<%=application.getInitParameter("reservaBonoView")%>">aquí</a></em></p>
			
			<% if (pista.getDificultad() == Dificultad.FAMILIAR) { %>
				<p>Indique la cantidad de menores que participarán</p>
				<input id="NparticipantesMenores" name="NparticipantesMenores" type="number" min="0" placeholder="Número de menores" />
				<p>Indique la cantidad de adultos que participarán</p>
				<input id="NparticipantesMayores" name="NparticipantesMayores" type="number" min="0" placeholder="Número de adultos" />
			<% } else { %>
				<p>Indique la cantidad de participantes</p>
				<input id="Nparticipantes" name="Nparticipantes" type="number" min="1" placeholder="Número de participantes" />
			<% } %>
			
			<p>Indique el día en el que desea canjear la reserva</p>
			<input type="Date" id="fechaCanjeo" name="fechaCanjeo"/>
			
			<p>Elija la duración de la sesión</p>
			<div class="custom-select">
			<select id="duracion" name="duracion">
				<option value="60">60 Minutos</option>
				<option value="60">60 Minutos</option>
				<option value="90">90 Minutos</option>
				<option value="120">120 Minutos</option>
			</select> 
			</div>
			
			<p><em>Precios por duración: 60 minutos 20€, 90 minutos 30€, 120 minutos 40€</em></p>	
			<p><em>Al emplear un bono, se aplicará un 5% de descuento</em></p>
			
			<div><input type="submit" value="Reservar" id="submit-button"/></div>
		</div>
	</form>	
	</div>
</div>

<jsp:include page="/include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/customselect.js"></script>
</body>
</html>