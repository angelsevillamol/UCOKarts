/**
 * Informacion de un kart disponible en los circuitos.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.business.pistas;

/**
 * Representa un kart disponible en los circuitos.
 */
public class KartDTO {
	
	/**
	 * Numero de identificacion del kart. 
	 * Debe ser unico para cada kart.
	 */
	private int id;
	
	/**
	 * Indica el tipo del kart.
	 * true si es adulto, false si es infantil.
	 */
	private boolean tipo;
	
	/**
	 * Estado en el que se encuentra el kart.
	 */
	private Estado estado;
	
	/**
	 * Crea un nuevo kart con los valores por defecto.
	 */
	public KartDTO() {
		setId(0);
		setTipo(false);
		setEstado(Estado.MANTENIMIENTO);
	}
	
	/**
	 * Crea un nuevo kart dada su informacion.
	 * @param id		el nuevo numero de identificacion del kart.
     * 					Debe comprobarse previamente que sea unico.	
	 * @param tipo		true si es adulto, false si es infantil.
	 * @param estado	indica el estado en el que se encuentra el kart.
	 */
	public KartDTO(int id, boolean tipo, Estado estado) {
		setId(id);
		setTipo(tipo);
		setEstado(estado);
	}
	
	/**
     * Devuelve el identificador del kart.
     * @return el numero de identificacion del kart.
     */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Devuelve el tipo del kart.
	 * @return true si es adulto, false si es infantil.
	 */
	public boolean getTipo() {
		return this.tipo;
	}
	
	/**
	 * Devuelve el estado del kart.
	 * @return el estado en el que se encuentra el kart.
	 */
	public Estado getEstado() {
		return this.estado;
	}
	
	/**
     * Asigna el identificador del usuario.
     * @param id	el nuevo numero de identificacion del kart.
     * 				Debe comprobarse previamente que sea unico.
     */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Asigna el tipo del kart.
	 * @param tipo true si es adulto, false si es infantil.
	 */
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Asigna el estado en el que se encuentra el kart.
	 * @param estado indica el estado en el que se encuentra el kart.
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}