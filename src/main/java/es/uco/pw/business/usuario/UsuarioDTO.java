/**
 * Informacion de un usuario registrado en el sistema.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.business.usuario;

import java.util.Date;

/**
 * Representa un usuario registrado en el sistema.
 */
public class UsuarioDTO {
	
	/**
	 * Numero de identificacion del usuario. 
	 * Debe ser unico para cada usuario.
	 */
    private int id;
    
    /**
     * Nombre del usuario.
     */
    private String nombre;
    
    /**
     * Apellidos del usuario.
     */
    private String apellidos;
    
    /**
     * Fecha de nacimiento del usuario.
     */
    private Date fechaNacimiento;
    
    /**
     * Fecha de inscripcion del usuario en el sistema.
     */
    private Date fechaInscripcion;
    
    /**
     * Correo electronico del usuario. 
     * Debe ser unico para cada usuario.
     */
    private String email;
    
    /**
     * Indica si el usuario es administrador.
     */
    private boolean admin;

    /**
     * Crea un nuevo usuario con los valores por defecto.
     */
    public UsuarioDTO() {
        setId(0);
        setNombre("");
        setApellidos("");
        setFechaNacimiento(new Date());
        setFechaInscripcion(new Date());
        setEmail("");
        setAdmin(false);
    }

    /**
     * Crea un nuevo usuario dado sus 
     * @param id				el nuevo numero de identificacion del usuario.
     * 							Debe comprobarse previamente que sea unico.				
     * @param nombre			la cadena con el nuevo nombre del usuario.
     * @param apellidos			la cadena con los nuevos apellidos del usuario.
     * @param fechaNacimiento	la nueva fecha de nacimiento del usuario.
     * @param fechaInscripcion	la nueva fecha de inscripcion del usuario.
     * @param email				la cadena con el nuevo correo electronico del usuario.
     * 							Debe comprobarse previamente que sea unico.		
     * @param admin				indicador de si el usuario es administrador.
     */
    public UsuarioDTO(int id, String nombre, String apellidos, Date fechaNacimiento, Date fechaInscripcion, String email, boolean admin) {
        setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setFechaNacimiento(fechaNacimiento);
        setFechaInscripcion(fechaInscripcion);
        setEmail(email);
        setAdmin(admin);
    }

    /**
     * Devuelve el identificador del usuario.
     * @return el numero de identificacion del usuario.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Devuelve el nombre del usuario.
     * @return la cadena con el nombre del usuario.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve los apellidos del usuario.
     * @return la cadena con los apellidos del usuario.
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     * @return la fecha de nacimiento del usuario.
     */
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Devuelve la fecha de inscripcion del usuario.
     * @return la fecha de inscripcion del usuario.
     */
    public Date getFechaInscripcion() {
        return this.fechaInscripcion;
    }

    /**
     * Devuelve el correo electronico del usuario.
     * @return la cadena con el correo electronico del usuario.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Devuelve el indicador del rol del usuario.
     * @return el indicador de si el usuario es administrador.
     */
    public boolean esAdmin() {
    	return this.admin;
    }
    
    /**
     * Asigna el identificador del usuario.
     * @param id	el nuevo numero de identificacion del usuario.
     * 				Debe comprobarse previamente que sea unico.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Asigna el nombre del usuario.
     * @param nombre	la cadena con el nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Asigna los apellidos del usuario.
     * @param apellidos	la cadena con los nuevos apellidos del usuario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Asigna la fecha de nacimiento del usuario.
     * @param fechaNacimiento	la nueva fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Asigna la fecha de inscripcion del usuario.
     * @param fechaInscripcion	la nueva fecha de inscripcion del usuario.
     */
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Asigna el correo electronico del usuario.
     * @param email	la cadena con el nuevo correo electronico del usuario.
     * 				Debe comprobarse previamente que sea unico.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Asigna la condicion de administrador 
     * @param admin	el indicador de si el usuario es administrador.
     */
    public void setAdmin(boolean admin) {
    	this.admin = admin;
    }
}
