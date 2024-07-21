<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.reservas.BonoDAO,es.uco.pw.business.reservas.BonoDTO, es.uco.pw.business.pistas.Dificultad" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";
String mensajeError = "";
boolean sonParametrosValidos = true;

if (!(customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado"))) {	
	// Se obtienen los parametros
	String tipo = request.getParameter("tipobono");
	Integer userid = customer.getId();
	java.util.Date fechaCompra = new java.util.Date();
	Calendar c = Calendar.getInstance();
	c.setTime(fechaCompra);
	c.add(Calendar.YEAR, 1);
	java.util.Date fechaCaducidad = c.getTime();
	int sesiones = 0;
	
	if (tipo == null || tipo.isEmpty()) {
		sonParametrosValidos = false;
	}
	
	if (!sonParametrosValidos) {
    	nextPage = application.getInitParameter("reservaFailureView");
    	mensajeError = "No se han introducido todos los datos necesarios para realizar la reserva.";
    }
	
	
	if (sonParametrosValidos) {
		Dificultad diff = Dificultad.valueOf(tipo);
		
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
		
		BonoDTO Bono = new BonoDTO(0, userid, diff, fechaCompra, fechaCaducidad, 0);
		BonoDAO.save(Bono);
		
		nextPage = application.getInitParameter("reservaBonoExitoView");
	}
	
}

else {
	// Se envía a la página de login.
	nextPage = application.getInitParameter("loginView");
	mensajeError = "Necesita iniciar sesión para realizar una reserva.";
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeError%>" name="mensajeError"/>
</jsp:forward>