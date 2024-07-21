/**
 * Objeto de acceso de datos de reservas familiar.
 * @author Carmona Balaguer, Gloria
 * @author Cortes Gonzalez, Alvaro
 */
package es.uco.pw.data.dao.reservas;

import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.business.reservas.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ReservaFamiliarDAO {
	
	/**
	 * Guarda una nueva reserva de tipo familiar en la base de datos.
	 * @param reserva	la reserva que se va a registrar en el sistema.
	 * @return el estado de la operacion.
	 */
	public static int save(ReservaFamiliarDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.save"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromayores());
			ps.setInt(10, reserva.getNumeromenores());
			ps.setString(11, "FAMILIAR");
			if(reserva.getIdbono()<0) {
				ps.setString(12, "INDIVIDUAL");
			}
			else {
				ps.setString(12, "BONO");
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
	
	public static int update(ReservaFamiliarDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.update"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromayores());
			ps.setInt(10, reserva.getNumeromenores());
			ps.setString(11, "FAMILIAR");
			if(reserva.getIdbono()<0) {
				ps.setString(12, "INDIVIDUAL");
			}
			else {
				ps.setString(12, "BONO");
			}
			ps.setInt(13, reserva.getReservaid());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
	 * Devuelve todas las reservas de tipo familiar de la base de datos.
	 * @return	una lista con todas las reservas de tipo familiar registradas en el sistema.
	 */
	public static ArrayList<ReservaFamiliarDTO> getAll() { 
		ArrayList<ReservaFamiliarDTO> reservas = new ArrayList<ReservaFamiliarDTO>();
		ReservaFamiliarDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.getAll"));
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
				int numeromayores = rs.getInt("numeromayores");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaFamiliarDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores, numeromayores);
				reservas.add(reserva);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	/**
	 * Devuelve todas las reservas de tipo familiar de un usuario de la base de datos.
	 * @param userreservaid contiene el id del usuario
	 * @return	una lista con todas las reservas de tipo familiar registradas en el sistema.
	 */
	public static ArrayList<ReservaFamiliarDTO> queryByUsuario(int userreservaid) { 
		ArrayList<ReservaFamiliarDTO> reservas = new ArrayList<ReservaFamiliarDTO>();
		ReservaFamiliarDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.queryByUsuario"));
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
				int numeromayores = rs.getInt("numeromayores");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaFamiliarDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores, numeromayores);
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
	public static ReservaFamiliarDTO queryById(int reservaid) {
		ReservaFamiliarDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.queryById"));
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
				int numeromayores = rs.getInt("numeromayores");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaFamiliarDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromenores, numeromayores);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reserva;
	}
	
	public static ArrayList<ReservaFamiliarDTO> queryByCanjeoPista(Date fecha_canjeo, int pistaid) {
		ReservaFamiliarDTO reserva = null;
		ArrayList<ReservaFamiliarDTO> reservas = new ArrayList<ReservaFamiliarDTO>();
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.queryByCanjeoPista"));
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
				int numeromayores = rs.getInt("numeromayores");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaFamiliarDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores, numeromenores);
				reservas.add(reserva);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		
		return reservas;
	}
	
	public static ReservaFamiliarDTO queryByUserCanjeo(int userreservaid, Date fecha_canjeo) {
		ReservaFamiliarDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.queryByCanjeoUser"));
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
				int numeromayores = rs.getInt("numeromayores");
				int numeromenores = rs.getInt("numeromenores");
				reserva = new ReservaFamiliarDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores, numeromenores);
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
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RFamiliar.delete"));
			ps.setInt(1, reservaid);
			status = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}			
		return status;
	}
	
}