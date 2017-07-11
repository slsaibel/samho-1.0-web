/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.servlet;

import com.samho.dao.DadosDAO;
import com.samho.necocio.Clientes;
import com.samho.necocio.Principal;
import com.samho.necocio.Prontuarios;
import com.samho.util.Formatacao;
import com.samho.util.Registrador;
import com.samho.util.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "servlet_prontuario", urlPatterns = {"/prontuarios"})
public class servlet_prontuario extends HttpServlet {

    private final boolean[] acoes;
    private Prontuarios objeto;

    public servlet_prontuario() {
        this.acoes = Util.getAcoesUsuario(Principal.PRONTUARIOS);
        objeto = new Prontuarios();
    }

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
        response.setContentType("text/html;charset=ISO-8859-1");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<?xml version=\"1.0\" pageEncoding=\"ISO-8859-1\"?>");
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
            out.println("<title>SAMHO - Sistema de Agendamento Médico Hospitalar</title>");
            out.println("");
            out.println("<!-- Estilos da página -->");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
            out.println("<!-- Datepicker --> <script type=\"text/javascript\" src=\"js/jquery-ui.js\"></script>");
            out.println("<!-- Datepicker --> <script> $( function() { $( \"#datepicker\" ).datepicker({ showOn: \"button\", buttonImage: \"images/calendario.png\", buttonImageOnly: true, buttonText: \"Seleciona uma data.\" }); }); </script>");
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
            out.println("<!-- div id=\"sidebar_container\"> <div class=\"sidebar\"> </div> </div> -->");
            out.println("");

            out.println("<div class=\"content_form\">");
            out.println("<!-- Formulário de cadcastro -->");
            out.println("<h2>Cadastro de Prontuário</h2>");
            out.println("<form action=\"prontuarios\" method=\"post\">");
            out.println("<div class=\"form_settings\">");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_codigo\">Código</span>");
            out.println("<input id=\"input_codigo\" name=\"param1\" class=\"di_100\" readonly=\"readonly\" type=\"text\" placeholder=\"Cód. Prontuario\" value=\"" + objeto.getIdProntuario() + "\"/>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_clientes\">Cliente</span>");
            out.println("<select id=\"input_clientes\" name=\"param2\" class=\"sel_300\" value=\"" + objeto.getCodCliente() + "\">");
            ArrayList listaClientes = new ArrayList();
            Clientes clientes = new Clientes();
            listaClientes = clientes.getObjetoDAO().getListaCodigoDescricao(clientes.getObjetoDAO().getCampoID(), "", clientes.getDadosDescricao().getCampo());
            for (Iterator it = listaClientes.iterator(); it.hasNext();) {
                Registrador reg = (Registrador) it.next();
                if (reg.getCodigo() == objeto.getCodCliente()) {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\" selected>" + reg.getDescricao() + "</option>");
                } else {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\">" + reg.getDescricao() + "</option>");
                }
            }
            out.println(" </select>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_motivo\">Motivo Consulta</span>");
            out.println("<textarea id=\"input_motivo\" name=\"param3\" placeholder=\"Utilize este espaço para escrever o motivo da consulta.\" value=\"" + objeto.getMotivoConsulta() + "\"></textarea>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_diagnostico\">Diagnóstico</span>");
            out.println("<textarea id=\"input_diagnostico\" name=\"param4\" placeholder=\"Utilize este espaço para escrever o diagnóstico.\" value=\"" + objeto.getDiagnostico() + "\"></textarea>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"datepicker\">Data Consulta</span>");
            out.println("<a><input id=\"datepicker\" name=\"param5\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"dd/mm/yyyy\" value=\"" + Formatacao.ajustaDataDMA(objeto.getDataConsulta()) + "\"/></a>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_parecer\">Parecer Profissional</span>");
            out.println("<textarea id=\"input_parecer\" name=\"param6\" placeholder=\"Utilize este espaço para escrever o parecer.\" value=\"" + objeto.getParecerProfissional() + "\"></textarea>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_observacoes\">Observações</span>");
            out.println("<textarea id=\"input_observacoes\" name=\"param7\" placeholder=\"Utilize este espaço para escrever suas observações.\" value=\"" + objeto.getObservacoes() + "\"></textarea>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_retornar\">Deve Retornar</span>");
            if (objeto.isDeveRetornar()) {
                out.println("<input id=\"input_check_retornar\" name=\"param8\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Executar consultar no sistema.\" value=\"" + objeto.isDeveRetornar() + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_retornar\" name=\"param8\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Executar consultar no sistema.\" value=\"" + objeto.isDeveRetornar() + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"separator\"/>");
            out.println("<p class=\"in_line_right\">");
            out.println("<input id=\"input_limpar\" class=\"button_down\" type=\"reset\" value=\" Limpar\" />");
            out.println("<input id=\"input_salvar\" class=\"button_down\" type=\"submit\" value=\" Salvar\" />");
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
        String codigo = request.getParameter("codigo");
        String acao = request.getParameter("acao");

        if (acao == null || acao.equals("incluir")) {
            if (acoes[Util.INCLUIR]) {
                objeto = new Prontuarios();
                objeto.setIdProntuario(objeto.getObjetoDAO().getProximoID(
                        objeto.getObjetoDAO().getCampoID()));

                processRequest(request, response);
            }
        } else {
            if (acao.equals("alterar")) {
                if (acoes[Util.ALTERAR]) {
                    objeto = new Prontuarios();
                    objeto.adicionarWhere(new DadosDAO(
                            objeto.getObjetoDAO().getCampoID(), "", codigo,
                            DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

                    Object[] obj = objeto.getObjetoDAO().getDadosObjeto();
                    objeto = new Prontuarios(
                            Long.parseUnsignedLong(obj[0].toString()),
                            Long.parseUnsignedLong(obj[1].toString()),
                            obj[2].toString(), obj[3].toString(),
                            Formatacao.ajustaData(obj[4].toString(), Formatacao.DATA_DMA),
                            obj[5].toString(), obj[6].toString(),
                            Boolean.parseBoolean(obj[7].toString()));

                    objeto.adicionarCampos();

                    processRequest(request, response);
                }
            } else {
                if (acao.equals("excluir")) {
                    if (acoes[Util.EXCLUIR]) {
                        objeto.getObjetoDAO().excluir(codigo);

                        request.getRequestDispatcher("/view/view_prontuarios.jsp").forward(request, response);
                    }
                }
            }
        }
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
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        String param3 = request.getParameter("param3");
        String param4 = request.getParameter("param4");
        String param5 = request.getParameter("param5");
        String param6 = request.getParameter("param6");
        String param7 = request.getParameter("param7");
        String[] param8 = request.getParameterValues("param8");
        String param9 = String.valueOf(param8 != null);

        if (param1 != null) {
            objeto = new Prontuarios(
                    Long.parseUnsignedLong(param1),
                    Long.parseUnsignedLong(param2),
                    param3, param4, Formatacao.ajustaData(param5, Formatacao.DATA_DMA),
                    param6, param7, Boolean.parseBoolean(param9));

            objeto.adicionarWhere(new DadosDAO(
                    objeto.getObjetoDAO().getCampoID(), "", param1,
                    DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

            objeto.adicionarCampos();
        }

        long id = objeto.getObjetoDAO().getProximoID(
                objeto.getObjetoDAO().getCampoID());

        if (id == objeto.getIdProntuario()) {
            if (acoes[Util.INCLUIR]) {
                objeto.getObjetoDAO().incluir();
            }
        } else {
            if (acoes[Util.ALTERAR]) {
                objeto.getObjetoDAO().alterar();
            }
        }

        request.getRequestDispatcher("/view/view_prontuarios.jsp").forward(request, response);
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
