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

import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.PistaDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ModificarPistaServlet
 */
public class ModificarPistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPistaServlet() {
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
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		if (customer == null || customer.getRol() == null || !customer.getRol().equals("Administrador")) {
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("adminACLErrorView"));
		}
		else {
			// Se obtienen el identificador de la pista
			int id = 0;
			try {
				id = Integer.parseInt(request.getParameter("id"));
	        } catch (NumberFormatException nfe) {
	        	request.setAttribute("id", request.getParameter("id"));
	        	rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
	        }
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			PistaDTO p = PistaDAO.queryById(id);
			if (p == null) {
				request.setAttribute("id", id);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
			} else {
				request.setAttribute("pista", p);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("modificarPistaView"));
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
			String nombre = request.getParameter("nombre");
			boolean disponibilidad = request.getParameter("disponibilidad").equals("true");
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			// Comprobar existencia de la pista
			PistaDTO pista = PistaDAO.queryByNombre(nombre);
			if (pista == null) {
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
			}
			else {
				// Se modifica el kart
				pista.setDisponibilidad(disponibilidad);
				PistaDAO.update(pista);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("listadoPistasController"));
			}
			rd.forward(request, response);
		}
	}
}
