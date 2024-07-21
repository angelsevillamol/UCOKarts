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
        <h1>Se ha producido un error</h1>
        <div class="texto">
        La reserva no se ha podido realizar. Compruebe que ha rellenado todos los parámetros correctamente y la cantidad de karts disponibles. Haga click <a href="/UCOKarts/Home">aquí</a> para volver a la página principal.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>