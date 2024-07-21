<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>sp" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD"%>
<%@ page import="es.uco.pw.data.dao.pistas.KartDAO,es.uco.pw.business.pistas.KartDTO" %>
<%@ page import="es.uco.pw.data.dao.pistas.PistaDAO,es.uco.pw.business.pistas.PistaDTO" %>
<jsp:useBean id="customer" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<%
String nextPage = "";

if (customer == null || customer.getEmail().equals("") || customer.getRol().equals("Invitado")) {
	nextPage = application.getInitParameter("loginView");
} else if (customer.getRol().equals("Usuario")) { 
	nextPage = application.getInitParameter("adminACLErrorView");
}
else {
	ConexionBD cbd = ConexionBD.getInstance();
	java.sql.Connection con = cbd.getConnection();
	java.util.ArrayList<KartDTO> karts = KartDAO.getAll();
	java.util.ArrayList<PistaDTO> pistas = new java.util.ArrayList<PistaDTO>();
	
	for (KartDTO k : karts) {
		PistaDTO pista = PistaDAO.queryByKart(k);
		pistas.add(pista);
	}
	
	request.setAttribute("karts", karts);
	request.setAttribute("pistas", pistas);
	nextPage = application.getInitParameter("listadoKartsView");
}
%>

<jsp:forward page="<%=nextPage%>"/>