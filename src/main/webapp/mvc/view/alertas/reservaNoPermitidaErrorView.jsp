<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="es.uco.pw.business.pistas.PistaDTO"%>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp" >
		<jsp:param name="title" value="Portal de Reservas - UCO"/>
   	</jsp:include>
</head>

<%
PistaDTO pista = (PistaDTO)request.getAttribute("pista");
if (pista == null) { %>
	<jsp:forward page="/include/errorPage.jsp"/>
<% } %>


<body>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
        <h1>Reserva no disponible</h1>
        <div class="texto">
        Error a la hora de reservar la pista <%= pista.getNombre() %>. Actualmente no se encuentra disponible para reservar, no dispone de karts disponibles o no ha solicitado la reserva adecuada.
        </div>
    </div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>
</html>