<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<head>
	<% request.setCharacterEncoding("UTF-8"); %>
    <jsp:include page="/include/commonhead.jsp">
			<jsp:param name="title" value="Crear kart - UCOKarts"/>
	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customselect.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" type="text/css">
	<script src="${pageContext.request.contextPath}/js/validarPista.js"></script>
</head>

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
        <li><a class="active" href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">
    <!-- Columna central -->
    <div class="columna">
    <h1>Crear kart</h1>
    
    <% 	String mensajeError = request.getParameter("mensajeError");
	if (mensajeError == null) {
		mensajeError = "";
	}
	if (!mensajeError.equals("")) { %>
		<div class="mensaje-error-on"><p><%= mensajeError %></p></div>
	<% } %>
    
    <form action="/UCOKarts/CrearKart" method="POST" accept-charset="UTF-8">
        <div class="caja">
            <h3>Tipo de kart</h3>
            <div class="custom-select">
            <select name="tipo" id="tipo">
            	<option value="INFANTIL">Infantil</option>
            	<option value="INFANTIL">Infantil</option>
            	<option value="ADULTO">Adulto</option>
            </select>
            </div>
            <div><input type="submit" value="Aceptar" id="submit-button"/></div>
        </div>
       </form>
   </div>
</div>

<script src="${pageContext.request.contextPath}/js/customselect.js"></script>
</body>