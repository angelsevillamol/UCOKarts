<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Registro de UCOKarts"/>
   	</jsp:include>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>Cuenta creada con éxito</h1>
        <div class="texto">
        Su registro ha sido completado con éxito. Haga click <a href="/UCOKarts/Home">aquí</a> para volver a la página principal.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>