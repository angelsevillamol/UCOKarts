/**
 * Objeto de acceso de datos de usuarios.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.data.dao.usuario;

import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.business.usuario.UsuarioDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class UsuarioDAO {
	
	/**
	 * Da de alta a un usuario en la base de datos.
	 * @param usuario	el usuario a dar de alta en el sistema.
	 * @return el estado de la operacion.
	 */
	public static int save(UsuarioDTO usuario, String password) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.save"));
			
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
			ps.setDate(5, new java.sql.Date(usuario.getFechaInscripcion().getTime()));
			ps.setString(6, password);
			ps.setInt(7, (usuario.esAdmin()? 1 : 0));
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
	 * Realiza el inicio de sesion 
	 * 
	 * @param   email     la cadena con el correo electronico del usuario 
	 * @param   password  la cadena con la contraseña de acceso del usuario
	 * @return            el usuario correspondiente si la informacion de 
	 *                    sesion es correcta
	 */
	public static UsuarioDTO login(String email, String password) { 
		UsuarioDTO usuario = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.login"));

			ps.setString(1,  email);
			ps.setString(2,  password);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
			    String nombre = rs.getString("nombre");
			    String apellidos = rs.getString("apellidos");
			    Date fechaNacimiento = rs.getDate("fecha_nacimiento");
			    Date fechaInscripcion = rs.getDate("fecha_inscripcion");
			    boolean admin = rs.getInt("admin") == 1;
			    usuario = new UsuarioDTO(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, admin);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
			
		return usuario;
	}
	
	/**
     * Modifica la informacion personal de un usuario en la base de datos. 
     * @param usuario contiene la nueva informacion del usuario. 
     * @param password contiene la nueva contraseña del usuario. 
     * @return el estado de la operacion.
     */
	public static int update(UsuarioDTO usuario, String password) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.update"));
			
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setDate(4, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
			ps.setString(5, password);
			ps.setInt(6, (usuario.esAdmin()? 1 : 0));
			ps.setInt(7, usuario.getId());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
	 * Devuelve todos los usuarios de la base de datos.
	 * @return	una lista con todos los usuarios registrados en el sistema.
	 */
	public static ArrayList<UsuarioDTO> getAll() { 
		ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuario = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.getAll"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
			    String email = rs.getString("email");
			    String nombre = rs.getString("nombre");
			    String apellidos = rs.getString("apellidos");
			    Date fechaNacimiento = rs.getDate("fecha_nacimiento");
			    Date fechaInscripcion = rs.getDate("fecha_inscripcion");
			    boolean admin = rs.getInt("admin") == 1;
			    usuario = new UsuarioDTO(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, admin);
			    usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return usuarios;
	}
	
	/**
     * Devuelve un usuario registrado a partir de su identificador.
     * @param id 	el numero de identificacion del usuario.
     * @return 	el usuario encontrado. 
     * 			Si no existe un usuario con dicho identificador, entonces devuelve null.
     */
	public static UsuarioDTO queryById(int id) {
		UsuarioDTO usuario = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.queryById"));
			
			ps.setInt(1,  id);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	String email = rs.getString("email");
		        String nombre = rs.getString("nombre");
		        String apellidos = rs.getString("apellidos");
		        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
		        Date fechaInscripcion = rs.getDate("fecha_insripcion");
		        boolean admin = rs.getInt("admin") == 1;
		        usuario = new UsuarioDTO(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, admin);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return usuario;
	}
	
	/**
     * Devuelve un usuario registrado a partir de su email.
     * @param email	el correo electronico del usuario a buscar.
     * @return 	el usuario encontrado. 
     * 			Si no existe un usuario con dicho correo, entonces devuelve null.
     */
	public static UsuarioDTO queryByEmail(String email) { 
		UsuarioDTO usuario = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("usuarios.queryByEmail"));
			
			ps.setString(1,  email);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
			    String nombre = rs.getString("nombre");
			    String apellidos = rs.getString("apellidos");
			    Date fechaNacimiento = rs.getDate("fecha_nacimiento");
			    Date fechaInscripcion = rs.getDate("fecha_inscripcion");
			    boolean admin = rs.getInt("admin") == 1;
			    usuario = new UsuarioDTO(id, nombre, apellidos, fechaNacimiento, fechaInscripcion, email, admin);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
			
		return usuario;
	}
}
