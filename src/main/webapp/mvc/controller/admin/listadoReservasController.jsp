<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>sp" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD"%>
<%@ page import="es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<%@ page import="es.uco.pw.data.dao.pistas.PistaDAO,es.uco.pw.business.pistas.PistaDTO,es.uco.pw.business.pistas.Dificultad" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaAdultosDAO,es.uco.pw.business.reservas.ReservaAdultosDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaInfantilDAO,es.uco.pw.business.reservas.ReservaInfantilDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaFamiliarDAO,es.uco.pw.business.reservas.ReservaFamiliarDTO" %>
<%@ page import="es.uco.pw.display.javabean.ReservaBean" %>
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
	ReservaBean rb = null;
	java.util.ArrayList<ReservaBean> reservas = new java.util.ArrayList<ReservaBean>();
	java.util.ArrayList<ReservaAdultosDTO> radultos = ReservaAdultosDAO.getAll();
	java.util.ArrayList<ReservaInfantilDTO> rinfantil = ReservaInfantilDAO.getAll();
	java.util.ArrayList<ReservaFamiliarDTO> rfamiliar = ReservaFamiliarDAO.getAll();
	

	// Campos del ReservaBean
	int id;
	int idUsuario;
	int idPista;
	String nombrePista;
	int idBono;
	java.util.Date fechaReserva;
	java.util.Date fechaCanjeo;
	int duracion;
	float precio;
	float descuento;
	int numAdultos;
	int numMenores;
	Dificultad diff;
	boolean tipo;
	
	for (ReservaAdultosDTO r : radultos) {
		id = r.getReservaid();
		idUsuario = r.getUserreservaid();
		idPista = r.getPistaid();
		nombrePista = PistaDAO.queryById(r.getPistaid()).getNombre();
		idBono = r.getIdbono();
		fechaReserva = r.getFecha_reserva();
		fechaCanjeo = r.getFecha_canjeo();
		duracion = r.getDuracion();
		precio = r.getPrecio();
		descuento = r.getDescuento();
		numAdultos = r.getNumeromayores();
		numMenores = 0;
		diff = Dificultad.ADULTOS;
		tipo = r.getIdbono() == -1? true : false;
		rb = new ReservaBean(id, idUsuario, idPista, nombrePista, idBono, fechaReserva, fechaCanjeo, 
				duracion, precio, descuento, numAdultos, numMenores, diff, tipo);
		reservas.add(rb);
	}
	
	for (ReservaInfantilDTO r : rinfantil) {
		id = r.getReservaid();
		idUsuario = r.getUserreservaid();
		idPista = r.getPistaid();
		nombrePista = PistaDAO.queryById(r.getPistaid()).getNombre();
		idBono = r.getIdbono();
		fechaReserva = r.getFecha_reserva();
		fechaCanjeo = r.getFecha_canjeo();
		duracion = r.getDuracion();
		precio = r.getPrecio();
		descuento = r.getDescuento();
		numAdultos = 0;
		numMenores = r.getNumeromenores();
		diff = Dificultad.INFANTIL;
		tipo = r.getIdbono() == -1? true : false;
		rb = new ReservaBean(id, idUsuario, idPista, nombrePista, idBono, fechaReserva, fechaCanjeo, 
				duracion, precio, descuento, numAdultos, numMenores, diff, tipo);
		reservas.add(rb);
	}
	
	for (ReservaFamiliarDTO r : rfamiliar) {
		id = r.getReservaid();
		idUsuario = r.getUserreservaid();
		idPista = r.getPistaid();
		nombrePista = PistaDAO.queryById(r.getPistaid()).getNombre();
		idBono = r.getIdbono();
		fechaReserva = r.getFecha_reserva();
		fechaCanjeo = r.getFecha_canjeo();
		duracion = r.getDuracion();
		precio = r.getPrecio();
		descuento = r.getDescuento();
		numAdultos = r.getNumeromayores();
		numMenores = r.getNumeromenores();
		diff = Dificultad.FAMILIAR;
		tipo = r.getIdbono() == -1? true : false;
		rb = new ReservaBean(id, idUsuario, idPista, nombrePista, idBono, fechaReserva, fechaCanjeo, 
				duracion, precio, descuento, numAdultos, numMenores, diff, tipo);
		reservas.add(rb);
	}
	
	request.setAttribute("reservas", reservas);
	nextPage = application.getInitParameter("listadoReservasView");
}
%>

<jsp:forward page="<%=nextPage%>"/>