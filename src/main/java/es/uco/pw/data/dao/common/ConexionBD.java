/**
 * Conector con el servidor de la base de datos
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.data.dao.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionBD {
	
	private static ConexionBD instance = null;
	
	/**
	 * Conector con el servidor de base de datos.
	 */
	protected Connection con = null;
	
	/**
	 * Direcci�n del servidor de la base de datos.
	 */
	private String url;
	
	/**
	 * Nombre del usuario que realiza la conexi�n con el servidor.
	 */
	private String usuario;
	
	/**
	 * Cadena con la contrase�a del usuario del servidor.
	 */
	private String password;
	
	/**
	 * Fichero de propiedades con las sentencias SQL a realizar.
	 */
	Properties sqlstmt;
	
	/**
	 * Constructor privado del conector.
	 */
	private ConexionBD() {
		
	}
	
	/**
	 * Devuelve la instancia de la conexi�n con la base de datos.
	 * @return la instancia del conector.
	 */
	public static ConexionBD getInstance() {
		if (instance == null) {
			instance = new ConexionBD();
		}
		return instance;
	}
	
	/**
	 * Establece los par�metros necesarios del servidor de la base de datos.
	 * @param url 		cadena con la direcci�n del servidor de base de datos.
	 * @param usuario	cadena con el nombre del usuario que realiza la conexi�n.
	 * @param password	cadena con la contrase�a del usuario del servidor.
	 * @param sqlstmt	fichero de propiedades con las sentencias SQL a realizar.
	 */
	public void setConnectionParameters(String url, String usuario, String password, Properties sqlstmt) {
		this.url = url;
		this.usuario = usuario;
		this.password = password;
		this.sqlstmt = sqlstmt;
	}

	/**
	 * Establece conexion con el servidor de base de datos.
	 * @return la conexion con la base de datos.
	 */
	public Connection getConnection() {	
		if (con == null) {
			// Obtiene una instancia del Driver de MySQL
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con =  DriverManager.getConnection(url, usuario, password);
			} catch(Exception e) {
				System.out.println(e);
			}
		}
		
		return con;
	}			

	/**
	 * Obtiene una sentencia de SQL del fichero de propiedades.
	 * @param optStr	cadena con la clave de la sentencia a obtener.
	 * @return la cadena con la sentencia en SQL.
	 */
	public String getStmt(String optStr) {
		String stmt = null;
        stmt = sqlstmt.getProperty(optStr);
		return stmt;
	}
}
