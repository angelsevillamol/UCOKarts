package es.uco.pw.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import es.uco.pw.business.pistas.Dificultad;
import es.uco.pw.business.pistas.Estado;
import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.business.reservas.ReservaAdultosDTO;
import es.uco.pw.business.reservas.ReservaInfantilDTO;
import es.uco.pw.business.reservas.ReservaFamiliarDTO;
import es.uco.pw.business.reservas.BonoDTO;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.KartDAO;
import es.uco.pw.data.dao.pistas.PistaDAO;
import es.uco.pw.data.dao.reservas.ReservaAdultosDAO;
import es.uco.pw.data.dao.reservas.ReservaInfantilDAO;
import es.uco.pw.data.dao.reservas.ReservaFamiliarDAO;
import es.uco.pw.data.dao.reservas.BonoDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class CrearReservaServlet
 */
public class CrearReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearReservaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void conectarServidorBD(HttpSession session) {
    	ConexionBD cbd = ConexionBD.getInstance();
		Connection con = cbd.getConnection();
		if (con == null) {
			ServletContext application = session.getServletContext();
			String dburl = application.getInitParameter("servidor");
			String dbusuario = application.getInitParameter("usuario");
			String dbpassword = application.getInitParameter("password");
			String pathSQL = application.getInitParameter("pathSQL");
			java.io.InputStream input = application.getResourceAsStream(pathSQL);
			java.util.Properties prop = new Properties();
			try {
				prop.load(input);
			} catch (IOException ex) {
	            ex.printStackTrace();
	        }
			cbd.setConnectionParameters(dburl, dbusuario, dbpassword, prop);
			con = cbd.getConnection();
		}
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		// Solo se puede reservar si se está logeado
		CustomerBean customer = (CustomerBean) session.getAttribute("customer"); //$NON-NLS-1$
		if (customer == null || customer.getRol() == null || customer.getRol().equals("Invitado")) {
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("loginView"));
			rd.forward(request, response);
		}
		
		// Obtiene el identificador de la pista.
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException nfe) {
        	request.setAttribute("id", request.getParameter("id"));
        	rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
        	rd.forward(request, response);
        }

		// Comprobar conexion a base de datos
		conectarServidorBD(session);
		
		// Obtiene la información de la pista.
		PistaDTO p = PistaDAO.queryById(id);
		
		// Si la pista no existe, lleva a error.
		if (p == null) {
			request.setAttribute("id", id); 
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
			rd.forward(request, response);
		} 
		// Si la pista no está disponible, indicarselo al usuario.
		else if ((p.getDisponibilidad() == false) || (p.getNumKartsDisponibles() == 0)) {
			request.setAttribute("pista", p); 
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaNoPermitidaErrorView"));
			rd.forward(request, response);
		} 
		// Si la pista puede reservarse, llevar a página de reservas.
		else {
			request.setAttribute("pista", p); 
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("crearReservaView"));
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		boolean sonParametrosValidos = true;
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		if (customer == null || customer.getRol() == null || customer.getRol().equals("Invitado")) {
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("loginView"));
			rd.forward(request, response);
		}
		else {
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
				// Realizar conexion con el servidor de base de datos
				conectarServidorBD(session);
				
				// Obtiene la información de la pista.
				PistaDTO p = PistaDAO.queryByNombre(nombre);
				
				// Si la pista no existe, lleva a error.
				if (p == null) {
					rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
					rd.forward(request, response);
				}
				else {
					int idpista = p.getId();
					precio = duracion / 4;
					
					if (customer.getAntiguedad() >= 12) {
						descuento = (float)0.9;
					}
				
					// Si se solicita hacerlo con bono
					if (bono.equals("si")) {
						Dificultad d = p.getDificultad();
						BonoDTO b = BonoDAO.queryByUseridTipo(userid, d);
						
						// Si el usuario pide que sea con bono y no tiene
						if (b == null) {
							rd = request.getRequestDispatcher(getServletContext().getInitParameter("noBonoErrorView"));
							rd.forward(request, response);
							return;
						}
						else {
							idbono = b.getIdbono();
							
							// Si el usuario pide que sea con bono y está caducado o gastado
							if (b.getSesionesgastadas() == 5 || fechaReserva.after(b.getFechacaducidad())) {
								rd = request.getRequestDispatcher(getServletContext().getInitParameter("noBonoErrorView"));
								rd.forward(request, response);
								return;
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
						rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaFailureView"));
						rd.forward(request, response);
					}
					// En el caso de FAMILIAR, hay que comprobar eso para ambos tipos de karts
					else if (p.getDificultad() == Dificultad.FAMILIAR && 
							(participantesMayores > p.getNumKartsAdultosDisponibles() || participantesMenores > p.getNumKartsInfantilesDisponibles())) {	
						rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaFailureView"));
						rd.forward(request, response);
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
							rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaExitoView"));
							rd.forward(request, response);
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
							rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaExitoView"));
							rd.forward(request, response);
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
							rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaExitoView"));
							rd.forward(request, response);
						}
					}
				}
			}
			// Si los valores del formulario son incorrectos.
			else {
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("reservaFailureView"));
				rd.forward(request, response);
			}
		}
	}
}
