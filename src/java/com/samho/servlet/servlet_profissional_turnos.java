/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.servlet;

import com.samho.dao.DadosDAO;
import com.samho.necocio.Principal;
import com.samho.necocio.Profissionais;
import com.samho.necocio.ProfissionaisTurnos;
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
@WebServlet(name = "servlet_profissional_turnos", urlPatterns = {"/profissionais_turnos"})
public class servlet_profissional_turnos extends HttpServlet {

    private final boolean[] acoes;
    private ProfissionaisTurnos objeto;

    public servlet_profissional_turnos() {
        this.acoes = Util.getAcoesUsuario(Principal.PROFISSIONAIS_TURNOS);
        objeto = new ProfissionaisTurnos();
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
            out.println("<h2>Cadastro de Profissionais/Turnos</h2>");
            out.println("<form action=\"profissionais_turnos\" method=\"post\">");
            out.println("<div class=\"form_settings\">");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_codigo\">Código</span>");
            out.println("<input id=\"input_codigo\" name=\"param1\" class=\"di_100\" readonly=\"readonly\" type=\"text\" placeholder=\"Cód. Prof. Turnos\" value=\"" + objeto.getIdProfissionalTurnos() + "\"/>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_profissional\">Profissional</span>");
            out.println("<select id=\"input_profissional\" name=\"param2\" class=\"sel_300\" value=\"" + objeto.getCodProfissional() + "\">");
            ArrayList listaProfissionais = new ArrayList();
            Profissionais profissionais = new Profissionais();
            listaProfissionais = profissionais.getObjetoDAO().getListaCodigoDescricao(profissionais.getObjetoDAO().getCampoID(), "", profissionais.getDadosDescricao().getCampo());
            for (Iterator it = listaProfissionais.iterator(); it.hasNext();) {
                Registrador reg = (Registrador) it.next();
                if (reg.getCodigo() == objeto.getCodProfissional()) {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\" selected>" + reg.getDescricao() + "</option>");
                } else {
                    out.println("<option value=\"" + String.valueOf(reg.getCodigo()) + "\">" + reg.getDescricao() + "</option>");
                }
            }
            out.println(" </select>");
            out.println("</p>");
            out.println("<p>");
            out.println("<span class=\"required\" for=\"input_dias_semana\">Dia da Semana</span>");
            out.println("<select id=\"input_dias_semana\" name=\"param3\" class=\"sel_300\" value=\"" + objeto.getDiaSemana() + "\">");
            ArrayList listaDiasSemana = new ArrayList();
            listaDiasSemana = Formatacao.DIAS_SEMANA_COMPL;
            for (int i = 0; i < listaDiasSemana.size(); i++) {
                if (i == objeto.getDiaSemana()) {
                    out.println("<option value=\"" + String.valueOf(i) + "\" selected>" + listaDiasSemana.get(i).toString() + "</option>");
                } else {
                    out.println("<option value=\"" + String.valueOf(i) + "\">" + listaDiasSemana.get(i).toString() + "</option>");
                }
            }
            out.println(" </select>");
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_madrugada\">Madrugada</span>");
            if (objeto.isMadrugada()) {
                out.println("<input id=\"input_check_madrugada\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Madrugada.\" value=\"" + Util.MADRUGADA + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_madrugada\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Madrugada.\" value=\"" + Util.MADRUGADA + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_manha\">Manhã</span>");
            if (objeto.isManha()) {
                out.println("<input id=\"input_check_manha\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Manhã.\" value=\"" + Util.MANHA + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_manha\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Manhã.\" value=\"" + Util.MANHA + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_tarde\">Tarde</span>");
            if (objeto.isTarde()) {
                out.println("<input id=\"input_check_tarde\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Tarde.\" value=\"" + Util.TARDE + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_tarde\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Tarde.\" value=\"" + Util.TARDE + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_noite\">Noite</span>");
            if (objeto.isNoite()) {
                out.println("<input id=\"input_check_noite\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Noite.\" value=\"" + Util.NOITE + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_noite\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende de Noite.\" value=\"" + Util.NOITE + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_extras\">Extras</span>");
            if (objeto.isAtendeExtra()) {
                out.println("<input id=\"input_check_extras\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende Extras.\" value=\"" + Util.EXTRA + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_extras\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende Extras.\" value=\"" + Util.EXTRA + "\"/>");
            }
            out.println("</p>");
            out.println("<p class=\"in_line\">");
            out.println("<span class=\"required\" for=\"input_check_feriados\">Feriados</span>");
            if (objeto.isAtendeFeriado()) {
                out.println("<input id=\"input_check_feriados\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende Feriados.\" value=\"" + Util.FERIADO + "\" checked/>");
            } else {
                out.println("<input id=\"input_check_feriados\" name=\"param4\" class=\"checkbox\" type=\"checkbox\" placeholder=\"Atende Feriados.\" value=\"" + Util.FERIADO + "\"/>");
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
                objeto = new ProfissionaisTurnos();
                objeto.setIdProfissionalTurnos(objeto.getObjetoDAO().getProximoID(
                        objeto.getObjetoDAO().getCampoID()));

                processRequest(request, response);
            }
        } else {
            if (acao.equals("alterar")) {
                if (acoes[Util.ALTERAR]) {
                    objeto = new ProfissionaisTurnos();
                    objeto.adicionarWhere(new DadosDAO(
                            objeto.getObjetoDAO().getCampoID(), "", codigo,
                            DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

                    Object[] obj = objeto.getObjetoDAO().getDadosObjeto();
                    objeto = new ProfissionaisTurnos(
                            Long.parseUnsignedLong(obj[0].toString()),
                            Long.parseUnsignedLong(obj[1].toString()),
                            Formatacao.DIAS_SEMANA_COMPL.indexOf(obj[2].toString()),
                            Boolean.parseBoolean(obj[3].toString()),
                            Boolean.parseBoolean(obj[4].toString()),
                            Boolean.parseBoolean(obj[5].toString()),
                            Boolean.parseBoolean(obj[6].toString()),
                            Boolean.parseBoolean(obj[7].toString()),
                            Boolean.parseBoolean(obj[8].toString()));

                    objeto.adicionarCampos();

                    processRequest(request, response);
                }
            } else {
                if (acao.equals("excluir")) {
                    if (acoes[Util.EXCLUIR]) {
                        objeto.getObjetoDAO().excluir(codigo);

                        request.getRequestDispatcher("/view/view_profissionais_turnos.jsp").forward(request, response);
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
        String[] param4 = request.getParameterValues("param4");
        String param5 = "false", param6 = "false", param7 = "false",
                param8 = "false", param9 = "false", param10 = "false";
        for (String string : param4) {
            if (string.equals(String.valueOf(Util.MADRUGADA))) {
                param5 = "true";
            } else {
                if (string.equals(String.valueOf(Util.MANHA))) {
                    param6 = "true";
                } else {
                    if (string.equals(String.valueOf(Util.TARDE))) {
                        param7 = "true";
                    } else {
                        if (string.equals(String.valueOf(Util.NOITE))) {
                            param8 = "true";
                        } else {
                            if (string.equals(String.valueOf(Util.EXTRA))) {
                                param9 = "true";
                            } else {
                                if (string.equals(String.valueOf(Util.FERIADO))) {
                                    param10 = "true";
                                }
                            }
                        }
                    }
                }
            }
        }

        if (param1 != null) {
            objeto = new ProfissionaisTurnos(
                    Long.parseUnsignedLong(param1),
                    Long.parseUnsignedLong(param2),
                    Integer.parseUnsignedInt(param3),
                    Boolean.parseBoolean(param5), Boolean.parseBoolean(param6),
                    Boolean.parseBoolean(param7), Boolean.parseBoolean(param8),
                    Boolean.parseBoolean(param9), Boolean.parseBoolean(param10));
            objeto.adicionarWhere(new DadosDAO(
                    objeto.getObjetoDAO().getCampoID(), "", param1,
                    DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));

            objeto.adicionarCampos();
        }

        long id = objeto.getObjetoDAO().getProximoID(
                objeto.getObjetoDAO().getCampoID());

        if (id == objeto.getIdProfissionalTurnos()) {
            if (acoes[Util.INCLUIR]) {
                objeto.getObjetoDAO().incluir();
            }
        } else {
            if (acoes[Util.ALTERAR]) {
                objeto.getObjetoDAO().alterar();
            }
        }

        request.getRequestDispatcher("/view/view_profissionais_turnos.jsp").forward(request, response);
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
