<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*" import="es.uco.pw.business.pistas.Estado,es.uco.pw.business.pistas.KartDTO,es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp">
		<jsp:param name="title" value="Listado de karts - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabla.css" type="text/css">
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
        <li><a class="active" href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">
    <div class="columna">
        <h1>Listado de karts</h1>
        <table class="tabla">
    	<thead>
       		<tr>
       			<th>ID.</th>
            	<th>Tipo</th>
            	<th>Estado</th>
            	<th>Pista asociada</th>
            	<th>Opciones</th>
        	</tr>
    	</thead>
    	<tbody>
    	<%
		ArrayList<KartDTO> karts = (ArrayList<KartDTO>)request.getAttribute("karts");
    	ArrayList<PistaDTO> pistas = (ArrayList<PistaDTO>)request.getAttribute("pistas");
    	int i = 0;
		for (KartDTO k : karts) { %>
        	<tr>
        		<td><%=k.getId()%></td>
            	<td><%=k.getTipo()? "ADULTO" : "INFANTIL"%></td>
            	<td><%=k.getEstado()%></td>
            	<% if (pistas.get(i) != null) { %>
            			<td><a href='<%= "/UCOKarts/Pista?id=" + pistas.get(i).getId() %>'><%= pistas.get(i).getNombre() %></a></td>
            	<% } else { %>
            			<td></td>
            	<% } %>
            	<td><a href='<%= "/UCOKarts/ModificarKart?id=" + k.getId() %>'>Modificar</a> | 
            	<% if (pistas.get(i) != null) { %>
            		<a href=# onclick="return alertaPistaAsociada(this);">Asociar</a>
            	<% } else if (k.getEstado() == Estado.MANTENIMIENTO) { %>
        			<a href=# onclick="return alertaPistaMantenimiento(this);">Asociar</a>
            	<% } else { %>
            		<a href='<%= "/UCOKarts/AsociarKart?id=" + k.getId() %>'>Asociar</a>
            	<% } %>
            	</td>
        	</tr>
        <% i++;
        } %>
    	</tbody>
		</table>
    </div>
</div>

</body>
</html>