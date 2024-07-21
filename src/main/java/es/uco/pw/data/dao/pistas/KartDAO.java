/**
 * Objeto de acceso de datos de karts.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.data.dao.pistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import es.uco.pw.business.pistas.Estado;
import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.data.dao.common.ConexionBD;

public class KartDAO {
	
	/**
     * Registra un kart en el sistema.
     * @param kart contiene la informacion del kart a registrar.
     * @return el estado de la operacion.
     */
	public static int save(KartDTO kart) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.save"));
			
			ps.setBoolean(1, kart.getTipo());
			ps.setString(2, kart.getEstado().toString());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Modifica la informacion de un kart en la base de datos. 
     * @param kart contiene la nueva informacion del kart. 
     * @return el estado de la operacion.
     */
	public static int update(KartDTO kart) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.update"));
			
			ps.setBoolean(1, kart.getTipo());
			ps.setString(2, kart.getEstado().toString());
			ps.setInt(3, kart.getId());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Devuelve todos los karts del circuito.
     * @return la lista con todos los kars registrados en el sistema.
     */
	public static ArrayList<KartDTO> getAll() {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		KartDTO kart = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.getAll"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				boolean tipo = rs.getBoolean("tipo");
		        Estado estado = Estado.valueOf(rs.getString("estado"));
		        kart = new KartDTO(id, tipo, estado);
			    karts.add(kart);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return karts;
	}
	
	/**
     * Asocia un kart a una pista, si cumple las condiciones.
     * Es necesario que el usuario realice previamente la comprobacion de puedeAsociar().
     * @param kart es el kart a asociar.
     * @param pista es la pista a la que se desea añadir el kart.
     * @return el estado de la operacion.
     */
	public static int asociarAPista(KartDTO kart, PistaDTO pista) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.asociarAPista"));
			
			ps.setInt(1,  pista.getId());
			ps.setInt(2,  kart.getId());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Devuelve un kart a partir de su identificador.
     * @param id	el numero de identificacion del kart.
     * @return 	el kart encontrado. 
     * 			Si no existe un kart con dicho id, entonces devuelve null.
     */
	public static KartDTO queryById(int id) {
		KartDTO kart = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.queryById"));
			ps.setInt(1,  id);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	boolean tipo = rs.getBoolean("tipo");
		        Estado estado = Estado.valueOf(rs.getString("estado"));
		        kart = new KartDTO(id, tipo, estado);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return kart;
	}
	
	/**
     * Comprueba si un kart esta asociado a una pista.
     * @param kart contiene la informacion del kart a comprobar.
     * @return true si el kart esta asociado, false en caso contrario.
     */
	public static boolean queryAsociado(KartDTO kart) {	
		boolean asociado = false;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.queryAsociado"));
			ps.setInt(1,  kart.getId());
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	asociado = (rs.getInt("id_pista") != 0);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return asociado;
	}
	
	/**
     * Devuelve todos los karts que se encuentren en un determinado estado.
     * @param estado	el estado de los karts a obtener.
     * @return la lista con todos los karts en dicho estado.
     */
	public static ArrayList<KartDTO> queryByEstado(Estado estado) {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		KartDTO kart = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.queryByEstado"));
			ps.setString(1,  estado.toString());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
		    	boolean tipo = rs.getBoolean("tipo");
			    kart = new KartDTO(id, tipo, estado);
			    karts.add(kart);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return karts;
	}
	
	/**
     * Devuelve todos los karts que no esten asociados a ninguna pista.
     * @return la lista con todos los karts no asociados a ninguna pista.
     */
	public static ArrayList<KartDTO> queryNoAsociados() {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		KartDTO kart = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.queryNoAsociados"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
		    	boolean tipo = rs.getBoolean("tipo");
			    Estado estado = Estado.valueOf(rs.getString("estado"));
			    kart = new KartDTO(id, tipo, estado);
			    karts.add(kart);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return karts;
	}
	
	/**
	 * Devuelve todos los karts asociados a una pista.
	 * @param pista la pista de la que se desea obtener los karts.
	 * @return la lista de karts asociados.
	 */
	public static ArrayList<KartDTO> queryByPista(PistaDTO pista) {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		KartDTO kart = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("karts.queryByPista"));
			ps.setInt(1,  pista.getId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			    int id = rs.getInt("id");
		    	boolean tipo = rs.getBoolean("tipo");
			    Estado estado = Estado.valueOf(rs.getString("estado"));
			    kart = new KartDTO(id, tipo, estado);
			    karts.add(kart);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return karts;
	}
}
