<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Portal de Reservas - UCO"/>
   	</jsp:include>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>No se ha encontrado un bono</h1>
        <div class="texto">
        Usted no posee un bono del tipo de dificultad de la pista que desea reservar. Puede que haya caducado, haya gastado todas las sesiones, o no lo haya reservado. Haga click <a href="${pageContext.request.contextPath}<%=application.getInitParameter("reservaBonoView")%>">aquí</a> para reservar un bono.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>