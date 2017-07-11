/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.servlet;

import com.samho.dao.DadosDAO;
import com.samho.necocio.Pessoas;
import com.samho.necocio.Principal;
import com.samho.necocio.SituacoesPessoas;
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
@WebServlet(name = "servlet_pessoa_juridica", urlPatterns = {"/pessoas_juridicas"})
public class servlet_pessoa_juridica extends HttpServlet {

    private final boolean[] acoes;
    private Pessoas objeto;

    public servlet_pessoa_juridica() {
        this.acoes = Util.getAcoesUsuario(Principal.PESSOAS_JURIDICAS);
        objeto = new Pessoas(Pessoas.TIPO_JURIDICA);
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
            out.println("<!-- Estilos da página --> <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" />");
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
            out.println("<!-- Formul?o de cadcastro -->");
            out.println("<h2>Cadastro Pessoa Jurídica</h2>");
            out.println("<form action=\"pessoas_juridicas\" method=\"post\">");
            out.println("<div class=\"form_settings\">");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_codigo\">Código</span>");
            out.println("<input id=\"input_codigo\" name=\"param1\" class=\"di_100\" readonly=\"readonly\" type=\"text\" placeholder=\"Cód. Pessoa\" value=\"" + objeto.getIdPessoa() + "\"/>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_nome\">Nome</span>");
            out.println("<input id=\"input_nome\" name=\"param2\" class=\"en_450\" required=\"required\" type=\"text\" placeholder=\"Nome Fantasia.\" value=\"" + objeto.getNome() + "\"/>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_sobrenome\">Rasão Social</span>");
            out.println("<input id=\"input_sobrenome\" name=\"param3\" class=\"en_450\" required=\"required\" type=\"text\" placeholder=\"Rasão Social ou Nome Fantasia.\" value=\"" + objeto.getSobrenome() + "\"/>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_observacao\">Observações</span>");
            out.println("<textarea id=\"input_observacao\" name=\"param4\" placeholder=\"Utilize este espaço para escrever suas observações.\" value=\"" + objeto.getObservacoes() + "\"></textarea>");
            out.println("</p>");
            out.println("");
            out.println("<p class=\"separator\"/>");
            out.println("<h3>Complemento</h3>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_cpf\">CNPJ</span>");
            out.println("<input id=\"input_cpf\" name=\"param5\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"CNPJ __.___.___/____-__\" value=\"" + objeto.getPessoaJuridica().getCnpj() + "\"/>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_rg\">IE</span>");
            out.println("<input id=\"input_rg\" name=\"param6\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"IE ___________________\" value=\"" + objeto.getPessoaJuridica().getIe() + "\"/>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"datepicker\">Data Fundação</span>");
            out.println("<a><input id=\"datepicker\" name=\"param7\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"dd/mm/yyyy\" value=\"" + Formatacao.ajustaDataDMA(objeto.getPessoaJuridica().getDataDeFundacao()) + "\"/></a>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_situacao\">Situação</span>");
            out.println("<select id=\"input_situacao\" name=\"param8\" class=\"sel_300\" value=\"" + objeto.getCodSituacao() + "\">");
            ArrayList listaSituacao = new ArrayList();
            listaSituacao = new SituacoesPessoas().getObjetoDAO().getListaCodigoDescricao("id_situacao", "", "descricao");
            for (Iterator it = listaSituacao.iterator(); it.hasNext();) {
                Registrador reg = (Registrador) it.next();
                if (reg.getCodigo() == objeto.getCodSituacao()) {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\" selected>" + reg.getDescricao() + "</option>");
                } else {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\">" + reg.getDescricao() + "</option>");
                }
            }
            out.println(" </select>");
            out.println("</p>");
            out.println("");
