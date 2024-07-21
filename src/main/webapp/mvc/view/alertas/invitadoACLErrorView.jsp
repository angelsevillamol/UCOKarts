<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Error de navegación - UCOKarts"/>
   	</jsp:include>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>Error de navegación</h1>
        <div class="texto">
        Debe cerrar sesión antes de continuar. Para ello, pulse sobre la opción "Cerrar sesión" que aparece en el menú de la cabecera.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>