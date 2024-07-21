<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/include/errorPage.jsp" %>sp" %>
<%@ page import="es.uco.pw.data.dao.common.ConexionBD,es.uco.pw.data.dao.pistas.PistaDAO,es.uco.pw.business.pistas.PistaDTO" %>
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
	java.util.ArrayList<PistaDTO> pistas = PistaDAO.getAll();
	request.setAttribute("pistas", pistas);
	nextPage = application.getInitParameter("listadoPistasView");
}
%>

<jsp:forward page="<%=nextPage%>"/>
