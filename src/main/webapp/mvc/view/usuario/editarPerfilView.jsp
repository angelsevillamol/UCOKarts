<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    
<head>
	<% request.setCharacterEncoding("UTF-8"); %>
    <jsp:include page="/include/commonhead.jsp">
			<jsp:param name="title" value="Editar perfil - UCOKarts"/>
	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<script src="${pageContext.request.contextPath}/js/validarDatos.js"></script>
</head>

<body>
<jsp:include page="/include/usuarioACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <div class="columna">
	<h1>Editar perfil</h1>
	
	<% 	String mensajeError = request.getParameter("mensajeError");
		if (mensajeError == null) {
			mensajeError = "";
		}
		if (!mensajeError.equals("")) { %>
			<div class="mensaje-error-on"><p><%= mensajeError %></p></div>
		<% } 
	%>
	
	<!-- Caja de informacion -->
	<form action="${pageContext.request.contextPath}<%=application.getInitParameter("editarPerfilController")%>" method="POST" accept-charset="UTF-8">
	<div class="caja">
        <h3>Nombre</h3>
        <input type="text" placeholder="Nombre" id="nombre" name="nombre" oninput="validarEditar()" value='<%= customer.getNombre() %>'/>
        <div id="invalid-nombre-message" class="mensaje-error-off"><p>Incluye un nombre válido.</p></div>
        
        <h3>Apellidos</h3>
        <input type="text" placeholder="Apellidos" id="apellidos" name="apellidos" oninput="validarEditar()" value='<%= customer.getApellidos() %>'/>
        <div id="invalid-apellidos-message" class="mensaje-error-off"><p>Incluye apellidos válidos.</p></div>
        
        <h3>Fecha de nacimiento</h3>
        <input type="text" placeholder="Fecha de nacimiento (dd/mm/aaaa)" id="fechaNacimiento" name="fechaNacimiento" oninput="validarEditar()" value='<%= customer.getFechaNacimientoStr() %>'/>
        <div id="invalid-date-message" class="mensaje-error-off"><p>La fecha debe cumplir el formato dd/mm/aaaa</p></div>
        
        <h3>Nueva contraseña</h3>
        <input type="password" placeholder="Contraseña" name="password" id="password" oninput="validarEditar()"/>
        <div id="invalid-pass-message" class="mensaje-error-off">
        	<p>La contraseña debe contener al menos 8 caracteres, un número y una letra.</p>
            <p>Caracteres válidos A-Z a-z 0-9 "." "_" "-" .</p>
        </div>
        
        <h3>Repita su contraseña</h3>
        <input type="password" placeholder="Contraseña" name="password2" id="password2" oninput="validarEditar()"/>
        <div id="pass-match-message" class="mensaje-error-off"><p>Las contraseñas deben coincidir.</p></div>
        
		<div><input type="submit" value="Aceptar" id="submit-button" disabled="disabled"/></div>
	</div>
	</form>
	</div>
</div>

<jsp:include page="/include/footer.jsp"/>
</body>