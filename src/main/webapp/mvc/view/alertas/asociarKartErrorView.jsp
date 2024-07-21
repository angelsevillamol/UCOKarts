<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Pistas no disponibles - UCOKarts"/>
   	</jsp:include>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>Pistas no disponibles</h1>
        <div class="texto">
        No se han encontrado pistas que cumplan las condiciones necesarias para realizar esa operación.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>