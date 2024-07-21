/**
 * Objeto de acceso de datos de reservas infantil.
 * @author Carmona Balaguer, Gloria
 * @author Cortes Gonzalez, Alvaro
 */
package es.uco.pw.data.dao.reservas;

import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.business.reservas.ReservaInfantilDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ReservaInfantilDAO {
	
	/**
	 * Guarda una nueva reserva de tipo infantil en la base de datos.
	 * @param reserva	la reserva que se va a registrar en el sistema.
	 * @return el estado de la operacion.
	 */
	public static int save(ReservaInfantilDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.save"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromenores());
			ps.setString(10, "INFANTIL");
			if(reserva.getIdbono()<0) {
				ps.setString(11, "INDIVIDUAL");
			}
			else {
				ps.setString(11, "BONO");
			}

			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Modifica la informacion de una reserva en la base de datos. 
     * @param reserva contiene la nueva informacion de la reserva. 
     * @return el estado de la operacion.
     */
	public static int update(ReservaInfantilDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.update"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromenores());
			ps.setString(10, "INFANTIL");
			if(reserva.getIdbono()<0) {
				ps.setString(11, "INDIVIDUAL");
			}
			else {
				ps.setString(11, "BONO");
			}
			ps.setInt(12, reserva.getReservaid());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
	 * Devuelve todas las reservas de tipo infantil de la base de datos.
	 * @return	una lista con todas las reservas de tipo infantil registradas en el sistema.
	 */
	public static ArrayList<ReservaInfantilDTO> getAll() { 
		ArrayList<ReservaInfantilDTO> reservas = new ArrayList<ReservaInfantilDTO>();
		ReservaInfantilDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.getAll"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int reservaid = rs.getInt("reservaid");
				int userreservaid = rs.getInt("userreservaid");
				int pistaid = rs.getInt("pistaid");
				int idbono = rs.getInt("idbono");
				Date fecha_reserva = rs.getDate("fechareserva");
				Date fecha_canjeo = rs.getDate("fechacanjeo");
				int duracion = rs.getInt("duracion");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaInfantilDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores);
				reservas.add(reserva);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	/**
	 * Devuelve todas las reservas de tipo infantil de un usuario de la base de datos.
	 * @param userreservaid contiene el id del usuario
	 * @return	una lista con todas las reservas de tipo infantil registradas en el sistema.
	 */
	public static ArrayList<ReservaInfantilDTO> queryByUsuario(int userreservaid) { 
		ArrayList<ReservaInfantilDTO> reservas = new ArrayList<ReservaInfantilDTO>();
		ReservaInfantilDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.queryByUsuario"));
			ps.setInt(1, userreservaid);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int reservaid = rs.getInt("reservaid");
				int pistaid = rs.getInt("pistaid");
				int idbono = rs.getInt("idbono");
				Date fecha_reserva = rs.getDate("fechareserva");
				Date fecha_canjeo = rs.getDate("fechacanjeo");
				int duracion = rs.getInt("duracion");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaInfantilDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores);
				reservas.add(reserva);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	/**
	 * Devuelve los datos de una reserva a partir de su identificador.
	 * @param reservaid contiene el id de la reserva
	 * @return	todos los datos de la reserva en concreto.
	 */	 
	public static ReservaInfantilDTO queryById(int reservaid) {
		ReservaInfantilDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.queryById"));
			ps.setInt(1, reservaid);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	int userreservaid = rs.getInt("userreservaid");
				int pistaid = rs.getInt("pistaid");
				int idbono = rs.getInt("idbono");
				Date fecha_reserva = rs.getDate("fechareserva");
				Date fecha_canjeo = rs.getDate("fechacanjeo");
				int duracion = rs.getInt("duracion");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaInfantilDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reserva;
	}
	
	public static ArrayList<ReservaInfantilDTO> queryByCanjeoPista(Date fecha_canjeo, int pistaid) {
		ReservaInfantilDTO reserva = null;
		ArrayList<ReservaInfantilDTO> reservas = new ArrayList<ReservaInfantilDTO>();
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.queryByCanjeoPista"));
			ps.setDate(1, new java.sql.Date(fecha_canjeo.getTime()));
			ps.setInt(2, pistaid);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
				int reservaid = rs.getInt("reservaid");
		    	int userreservaid = rs.getInt("userreservaid");
				int idbono = rs.getInt("idbono");
				Date fecha_reserva = rs.getDate("fechareserva");
				int duracion = rs.getInt("duracion");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaInfantilDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores);
				reservas.add(reserva);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	public static ReservaInfantilDTO queryByUserCanjeo(int userreservaid, Date fecha_canjeo) {
		ReservaInfantilDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.queryByCanjeoUser"));
			ps.setDate(1, new java.sql.Date(fecha_canjeo.getTime()));
			ps.setInt(2, userreservaid);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
				int reservaid = rs.getInt("reservaid");
		    	int pistaid = rs.getInt("pistaid");
				int idbono = rs.getInt("idbono");
				Date fecha_reserva = rs.getDate("fechareserva");
				int duracion = rs.getInt("duracion");
				float precio = rs.getFloat("precio");
				float descuento = rs.getFloat("descuento");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaInfantilDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reserva;
	}
	
	public static int delete(int reservaid) {
		int status=0;
		
		try {
		
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RInfantil.delete"));
			ps.setInt(1, reservaid);
			status = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}			
			return status;
	}
	
}