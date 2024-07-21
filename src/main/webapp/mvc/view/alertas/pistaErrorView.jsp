<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Error de pista - UCOKarts"/>
   	</jsp:include>
</head>

<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>Error de pista</h1>
        <div class="texto">
        Se ha encontrado un error. La pista solicitada con identificador "<%= request.getParameter("id") %>" no existe.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>