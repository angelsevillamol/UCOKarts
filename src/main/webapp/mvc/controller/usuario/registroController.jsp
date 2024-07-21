<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import= "java.time.Period,java.time.LocalDate,java.time.ZoneId" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";
String mensajeError = "";
boolean sonParametrosValidos = true;

if (customer == null || customer.getEmail().equals("")) {	
	// Se obtienen los parametros
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
	String email = request.getParameter("email");
	String strFechaNacimiento = request.getParameter("fechaNacimiento");
	String password = request.getParameter("password");
	int edad = 0;
	
	// Se verifican los parametros
	if (nombre == null || nombre.isEmpty()) {
		sonParametrosValidos = false;
    }
		
	if (apellidos == null || apellidos.isEmpty()) {
		sonParametrosValidos = false;
    }
	
	if (email == null || email.isEmpty()) {
        sonParametrosValidos = false;
    }
	
	if (strFechaNacimiento == null) {
		sonParametrosValidos = false; 
    }
	
	java.util.Date fechaNacimiento = new java.util.Date();
    try {
    	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
    	fechaNacimiento = df.parse(strFechaNacimiento);
    	LocalDate today = LocalDate.now();
		LocalDate fechaNacimientold = LocalDate.parse(new java.text.SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento));
		edad = Period.between(fechaNacimientold, today).getYears();
	} catch (java.text.ParseException e) {
		sonParametrosValidos = false;
	}

	if (edad < 18) {
		sonParametrosValidos = false;
	}
    
    java.util.Date fechaInscripcion = new java.util.Date();
    if (fechaNacimiento.compareTo(fechaInscripcion) > 0) {
    	sonParametrosValidos = false;
    }
    
	if (password == null || password.isEmpty()) {
		sonParametrosValidos = false;
    }
	
	// Comprobar parametros
	if (sonParametrosValidos) {
		ConexionBD cbd = ConexionBD.getInstance();
		java.sql.Connection con = cbd.getConnection();
		
		// Realizar conexión a la base de datos.
		if (con == null) {
			String dburl = application.getInitParameter("servidor");
			String dbusuario = application.getInitParameter("usuario");
			String dbpassword = application.getInitParameter("password");
			String pathSQL = application.getInitParameter("pathSQL");
			java.io.InputStream input = application.getResourceAsStream(pathSQL);
			java.util.Properties prop = new java.util.Properties();
			
			try {
				prop.load(input);
			} catch (java.io.IOException ex) {
	            ex.printStackTrace();
	        }
			
			cbd.setConnectionParameters(dburl, dbusuario, dbpassword, prop);
			con = cbd.getConnection();
		}
		
		// Comprobar disponibilidad del email
		UsuarioDTO usuario = UsuarioDAO.queryByEmail(email);
		if (usuario == null) {
			usuario = new UsuarioDTO(0, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, false);
			UsuarioDAO.save(usuario, password);
			UsuarioDTO registrado = UsuarioDAO.queryByEmail(email);
			int antiguedad = 0;
			%>
			
			<jsp:setProperty property="id" value="<%=registrado.getId()%>" name="customer"/>
			<jsp:setProperty property="nombre" value="<%=usuario.getNombre()%>" name="customer"/>
			<jsp:setProperty property="apellidos" value="<%=usuario.getApellidos()%>" name="customer"/>
			<jsp:setProperty property="fechaNacimiento" value="<%=usuario.getFechaNacimiento()%>" name="customer"/>
			<jsp:setProperty property="fechaInscripcion" value="<%=usuario.getFechaInscripcion()%>" name="customer"/>
			<jsp:setProperty property="email" value="<%=usuario.getEmail()%>" name="customer"/>
			<jsp:setProperty property="edad" value="<%=edad%>" name="customer"/>
			<jsp:setProperty property="antiguedad" value="<%=antiguedad%>" name="customer"/>
			<jsp:setProperty property="rol" value="Usuario" name="customer"/>
			
			<%
			nextPage = application.getInitParameter("registroExitoView");
		}
		// Si el correo ya está en uso.
		else {
			nextPage = application.getInitParameter("registroView");
			mensajeError = "El correo electrónico indicado ya se encuentra en uso.";
		}
	}
	// Se impide a los menores registrarse.
	else if (edad < 18) {
		nextPage = application.getInitParameter("registroView");
		mensajeError = "Debe ser mayor de edad para registrarse.";
	}
	// Si se accede sin haber pasado por el formulario, o con datos incorrectos.
	else {
		nextPage = application.getInitParameter("registroView");
		mensajeError = "Los parámetros indicados no son válidos.";
	}	
}
// Si se accede a esta página estando logeado.
else {
	// Se envía a la página de error
	nextPage = application.getInitParameter("invitadoACLErrorView");
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeError%>" name="mensajeError"/>
</jsp:forward>
