/**
 * Conector con el servidor de la base de datos
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.data.dao.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
	
	private static Conexion instance = null;
	
	/**
	 * Conector con el servidor de base de datos.
	 */
	private Connection con;
	private String url;
	private String usuario;
	private String password;
	Properties stmt;
	
	private Conexion() {
		// Obtiene la informacion del servidor
		try {
			InputStream pathServer = getClass().getClassLoader().getResourceAsStream("/config.properties");
			InputStream pathSQL = getClass().getClassLoader().getResourceAsStream("/config.properties");
			BufferedReader bufferedServer = new BufferedReader(new InputStreamReader(pathServer));
			BufferedReader bufferedSQL = new BufferedReader(new InputStreamReader(pathSQL));
			        	
			Properties prop = new Properties();
			prop.load(bufferedServer);
			            
			url = prop.getProperty("database.url");
			usuario = prop.getProperty("database.usuario");
			password = prop.getProperty("database.password");
			
			stmt = new Properties();
			stmt.load(bufferedSQL);
			            
		} catch (IOException ex) {
			ex.printStackTrace();
		}
					
		System.out.println(url);
		System.out.println(usuario);
		System.out.println(password);
					
		// Obtiene una instancia del Driver de MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection(url, usuario, password);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();
		}
		return instance;
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
	public static String getStmt(String optStr) {
		String stmt = null;
		
		try (InputStream input = new FileInputStream("/UCOKarts/WEB-INF/sql.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            stmt = prop.getProperty(optStr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return stmt;
	}
}
