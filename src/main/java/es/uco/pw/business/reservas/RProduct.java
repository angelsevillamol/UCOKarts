/**
 * Patron de disenio producto
 * @author Alvaro Cortes Gonzalez
 */
package es.uco.pw.business.reservas;

import java.util.Date;

/**
 * Prototipo para el patron de disenio producto.
 * Se usara para Modificador der las variables comunes de los productos ReservaInfantilDTO, ReservaAdultosDTO y ReservaFamiliarDTO.
 * Incluye las variables comunes.
 */
public abstract class RProduct {
	
	/**
	 * Numero de identificacion de la reserva.
	 * Debe ser unico para cada reserva
	 */
	
	protected int reservaid;
	
	/**
	 * Numero de identificacion del usuario. 
	 * Debe ser unico para cada usuario.
	 */

	protected int userreservaid;
	
	/**
	 * Identificador de la pista reservada
	 */
	
	protected int pistaid;
	
	/**
	 * Identificador del bono al que pertenece.
	 * Sera Modificador dedo a -1 siempre que la reserva sea individual
	 */
	protected int idbono;
	/**
	 * Fecha en la que fue realizada la reserva
	 */
	
	protected Date fecha_reserva;
	
	/**
	 * Fecha en la que se consumira la reserva
	 */
	
	protected Date fecha_canjeo;
	
	/**
	 * Duracion de la sesion
	 */
	
	protected int duracion;
	
	/**
	 * Precio de la reserva
	 */
	
	protected float precio;
	
	/**
	 * Posible descuento aplicado por antiguedad
	 */
	
	protected float descuento;
	
	/**
     * Observador del identificador de la reserva.
     * @return el identificador de la reserva.
     */
	public abstract int getReservaid();
	
	/**
     * Modificador del identificador de la reserva.
     * @param reservaid	el numero de identificacion de la reserva.
     * 				Debe comprobarse previamente que sea unico.
     */
	
	public abstract void setReservaid(int reservaid);
	
	/**
     * Observador del identificador del usuario.
     */

	public abstract int getUserreservaid();

    /**
     * Modificador del identificador del usuario.
     * @param userreservaid	el numero de identificacion del usuario.
     * 				Debe comprobarse previamente que sea unico.
     */

	public abstract void setUserreservaid(int userreservaid);
	
	/**
	 * Observador del id de la pista que se ha reservado
	 */

	public abstract int getPistaid();

	/**
	 * Modificador del id de la pista que se ha reservado
	 * @param pistaid el id de la pista reservada
	 */

	public abstract void setPistaid(int pistaid);
	/**
	 * Observador del id del bono en el que se ha reservado. Devolvera -1 en caso de ser una reserva individual
	 */

	public abstract int getIdbono();

	/**
	 * Modificador del id del bono en el que se ha reservado
	 * @param idbono el id del bono en el que se ha reservado
	 */

	public abstract void setIdbono(int idbono);

	/**
	 * Observador de la fecha en la que se hizo la reserva
	 */

	public abstract Date getFecha_reserva();

	/**
	 * Modificador de la fecha en la que fue realizada la reserva
	 * @param fecha_reserva la fecha en la que se ha realizado la reserva
	 */

	public abstract void setFecha_reserva(Date fecha_reserva);
	
	/**
	 * Observador de la fecha en la que se canjeara la reserva
	 */
	
	public abstract Date getFecha_canjeo();

	/**
	 * Modificador de la fecha en la que se canjeara la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 */

	public abstract void setFecha_canjeo(Date fecha_canjeo);

	/**
	 * Observador de la duracion de la sesion reservada
	 */

	public abstract int getDuracion();

	/**
	 * Modificador de la duracion de la sesion reservada
	 * @param duracion la duracion de la reserva
	 */

	public abstract void setDuracion(int duracion);

	/**
	 * Observador del precio de la reserva
	 */

	public abstract float getPrecio();

	/**
	 * Modificador del precio de la resesrva
	 * @param precio el precio de la reserva
	 */

	public abstract void setPrecio(float precio);
	/**
	 * Observador del descuento aplicado al precio de la reserva
	 */

	public abstract float getDescuento();

	/**
	 * Modificador del descuento aplicado al precio de la reserva
	 * @param descuento el descuento a la reserva
	 */

	public abstract void setDescuento(float descuento);
}
