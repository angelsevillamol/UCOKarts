/**
 * Objeto de acceso de datos de bonos.
 * @author Carmona Balaguer, Gloria
 * @author Cortes Gonzalez, Alvaro
 */
package es.uco.pw.data.dao.reservas;

import es.uco.pw.data.dao.common.ConexionBD;
import es.uco.pw.business.pistas.Dificultad;
import es.uco.pw.business.reservas.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BonoDAO {

	/**
	* Guarda un nuevo bono en la base de datos.
	* @param bono	el bono que se va a registrar en el sistema.
	* @return el estado de la operacion.
	*/
	public static int save(BonoDTO bono) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("Bono.save"));
			
			ps.setString(1, bono.getTipobono().toString());
			ps.setDate(2, new java.sql.Date(bono.getFechacomprabono().getTime()));
			ps.setDate(3, new java.sql.Date(bono.getFechacaducidad().getTime()));
			ps.setInt(4, bono.getSesionesgastadas());
			ps.setInt(5, bono.getUserid());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Modifica la informacion de un bono registrado en la base de datos. 
     * @param bono contiene la nueva información del bono. 
     * @return el estado de la operacion.
     */
	public static int update(BonoDTO bono) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("Bono.update"));
			
			ps.setString(1, bono.getTipobono().toString());
			ps.setDate(2, new java.sql.Date(bono.getFechacomprabono().getTime()));
			ps.setDate(3, new java.sql.Date(bono.getFechacaducidad().getTime()));
			ps.setInt(4, bono.getSesionesgastadas());
			ps.setInt(5, bono.getUserid());
			ps.setInt(6, bono.getIdbono());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
	 * Devuelve todos los bonos de la base de datos.
	 * @return	una lista con todos los bonos registrados en el sistema.
	 */
	public static ArrayList<BonoDTO> getAll() { 
		ArrayList<BonoDTO> bonos = new ArrayList<BonoDTO>();
		BonoDTO bono = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("Bono.getAll"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int idbono = rs.getInt("idbono");
			    Dificultad tipobono = Dificultad.valueOf(rs.getString("tipobono"));
				Date fechacomprabono = rs.getDate("fechacomprabono");
				Date fechacaducidad = rs.getDate("fechacaducidad");
				int sesionesgastadas = rs.getInt("sesionesgastadas");
				int userid = rs.getInt("userid");
				bono = new BonoDTO(idbono, userid, tipobono, fechacomprabono, fechacaducidad, sesionesgastadas);
				bonos.add(bono);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return bonos;
	}
	
	
	/**
	 * Devuelve los datos de un bono a partir de su identificador.
	 * @param idbono contiene el id del bono.
	 * @return	todos los datos del bono en concreto.
	 */
	public static BonoDTO queryById(int idbono) {
		BonoDTO bono = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("Bono.queryById"));
			ps.setInt(1, idbono);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	Dificultad tipobono = Dificultad.valueOf(rs.getString("tipobono"));
				Date fechacomprabono = rs.getDate("fechacomprabono");
				Date fechacaducidad = rs.getDate("fechacaducidad");
				int sesionesgastadas = rs.getInt("sesionesgastadas");
				int userid = rs.getInt("userid");
				bono = new BonoDTO(idbono, userid, tipobono, fechacomprabono, fechacaducidad, sesionesgastadas);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return bono;
	}
	
	public static BonoDTO queryByUseridTipo(int userid, Dificultad tipobono) {
		BonoDTO bono = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("Bono.queryByUseridTipo"));
			ps.setInt(1, userid);
			ps.setString(2,  tipobono.toString());
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	int idbono = rs.getInt("idbono");
				Date fechacomprabono = rs.getDate("fechacomprabono");
				Date fechacaducidad = rs.getDate("fechacaducidad");
				int sesionesgastadas = rs.getInt("sesionesgastadas");
				bono = new BonoDTO(idbono, userid, tipobono, fechacomprabono, fechacaducidad, sesionesgastadas);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return bono;
	}	
}
	
	