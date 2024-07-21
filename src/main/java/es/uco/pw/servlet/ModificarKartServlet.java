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

import es.uco.pw.business.pistas.Estado;
import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.KartDAO;
import es.uco.pw.data.dao.pistas.PistaDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class ModificarKartServlet
 */
public class ModificarKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarKartServlet() {
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
	        	rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
	        }
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			KartDTO k = KartDAO.queryById(id);
			if (k == null) {
				request.setAttribute("id", id);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
			} else {
				request.setAttribute("kart", k);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("modificarKartView"));
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
			Estado estado = Estado.valueOf(request.getParameter("estado"));
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			// Comprobar existencia del kart
			KartDTO kart = KartDAO.queryById(id);
			if (kart == null) {
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
			}
			else {
				// Se modifica el kart
				kart.setEstado(estado);
				KartDAO.update(kart);
				rd = request.getRequestDispatcher(getServletContext().getInitParameter("listadoKartsController"));
			}
			rd.forward(request, response);
		}
	}
}
