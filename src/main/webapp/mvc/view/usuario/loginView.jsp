<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="/include/commonhead.jsp">
		<jsp:param name="title" value="Iniciar sesión - UCOKarts"/>
   	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
</head>

<body>
<jsp:include page="/include/invitadoACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
	<h1>Iniciar sesión</h1>
	
	<% 	String failed = request.getParameter("failedLogin");
	if (failed == null) {
		failed = "";
	}
	if (failed.equals("true")) { %>
		<div class="mensaje-error-on"><p>El correo o contraseña introducidos son inválidos.</p></div>
	<% } %>
	
	<div class="caja">
	<form action="${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>" method="POST" accept-charset="UTF-8">
		<h3>Correo electrónico</h3>
        <input type="email" placeholder="Correo electrónico" name="email">
        <h3>Contraseña</h3>
        <input type="password" placeholder="Contraseña" name="password">
		<input type="submit" value="Entrar">
		
	</form>
	</div>
		
	<div>¿Eres nuevo en UCOKarts? <a href="${pageContext.request.contextPath}<%=application.getInitParameter("registroView")%>">Crear cuenta</a></div>
	</div>
</div>

</body>
</html>