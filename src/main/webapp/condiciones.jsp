<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:include page="./include/commonhead.jsp" >
		<jsp:param name="title" value="Condiciones generales - UCOKarts"/>
   	</jsp:include>
</head>
<body>

<%@ include file="/include/header.jsp" %>

<div class="columnas">
    <div class="columna">
        <h1>Condiciones generales</h1>
        <div class="texto">
        <h2>Reglamento para la utilización de los karts</h2>
		
		<ol>
		<li>La conducción de los karts deberá efectuarse en forma correcta. Podrá darse por finalizado el tiempo al 
		que da derecho el ticket, perdiendo totalmente su importe sin derecho a reclamación alguna por alguna de las 
		siguientes causas:
		
		<ul>
		<li>Por salirse de la pista.</li>
		<li>Por embestir los neumáticos de protección.</li>
		<li>Por cerrar o no ceder el paso.</li>
		<li>Por empujar a alguno de los demás vehículos en circulación.</li>
		<li>Por no atender las señales de los empleados.</li>
		<li>Por conducción peligrosa.</li>
		<li>Por permanecer en los Boxes (zona peligrosa) o por otras causas justificadas que estime el jefe de pista.</li>
		</ul>
		</li>
		
		<li>Está totalmente prohibido permanecer en zona de boxes. Una vez adquirido el ticket deberá esperarse el 
		turno correspondiente tras la valla protectora, los infractores se harán responsables de los daños que puedan 
		sufrir a causa de este incumplimiento. Solamente podrán entrar o salir de esta zona cuando se lo indiquen los 
		empleados de la pista, perdiendo el importe del ticket en caso de hacerlo sin debida autorización. Recuerde, 
		por su propia seguridad, ¡NO PERMACEZCA EN LA ZONA DE BOXES!</li>
		
		<li>Está prohibido fumar en zona de boxes y mientras se conducen los karts.</li>
		
		<li>En caso de accidente provocado, el usuario de kart, deberá abonar los desperfectos que haya ocasionado 
		a su vehículo y a los demás.</li>
		
		<li>Es obligatorio el uso de casco protector que le será entregado para emplear durante la conducción del 
		kart y que deberá ir perfectamente abrochado. Así mismo, se empleará vestimenta adecuada, a cargo del usuario.</li>
		
		<li>Está prohibido arrojar objetos desde los karts en marcha, tales como calzado, gafas, prendas de vestir, 
		cascos, etc…</li>
		
		<li>En caso de avería o despiste de su vehículo, si éste quedara inmovilizado en cualquier punto del circuito, 
		no descienda de él ni atraviese andando las pistas (ES SUMAMENTE PELIGROSO), levante la mano y espere a que 
		nuestro personal le atienda en el punto donde ocurrió el percance.</li>
		
		<li>El Karting vela constantemente para que los karts estén en sus óptimas condiciones de uso, no obstante, si 
		Vd. considera que su vehículo no marcha como Vd. desea, deténgase en boxes una vez transcurrida la primera 
		vuelta, NO ESPERE AL FINAL DEL TRAYECTO para efectuar su reclamación, pues entonces nos será imposible 
		atenderle puesto que habrá consumido ya su ticket. Nuestro deseo es que Vd. quede plenamente satisfecho.</li>
		
		<li>Esta empresa podrá anular cualquier ticket (devolviendo su importe antes de subir a los karts) si lo cree 
		necesario, por estado de embriaguez o por cualquier otra causa justa que se estime.</li>
		
		<li>La empresa se reserva el derecho de venta de tickets fuera del horario previsto por causas meteorológicas 
		que lo aconsejen, por deficiencias en los alumbrados o por fuerzas mayores. En El Karting los Tickets de alquiler 
		no caducan, por lo que no se devuelve su importe.</li>
		
		<li>EL PRECIO DEL TICKET, SE ENTIENDE POR PERSONA Y KART, no podrán repetirse las vueltas o la duración del 
		tiempo de un mismo ticket entre varios usuarios. Si Vd. piensa venir más de una vez a El Karting le aconsejamos 
		se informe sobre nuestros abonos para clientes habituales y también para grupos numerosos, todos ellos a precios 
		más económicos.</li>
		
		<li>La conducción de los karts deberá hacerse correcta, cediendo el paso a los que quieran adelantar. Los 
		adelantamientos deberán realizarse siempre que no se ponga en peligro la seguridad, con el fin de evitar en 
		todo lo posible los accidentes.</li>
		
		<li>El Karting no se hará responsable de los automóviles estacionados en su parking ni de los objetos que 
		se hallen en su interior, tampoco se hará responsable de los objetos que puedan extraviar los usuarios 
		durante la conducción de los karts y que no hayan sido previamente depositados en taquilla.</li>
		
		<li>Siempre velamos por el buen estado de todos nuestros servicios ligados al alquiler, tales como el 
		control de tiempos, la impresión de tarjetas, etc. En caso de haber algún fallo en los mismos se solucionaría 
		en el menor tiempo posible, no admitiendo ningún tipo de reclamación. Este reglamento que ha sido aprobado en 
		su integridad por la Asociación Española de Pistas de Karts, pretende seleccionar a nuestra distinguida 
		clientela que nos honra con su presencia y velar en todo momento por su seguridad. Para cualquier reclamación 
		les rogamos se dirijan al Director del circuito que gustosamente les atenderá.</li>
		</ol>
		
		<h2>Compra de tickets y pagos on-line</h2>
		<p>Los tickets adquiridos con los descuentos por compra on-line tienen la validez de un año a contar desde su pago.</p>
		
		<h2>Política de desistimiento</h2>
		<p>Dadas las características de los productos vendidos (entradas o pagos con una fecha de evento o un periodo de 
		ejecución específicos) resulta de aplicación lo dispuesto en el art. 103. l) de la Ley General para la 
		Defensa de los Consumidores y Usuarios quedando excluido el derecho de desistimiento al sistema de compra 
		de tickets o reservas para eventos.</p>
        </div>
    </div>
</div>

<%@ include file="/include/footer.jsp" %>

</body>
</html>