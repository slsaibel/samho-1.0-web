/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "controller_contato", urlPatterns = {"/contato"})
public class controller_contato extends HttpServlet {

    /**
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
            out.println("<title>SAMHO - Contato</title>");
            out.println("");
            out.println("<!-- Estilos da página -->");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"main\">");
            out.println("<header>");
            out.println("<div id=\"logo\">");
            out.println("<div id=\"logo_text\">");
            out.println("<!-- class=\"logo_colour\", allows you to change the colour of the text -->");
            out.println("<h1><a href=\"index.html\"><span class=\"logo_colour\">SAMHO</span></a></h1>");
            out.println("<h2>Sistema de Agendamento Médico Hospitalar</h2>");
            out.println("</div>");
            out.println("</div>");
            out.println("<nav>");
            out.println("<div id=\"menu_container\">");
            out.println("<ul class=\"sf-menu\" id=\"nav\">");
            out.println("<li><a href=\"index.html\">Inicio</a></li>");
            out.println("<li><a href=\"#\">Ajuda</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</header>");
            out.println("");
            out.println("<div id=\"site_content\">");
            out.println("");
            
            out.println("<div id=\"sidebar_container\">");
            out.println("<div class=\"sidebar\">");
            out.println("<img src=\"images/contato.png\" /></a>");
            out.println("</div>");
            out.println("</div>");
            out.println("");

            out.println("<div class=\"content_form\">");
            out.println("<!-- Formulário de cadcastro -->");
            out.println("<h2>Contato</h2>");
            out.println("<form action=\"#\" method=\"post\">");
            out.println("<div class=\"form_settings\">");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_nome\">Nome/Usuário</span>");
            out.println("<input id=\"input_nome\" class=\"en_300\" required=\"required\" type=\"text\" placeholder=\"Infomre seu Nome ou Usuário.\" />");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_cidade\">Cidade/Estado</span>");
            out.println("<input id=\"input_cidade\" class=\"en_300\" required=\"required\" type=\"text\" placeholder=\"Infomre o nome de sua Cidade.\" />");
            out.println("<select id=\"lista_uf\" class=\"en_50\" required=\"required\" placeholder=\"Selecione o Estado.\" >");
            out.println("<option value=\"\">Selecione o Estado.</option>");
            out.println("<option value=\"AC\">Acre</option>");
            out.println("<option value=\"AL\">Alagoas</option>");
            out.println("<option value=\"AP\">Amapá</option>");
            out.println("<option value=\"AM\">Amazonas</option>");
            out.println("<option value=\"BA\">Bahia</option>");
            out.println("<option value=\"CE\">Ceará</option>");
            out.println("<option value=\"DF\">Distrito Federal</option>");
            out.println("<option value=\"ES\">Espírito Santo</option>");
            out.println("<option value=\"GO\">Goiás</option>");
            out.println("<option value=\"MA\">Maranhão</option>");
            out.println("<option value=\"MT\">Mato Grosso</option>");
            out.println("<option value=\"MS\">Mato Grosso do Sul</option>");
            out.println("<option value=\"MG\">Minas Gerais</option>");
            out.println("<option value=\"PA\">Pará</option>");
            out.println("<option value=\"PB\">Paraába</option>");
            out.println("<option value=\"PR\">Paraná</option>");
            out.println("<option value=\"PE\">Pernambuco</option>");
            out.println("<option value=\"PI\">Piauí</option>");
            out.println("<option value=\"RJ\">Rio de Janeiro</option>");
            out.println("<option value=\"RN\">Rio Grande do Norte</option>");
            out.println("<option value=\"RS\">Rio Grande do Sul</option>");
            out.println("<option value=\"RO\">Rondônia</option>");
            out.println("<option value=\"RR\">Roraima</option>");
            out.println("<option value=\"SC\">Santa Catarina</option>");
            out.println("<option value=\"SP\">São Paulo</option>");
            out.println("<option value=\"SE\">Sergipe</option>");
            out.println("<option value=\"TO\">Tocantins</option>");
            out.println("</select>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_email\">E-mail</span>");
            out.println("<input id=\"input_email\" class=\"en_300\" required=\"required\" type=\"email\" placeholder=\"Informe um e-mail para contato.\" />");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_tel_fixo\">Telefone - Fixo</span>");
            out.println("<input id=\"input_tel_fixo\" class=\"en_150_in_line\" required=\"required\" type=\"text\" placeholder=\"XX(XX)XXXX-XXXX - Fixo\" />");
            out.println("<span class=\"required\" for=\"input_tel_celular\">- Celular</span>");
            out.println("<input id=\"input_tel_celular\" class=\"en_150_in_line\" required=\"required\" type=\"text\" placeholder=\"XX(XX)XXXXX-XXXX\" />");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"lista_assunto\">Assunto</span>");
            out.println("<select id=\"lista_assunto\" class=\"sel_300\" required=\"required\" placeholder=\"Selecione um assunto.\">");
            out.println("<option value=\"\">Selecione um assunto</option>");
            out.println("<option value=\"Informação\">Informação</option>");
            out.println("<option value=\"Suporte\">Suporte</option>");
            out.println("<option value=\"Compra\">Compra</option>");
            out.println("<option value=\"Sugestão\">Sugestão</option>");
            out.println("</select>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_mensagem\">Mensagem</span>");
            out.println("<textarea id=\"input_mensagem\" required=\"required\" placeholder=\"Utilize este espaço para escrever suas dúvidas.\"></textarea>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"checagens_label\"></span>");
            out.println("<span class=\"required_long\" for=\"checagens_label\">Quero receber novidade em meu e-mail.</span>");
            out.println("<input id=\"checagens_label\" class=\"checkbox\" type=\"checkbox\">");
            out.println("</p>");
            out.println("");
            out.println("<p class=\"separator\"/>");
            out.println("<p class=\"in_line_right\">");
            out.println("<input id=\"input_limpar\" class=\"button_down\" type=\"reset\" value=\" Limpar\" formaction=\"javascript:window.history.go(-1)\"/>");
            out.println("<input id=\"input_enviar\" class=\"button_down\" type=\"submit\" value=\" Enviar\" formaction=\"javascript:window.history.go(-1)\"/>");
            out.println("</p>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");

            out.println("");
            out.println("</div>");
            out.println("<div id=\"scroll\">");
            out.println("<a title=\"Mover para cima\" class=\"top\" href=\"#\"><img src=\"images/up.png\" alt=\"top\" /></a>");
            out.println("</div>");
            out.println("");
            out.println("<footer>");
            out.println("<p>");
            out.println("<a href=\"index.html\">Inicio</a> | ");
            out.println("<a href=\"#\">Ajuda</a>");
            out.println("</p>");
            out.println("<p>Copyright &copy; Saibel, Sergio Luis | 2017</p>");
            out.println("</footer>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
