/**
 * Informacion de las pistas que componen el circuito.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.business.pistas;

import java.util.ArrayList;

/**
 * Representa una pista que componen los circuitos.
 */
public class PistaDTO {
	
	/**
	 * Numero de identificacion de la pista. 
	 * Debe ser unico para cada pista.
	 */
	private int id;

	/**
	 * Nombre de la pista.
	 * Debe ser unico para cada pista.
	 */
	private String nombre;
	
	/**
	 * Estado en el que se encuentra la pista.
	 * true si se permiten reservas, false si no se permite.
	 */
	private boolean disponibilidad;
	
	/**
	 * Dificultad de la pista.
	 */
	private Dificultad diff;
	
	/**
	 * Numero maximo de karts de la pista.
	 */
	private int maxKarts;
	
	/**
	 * Descripcion de la pista.
	 */
	private String descripcion;
	
	/**
	 * Lista de Karts
	 */
	private ArrayList<KartDTO> karts;
	
	/**
	 * Crea una nueva pista con los valores por defecto.
	 */
	public PistaDTO() {
		setId(0);
		setNombre("");
		setDisponibilidad(false);
		setDificultad(Dificultad.INFANTIL);
		setMaxKarts(0);
		setDescripcion("");
		this.karts = new ArrayList<KartDTO>();
	}
	
	/**
	 * Crea una nueva pista dada su informacion.
	 * @param id				el nuevo numero de identificacion de la pista.
     * 							Debe comprobarse previamente que sea unico.	
	 * @param nombre			la cadena con el nombre de la pista.
	 * @param disponibilidad	true si permite reservas, false en caso contrario.
	 * @param diff				indica la dificultad de la pista.
	 * @param maxKarts			el numero maximo de karts de la pista. 
	 * @param descripcion		la descripcion de la pista.
	 */
	public PistaDTO(int id, String nombre, boolean disponibilidad, Dificultad diff, int maxKarts, String descripcion) {
		setId(id);
		setNombre(nombre);
		setDisponibilidad(disponibilidad);
		setDificultad(diff);
		setMaxKarts(maxKarts);
		setDescripcion(descripcion);
		this.karts = new ArrayList<KartDTO>();
	}
	
	/**
     * Devuelve el identificador de la pista.
     * @return el numero de identificacion de la pista.
     */
    public int getId() {
        return this.id;
    }
	
	/**
     * Devuelve el nombre de la pista..
     * @return el nombre de la pista.
     */
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * Devuelve el disponibilidad de disponibilidad de la pista.
	 * @return true si permite reservas, false en caso contrario.
	 */
	public boolean getDisponibilidad() {
		return this.disponibilidad;
	}
	
	/**
	 * Devuelve la dificultad de la pista.
	 * @return dificultad de la pista.
	 */
	public Dificultad getDificultad() {
		return this.diff;
	}
	
	/**
	 * Devuelve el numero maximo de karts de la pista.
	 * @return el numero maximo de karts de la pista.
	 */
	public int getMaxKarts() {
		return this.maxKarts;
	}
	
	/**
     * Devuelve la descripcion de la pista.
     * @return la descripcion de la pista.
     */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * Devuelve los karts asociados a la pista.
	 * @return la lista de karts asociados a la pista.
	 */
	public ArrayList<KartDTO> getKarts() {
		return this.karts;
	}
	
	/**
	 * Devuelve el numero de karts asociados a la pista.
	 * @return el numero de karts asocialdos a la pista.
	 */
	public int getNumKartsAsociados() {
		return this.karts.size();
	}
	
	/**
	 * Devuelve el numero de karts infantiles que se encuentren disponibles.
	 * @return el numero de karts infantiles disponibles.
	 */
	public int getNumKartsInfantilesDisponibles() {
		int cont = 0;
		
		if (getDificultad() == Dificultad.ADULTOS) {
			return 0;
		}
		
		for(KartDTO k : this.karts){
			if(k.getEstado() == Estado.DISPONIBLE && k.getTipo() == false) {
				cont++;	
			}
		}
		
		return cont;
	}
	
	/**
	 * Devuelve el numero de karts adultos que se encuentren disponibles.
	 * @return el numero de karts adultos disponibles.
	 */
	public int getNumKartsAdultosDisponibles() {
		int cont = 0;
		
		if (getDificultad() == Dificultad.INFANTIL) {
			return 0;
		}
		
		for(KartDTO k : this.karts){
			if(k.getEstado() == Estado.DISPONIBLE && k.getTipo() == true) {
				cont++;	
			}
		}
		
		return cont;
	}
	
	/**
	 * Devuelve el numero de karts que se encuentren disponibles.
	 * @return el numero de karts disponibles.
	 */
	public int getNumKartsDisponibles() {
		int cont = 0;
		
		for(KartDTO k : this.karts){
			if(k.getEstado() == Estado.DISPONIBLE) {
				cont++;	
			}
		}
		
		return cont;
	}
	
	/**
     * Asigna el identificador de la pista.
     * @param id	el nuevo numero de identificacion de la pista.
     * 				Debe comprobarse previamente que sea unico.
     */
    public void setId(int id) {
        this.id = id;
    }
	
	/**
	 * Asigna el nombre de la pista.
	 * @param nombre	la cadena con el nombre de la pista.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Asigna el disponibilidad de disponibilidad de la pista.
	 * @param disponibilidad	true si permite reservas, false en caso contrario.
	 */
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	/**
	 * Asigna la dificultad de la pista.
	 * @param diff	indica la dificultad de la pista.
	 */
	public void setDificultad(Dificultad diff) {
		this.diff = diff;
	}
	
	/**
	 * Asigna el numero maximo de karts de la pista.
	 * @param maxKarts	el numero maximo de karts de la pista. 
	 */
	public void setMaxKarts(int maxKarts) {
		this.maxKarts = maxKarts;
	}
	
	/**
	 * Asigna la descripcion de la pista.
	 * @param nombre	la cadena con la descripcion de la pista.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Asigna los karts asociados a la pista.
	 * @param karts	la lista de karts asociados a la pista.
	 */
	public void setKarts(ArrayList<KartDTO> karts) {
		this.karts = karts;
	}
}

	
	
	
