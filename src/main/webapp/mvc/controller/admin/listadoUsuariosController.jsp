<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>sp" %>
<%@ page import="java.util.*" %>
<%@ page import= "java.time.Period,java.time.LocalDate,java.time.ZoneId" %>
<%@ page import="es.uco.pw.display.javabean.CustomerBean" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaAdultosDAO,es.uco.pw.business.reservas.ReservaAdultosDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaInfantilDAO,es.uco.pw.business.reservas.ReservaInfantilDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaFamiliarDAO,es.uco.pw.business.reservas.ReservaFamiliarDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";

if (customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado")) {
	nextPage = application.getInitParameter("loginView");
} else if (customer.getRol().equals("Usuario")) { 
	nextPage = application.getInitParameter("adminACLErrorView");
}
else {
	ConexionBD cbd = ConexionBD.getInstance();
	java.sql.Connection con = cbd.getConnection();
	Date today = new Date();
	LocalDate todayld = LocalDate.now();
	ArrayList<UsuarioDTO> usuariosdto = UsuarioDAO.getAll();
	ArrayList<CustomerBean> usuarios = new ArrayList<CustomerBean>();

	for (UsuarioDTO u : usuariosdto) {
	    int id = u.getId();
	    String nombre = u.getNombre();
	    String apellidos = u.getApellidos();
	    Date fechaNacimiento = u.getFechaNacimiento();
	    LocalDate fechaNacimientold = LocalDate.parse(u.getFechaNacimiento().toString());
	    Date fechaInscripcion = u.getFechaInscripcion();
	    LocalDate fechaInscripcionld = LocalDate.parse(u.getFechaInscripcion().toString());
	    String email = u.getEmail();
	    int edad = Period.between(fechaNacimientold, todayld).getYears();
		int antiguedad = Period.between(fechaInscripcionld, todayld).getMonths();	
	    String rol = u.esAdmin()? "Administrador" : "Usuario";
		CustomerBean cb = new CustomerBean(id, nombre, apellidos, fechaNacimiento, 
	    	fechaInscripcion,email, edad, antiguedad, rol);
		usuarios.add(cb);
	}
	
	ArrayList<ReservaAdultosDTO> radultos = ReservaAdultosDAO.getAll();
	ArrayList<ReservaInfantilDTO> rinfantil = ReservaInfantilDAO.getAll();
	ArrayList<ReservaFamiliarDTO> rfamiliar = ReservaFamiliarDAO.getAll();
	ArrayList<Integer> reservasCompletadas = new ArrayList<>(Arrays.asList(new Integer[usuarios.size()]));
	Collections.fill(reservasCompletadas, 0);
	
	// Se recorren todas las reservas adultas
	for (ReservaAdultosDTO r : radultos) {
		// Si la fecha de la reserva es anterior al dia de hoy (se ha consumido)
		if (r.getFecha_canjeo().before(today)) {
			// Se incrementa en 1 el numero de reservas completadas de ese usuario
			int idx = r.getUserreservaid() - 1;
			reservasCompletadas.set(idx, reservasCompletadas.get(idx) + 1);
		}
	}
	
	// Se recorren todas las reservas infantiles
	for (ReservaInfantilDTO r : rinfantil) {
		if (r.getFecha_canjeo().before(today)) {
			int idx = r.getUserreservaid() - 1;
			reservasCompletadas.set(idx, reservasCompletadas.get(idx) + 1);
		}
	}
	
	// Se recorren todas las reservas familiares
	for (ReservaFamiliarDTO r : rfamiliar) {
		if (r.getFecha_canjeo().before(today)) {
			int idx = r.getUserreservaid() - 1;
			reservasCompletadas.set(idx, reservasCompletadas.get(idx) + 1);
		}
	}

	request.setAttribute("reservasCompletadas", reservasCompletadas);
	request.setAttribute("usuarios", usuarios);
	nextPage = application.getInitParameter("listadoUsuariosView");
}
%>

<jsp:forward page="<%=nextPage%>"/>
