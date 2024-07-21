/**
 * Objeto de acceso de datos de reservas adulto.
 * @author Carmona Balaguer, Gloria
 * @author Cortes Gonzalez, Alvaro
 */
package es.uco.pw.data.dao.reservas;

import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.business.reservas.ReservaAdultosDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ReservaAdultosDAO {
	
	/**
	 * Guarda una nueva reserva de tipo adulto en la base de datos.
	 * @param reserva	la reserva que se va a registrar en el sistema.
	 * @return el estado de la operacion.
	 */
	public static int save(ReservaAdultosDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.save"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromayores());
			ps.setString(10, "ADULTOS");
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
	
	public static int update(ReservaAdultosDTO reserva) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.update"));
			
			ps.setInt(1, reserva.getUserreservaid());
			ps.setInt(2, reserva.getPistaid());
			ps.setInt(3, reserva.getIdbono());
			ps.setDate(4, new java.sql.Date(reserva.getFecha_reserva().getTime()));
			ps.setDate(5, new java.sql.Date(reserva.getFecha_canjeo().getTime()));
			ps.setInt(6, reserva.getDuracion());
			ps.setFloat(7, reserva.getPrecio());
			ps.setFloat(8, reserva.getDescuento());
			ps.setInt(9, reserva.getNumeromayores());
			ps.setString(10, "ADULTOS");
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
	 * Devuelve todas las reservas de tipo adulto de la base de datos.
	 * @return	una lista con todas las reservas de tipo iadulto registradas en el sistema.
	 */
	public static ArrayList<ReservaAdultosDTO> getAll() { 
		ArrayList<ReservaAdultosDTO> reservas = new ArrayList<ReservaAdultosDTO>();
		ReservaAdultosDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.getAll"));
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
				reserva = new ReservaAdultosDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores);
				reservas.add(reserva);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	/**
	 * Devuelve todas las reservas de tipo adulto de un usuario de la base de datos.
	 * @param userreservaid contiene el id del usuario
	 * @return	una lista con todas las reservas de tipo adulto registradas en el sistema.
	 */
	public static ArrayList<ReservaAdultosDTO> queryByUsuario(int userreservaid) { 
		ArrayList<ReservaAdultosDTO> reservas = new ArrayList<ReservaAdultosDTO>();
		ReservaAdultosDTO reserva = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.queryByUsuario"));
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
				reserva = new ReservaAdultosDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores);
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
	public static ReservaAdultosDTO queryById(int reservaid) {
		ReservaAdultosDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.queryById"));
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
				reserva = new ReservaAdultosDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reserva;
	}
	
	public static ArrayList<ReservaAdultosDTO> queryByCanjeoPista(Date fecha_canjeo, int pistaid) {
		ReservaAdultosDTO reserva = null;
		ArrayList<ReservaAdultosDTO> reservas = new ArrayList<ReservaAdultosDTO>();
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.queryByCanjeoPista"));
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
				reserva = new ReservaAdultosDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores);
				reservas.add(reserva);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return reservas;
	}
	
	public static ReservaAdultosDTO queryByUserCanjeo(int userreservaid, Date fecha_canjeo) {
		ReservaAdultosDTO reserva = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.queryByCanjeoUser"));
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
				reserva = new ReservaAdultosDTO(reservaid, userreservaid, pistaid, idbono, fecha_reserva, fecha_canjeo, duracion, precio, descuento, numeromayores);
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
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("RAdultos.delete"));
			ps.setInt(1, reservaid);
			status = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}			
			return status;
	}
	
}