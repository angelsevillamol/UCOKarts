/**
 * Informacion de un bono registrado en el sistema
 * @author Cortes Gonzalez, Alvaro
 * @author Carmona Balaguer, Gloria
 */

package es.uco.pw.business.reservas;


import java.util.Date;

import es.uco.pw.business.pistas.*;

/**
 * Representa un bono registrado en el sistema
 */

public class BonoDTO {
	/**
	 * Numero de identificacion del bono
	 * Debe ser unico para cada bono
	 */
	private int idbono;
	
	/**
	 * Numero de identificacion del usuario. 
	 * Debe ser unico para cada usuario.
	 */
	
	private int userid;
	
	/**
	 * Tipo de dificultad de la pista
	 */
	
	private Dificultad tipobono;
	
	/**
	 * Fecha en la que el bono fue adquirido
	 */
	
	private Date fechacomprabono;
	
	/**
	 * Fecha maxima para canjear el bono
	 */
	
	private Date fechacaducidad;
	
	/**
	 * Numero de sesiones consumidas
	 */
	private int sesionesgastadas;
	
	/**
	 * Crea un nuevo bono con los valores por defecto
	 */
	public BonoDTO() {
		this.idbono=-1;
		this.userid= 0;
		this.tipobono= Dificultad.FAMILIAR;
		this.fechacomprabono= new Date(0);
		this.fechacaducidad= new Date(0);
		this.sesionesgastadas= 0;
	}
	
	/**
	 * Crea un nuevo bono con los valores por defecto
	 * @param idbono el numero de identificacion del bono.
	 * @param userid	el numero de identificacion del usuario.
	 * @param tipobono	el tipo de dificultad del bono.
	 * @param fechacomprabono	la fecha en la que fue comprado el bono
	 * @param fechacaducidad	la fecha de caducidad del bono
	 * @param sesionesgastadas la cantidad de sesiones gastadas
	 */
	public BonoDTO(int idbono, int userid, Dificultad tipobono, Date fechacomprabono, Date fechacaducidad, int sesionesgastadas) {
		
		this.idbono=idbono;
		this.userid= userid;
		this.tipobono= tipobono;
		this.fechacomprabono= fechacomprabono;
		this.fechacaducidad= fechacaducidad;
		this.sesionesgastadas= sesionesgastadas;
	}
	
	/**
     * Devuelve el identificador del bono.
     * @return el numero de identificacion del bono.
     */
	public int getIdbono() {
		return idbono;
	}
	
    /**
     * Asigna el identificador del bono.
     * Debe comprobarse previamente que sea unico.
     * @param idbono el numero de identificacion del bono.
     */
	public void setIdbono(int idbono) {
		this.idbono = idbono;
	}
	
    /**
     * Devuelve el identificador del usuario.
     * @return el numero de identificacion del usuario.
     */

	public int getUserid() {
		return userid;
	}
	
    /**
     * Asigna el identificador del usuario.
     * @param userid	el numero de identificacion del usuario.
     */
	public void setUserid(int userid) {
		this.userid = userid;
	}

    /**
     * Devuelve la dificultad de la pista
     * @return el tipo de dificultad del bono
     */
	public Dificultad getTipobono() {
		return tipobono;
	}
	
    /**
     * Asigna la dificultad del bono
     * @param tipobono	el tipo de dificultad del bono.
     */
	public void setTipobono(Dificultad tipobono) {
		this.tipobono = tipobono;
	}

    /**
     * Devuelve la fecha en la que el bono fue adquirido
     * @return fecha de la compra del bono
     */
	public Date getFechacomprabono() {
		return fechacomprabono;
	}
	
    /**
     * Asigna la fecha en la que fue comprado el bono
     * @param fechacomprabono	la fecha en la que fue comprado el bono
     */
	public void setFechacomprabono(Date fechacomprabono) {
		this.fechacomprabono = fechacomprabono;
	}

    /**
     * Devuelve la fecha en la que el bono caducara
     * @return fecha de caducidad del bono
     */
	public Date getFechacaducidad() {
		return fechacaducidad;
	}

    /**
     * Asigna la fecha en la que el bono caducara
     * @param fechacaducidad	la fecha de caducidad del bono
     */
	public void setFechacaducidad(Date fechacaducidad) {
		this.fechacaducidad = fechacaducidad;
	}

    /**
     * Devuelve la cantidad de sesiones que han sido consumidas
     * @return cantidad de sesiones gastadas
     */
	public int getSesionesgastadas() {
		return sesionesgastadas;
	}
	
    /**
     * Asigna la cantidad de sesiones que han sido consumidas
     * @param sesionesgastadas la cantidad de sesiones gastadas
     */
	public void setSesionesgastadas(int sesionesgastadas) {
		this.sesionesgastadas = sesionesgastadas;
	}
	
	/**
	 * Imprime por pantalla la informacion del bono
	 * @return cadena con la informacion del bono
	 */
	@Override
	public String toString() {
		return "BonoDTO [idbono=" + idbono + ", userid=" + userid + ", tipobono=" + tipobono + ", fechacomprabono=" + fechacomprabono
				+ ", fechacaducidad=" + fechacaducidad + ", sesionesgastadas=" + sesionesgastadas + "]";
	}
	
}
