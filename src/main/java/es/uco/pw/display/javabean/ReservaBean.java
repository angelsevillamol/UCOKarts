package es.uco.pw.display.javabean;

import es.uco.pw.business.pistas.Dificultad;
import java.util.Date;

public class ReservaBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int idUsuario;
	private int idPista;
	private String nombrePista;
	private int idBono;
	private Date fechaReserva;
	private Date fechaCanjeo;
	private int duracion;
	private float precio;
	private float descuento;
	private int numAdultos;
	private int numMenores;
	private Dificultad diff;
	private boolean tipo;
	
	/**
	 * Constructor vacío.
	 */
	public ReservaBean() {
		setId(0);
		setIdUsuario(0);
		setIdPista(0);
		setNombrePista("");
		setIdBono(0);
		setFechaReserva(null);
		setFechaCanjeo(null);
		setDuracion(0);
		setPrecio(0);
		setDescuento(0);
		setNumAdultos(0);
		setNumMenores(0);
		setDificultad(Dificultad.INFANTIL);
		setTipo(false);
	}
	
	/**
	 * Constructor parametrizado.
	 * @param id			el numero de identificacion de la reserva.
	 * @param idUsuario		el numero de identificacion del usuario.
	 * @param idPista 		el id de la pista reservada
	 * @param nombrePista 	el nombre de la pista reservada
	 * @param idBono 		el id del bono en el que se ha reservado
	 * @param fechaReserva 	la fecha en la que se ha realizado la reserva
	 * @param fechaCanjeo 	la fecha de canjeo de la reserva
	 * @param duracion 		la duracion de la reserva
	 * @param precio 		el precio de la reserva
	 * @param descuento 	el descuento a la reserva
	 * @param numAdultos 	el numero de adultos para la reserva
	 * @param numMenores 	el numero de menores para la reserva
	 * @param diff 			la dificultad de la pista reservada
	 * @param tipo 			si la pista es individual o con bono
	 */
	public ReservaBean(int id, int idUsuario, int idPista, String nombrePista, int idBono, 
			Date fechaReserva, Date fechaCanjeo, int duracion, float precio, float descuento,
			int numAdultos, int numMenores, Dificultad diff, boolean tipo) {
		setId(id);
		setIdUsuario(idUsuario);
		setIdPista(idPista);
		setNombrePista(nombrePista);
		setIdBono(idBono);
		setFechaReserva(fechaReserva);
		setFechaCanjeo(fechaCanjeo);
		setDuracion(duracion);
		setPrecio(precio);
		setDescuento(descuento);
		setNumAdultos(numAdultos);
		setNumMenores(numMenores);
		setDificultad(diff);
		setTipo(tipo);
	}
	
	/**
     * Observador del identificador de la reserva.
     * @return el identificador de la reserva.
     */
	public int getId() { 
		return id; 
	}
	
	/**
     * Observador del identificador del usuario propietario.
     * @return identificador del usuario propietario.
     */
	public int getIdUsuario() { 
		return idUsuario; 
	}
	
	/**
	 * Observador del id de la pista que se ha reservado.
	 * @return el id de la pista que se ha reservado.
	 */
	public int getIdPista() { 
		return idPista; 
	}
	
	/**
	 * Observador del nombre de la pista que se ha reservado.
	 * @return el nombre de la pista que se ha reservado.
	 */
	public String getNombrePista() { 
		return nombrePista; 
	}
	
	/**
	 * Observador del id del bono en el que se ha reservado. Devolvera -1 en caso de ser una reserva individual
	 * @return el id del bono en el que se ha reservado. Devolvera -1 en caso de ser una reserva individual
	 */
	public int getIdBono() { 
		return idBono; 
	}
	
	/**
	 * Observador de la fecha en la que se hizo la reserva
	 * @return la fecha en la que se hizo la reserva
	 */
	public Date getfechaReserva() { 
		return fechaReserva; 
	}
	
	/**
	 * Observador de la fecha en la que se canjeara la reserva
	 * @return la fecha en la que se canjeara la reserva
	 */
	public Date getfechaCanjeo() { 
		return fechaCanjeo; 
	}
	
	/**
	 * Observador de la duracion de la sesion reservada
	 * @return la duracion dela sesion reservada
	 */
	public int getDuracion() { 
		return duracion; 
	}
	
	/**
	 * Observador del precio de la reserva
	 * @return precio de la reserva
	 */
	public float getPrecio() { 
		return precio; 
	}
	
	/**
	 * Observador del descuento aplicado al precio de la reserva
	 * @return descuento aplicado al precio de la reserva
	 */
	public float getDescuento() { 
		return descuento; 
	}
	
	/**
	 * Observador de la cantidad de adultos que canjearan la reserva
	 * @return la cantidad de adultos que canjearan la reserva
	 */
	public int getNumAdultos() { 
		return numAdultos; 
	}
	
	/**
	 * Observador de la cantidad de menores que canjearan la reserva
	 * @return la cantidad de menores que canjearan la reserva
	 */
	public int getNumMenores() { 
		return numMenores; 
	}
	
	/**
	 * Observador de la dificultad de la pista reservada.
	 * @return la dificultad de la pista reservada.
	 */
	public Dificultad getDificultad() { 
		return diff; 
	}
	
	/**
	 * Observador del tipo de reserva, si es individual o con bono.
	 * @return el tipo de la reserva.
	 */
	public boolean getTipo() { 
		return tipo; 
	}
	
	/**
     * Modificador del identificador de la reserva.
     * @param id el numero de identificacion de la reserva.
     * 	Debe comprobarse previamente que sea unico.
     */
	public void setId(int id) { 
		this.id = id; 
	}
	
	/**
     * Modificador del identificador del usuario.
     * @param idUsuario	el numero de identificacion del usuario.
     */
	public void setIdUsuario(int idUsuario) { 
		this.idUsuario = idUsuario; 
	}
	
	/**
	 * Modificador del id de la pista que se ha reservado
	 * @param idPista el id de la pista reservada
	 */
	public void setIdPista(int idPista) { 
		this.idPista = idPista;
	}
	
	/**
	 * Modificador del nombre de la pista reservada.
	 * @param nombrePista	el nombre de la pista reservada.
	 */
	public void setNombrePista(String nombrePista) { 
		this.nombrePista = nombrePista; 
	}
	
	/**
	 * Modificador del id del bono en el que se ha reservado
	 * @param idbono el id del bono en el que se ha reservado
	 */
	public void setIdBono(int idBono) { 
		this.idBono = idBono; 
	}
	
	/**
	 * Modificador de la fecha en la que fue realizada la reserva
	 * @param fechaReserva la fecha en la que se ha realizado la reserva
	 */
	public void setFechaReserva(Date fechaReserva) { 
		this.fechaReserva = fechaReserva; 
	}
	
	/**
	 * Modificador de la fecha en la que se canjeara la reserva
	 * @param fechaCanjeo la fecha de canjeo de la reserva
	 */
	public void setFechaCanjeo(Date fechaCanjeo) { 
		this.fechaCanjeo = fechaCanjeo; 
	}
	
	/**
	 * Modificador de la duracion de la sesion reservada
	 * @param duracion la duracion de la reserva
	 */
	public void setDuracion(int duracion) { 
		this.duracion = duracion; 
	}
	
	/**
	 * Modificador del precio de la resesrva
	 * @param precio el precio de la reserva
	 */
	public void setPrecio(float precio) { 
		this.precio = precio; 
	}
	
	/**
	 * Modificador del descuento aplicado al precio de la reserva
	 * @param descuento el descuento a la reserva
	 */
	public void setDescuento(float descuento) { 
		this.descuento = descuento; 
	}
	
	/**
	 * Modificador de la cantidad de adultos que canjearan la reserva
	 * @param numAdultos el numero de adultos para la reserva
	 */
	public void setNumAdultos(int numAdultos) { 
		this.numAdultos = numAdultos; 
	}
	
	/**
	 * Modificador de la cantidad de menores que canjearan la reserva
	 * @param numMenores el numero de adultos para la reserva
	 */
	public void setNumMenores(int numMenores) { 
		this.numMenores = numMenores; 
	}
	
	/**
	 * Modificador de la dificultad de la pista reservada.
	 * @param diff	la dificultad de la pista reservada.
	 */
	public void setDificultad(Dificultad diff) { 
		this.diff = diff; 
	}
	
	/**
	 * Modificador del tipo de reserva, si es individual o con bono.
	 * @param tipo 	el tipo de la reserva.
	 */
	public void setTipo(boolean tipo) { 
		this.tipo = tipo; 
	}
}
