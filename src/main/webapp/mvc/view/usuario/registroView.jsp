<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>

<head>
	<% request.setCharacterEncoding("UTF-8"); %>
    <jsp:include page="/include/commonhead.jsp">
			<jsp:param name="title" value="Registro de UCOKarts"/>
	</jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cajaform.css" type="text/css">
	<script src="${pageContext.request.contextPath}/js/validarDatos.js"></script>
</head>

<body>
<jsp:include page="/include/invitadoACL.jsp"/>
<jsp:include page="/include/header.jsp"/>

<div class="columnas">
    <!-- Columna central -->
    <div class="columna">
    <h1>Crear cuenta</h1>
    
    <% 	String mensajeError = request.getParameter("mensajeError");
	if (mensajeError == null) {
		mensajeError = "";
	}
	if (!mensajeError.equals("")) { %>
		<div class="mensaje-error-on"><p><%= mensajeError %></p></div>
	<% } %>
    
    <form action="${pageContext.request.contextPath}<%=application.getInitParameter("registroController")%>" method="POST" accept-charset="UTF-8">
        <div class="caja">
        	<h3>Nombre</h3>
            <input type="text" placeholder="Nombre" id="nombre" name="nombre" oninput="validarDatos()"/>
            <div id="invalid-nombre-message" class="mensaje-error-off"><p>Incluye un nombre válido.</p></div>
            
            <h3>Apellidos</h3>
            <input type="text" placeholder="Apellidos" id="apellidos" name="apellidos" oninput="validarDatos()"/>
            <div id="invalid-apellidos-message" class="mensaje-error-off"><p>Incluye apellidos válidos.</p></div>
           
            <h3>Fecha de nacimiento</h3>
            <input type="text" placeholder="Fecha de nacimiento (dd/mm/aaaa)" id="fechaNacimiento" name="fechaNacimiento" oninput="validarDatos()"/>
            <div id="invalid-date-message" class="mensaje-error-off"><p>La fecha debe cumplir el formato dd/mm/aaaa</p></div>
            
            <h3>Correo electrónico</h3>
            <input type="email" placeholder="Correo electrónico" id="email" name="email" oninput="validarDatos()"/>
            <div id="invalid-email-message" class="mensaje-error-off"><p>Incluye una dirección de correo elecrónico válida.</p></div>
            
            <h3>Contraseña</h3>
            <input type="password"  placeholder="Contraseña" id="password" name="password" oninput="validarDatos()"/>
            <div id="invalid-pass-message" class="mensaje-error-off">
            	<p>La contraseña debe contener al menos 8 caracteres, un número y una letra.</p>
            	<p>Caracteres válidos A-Z a-z 0-9 "." "_" "-" .</p>
            </div>
            
            <h3>Repita su contraseña</h3>
            <input type="password"  placeholder="Repita su contraseña" id="password2" oninput="validarDatos()"/>
            <div id="pass-match-message" class="mensaje-error-off"><p>Las contraseñas deben coincidir.</p></div>
            
            <div><input type="submit" value="Registrarte" id="submit-button" disabled="disabled"/></div>
        </div>
       </form>

        <div class="terminos" id="terminosycondiciones-group">
            Al registrarte aceptas nuestras <a href="${pageContext.request.contextPath}<%=application.getInitParameter("avisolegal")%>">Condiciones de uso</a> y nuestra <a href="${pageContext.request.contextPath}<%=application.getInitParameter("privacidad")%>">Política de privacidad</a>.
       </div>
       <div>¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}<%=application.getInitParameter("loginView")%>">Iniciar sesión</a></div>
   </div>
</div>

</body>