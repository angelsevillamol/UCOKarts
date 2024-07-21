<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*" import="es.uco.pw.business.pistas.Estado, es.uco.pw.business.pistas.KartDTO, es.uco.pw.business.pistas.PistaDTO"%>

<head>
	<% request.setCharacterEncoding("UTF-8"); %>
    <jsp:include page="/include/commonhead.jsp">
			<jsp:param name="title" value="Asociar kart - UCOKarts"/>
	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customselect.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" type="text/css">
	<script src="${pageContext.request.contextPath}/js/validarPista.js"></script>
</head>

<% KartDTO kart = (KartDTO)request.getAttribute("kart");
if (kart == null) { %>
	<jsp:forward page="/include/errorPage.jsp"/>
<% } %>

<% ArrayList<PistaDTO> pistas = (ArrayList<PistaDTO>)request.getAttribute("pistas"); 
if (pistas == null) { %>
	<jsp:forward page="/include/asociarKartErrorView.jsp"/>
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
    <h1>Asociar kart</h1>    
    <form action="/UCOKarts/AsociarKart" method="POST" accept-charset="UTF-8">
        <div class="caja">
        	<h3>Identificador</h3>
            <input type="text"  placeholder="Identificador" id="id" name="id" value='<%= kart.getId() %>' readonly="readonly"/>
        
            <h3>Tipo de kart</h3>
            <input type="text"  placeholder="Tipo de kart" id="tipo" name="tipo" value='<%= kart.getTipo()? "ADULTO" : "INFANTIL"%>' disabled/>
            
            <h3>Estado del kart</h3>
            <input type="text"  placeholder="Estado de kart" id="estado" name="estado" value='<%= kart.getEstado()%>' disabled/>
            
            <h3>Seleccione la pista</h3>
            <div class="custom-select">
            <select name="nombrePista" id="nombrePista">
            	<option value="">Seleccione la pista:</option>
            	<% for (PistaDTO p : pistas)  { %>
            		<option value="<%= p.getNombre() %>"><%= p.getNombre() %></option>
            	<% } %>
            </select>
            </div>
            <div><input type="submit" value="Aceptar" id="submit-button"/></div>
        </div>
       </form>
   </div>
</div>

<script src="${pageContext.request.contextPath}/js/customselect.js"></script>
</body>