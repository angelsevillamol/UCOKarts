/**
 * Informacion de una reserva familiar registrada en el sistema
 * @author Alvaro Cortes Gonzalez
 */
package es.uco.pw.business.reservas;

import java.util.Date;

public class ReservaFamiliarDTO extends RProduct {

	/**
	 * Cantidad de ninios que canjearan la reserva
	 */
	
	private int numeromenores;
	
	/**
	 * Cantidad de adultos que canjearan la reserva
	 */
	
	private int numeromayores;
	
	/**
	 * Constructor vacio.
	 * Crea una nueva reserva infantil vacia
	 */
	
	public ReservaFamiliarDTO() {
		
		this.reservaid=-1;
		this.userreservaid= 0;
		this.pistaid= 0;
		this.idbono=-1;
		this.fecha_reserva= new Date(0);
		this.fecha_canjeo= new Date(0);
		this.duracion= 0;
		this.precio= (float) 0.0;
		this.descuento= (float) 1.0;
		this.numeromenores= 0;
		this.numeromayores= 0;
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param reservaid	el numero de identificacion de la reserva.
	 * @param userreservaid	el numero de identificacion del usuario.
	 * @param pistaid el id de la pista reservada
	 * @param idbono el id del bono en el que se ha reservado
	 * @param fecha_reserva la fecha en la que se ha realizado la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 * @param duracion la duracion de la reserva
	 * @param precio el precio de la reserva
	 * @param descuento el descuento a la reserva
	 * @param numeromenores el numero de menores para la reserva
	 * @param numeromayores el numero de mayores para la reserva
	 */
	public ReservaFamiliarDTO(int reservaid, int userreservaid, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, float precio, float descuento, int numeromenores, int numeromayores) {
		
		this.reservaid=reservaid;
		this.userreservaid= userreservaid;
		this.pistaid= pistaid;
		this.idbono=idbono;
		this.fecha_reserva= fecha_reserva;
		this.fecha_canjeo= fecha_canjeo;
		this.duracion= duracion;	
		this.precio= precio;
		this.descuento= descuento;
		this.numeromenores= numeromenores;
		this.numeromayores=numeromayores;
		
	}
	
	/**
     * Observador del identificador de la reserva.
     * @return el identificador de la reserva.
     */
	
	public int getReservaid() {
		
		return reservaid;
		
	}
	
	/**
     * Modificador del identificador de la reserva.
     * @param reservaid	el numero de identificacion de la reserva.
     * 	Debe comprobarse previamente que sea unico.
     */
	
	public void setReservaid(int reservaid) {
		
		this.reservaid=reservaid;
		
	}
	
	/**
     * Observador del identificador del usuario propietario.
     * @return identificador del usuario propietario.
     */

	public int getUserreservaid() {
		
		return userreservaid;
		
	}

    /**
     * Modificador del identificador del usuario.
     * @param userreservaid	el numero de identificacion del usuario.
     *Debe comprobarse previamente que sea unico.
     */

	public void setUserreservaid(int userreservaid) {
		
		this.userreservaid=userreservaid;
		
	}
	
	/**
	 * Observador del id de la pista que se ha reservado.
	 * @return el id de la pista que se ha reservado.
	 */

	public int getPistaid() {
		
		return pistaid;
		
	}

	/**
	 * Modificador del id de la pista que se ha reservado
	 * @param pistaid el id de la pista reservada
	 * Debe comprobarse previamente que el identificador sea unico
	 */

	public void setPistaid(int pistaid) {
		
		this.pistaid=pistaid;
		
	}
	
	/**
	 * Observador del id del bono en el que se ha reservado. Devolvera -1 en caso de ser una reserva individual
	 * @return el id del bono en el que se ha reservado. Devolvera -1 en caso de ser una reserva individual
	 */

	public int getIdbono() {
		
		return idbono;
		
	}

	/**
	 * Modificador del id del bono en el que se ha reservado
	 * @param idbono el id del bono en el que se ha reservado
	 * Debe comprobarse previamente que el identificador sea unico
	 */

	public void setIdbono(int idbono) {
		
		this.idbono=idbono;
		
	}

	/**
	 * Observador de la fecha en la que se hizo la reserva
	 * @return la fecha en la que se hizo la reserva
	 */

	public Date getFecha_reserva() {
		
		return fecha_reserva;
		
	}

	/**
	 * Modificador de la fecha en la que fue realizada la reserva
	 * @param fecha_reserva la fecha en la que se ha realizado la reserva
	 */

	public void setFecha_reserva(Date fecha_reserva) {
		
		this.fecha_reserva=fecha_reserva;
		
	}
	
	/**
	 * Observador de la fecha en la que se canjeara la reserva
	 * @return la fecha en la que se canjeara la reserva
	 */
	
	public Date getFecha_canjeo() {
		
		return fecha_canjeo;
		
	}

	/**
	 * Modificador de la fecha en la que se canjeara la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 */

	public void setFecha_canjeo(Date fecha_canjeo) {
		
		this.fecha_canjeo=fecha_canjeo;
		
	}

	/**
	 * Observador de la duracion de la sesion reservada
	 * @return la duracion dela sesion reservada
	 */

	public int getDuracion() {
		
		return duracion;
		
	}

	/**
	 * Modificador de la duracion de la sesion reservada
	 * @param duracion la duracion de la reserva
	 */

	public void setDuracion(int duracion) {
		
		this.duracion=duracion;
		
	}

	/**
	 * Observador del precio de la reserva
	 * @return precio de la reserva
	 */

	public float getPrecio() {
		
		return precio;
		
	}

	/**
	 * Modificador del precio de la resesrva
	 * @param precio el precio de la reserva
	 */

	public void setPrecio(float precio) {
		
		this.precio=precio;
		
	}
	/**
	 * Observador del descuento aplicado al precio de la reserva
	 * @return descuento aplicado al precio de la reserva
	 */

	public float getDescuento() {
		
		return descuento;
		
	}

	/**
	 * Modificador del descuento aplicado al precio de la reserva
	 * @param descuento el descuento a la reserva
	 */

	public void setDescuento(float descuento) {
		
		this.descuento=descuento;
		
	}
	
	/**
	 * Observador de la cantidad de ninios que canjearan la reserva
	 * @return la cantidad de ninios que canjearan la reserva
	 */

	public int getNumeromenores() {
		
		return numeromenores;
		
	}

	/**
	 * Modificador de la cantidad de ninios que canjearan la reserva
	 * @param numeromenores el numero de ninios para la reserva
	 */

	public void setNumeromenores(int numeromenores) {
		
		this.numeromenores=numeromenores;
		
	}
	
	/**
	 * Observador de la cantidad de adultos que canjearan la reserva
	 * @return la cantidad de adultos que canjearan la reserva
	 */

	public int getNumeromayores() {
		
		return numeromayores;
		
	}

	/**
	 * Modificador de la cantidad de adultos que canjearan la reserva
	 * @param numeromayores el numero de adultos para la reserva
	 */

	public void setNumeromayores(int numeromayores) {
		
		this.numeromayores=numeromayores;
		
	}
	
	/**
	 * Metodo toString de la clase ReservaFamiliarDTO
	 */

	@Override
	public String toString() {
		return "ReservaFamiliarDTO [reservaid=" + reservaid + ", userreservaid="
				+ userreservaid + ", pistaid=" + pistaid + ", idbono=" + idbono + ", fecha_reserva=" + fecha_reserva
				+ ", fecha_canjeo=" + fecha_canjeo + ", duracion=" + duracion + ", precio=" + precio + ", descuento="
				+ descuento + ", numeromenores=" + numeromenores + ", numeromayores=" + numeromayores + "]";
	}
	
}