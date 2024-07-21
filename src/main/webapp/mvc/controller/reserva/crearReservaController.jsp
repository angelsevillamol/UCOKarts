<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD"%>
<%@ page import="es.uco.pw.data.dao.usuario.UsuarioDAO,es.uco.pw.business.usuario.UsuarioDTO" %>
<%@ page import="es.uco.pw.data.dao.pistas.KartDAO,es.uco.pw.business.pistas.KartDTO,es.uco.pw.business.pistas.Estado" %>
<%@ page import="es.uco.pw.data.dao.pistas.PistaDAO,es.uco.pw.business.pistas.PistaDTO,es.uco.pw.business.pistas.Dificultad" %>
<%@ page import="es.uco.pw.data.dao.reservas.BonoDAO,es.uco.pw.business.reservas.BonoDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaAdultosDAO,es.uco.pw.business.reservas.ReservaAdultosDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaInfantilDAO,es.uco.pw.business.reservas.ReservaInfantilDTO" %>
<%@ page import="es.uco.pw.data.dao.reservas.ReservaFamiliarDAO,es.uco.pw.business.reservas.ReservaFamiliarDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";
boolean sonParametrosValidos = true;

if (!(customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado"))) {	
	// Se obtienen los parametros
	int duracion = Integer.parseInt(request.getParameter("duracion"));
	int userid = customer.getId();
	String nombre = request.getParameter("nombre");
	float precio = 0;
	float descuento = 1;
	
	// Se obtiene el número de participantes
	int participantes = 0;
	try {
		participantes = Integer.parseInt(request.getParameter("Nparticipantes"));
	} catch (NumberFormatException e) {
		participantes = 0;
	}
	
	int participantesMenores = 0;
	try {
		participantesMenores = Integer.parseInt(request.getParameter("NparticipantesMenores"));
	} catch (NumberFormatException e) {
		participantesMenores = 0;
	}
	
	int participantesMayores = 0;
	try {
		participantesMayores = Integer.parseInt(request.getParameter("NparticipantesMayores"));
	} catch (NumberFormatException e) {
		participantesMayores = 0;
	}

	// Se verifica la fecha de canjeo
	java.util.Date fechaReserva = new java.util.Date();
	java.util.Date fechaCanjeo = new java.util.Date();
    try {
    	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
    	fechaCanjeo = df.parse(request.getParameter("fechaCanjeo"));
	} catch (java.text.ParseException e) {
		sonParametrosValidos = false;
	}
    if (fechaReserva.after(fechaCanjeo)) {
    	sonParametrosValidos = false;
    }

    // Se verifica el tipo de reserva (individual o bono)
    String bono = request.getParameter("bono");
    int idbono = -1;
	if (bono == null || bono.isEmpty()) {
		sonParametrosValidos = false;
    }
		
    // Se verifica el número de participantes
	if ((participantes < 1) && ((participantesMenores + participantesMayores) < 1)) {
		sonParametrosValidos = false;
    }
    
	// Se verifica la duracion
	if (duracion != 60 && duracion != 90 && duracion != 120) {
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
		
		// Obtiene la información de la pista.
		PistaDTO p = PistaDAO.queryByNombre(nombre);
		
		// Si la pista no existe, lleva a error.
		if (p == null) {
			nextPage = application.getInitParameter("pistaErrorView");
		}
		else {
			int idpista = p.getId();
			precio = duracion / 4;
			
			if (customer.getAntiguedad() >= 12) {
				descuento = (float)0.9;
			}
		
			// Si se solicita hacerlo con bono
			if (bono.equals("si")) {
				Dificultad d = Dificultad.valueOf("INFANTIL");
				BonoDTO b = BonoDAO.queryByUseridTipo(userid, d);
				
				// Si el usuario pide que sea con bono y no tiene
				if (b == null) {
					nextPage = application.getInitParameter("noBonoErrorView");
				}
				else {
					idbono = b.getIdbono();
					
					// Si el usuario pide que sea con bono y está caducado o gastado
					if (b.getSesionesgastadas() == 5 || fechaReserva.after(b.getFechacaducidad())) {
						nextPage = application.getInitParameter("noBonoErrorView");
					}
					// Si es válido, se consume una sesión y se actualiza el precio
					else {
						b.setSesionesgastadas(b.getSesionesgastadas() + 1);
						BonoDAO.update(b);
						precio = precio * (float)0.95;
					}
				}
			}
			
			// Si el número de karts solicitados es mayor que el numero de karts disponibles
			if (participantes > p.getNumKartsDisponibles()) {
				nextPage = application.getInitParameter("reservaFailureView");
			}
			// En el caso de FAMILIAR, hay que comprobar eso para ambos tipos de karts
			else if (p.getDificultad() == Dificultad.FAMILIAR && 
					(participantesMayores > p.getNumKartsAdultosDisponibles() || participantesMenores > p.getNumKartsInfantilesDisponibles())) {	
				nextPage = application.getInitParameter("reservaFailureView");
			}
			// Si la petición de reserva es correcta
			else {
				java.util.ArrayList<KartDTO> Karts = p.getKarts();
				// Si la pista es infantil
				if (p.getDificultad() == Dificultad.INFANTIL) {
					// Se reservan los karts
					int numKartsReservados = 0;
					for (KartDTO k:Karts) {
						if (k.getEstado() == Estado.DISPONIBLE && numKartsReservados < participantes) {
							k.setEstado(Estado.RESERVADO);
							KartDAO.update(k);
							numKartsReservados++;
						}
					}
					
					// Se realiza la reserva
					ReservaInfantilDTO Reserva = new ReservaInfantilDTO(0, userid, idpista, idbono, fechaReserva, fechaCanjeo, duracion, precio, descuento, participantes);
					ReservaInfantilDAO.save(Reserva);
					nextPage = application.getInitParameter("reservaExitoView");
				}
				// Si la pista es adulta
				else if (p.getDificultad() == Dificultad.ADULTOS) {
					// Se reservan los karts
					int numKartsReservados = 0;
					for (KartDTO k : Karts) {
						if (k.getEstado() == Estado.DISPONIBLE && numKartsReservados < participantes) {
							k.setEstado(Estado.RESERVADO);
							KartDAO.update(k);
							numKartsReservados++;
						}
					}
					
					// Se realiza la reserva
					ReservaAdultosDTO Reserva = new ReservaAdultosDTO(0, userid, idpista, idbono, fechaReserva, fechaCanjeo, duracion, precio, descuento, participantes);
					ReservaAdultosDAO.save(Reserva);
					nextPage = application.getInitParameter("reservaExitoView");
				}
				// Si la pista es familiar
				else if (p.getDificultad() == Dificultad.FAMILIAR) {
					// Se reservan los karts
					int numKartsInfantilesReservados = 0; 
					int numKartsAdultosReservados = 0;
					for (KartDTO k : Karts) {
						if (k.getEstado() == Estado.DISPONIBLE && !k.getTipo() && numKartsInfantilesReservados < participantesMenores) {
							k.setEstado(Estado.RESERVADO);
							KartDAO.update(k);
							numKartsInfantilesReservados++;
						}
						else if (k.getEstado() == Estado.DISPONIBLE && k.getTipo() && numKartsAdultosReservados < participantesMayores) {
							k.setEstado(Estado.RESERVADO);
							KartDAO.update(k);
							numKartsAdultosReservados++;
						}
					}
					
					// Se realiza la reserva
					ReservaFamiliarDTO Reserva = new ReservaFamiliarDTO(0, userid, idpista, idbono, fechaReserva, fechaCanjeo, duracion, precio, descuento, participantesMenores, participantesMayores);
					ReservaFamiliarDAO.save(Reserva);
					nextPage = application.getInitParameter("reservaExitoView");
				}
			}
		}
	}
	// Si los valores del formulario son incorrectos.
	else {
		nextPage = application.getInitParameter("reservaFailureView");
	}
}
else {
	// Se envía a la página de login.
	nextPage = application.getInitParameter("loginView");
}
%>

<jsp:forward page="<%=nextPage%>"></jsp:forward>