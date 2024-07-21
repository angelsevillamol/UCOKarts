/**
 * Objeto de acceso de datos de pistas.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.data.dao.pistas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import es.uco.pw.business.pistas.Dificultad;
import es.uco.pw.business.pistas.KartDTO;
import es.uco.pw.business.pistas.PistaDTO;
import es.uco.pw.data.dao.common.ConexionBD;

public class PistaDAO {
	
	/**
     * Registra una pista en el sistema.
     * @param pista contiene la informacion de la pista a registrar.
     * @return el estado de la operacion.
     */
	public static int save(PistaDTO pista) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.save"));
			
			String strDisponibilidad = pista.getDisponibilidad()? "Si" : "No";
			ps.setString(1, pista.getNombre());
			ps.setString(2, strDisponibilidad);
			ps.setString(3, pista.getDificultad().toString());
			ps.setInt(4, pista.getMaxKarts());
			ps.setString(5, pista.getDescripcion());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Modifica la informacion de una pista en la base de datos. 
     * @param pista contiene la nueva informacion de la pista. 
     * @return el estado de la operacion.
     */
	public static int update(PistaDTO pista) {
		int status = 0;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.update"));
			
			ps.setString(1, pista.getNombre());
			String strDisponibilidad = pista.getDisponibilidad()? "Si" : "No";
			ps.setString(2, strDisponibilidad);
			ps.setString(3, pista.getDificultad().toString());
			ps.setInt(4, pista.getMaxKarts());
			ps.setString(5, pista.getDescripcion());
			ps.setInt(6, pista.getId());
			
			status = ps.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	/**
     * Busca una pista a partir de su identificador.
     * @param id 	el numero de identificacion de la pista.
     * @return 	la pista encontrada.
     * 			Si no existe una pista con dicho identificador, entonces devuelve null.
     */
	public static PistaDTO queryById(int id) {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		PistaDTO pista = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.queryById"));
			ps.setInt(1,  id);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	String nombre = rs.getString("nombre");
		    	boolean disponibilidad = (rs.getString("disponibilidad").compareTo("Si") == 0);
		        Dificultad diff = Dificultad.valueOf(rs.getString("dificultad"));
		        int maxKarts = rs.getInt("max_karts");
		        String descripcion = rs.getString("descripcion");
		        pista = new PistaDTO(id, nombre, disponibilidad, diff, maxKarts, descripcion);
		        karts = KartDAO.queryByPista(pista);
		        pista.setKarts(karts);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return pista;
	}
	
	/**
     * Devuelve una pista a partir de su nombre.
     * @param nombre	el nombre de la pista.
     * @return 	la pista encontrada. 
     * 			Si no existe una pista con dicho nombre, entonces devuelve null.
     */
	public static PistaDTO queryByNombre(String nombre) {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		PistaDTO pista = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.queryByNombre"));
			ps.setString(1,  nombre);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	int id = rs.getInt("id");
		    	boolean disponibilidad = (rs.getString("disponibilidad").compareTo("Si") == 0);
		        Dificultad diff = Dificultad.valueOf(rs.getString("dificultad"));
		        int maxKarts = rs.getInt("max_karts");
		        String descripcion = rs.getString("descripcion");
		        pista = new PistaDTO(id, nombre, disponibilidad, diff, maxKarts, descripcion);
		        karts = KartDAO.queryByPista(pista);
		        pista.setKarts(karts);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return pista;
	}
	
	/**
     * Devuelve una pista asociada a un determinado kart.
     * @param kart	el kart de la pista.
     * @return 	la pista encontrada. 
     * 			Si no existe una pista con dicho kart, entonces devuelve null.
     */
	public static PistaDTO queryByKart(KartDTO kart) {
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		PistaDTO pista = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.queryByKart"));
			ps.setInt(1,  kart.getId());
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	int id = rs.getInt("Pista.id");
		    	String nombre = rs.getString("nombre");
		    	boolean disponibilidad = (rs.getString("disponibilidad").compareTo("Si") == 0);
		        Dificultad diff = Dificultad.valueOf(rs.getString("dificultad"));
		        int maxKarts = rs.getInt("max_karts");
		        String descripcion = rs.getString("descripcion");
		        pista = new PistaDTO(id, nombre, disponibilidad, diff, maxKarts, descripcion);
		        karts = KartDAO.queryByPista(pista);
		        pista.setKarts(karts);
		    }
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return pista;
	}
	
	/**
     * Devuelve todas las pistas del circuito.
     * @return la lista con todas las pistas registradas en el sistema.
     */
	public static ArrayList<PistaDTO> getAll() {
		ArrayList<PistaDTO> pistas = new ArrayList<PistaDTO>();
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		PistaDTO pista = null;
		
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.getAll"));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
			    String nombre = rs.getString("nombre");
			    boolean disponibilidad = (rs.getString("disponibilidad").compareTo("Si") == 0);
			    Dificultad diff = Dificultad.valueOf(rs.getString("dificultad"));
			    int maxKarts = rs.getInt("max_karts");
			    String descripcion = rs.getString("descripcion");
			    pista = new PistaDTO(id, nombre, disponibilidad, diff, maxKarts, descripcion);
			    karts = KartDAO.queryByPista(pista);
			    pista.setKarts(karts);
			    pistas.add(pista);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return pistas;
	}
	
	/**
     * Devuelve las pistas que se encuentren disponibles.
     * @param disponibilidad	true si permite reservas, false en caso contrario.
     * @return la lista con todas las pistas disponibles.
     */
	public static ArrayList<PistaDTO> queryByDisponibilidad(boolean disponibilidad) {
		ArrayList<PistaDTO> pistas = new ArrayList<PistaDTO>();
		ArrayList<KartDTO> karts = new ArrayList<KartDTO>();
		PistaDTO pista = null;
			
		try {
			ConexionBD cbd = ConexionBD.getInstance();
			Connection con = cbd.getConnection();
			PreparedStatement ps = con.prepareStatement(cbd.getStmt("pistas.queryByDisponibilidad"));
			String strDisponibilidad = disponibilidad? "Si" : "No";
			ps.setString(1,  strDisponibilidad);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
			    String nombre = rs.getString("nombre");
			    Dificultad diff = Dificultad.valueOf(rs.getString("dificultad"));
			    int maxKarts = rs.getInt("max_karts");
			    String descripcion = rs.getString("descripcion");
			    pista = new PistaDTO(id, nombre, disponibilidad, diff, maxKarts, descripcion);
			    karts = KartDAO.queryByPista(pista);
			    pista.setKarts(karts);
			    pistas.add(pista);
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		
		return pistas;
	}
}
