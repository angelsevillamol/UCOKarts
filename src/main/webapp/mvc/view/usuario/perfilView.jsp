<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*, es.uco.pw.display.javabean.ReservaBean" %>
<%@ page import="es.uco.pw.business.pistas.Dificultad" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Mi perfil - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/circuito.css" type="text/css">
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
</head>

<body>
<jsp:include page="/include/usuarioACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<!-- Información del perfil -->
<div class="columnas">
    <!-- Columna izquierda -->
    <div class="col-izquierda">
        <!-- información de contacto -->
        <div class="caja">
            <table class="tab-informacion">
                <tr><td><span class="etiqueta">Nombre: </span><span class="valor-etiqueta"><%= customer.getNombre() %></span></td></tr>
                <tr><td><span class="etiqueta">Apellidos: </span><span class="valor-etiqueta"><%= customer.getApellidos() %></span></td></tr>
                <tr><td><span class="etiqueta">Identificador: </span><span class="valor-etiqueta"><%= customer.getId() %></span></td></tr>
                <tr><td><span class="etiqueta">Correo electrónico: </span><span class="valor-etiqueta"><a href="<%="mailto:" + customer.getEmail() %> "><%= customer.getEmail() %></a></span></td></tr>
                <tr><td><span class="etiqueta">Edad: </span><span class="valor-etiqueta"><%= customer.getEdad() %></span></td></tr>
                <tr><td><span class="etiqueta">Fecha de nacimiento: </span><span class="descripcion"><%= customer.getFechaNacimientoStr() %></span></td></tr>
                <tr><td><span class="etiqueta">Fecha de inscripción: </span><span class="descripcion"><%= customer.getFechaInscripcionStr() %></span></td></tr>
            </table>
        </div>
    </div>

    <!-- Columna central -->
    <div class="col-central">
		<% 	String mensajeBienvenida = request.getParameter("mensajeBienvenida");
		if (mensajeBienvenida == null || mensajeBienvenida.equals("null")) {
			mensajeBienvenida = "";
		}
		if (!mensajeBienvenida.equals("")) { %>
			<div class="mensaje-error-on"><p><%= mensajeBienvenida %></p></div>
		<% } %>
		
        <h2>Lista de reservas pendientes</h2>
        <% ArrayList<ReservaBean> reservasPendientes = (ArrayList<ReservaBean>)request.getAttribute("reservasPendientes");
        if (reservasPendientes == null || reservasPendientes.size() == 0) { %>
        	<div class="mensaje-error-on"><p>No dispone de reservas pendientes en estos momentos.</p></div>
        <% }
        for (ReservaBean rb : reservasPendientes) { %>
		   <div class="caja">
		       <!-- Elemento Pista -->
		       <div class="circuito">
		       <div>
			       <span class="id"><%=rb.getId() %></span>
			       <span class="nombre"><a href='<%= "/UCOKarts/Pista?id=" + rb.getIdPista() %>'><%=rb.getNombrePista() %></a></span>
			   </div>
			   <div>
			       <span class="tipo"><%=rb.getDificultad()%></span>
			       <span class="tipo"><%=rb.getTipo()? "INDIVIDUAL" : "BONO"%></span>
			       <span class="tipo">Duración: <%=rb.getDuracion()%> minutos</span>
			   </div>
			   <div>
			       <span class="tipo">Propietario: <%=customer.getNombre() + " " + customer.getApellidos()%> </span>
			       <% if (rb.getDificultad() == Dificultad.FAMILIAR) { %>
			    	   <span class="tipo">Número de adultos: <%=rb.getNumAdultos()%></span>
				       <span class="tipo">Número de menores: <%=rb.getNumMenores()%></span>
			       <% } else { %>
			    	   <span class="tipo">Número de participantes: <%=rb.getNumAdultos() + rb.getNumMenores()%></span>
			       <% } %>
			   </div>
			   <div>
			       <span class="tipo">Fecha de reserva: <%=rb.getfechaReserva()%></span>
			       <span class="tipo">Fecha de canjeo: <%=rb.getfechaCanjeo()%></span>
			   </div>
			   <div>
			       <span class="tipo">Precio: <%=rb.getPrecio()%> €</span>
			       <span class="tipo">Descuento: <%=rb.getDescuento()%></span>
			   </div>
		       </div>
		   </div>
   		<% } %>
   		
   		
        <% ArrayList<ReservaBean> reservasCompletadas = (ArrayList<ReservaBean>)request.getAttribute("reservasCompletadas");
        if (!(reservasCompletadas == null || reservasCompletadas.size() == 0)) { %>
        <br><h2>Lista de reservas completadas</h2>
        <%  for (ReservaBean rb : reservasCompletadas) { %>
		   <div class="caja">
		       <!-- Elemento Pista -->
		       <div class="circuito">
		       <div>
			       <span class="id"><%=rb.getId() %></span>
			       <span class="nombre"><a href='<%= "/UCOKarts/Pista?id=" + rb.getIdPista() %>'><%=rb.getNombrePista() %></a></span>
			   </div>
			   <div>
			       <span class="tipo"><%=rb.getDificultad()%></span>
			       <span class="tipo"><%=rb.getTipo()? "INDIVIDUAL" : "BONO"%></span>
			       <span class="tipo">Duración: <%=rb.getDuracion()%> minutos</span>
			   </div>
			   <div>
			       <span class="tipo">Propietario: <%=customer.getNombre() + " " + customer.getApellidos()%> </span>
			       <% if (rb.getDificultad() == Dificultad.FAMILIAR) { %>
			    	   <span class="tipo">Número de adultos: <%=rb.getNumAdultos()%></span>
				       <span class="tipo">Número de menores: <%=rb.getNumMenores()%></span>
			       <% } else { %>
			    	   <span class="tipo">Número de participantes: <%=rb.getNumAdultos() + rb.getNumMenores()%></span>
			       <% } %>
			   </div>
			   <div>
			       <span class="tipo">Fecha de reserva: <%=rb.getfechaReserva()%></span>
			       <span class="tipo">Fecha de canjeo: <%=rb.getfechaCanjeo()%></span>
			   </div>
			   <div>
			       <span class="tipo">Precio: <%=rb.getPrecio()%> €</span>
			       <span class="tipo">Descuento: <%=rb.getDescuento()%></span>
			   </div>
		       </div>
		   </div>
   		<% } } %>
  </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>