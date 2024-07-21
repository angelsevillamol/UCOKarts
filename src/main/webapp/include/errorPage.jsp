<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="./commonhead.jsp" >
		<jsp:param name="title" value="Página de error - UCOKarts"/>
   	</jsp:include>
</head>
<body>

<%@ include file="./header.jsp" %>

<div class="columnas">
    <div class="columna">
        <h1>Se ha producido un error</h1>
        <div class="texto">
        Lo sentimos, se ha producido un error. Vuelva a la página principal.
        </div>
    </div>
</div>

<%@ include file="./footer.jsp" %>

</body>
</html>