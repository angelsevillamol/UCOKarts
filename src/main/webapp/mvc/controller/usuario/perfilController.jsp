<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
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
String mensajeBienvenida = request.getParameter("mensajeBienvenida");

// Si está logeado
if (!(customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado"))) {
	
	// Obtenemos sus reservasCompletadas
	ConexionBD cbd = ConexionBD.getInstance();
	java.sql.Connection con = cbd.getConnection();
	ReservaBean rb = null;
	java.util.Date today = new java.util.Date();
	java.util.ArrayList<ReservaBean> reservasPendientes = new java.util.ArrayList<ReservaBean>();
	java.util.ArrayList<ReservaBean> reservasCompletadas = new java.util.ArrayList<ReservaBean>();
	java.util.ArrayList<ReservaAdultosDTO> radultos = ReservaAdultosDAO.queryByUsuario(customer.getId());
	java.util.ArrayList<ReservaInfantilDTO> rinfantil = ReservaInfantilDAO.queryByUsuario(customer.getId());
	java.util.ArrayList<ReservaFamiliarDTO> rfamiliar = ReservaFamiliarDAO.queryByUsuario(customer.getId());

	// Campos del ReservaBean
	int id;
	int idUsuario = customer.getId();
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
	
	// Obtiene las reservas
	// Reservas adultos
	for (ReservaAdultosDTO r : radultos) {
		id = r.getReservaid();
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
		if (fechaCanjeo.before(today)) {
			reservasCompletadas.add(rb);
		}
		else {
			reservasPendientes.add(rb);
		}
	}
	
	// Reservas infantiles
	for (ReservaInfantilDTO r : rinfantil) {
		id = r.getReservaid();
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
		if (fechaCanjeo.before(today)) {
			reservasCompletadas.add(rb);
		}
		else {
			reservasPendientes.add(rb);
		}
	}
	
	// Reservas familiares
	for (ReservaFamiliarDTO r : rfamiliar) {
		id = r.getReservaid();
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
		if (fechaCanjeo.before(today)) {
			reservasCompletadas.add(rb);
		}
		else {
			reservasPendientes.add(rb);
		}
	}
	
	request.setAttribute("reservasPendientes", reservasPendientes);
	request.setAttribute("reservasCompletadas", reservasCompletadas);
	nextPage = application.getInitParameter("perfilView");
}
// Si se accede a esta página sin estar logeado.
else {
	// Se envía a la página de login.
	nextPage = application.getInitParameter("loginView");
}
%>

<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeBienvenida%>" name="mensajeBienvenida"/>
</jsp:forward>