/**
            out.println("<p class=\"separator\"/>");
            out.println("<p>");
            out.println("<a onclick=\"carregar('enderecos', 'view/view_enderecos.jsp #form_settings')\" href=\"#enderecos\">Endereços</a>");
            out.println("<div id=\"enderecos\">");
            out.println("</div>");
            out.println("</p>");
            out.println("");

            out.println("<p class=\"separator\"/>");
            out.println("<p>");
            out.println("<a onclick=\"carregar('telefones', 'view/view_telefones.jsp #form_settings')\" href=\"#telefones\">Telefones</a>");
            out.println("<div id=\"telefones\">");
            out.println("</div>");
            out.println("</p>");
            out.println("");

            out.println("<p class=\"separator\"/>");
            out.println("<p>");
            out.println("<a onclick=\"carregar('especializacoes', 'view/view_especializacoes.jsp #form_settings')\" href=\"#especializacoes\">Especializações</a>");
            out.println("<div id=\"especializacoes\">");
            out.println("</div>");
            out.println("</p>");
            out.println("");
*/
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
                objeto = new Pessoas(Pessoas.TIPO_JURIDICA);
                objeto.setIdPessoa(objeto.getObjetoDAO().getProximoID(
                        objeto.getObjetoDAO().getCampoID()));

                processRequest(request, response);
            }
        } else {
            if (acao.equals("alterar")) {
                if (acoes[Util.ALTERAR]) {
                    objeto = new Pessoas(Pessoas.TIPO_JURIDICA);
                    objeto.adicionarWhere(new DadosDAO(
                            objeto.getObjetoDAO().getCampoID(), "", codigo,
                            DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));
                    objeto.getPessoaJuridica().adicionarWhere(new DadosDAO(
                            objeto.getPessoaJuridica().getObjetoDAO().getCampoID(), "", codigo,
                            DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

                    Object[] obj = objeto.getObjetoDAO().getDadosObjeto();
                    Object[] objFilho = objeto.getPessoaJuridica().getObjetoDAO().getDadosObjeto();

                    objeto = new Pessoas(Pessoas.TIPO_JURIDICA);
                    objeto.setIdPessoa(Long.parseUnsignedLong(obj[0].toString()));
                    objeto.setCodSituacao(Long.parseUnsignedLong(obj[1].toString()));
                    objeto.setNome(obj[2].toString());
                    objeto.setSobrenome(obj[3].toString());
                    objeto.setObservacoes(obj[4].toString());
                    objeto.getPessoaJuridica().setCodPessoa(
                            Long.parseUnsignedLong(objFilho[0].toString()));
                    objeto.getPessoaJuridica().setCnpj(
                            Long.parseUnsignedLong(objFilho[1].toString()));
                    objeto.getPessoaJuridica().setIe(
                            Long.parseUnsignedLong(objFilho[2].toString()));
                    objeto.getPessoaJuridica().setDataDeFundacao(
                            Formatacao.ajustaData(objFilho[3].toString(), Formatacao.DATA_DMA));
                    
                    objeto.adicionarCampos();

                    processRequest(request, response);
                }
            } else {
                if (acao.equals("excluir")) {
                    if (acoes[Util.EXCLUIR]) {
                        objeto.getObjetoDAO().excluir(codigo);

                        request.getRequestDispatcher("/view/view_pessoas_juridicas.jsp").forward(request, response);
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
        String param8 = request.getParameter("param8");

        if (param1 != null) {
            objeto = new Pessoas(Pessoas.TIPO_JURIDICA);
            objeto.setIdPessoa(Long.parseUnsignedLong(param1));
            objeto.setNome(param2);
            objeto.setSobrenome(param3);
            objeto.setObservacoes(param4);
            objeto.getPessoaJuridica().setCodPessoa(
                    Long.parseUnsignedLong(param1));
            objeto.getPessoaJuridica().setCnpj(
                    Long.parseUnsignedLong(param5));
            objeto.getPessoaJuridica().setIe(
                    Long.parseUnsignedLong(param6));
            objeto.getPessoaJuridica().setDataDeFundacao(
                    Formatacao.ajustaData(param7, Formatacao.DATA_DMA));
            objeto.setCodSituacao(Long.parseUnsignedLong(param8));

            objeto.adicionarWhere(new DadosDAO(
                    objeto.getObjetoDAO().getCampoID(), "", param1,
                    DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));
            
            objeto.adicionarCampos();
        }

        long id = objeto.getObjetoDAO().getProximoID(
                objeto.getObjetoDAO().getCampoID());

        if (id == objeto.getIdPessoa()) {
            if (acoes[Util.INCLUIR]) {
                objeto.getObjetoDAO().incluir();
                objeto.getPessoaJuridica().getObjetoDAO().incluir();
            }
        } else {
            if (acoes[Util.ALTERAR]) {
                objeto.getObjetoDAO().alterar();
                objeto.getPessoaJuridica().getObjetoDAO().alterar();
            }
        }

        request.getRequestDispatcher("/view/view_pessoas_juridicas.jsp").forward(request, response);
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
