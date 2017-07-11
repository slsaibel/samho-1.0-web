/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.servlet;

import com.samho.dao.DadosDAO;
import com.samho.necocio.Principal;
import com.samho.necocio.PessoasEspecializacoes;
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
 *
 * @author Sergio
 */
@WebServlet(name = "servlet_pessoa_especializacao", urlPatterns = {"/pessoas_especializacoes"})
public class servlet_pessoa_especializacao extends HttpServlet {

    private final boolean[] acoes;
    private PessoasEspecializacoes objeto;

    public servlet_pessoa_especializacao() {
        this.acoes = Util.getAcoesUsuario(Principal.PESSOAS_ESPECIALIZACOES);
        objeto = new PessoasEspecializacoes();
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
            
            out.println("Página em desenvolvimento");

            /**
             * out.println("<div class=\"content_form\">");
             * out.println("<!-- Formulário de cadcastro -->");
             * out.println("<h2>Cadastro de Clientes/Planos de Saúde</h2>");
             * out.println("<form action=\"cliente_tipo_plano\" method=\"post\">");
             * out.println("<div class=\"form_settings\">");
            out.println("
             * <p>
             * ");
             * out.println("<span class=\"required\" for=\"input_codigo\">Código</span>");
             * out.println("<input id=\"input_codigo\" name=\"param1\" class=\"di_100\" readonly=\"readonly\" type=\"text\" placeholder=\"Cód. Prof. Plano\" value=\"" + objeto.getIdClienteTipoPlano() + "\"/>");
             * out.println("</p>");
            out.println("
             * <p>
             * ");
             * out.println("<span class=\"required\" for=\"input_cod_cliente\">Cód.
             * Cliente</span>");
             * out.println("<input id=\"input_cod_cliente\" name=\"param2\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"Cód. Profissional\" value=\"" + objeto.getCodCliente() + "\"/>");
             * out.println("<a href=\"view/view_clientes.jsp\"><img class=\"img_icon\" src=\"images/localizar.png\" alt=\"Localizar Profissional...\" /></a>");
             * out.println("<input id=\"input_descricao_cliente\" class=\"di_300\" disabled type=\"text\" placeholder=\"Descrição do Profissional\" />");
             * out.println("</p>");
            out.println("
             * <p>
             * ");
             * out.println("<span class=\"required\" for=\"input_cod_plano_saude\">Cód.
             * Tipo Plano</span>");
             * out.println("<input id=\"input_cod_plano_saude\" name=\"param3\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"Cód. Tipo Plano\" value=\"" + objeto.getCodTipoPlano() + "\"/>");
             * out.println("<a href=\"view/view_profissionais.jsp\"><img class=\"img_icon\" src=\"images/localizar.png\" alt=\"Localizar Tipo Plano de Saúde...\" /></a>");
             * out.println("<input id=\"input_descricao_profissional\" class=\"di_300\" disabled type=\"text\" placeholder=\"Descrição do tipo de plano de saúde\" />");
             * out.println("</p>");
            out.println("
             * <p>
             * "); out.println("<span class=\"required\" for=\"datepicker\">Data
             * Adesao</span>");
             * out.println("<a><input id=\"datepicker\" name=\"param4\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"dd/mm/yyyy\" value=\"" + Formatacao.ajustaDataDMA(objeto.getDataAdesao()) + "\"/></a>");
             * out.println("</p>"); out.println("<p class=\"in_line\">");
             * out.println("<span class=\"required\" for=\"input_check\">Ativo</span>");
             * out.println("<input id=\"input_check\" name=\"param5\" class=\"checkbox\" required=\"required\" type=\"checkbox\" placeholder=\"Ativo para o sistema.\" value=\"" + objeto.isAtivo() + "\"/>");
             * out.println("</p>"); out.println("<p class=\"separator\"/>");
             * out.println("<p class=\"in_line_right\">");
             * out.println("<input id=\"input_limpar\" class=\"button_down\" type=\"reset\" value=\" Limpar\" />");
             * out.println("<input id=\"input_salvar\" class=\"button_down\" type=\"submit\" value=\" Salvar\" />");
             * out.println("</p>"); out.println("</div>");
             * out.println("</form>"); out.println("</div>");
             */
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
                objeto = new PessoasEspecializacoes();
                objeto.setIdPessoaEspecializacao(objeto.getObjetoDAO().getProximoID(
                        objeto.getObjetoDAO().getCampoID()));

                processRequest(request, response);
            }
        } else {
            if (acao.equals("alterar")) {
                if (acoes[Util.ALTERAR]) {
                    objeto = new PessoasEspecializacoes();
                    objeto.adicionarWhere(new DadosDAO(
                            objeto.getObjetoDAO().getCampoID(), "", codigo,
                            DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

                    Object[] obj = objeto.getObjetoDAO().getDadosObjeto();
                    objeto = new PessoasEspecializacoes(
                            Long.parseUnsignedLong(obj[0].toString()),
                            Long.parseUnsignedLong(obj[1].toString()),
                            Long.parseUnsignedLong(obj[2].toString()),
                            obj[3].toString(), Integer.parseUnsignedInt(obj[4].toString()),
                            Formatacao.ajustaData(obj[5].toString(), Formatacao.DATA_DMA),
                            Boolean.parseBoolean(obj[6].toString()), obj[7].toString());
                    
                    objeto.adicionarCampos();

                    processRequest(request, response);
                }
            } else {
                if (acao.equals("excluir")) {
                    if (acoes[Util.EXCLUIR]) {
                        objeto.getObjetoDAO().excluir(codigo);

                        request.getRequestDispatcher("/view/view_pessoas_especializacoes.jsp").forward(request, response);
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
        String param6 = request.getParameter("param7");
        String param7 = request.getParameter("param7");
        String param8 = request.getParameter("param8");

        if (param1 != null) {
            objeto = new PessoasEspecializacoes(
                    Long.parseUnsignedLong(param1),
                    Long.parseUnsignedLong(param2),
                    Long.parseUnsignedLong(param3),
                    param4, Integer.parseUnsignedInt(param5),
                    Formatacao.ajustaData(param6, Formatacao.DATA_DMA),
                    Boolean.parseBoolean(param7), param8);
            objeto.adicionarWhere(new DadosDAO(
                    objeto.getObjetoDAO().getCampoID(), "", param1,
                    DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));
            
            objeto.adicionarCampos();
        }

        long id = objeto.getObjetoDAO().getProximoID(
                objeto.getObjetoDAO().getCampoID());

        if (id == objeto.getIdPessoaEspecializacao()) {
            if (acoes[Util.INCLUIR]) {
                objeto.getObjetoDAO().incluir();
            }
        } else {
            if (acoes[Util.ALTERAR]) {
                objeto.getObjetoDAO().alterar();
            }
        }

        request.getRequestDispatcher("/view/view_pessoas_especializacoes.jsp").forward(request, response);
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
