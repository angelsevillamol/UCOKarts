function esPasswordValida(val) {
    const hasNumber = /.*\d.*/;
    const hasLetter = /.*[A-Za-z].*/;
    const strongRegex = /^[A-Za-z0-9._\-]{8,}$/;
    return hasNumber.test(val) && hasLetter.test(val) && strongRegex.test(val);
}

function comprobarPassword() {
    const password = document.getElementById("password");
    const password2 = document.getElementById("password2");

    if (esPasswordValida(password.value)) {
        document.getElementById("invalid-pass-message").style.visibility = "hidden";
        document.getElementById("invalid-pass-message").style.height = "0";
    } else {
        document.getElementById("invalid-pass-message").style.visibility = "visible";
        document.getElementById("invalid-pass-message").style.height = "auto";
    }  
    
    if (password.value === password2.value) {
        document.getElementById("pass-match-message").style.visibility = "hidden";
        document.getElementById("pass-match-message").style.height = "0";
        if (esPasswordValida(password.value)) {
            return true;
        }
    } else {
        document.getElementById("pass-match-message").style.visibility = "visible";
        document.getElementById("pass-match-message").style.height = "auto";
    }
    
    return false;
}

function comprobarFecha() {
	const fechaNacimiento = document.getElementById("fechaNacimiento");
	const regFecha = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
	
	if (regFecha.test(fechaNacimiento.value)) {
		document.getElementById("invalid-date-message").style.visibility = "hidden";
        document.getElementById("invalid-date-message").style.height = "0";
        return true;
	} else {
		document.getElementById("invalid-date-message").style.visibility = "visible";
        document.getElementById("invalid-date-message").style.height = "auto";
	}
	    
    return false;
}

function comprobarEmail() {
	const email = document.getElementById("email");
	const regEmail = /[a-zA-Z0-9\.\_]+\@[a-zA-Z0-9\.\_]+(\.\w+){1,}/;
	
	if (regEmail.test(email.value)) {
		document.getElementById("invalid-email-message").style.visibility = "hidden";
        document.getElementById("invalid-email-message").style.height = "0";
        return true;
	} else {
		document.getElementById("invalid-email-message").style.visibility = "visible";
        document.getElementById("invalid-email-message").style.height = "auto";
	}
	    
    return false;
}

function comprobarNombre() {
	const nombre = document.getElementById("nombre");
	const regNombre = /^[a-zA-Z ]{2,30}$/;
	
	if (regNombre.test(nombre.value)) {
		document.getElementById("invalid-nombre-message").style.visibility = "hidden";
        document.getElementById("invalid-nombre-message").style.height = "0";
        return true;
	} else {
		document.getElementById("invalid-nombre-message").style.visibility = "visible";
        document.getElementById("invalid-nombre-message").style.height = "auto";
	}
	    
    return false;
}

function comprobarApellidos() {
	const apellidos = document.getElementById("apellidos");
	const regApellidos = /^[a-zA-Z ]{2,30}$/;
	
	if (regApellidos.test(apellidos.value)) {
		document.getElementById("invalid-apellidos-message").style.visibility = "hidden";
        document.getElementById("invalid-apellidos-message").style.height = "0";
        return true;
	} else {
		document.getElementById("invalid-apellidos-message").style.visibility = "visible";
        document.getElementById("invalid-apellidos-message").style.height = "auto";
	}
	    
    return false;
}

function validarDatos() {
	document.getElementById("submit-button").disabled = true; 
	
	const valNombre = comprobarNombre();
	const valApellidos = comprobarApellidos();
	const valFecha = comprobarFecha();
	const valEmail = comprobarEmail();
	const valPassword = comprobarPassword();
	
	if (valNombre && valApellidos && valFecha && valEmail && valPassword) {
		document.getElementById("submit-button").disabled = false;
	}
}

function validarEditar() {
	document.getElementById("submit-button").disabled = true; 
	
	const valNombre = comprobarNombre();
	const valApellidos = comprobarApellidos();
	const valFecha = comprobarFecha();
	const valPassword = comprobarPassword();
	
	if (valNombre && valApellidos && valFecha && valPassword) {
		document.getElementById("submit-button").disabled = false;
	}
}
