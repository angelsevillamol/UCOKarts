package es.uco.pw.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import es.uco.pw.business.pistas.Dificultad;
import es.uco.pw.business.pistas.Estado;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.KartDAO;
import es.uco.pw.data.dao.pistas.PistaDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class AsociarKartServlet
 */
public class AsociarKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsociarKartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Comrpueba si un kart cumple las condiciones para asociarse a una pista.
     * @param kart contiene la informacion del kart a comprobar.
     * @return true si el kart se puede asociar a la pista, false en caso contrario.
     */
    public boolean puedeAsociar(PistaDTO pista, KartDTO kart) {
    	if (kart.getEstado() == Estado.MANTENIMIENTO) {
    		return false;
    	}
    	else if (pista.getNumKartsAsociados() >= pista.getMaxKarts()) {
    		return false;
    	}
    	else if (pista.getDificultad() == Dificultad.INFANTIL && kart.getTipo() == true) {
			return false;
		}
		else if (pista.getDificultad() == Dificultad.ADULTOS && kart.getTipo() == false) {
			return false;
		}
    	
		return true;
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
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		if (customer == null || customer.getRol() == null || !customer.getRol().equals("Administrador")) {
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("adminACLErrorView"));
		}
		else {
			// Se obtienen el identificador del kart
			int id = 0;
			try {
				id = Integer.parseInt(request.getParameter("id"));
	        } catch (NumberFormatException nfe) {
	        	request.setAttribute("id", request.getParameter("id"));
	        	rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
	        }
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			KartDTO k = KartDAO.queryById(id);
			if (k == null) {
				request.setAttribute("id", id);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
			} else {
				// Se verifica que el kart no esté asociado
				if (KartDAO.queryAsociado(k)) {
					rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
				} else {
					// Se obtienen todas las pistas
					java.util.ArrayList<PistaDTO> pistas = PistaDAO.getAll();
					// Se seleccionan aquellas a las que el kart pueda asociarse
					java.util.ArrayList<PistaDTO> resultado = new java.util.ArrayList<PistaDTO>();
					for (PistaDTO p : pistas) {
						if (puedeAsociar(p, k)) {
							resultado.add(p);
						}
					}
					
					// Si no se han encontrado pistas suficientes.
					if (resultado == null || resultado.size() == 0) {
						rd = request.getRequestDispatcher(getServletContext().getInitParameter("asociarKartErrorView"));				
					} else {
						request.setAttribute("kart", k);
						request.setAttribute("pistas", resultado);
						rd = request.getRequestDispatcher(getServletContext().getInitParameter("asociarKartView"));
					}
				}
			}
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		if (customer == null || customer.getRol() == null || !customer.getRol().equals("Administrador")) {
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("adminACLErrorView"));
			rd.forward(request, response);
		}
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombrePista");
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			// Comprobar existencia del kart y de la pista.
			KartDTO kart = KartDAO.queryById(id);
			PistaDTO pista = PistaDAO.queryByNombre(nombre);
			if (kart == null || pista == null) {
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
			}
			else {
				// Se asocia el kart
				if (puedeAsociar(pista, kart)) {
					KartDAO.asociarAPista(kart, pista);
					rd = request.getRequestDispatcher(getServletContext().getInitParameter("listadoKartsController"));
				}
				else {
					rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
				}
				
			}
			rd.forward(request, response);
		}
	}

}
