<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import= "java.time.Period,java.time.LocalDate,java.time.ZoneId" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";
String mensajeError = "";
boolean sonParametrosValidos = true;

// Si está logeado
if (!(customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado"))) {	
	// Se obtienen los parametros
	String nombre = request.getParameter("nombre");
	String apellidos = request.getParameter("apellidos");
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
		
		int id = customer.getId();
		String email = customer.getEmail();
		boolean admin = customer.getRol().equals("Administrador");
		java.util.Date fechaInscripcion = customer.getFechaInscripcion();
		UsuarioDTO usuario = new UsuarioDTO(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, admin);
		UsuarioDAO.update(usuario, password);
		%>
		
		<jsp:setProperty property="nombre" value="<%=usuario.getNombre()%>" name="customer"/>
		<jsp:setProperty property="apellidos" value="<%=usuario.getApellidos()%>" name="customer"/>
		<jsp:setProperty property="fechaNacimiento" value="<%=usuario.getFechaNacimiento()%>" name="customer"/>
		<jsp:setProperty property="edad" value="<%=edad%>" name="customer"/>
			
		<%
		nextPage = application.getInitParameter("editarPerfilView");
		mensajeError = "Cambios realizados con éxito.";
	}
	// Se impide a los menores registrarse.
	else if (edad < 18) {
		nextPage = application.getInitParameter("editarPerfilView");
		mensajeError = "Debe ser mayor de edad para registrarse.";
	}
	// Si los parámetros no son válidos.
	else {
		nextPage = application.getInitParameter("editarPerfilView");
		mensajeError = "Los valores indicados no son válidos.";
	}	
}
// Si se accede a esta página sin estar logeado.
else {
	// Se envía a la página de login.
	nextPage = application.getInitParameter("loginView");
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeError%>" name="mensajeError"/>
</jsp:forward>