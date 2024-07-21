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
import java.util.ArrayList;
import java.util.Properties;

import es.uco.pw.business.pistas.Dificultad;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.data.dao.pistas.PistaDAO;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
    
    private ArrayList<PistaDTO> getPistas(boolean fAdulto, boolean fInfantiles, boolean fFamiliares, int fMinKartsDisponibles, boolean fDisponible) {
    	ArrayList<PistaDTO> pistas = PistaDAO.getAll();
    	ArrayList<PistaDTO> resultado = new ArrayList<PistaDTO>();
    	
    	for (PistaDTO p : pistas) {
    		if (filtrarPista(p, fAdulto, fInfantiles, fFamiliares, fMinKartsDisponibles, fDisponible)) {
        		resultado.add(p);
    		}
    	}
    	
    	return resultado;
    }
    
    private boolean filtrarPista(PistaDTO p, boolean fAdulto, boolean fInfantiles, boolean fFamiliares, int fMinKartsDisponibles, boolean fDisponible) {
    	
    	if (fAdulto == false && p.getDificultad() == Dificultad.ADULTOS) {
    		return false;
    	}
    	
    	if (fInfantiles == false && p.getDificultad() == Dificultad.INFANTIL) {
    		return false;
    	}
    	
    	if (fFamiliares == false && p.getDificultad() == Dificultad.FAMILIAR) {
    		return false;
    	}
    	
    	if (fMinKartsDisponibles > p.getNumKartsDisponibles()) {
    		return false;
    	}
    	
    	return true;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer"); //$NON-NLS-1$
		if (customer == null) {
			customer = new CustomerBean();
			session.setAttribute("customer", customer);
		}
		
		// Asigna los filtros.
		boolean fAdulto = request.getParameter("adultos") != null;
		boolean fInfantiles = request.getParameter("infantiles") != null;
		boolean fFamiliares = request.getParameter("familiares") != null;
		if (!fAdulto && !fInfantiles && !fFamiliares) {
    		fAdulto = true;
    		fInfantiles = true;
    		fFamiliares = true;
    	}
    	
		String str = request.getParameter("numKartsDisponibles");
		int fMinKartsDisponibles = (str == null || str == "")? 0 : Integer.parseInt(str);
		boolean fDisponible = true;
		
		// Comprobar conexion a base de datos
		conectarServidorBD(session);
		
		// Aplica los filtros
		ArrayList<PistaDTO> pistas = getPistas(fAdulto, fInfantiles, fFamiliares, fMinKartsDisponibles, fDisponible);
		if (pistas == null) {
			pistas = new ArrayList<PistaDTO>();
		}
		
		request.setAttribute("pistas", pistas);
		rd = request.getRequestDispatcher(getServletContext().getInitParameter("homeView"));
		rd.forward(request, response);
	}
}
