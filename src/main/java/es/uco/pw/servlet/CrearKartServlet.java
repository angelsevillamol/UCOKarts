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

import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.business.pistas.Estado;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.KartDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class CrearKartServlet
 */
public class CrearKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearKartServlet() {
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
	 * @see HttpServletdoPost(HttpServletRequest request, HttpServletResponse response)
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
			// Se obtienen los valores
			boolean tipo = request.getParameter("tipo").equals("ADULTO");
			
			// Comprobar conexion a base de datos
			conectarServidorBD(session);
			
			// Se crea el kart y se inserta en la base de datos.
			KartDTO kart = new KartDTO(0, tipo, Estado.DISPONIBLE);
			KartDAO.save(kart);
			
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("listadoKartsController"));
			rd.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(getServletContext().getInitParameter("errorPage"));
		rd.forward(request, response);
	}
}
