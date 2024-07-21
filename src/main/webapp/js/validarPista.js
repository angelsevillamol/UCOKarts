function comprobarNombrePista() {
	const nombre = document.getElementById("nombre");
	const regNombre = /^[a-z-A-Z ]{2,50}$/;
	
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

function comprobarTipo() {
	return true;
}

function comprobarMaxKarts() {
	const maxKarts = document.getElementById("maxKarts");
	const regNumber = /^\+?(0|[1-9]\d*)$/;
	
	if (regNumber.test(maxKarts.value)) {
		const num = maxKarts.value;
		if (num > 0) {
			document.getElementById("invalid-maxkarts-message").style.visibility = "hidden";
        	document.getElementById("invalid-maxkarts-message").style.height = "0";
        	return true;
		}
		else {
			document.getElementById("invalid-maxkarts-message").style.visibility = "visible";
       		document.getElementById("invalid-maxkarts-message").style.height = "auto";
		}
	} else {
		document.getElementById("invalid-maxkarts-message").style.visibility = "visible";
        document.getElementById("invalid-maxkarts-message").style.height = "auto";
	}
	    
    return false;
}

function comprobarDescripcion() {
	const descripcion = document.getElementById("descripcion");
	const regDescripcion = /^[a-z-A-Z -_0-9]{25,1000}$/;
	
	if (regDescripcion.test(descripcion.value)) {
		document.getElementById("invalid-descripcion-message").style.visibility = "hidden";
        document.getElementById("invalid-descripcion-message").style.height = "0";
        return true;
	} else {
		document.getElementById("invalid-descripcion-message").style.visibility = "visible";
        document.getElementById("invalid-descripcion-message").style.height = "auto";
	}
	    
    return false;
}

function alertaPistaAsociada(node) {
    alert("La pista ya se encuentra asociada.");
    return false;
}

function alertaPistaMantenimiento(node) {
    alert("Los karts en mantenimiento no pueden asociarse.");
    return false;
}

function alertaNoKartsDisponibles(node) {
	alert("En este momento no quedan karts disponibles para reservar.");
    return false;
}

function validarCrearPista() {
	document.getElementById("submit-button").disabled = true;
	
	const valNombre = comprobarNombrePista();
	const valTipo = comprobarTipo();
	const valMaxKarts = comprobarMaxKarts();
	const valDescripcion = comprobarDescripcion();
	
	if (valNombre && valTipo && valMaxKarts && valDescripcion) {
		document.getElementById("submit-button").disabled = false;
	}
}