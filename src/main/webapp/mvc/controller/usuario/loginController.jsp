<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import= "java.time.Period,java.time.LocalDate,java.time.ZoneId" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
	String failedLogin = "false";
	String nextPage = application.getInitParameter("loginView");
	String mensajeBienvenida = "";

	if (customer == null || customer.getEmail().equals("")) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email != null) {
			ConexionBD cbd = ConexionBD.getInstance();
			java.sql.Connection con = cbd.getConnection();
			
			// Comprobar conexion a base de datos
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
			
			UsuarioDTO usuario = UsuarioDAO.login(email, password);		
			if (usuario != null) {
				String rol = usuario.esAdmin()? "Administrador" : "Usuario";

				// Se actualiza la edad y la antiguedad.
				LocalDate today = LocalDate.now();
				LocalDate fechaNacimientold = LocalDate.parse(usuario.getFechaNacimiento().toString());
				LocalDate fechaInscripcionld = LocalDate.parse(usuario.getFechaInscripcion().toString());
				int edad = Period.between(fechaNacimientold, today).getYears();
				int antiguedad = Period.between(fechaInscripcionld, today).getMonths();	
				%>
				
				<jsp:setProperty property="id" value="<%=usuario.getId()%>" name="customer"/>
				<jsp:setProperty property="nombre" value="<%=usuario.getNombre()%>" name="customer"/>
				<jsp:setProperty property="apellidos" value="<%=usuario.getApellidos()%>" name="customer"/>
				<jsp:setProperty property="fechaNacimiento" value="<%=usuario.getFechaNacimiento()%>" name="customer"/>
				<jsp:setProperty property="fechaInscripcion" value="<%=usuario.getFechaInscripcion()%>" name="customer"/>
				<jsp:setProperty property="email" value="<%=usuario.getEmail()%>" name="customer"/>
				<jsp:setProperty property="edad" value="<%=edad%>" name="customer"/>
				<jsp:setProperty property="antiguedad" value="<%=antiguedad%>" name="customer"/>
				<jsp:setProperty property="rol" value="<%=rol%>" name="customer"/>
				
				<%
				
				// Si es administrador se pasa por la página de administracion.
				if (usuario.esAdmin()) {
					nextPage = application.getInitParameter("listadoUsuariosController");
					failedLogin = "false";
				}
				else {
					nextPage = application.getInitParameter("perfilController");
					failedLogin = "false";
					java.util.Date fechaActual = new java.util.Date();
					mensajeBienvenida = "Bienvenido de nuevo " + usuario.getNombre() + " " + usuario.getApellidos() + 
							".\nFecha actual: " + new java.text.SimpleDateFormat("dd/MM/yyyy").format(fechaActual) + 
							". Antiguedad: " + antiguedad + " meses";
				}
			}
			// Si el inicio de sesion ha sido fallido.
			else {
				nextPage = application.getInitParameter("loginView");
				failedLogin = "true";
			}	
		}
		// Si se accede sin haber pasado por el formulario.
		else {
			nextPage = application.getInitParameter("loginView");
			failedLogin = "false";
		}	
	}
	// Si se accede estando logeado.
	else {
		nextPage = application.getInitParameter("invitadoACLErrorView");
		failedLogin = "true";
	}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=failedLogin%>" name="failedLogin"/>
	<jsp:param value="<%=mensajeBienvenida%>" name="mensajeBienvenida"/>
</jsp:forward>
