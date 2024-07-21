/**
 * Patron de disenio factoria
 * @author Alvaro Cortes Gonzalez
 */
package es.uco.pw.business.reservas;

import java.util.Date;

import es.uco.pw.business.usuario.*;

/**
 * Prototipo para el patron de disenio de factoria.
 * Se usara para crear los productos ReservaInfantilDTO, ReservaAdultosDTO y ReservaFamiliarDTO desde las factorias ReservaIndividual y ReservaBono.
 * Incluye los tres tipos de reserva que se pueden realizar tanto en la reserva de un bono como en la reserva individual.
 */
public abstract class RFactory {
	
	/**
	 * Declaracion de la creacion de un producto ReservaInfantilDTO
	 */
	public abstract ReservaInfantilDTO Crear_Reserva_Infantil(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromenores);
	
	/**
	 * Declaracion de la creacion de un producto ReservaAdultosDTO
	 */
	public abstract ReservaAdultosDTO Crear_Reserva_Adultos(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromayores);
	
	/**
	 * Declaracion de la creacion de un producto ReservaFamiliarDTO
	 */
	public abstract ReservaFamiliarDTO Crear_Reserva_Familiar(int reservaid, UsuarioDTO user, int pistaid, int idbono, Date fecha_reserva, Date fecha_canjeo, int duracion, int numeromenores, int numeromayores);

}
