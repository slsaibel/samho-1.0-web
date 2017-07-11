package com.samho.servlet;

import com.samho.dao.DadosDAO;
import com.samho.necocio.Acoes;
import com.samho.necocio.Principal;
import com.samho.util.Formatacao;
import com.samho.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Document   : servlet_acoes
 * Created on : "Domingo, 25 de Junho de 2017"
 * Author     : "Sergio"
*/
@WebServlet(name = "servlet_acoes", urlPatterns = {"/acoes"})
public class servlet_acoes extends HttpServlet {

    private final boolean[] acoes;
    private Acoes objeto;

    public servlet_acoes() {
        this.acoes = Util.getAcoesUsuario(Principal.ACOES);
        objeto = new Acoes();
    }    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        try (PrintWriter out = response.getWriter()) {
           /* TODO output your page here. You may use following sample code. */           out.println("<?xml version="1.0" pageEncoding="ISO-8859-1"?>");           out.println("<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">");           out.println("<html xmlns="http://www.w3.org/1999/xhtml">");out.println("<head>");out.println("	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>");out.println("	<title>SAMHO - Sistema de Agendamento Médico Hospitalar</title>");out.println("");out.println("	<!-- Estilos da página --> <link rel="stylesheet" type="text/css" href="css/style.css" />");out.println("	<!-- Datepicker --> <script type="text/javascript" src="js/jquery-ui.js"></script>");out.println("	<!-- Datepicker --> <script> $( function() { $( "#datepicker" ).datepicker({ showOn: "button", buttonImage: "images/calendario.png", buttonImageOnly: true, buttonText: "Seleciona uma data." }); }); </script>");out.println("</head>");    <body>
        <div id="main">
out.println("<header>");out.println("	<div id="logo">");out.println("		<div id="logo_text">");out.println("			<!-- class="logo_colour", allows you to change the colour of the text -->");out.println("			<h1><a href="index.html"><span class="logo_colour">SAMHO</span></a></h1>");out.println("			<h2>Sistema de Agendamento Médico Hospitalar</h2>");out.println("		</div>");out.println("	</div>");out.println("	<nav>");out.println("		<div id="menu_container">");out.println("			<ul class="sf-menu" id="nav">");out.println("				<li><a href="index.html">Inicio</a></li>");out.println("				<li><a href="#">Ajuda</a></li>");out.println("			</ul>");out.println("		</div>");out.println("	</nav>");out.println("</header>");out.println("   <div id="site_content">");out.println("       <!-- div id="sidebar_container"> <div class="sidebar"> </div> </div> -->");out.println("");out.println("       <div class="content_form">");out.println("           <!-- Formul?o de cadcastro -->");out.println("           <h2>Cadastro "Acoes"</h2>");out.println("           <form action="acoes" method="post">");out.println("               <div class="form_settings">");out.println("");out.println("                   <p class="separator"/>");out.println("                   <p class="in_line_right">");out.println("                       <input id="input_limpar" class="button_down" type="reset" value=" Limpar" />");out.println("                       <input id="input_salvar" class="button_down" type="submit" value=" Salvar" />");out.println("                   </p>");out.println("               </div>");out.println("           </form>");out.println("       </div>");out.println("");out.println("   </div>");out.println("</div>");out.println("<div id="scroll">");out.println("	<a title="Mover para cima" class="top" href="#"><img src="images/up.png" alt="top" /></a>");out.println("</div>");out.println("<footer>");out.println("   <p>");out.println("		<a href="index.html">Inicio</a> | ");out.println("		<a href="#">Ajuda</a>");out.println("	</p>");out.println("	<p>Copyright &copy; Saibel, Sergio Luis | 2017</p>");out.println("</footer>");        </div>
    </body>
           out.println("</html>");
        }
    }    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}