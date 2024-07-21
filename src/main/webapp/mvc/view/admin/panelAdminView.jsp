<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Panel de administración - UCOKarts"/>
   	</jsp:include>
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
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
	</ul>
</div>

<div class="columnas">	
    <div class="columna">
        <h1>Panel de administración</h1>
        <div class="texto">
        <ol>
        	<li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoUsuariosController")%>">Listado de usuarios</a></li>
	        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoPistasController")%>">Listado de pistas</a></li>
	        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoKartsController")%>">Listado de karts</a></li>
	        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("listadoReservasController")%>">Listado de reservas</a></li>
	        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearPistaView")%>">Crear pista</a></li>
	        <li><a href="${pageContext.request.contextPath}<%=application.getInitParameter("crearKartView")%>">Crear kart</a></li>
        </ol>
        </div>
    </div>
</div>

</body>
</html>