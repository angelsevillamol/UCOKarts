/**
 * Informacion del usuario en la sesión.
 * @author Sevilla Molina, Angel
 */
package es.uco.pw.display.javabean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

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
     * Indica la edad en años del usuario.
     */
    private int edad;
    
    /**
     * Indica la antiguedad en meses del usuario.
     */
    private int antiguedad;
    
    /**
     * Indica el rol del usuario.
     */
    private String rol;

    /**
     * Crea un nuevo usuario con los valores por defecto.
     */
    public CustomerBean() {
        setId(0);
        setNombre("");
        setApellidos("");
        setFechaNacimiento(new Date());
        setFechaInscripcion(new Date());
        setEmail("");
        setEdad(0);
        setAntiguedad(0);
        setRol("Invitado");
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
     * @param edad				el numero de años del usuario.	
     * @param antiguedad		el numero de meses de antiguedad del usuario.
     * @param rol				la cadena con el rol del usuario.
     */
    public CustomerBean(int id, String nombre, String apellidos, Date fechaNacimiento, 
    		Date fechaInscripcion, String email, int edad, int antiguedad, String rol) {
        setId(id);
        setNombre(nombre);
        setApellidos(apellidos);
        setFechaNacimiento(fechaNacimiento);
        setFechaInscripcion(fechaInscripcion);
        setEmail(email);
        setEdad(edad);
        setAntiguedad(antiguedad);
        setRol(rol);
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
     * Devuelve la fecha de nacimiento del usuario en formato cadena.
     * @return la fecha de nacimiento del usuario.
     */
    public String getFechaNacimientoStr() {
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(getFechaNacimiento());
    }
    
    /**
     * Devuelve la fecha de inscripcion del usuario en formato cadena.
     * @return la fecha de inscripcion del usuario.
     */
    public String getFechaInscripcionStr() {
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(getFechaInscripcion());
    }

    /**
     * Devuelve el correo electronico del usuario.
     * @return la cadena con el correo electronico del usuario.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Devuelve la edad del usuario.
     * @return	el numero de anios del usuario.
     */
    public int getEdad() {
    	return this.edad;
    }
    
    /**
     * Devuelve la antiguedad del usuario.
     * @return el numero de meses que el usuario lleva registrados en el sistema.
     */
    public int getAntiguedad() {
    	return this.antiguedad;
    }
    
    /**
     * Devuelve el rol del usuario.
     * @return la cadena que indica el rol del usuario.
     */
    public String getRol() {
    	return this.rol;
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
     * Asigna la edad en años al usuario.
     * @param edad 	el numero de años del usuario.
     */
    public void setEdad(int edad) {
    	this.edad = edad;
    }
    
    /**
     * Asigna la antiguedad en meses del usuario.
     * @param antiguedad	el numero de meses de antiguedad del usuario.
     */
    public void setAntiguedad(int antiguedad) {
    	this.antiguedad = antiguedad;
    }
    
    /**
     * Asigna el rol del usuario.
     * @param rol la cadena que indica el rol del usuario.
     */
    public void setRol(String rol) {
    	this.rol = rol;
    }
}
