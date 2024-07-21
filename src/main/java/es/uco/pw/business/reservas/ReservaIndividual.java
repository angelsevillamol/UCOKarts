/**
 * Informacion de una reserva individual registrada en el sistema
 * @author Alvaro Cortes Gonzalez
 */

package es.uco.pw.business.reservas;

import java.util.Date;

import es.uco.pw.business.usuario.*;

/**
 * Representa una reserva individual registrada en el sistema
 * Hereda de la clase RFactory
 */

public class ReservaIndividual extends RFactory{
	
	/**
	 * Crea una nueva reserva individual vacia
	 */
	public ReservaIndividual() {}
	
	/**
	 * Funcion para realizar una reserva de tipo infantil
	 * @param reservaid el identificador de la reserva
	 * @param user el usuario que realiza la reserva
	 * @param pistaid el id de la pista que ha sido reservada
	 * @param idbono el id del bono en el que se ha hecho la reserva
	 * @param fecha_reserva la fecha en la que fue realizada la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 * @param duracion la cantidad de tiempo que durara la sesion
	 * @param numeromenores la cantidad de ninios que acudiran a la sesion
	 * @return la reserva infantil
	 */
	@Override
	public ReservaInfantilDTO Crear_Reserva_Infantil(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromenores) {
		
		ReservaInfantilDTO Reserva= new ReservaInfantilDTO();
		Reserva.setReservaid(reservaid);
		Reserva.setUserreservaid(user.getId());
		Reserva.setIdbono(idbono);
		Reserva.setDuracion(duracion);
		Reserva.setFecha_reserva(fecha_reserva);
		Reserva.setFecha_canjeo(fecha_canjeo);
		Reserva.setPistaid(pistaid);
		if(duracion==60) {
			Reserva.setPrecio(20);
		}
		else if(duracion==90) {
			Reserva.setPrecio(30);
		}
		else{
			Reserva.setPrecio(40);
		}
		Reserva.setPrecio(Reserva.getPrecio()*Reserva.getDescuento());
		Reserva.setNumeromenores(numeromenores);
		return Reserva;

	}
	
	/**
	 * Funcion para realizar una reserva de tipo adultos
	 * @param reservaid el identificador de la reserva.
	 * @param user el usuario que realiza la reserva
	 * @param pistaid el id de la pista que ha sido reservada
	 * @param idbono el id del bono en el que se ha hecho la reserva
	 * @param fecha_reserva la fecha en la que fue realizada la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 * @param duracion la cantidad de tiempo que durara la sesion
	 * @param numeromayores la cantidad de adultos que acudiran a la sesion
	 * @return la reserva adultos
	 */
	
	@Override
	public ReservaAdultosDTO Crear_Reserva_Adultos(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromayores) {
		
		ReservaAdultosDTO Reserva= new ReservaAdultosDTO();
		Reserva.setReservaid(reservaid);
		Reserva.setUserreservaid(user.getId());
		Reserva.setIdbono(idbono);
		Reserva.setDuracion(duracion);
		Reserva.setFecha_reserva(fecha_reserva);
		Reserva.setFecha_canjeo(fecha_canjeo);
		Reserva.setPistaid(pistaid);
		if(duracion==60) {
			Reserva.setPrecio(20);
		}
		else if(duracion==90) {
			Reserva.setPrecio(30);
		}
		else{
			Reserva.setPrecio(40);
		}
		Reserva.setPrecio(Reserva.getPrecio()*Reserva.getDescuento());
		Reserva.setNumeromayores(numeromayores);
		return Reserva;
	
	}
	
	/**
	 * Funcion para realizar una reserva de tipo familiar
	 * @param reservaid el identificador de la reserva
	 * @param user el usuario que realiza la reserva
	 * @param pistaid el id de la pista que ha sido reservada
	 * @param idbono el id del bono en el que se ha hecho la reserva
	 * @param fecha_reserva la fecha en la que fue realizada la reserva
	 * @param fecha_canjeo la fecha de canjeo de la reserva
	 * @param duracion la cantidad de tiempo que durara la sesion
	 * @param numeromenores la cantidad de ninios que acudiran a la sesion
	 * @param numeromayores la cantidad de adultos que acudiran a la sesion
	 * @return la reserva familiar
	 */
	@Override
	public ReservaFamiliarDTO Crear_Reserva_Familiar(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromenores, int numeromayores) {

		ReservaFamiliarDTO Reserva= new ReservaFamiliarDTO();
		Reserva.setReservaid(reservaid);
		Reserva.setUserreservaid(user.getId());
		Reserva.setIdbono(idbono);
		Reserva.setDuracion(duracion);
		Reserva.setFecha_reserva(fecha_reserva);
		Reserva.setFecha_canjeo(fecha_canjeo);
		Reserva.setPistaid(pistaid);
		if(duracion==60) {
			Reserva.setPrecio(20);
		}
		else if(duracion==90) {
			Reserva.setPrecio(30);
		}
		else{
			Reserva.setPrecio(40);
		}
		Reserva.setPrecio(Reserva.getPrecio()*Reserva.getDescuento());
		Reserva.setNumeromenores(numeromenores);
		Reserva.setNumeromayores(numeromayores);
		return Reserva;
	
	}

}
