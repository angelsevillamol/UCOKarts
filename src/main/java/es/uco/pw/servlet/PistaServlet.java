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
 * Servlet implementation class PistaServlet
 */
public class PistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PistaServlet() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException nfe) {
        	request.setAttribute("id", request.getParameter("id"));
        	rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
        }

		CustomerBean customer = (CustomerBean) session.getAttribute("customer"); //$NON-NLS-1$
		if (customer == null) {
			customer = new CustomerBean();
			session.setAttribute("customer", customer);
		}
		
		// Comprobar conexion a base de datos
		conectarServidorBD(session);
		PistaDTO p = PistaDAO.queryById(id);
		
		if (p == null) {
			request.setAttribute("id", id); //$NON-NLS-1$
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaErrorView"));
		} else {
			request.setAttribute("pista", p); //$NON-NLS-1$
			rd = request.getRequestDispatcher(getServletContext().getInitParameter("pistaView"));
		}
		
		rd.forward(request, response);
	}
}
