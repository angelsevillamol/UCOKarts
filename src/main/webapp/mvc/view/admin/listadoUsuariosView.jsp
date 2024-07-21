<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="java.util.*" import="es.uco.pw.display.javabean.CustomerBean"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Listado de usuarios - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabla.css" type="text/css">
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css" type="text/css">
</head>

<body>
<jsp:include page="/include/adminACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div id="navbar">
	<ul>
  		<li><a class="active" href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoUsuariosController")%>">Listado de usuarios</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoPistasController")%>">Listado de pistas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">
    <div class="columna">
        <h1>Listado de usuarios</h1>
        <table class="tabla">
    	<thead>
       		<tr>
       			<th>ID.</th>
            	<th>Nombre</th>
            	<th>Apellidos</th>
            	<th>Correo electrónico</th>
            	<th>Antigüedad (meses)</th>
            	<th>Reservas completadas</th>
        	</tr>
    	</thead>
    	<tbody>
    	<%
		ArrayList<CustomerBean> usuarios = (ArrayList<CustomerBean>)request.getAttribute("usuarios");
    	ArrayList<Integer> reservasCompletadas = (ArrayList<Integer>)request.getAttribute("reservasCompletadas");
		for (CustomerBean u : usuarios) { %>
        	<tr>
        		<td><%=u.getId()%></td>
            	<td><%=u.getNombre()%></td>
            	<td><%=u.getApellidos()%></td>
            	<td><%=u.getEmail()%></td>
            	<td><%=u.getAntiguedad()%></td>
            	<td><%=reservasCompletadas.get(u.getId()-1)%></td>
        	</tr>
        <% } %>
    	</tbody>
		</table>
    </div>
</div>

</body>
</html>