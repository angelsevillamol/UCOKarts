<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE htlm>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp">
		<jsp:param name="title" value="Portal de Reservas - UCOKarts"/>
   	</jsp:include>
   	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reserva.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/customselect.css" type="text/css">
</head>

<body>
<jsp:include page="/include/usuarioACL.jsp"/>
<jsp:include page="/include/header.jsp"/>
	
	<div class="columnas">
	
		<div class="columna">
	
		<h1>Reservar Bono</h1>
		<br/>
			<form action="${pageContext.request.contextPath}<%=application.getInitParameter("reservaBonoController")%>" method="POST" accept-charset="UTF-8">
				<div class="caja">
					<p>Seleccione el tipo de bono que desea reservar</p>
					<div class="custom-select">
            			<select name="tipobono" id="tipobono">
            				<option value="INFANTIL">Infantil</option>
            				<option value="INFANTIL">Infantil</option>
            				<option value="ADULTOS">Adulto</option>
            				<option value="FAMILIAR">Familiar</option>
            			</select>
            		</div>
					<p><em>Para realizar una reserva con un bono, es necesario que el tipo de bono coincida con el tipo de dificultad de la pista</em></p>
					<p><em>Al utilizar un bono, se le beneficiará con un 5% de descuento en su reserva</em></p>
					<div><input type="submit" value="Reservar" id="submit-button" /></div>
				</div>
			</form>
		</div>
	</div>
		
<jsp:include page="/include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/customselect.js"></script>
</body>
</html>