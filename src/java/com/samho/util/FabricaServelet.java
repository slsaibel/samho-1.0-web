/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.util;

import com.samho.necocio.InterfaceObj;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class FabricaServelet {

    private static File diretorio;
    private static OutputStreamWriter arquivo;
    private static InterfaceObj objeto;
    private static String nomeClasse;
    private static String nomeReferencia;
    private static String urlClasse;
    private static String descricaoClasse;
    private static String encoding;

    private static void inicializar() throws IOException {
        diretorio = new File(new File(".").getCanonicalPath());
        diretorio.mkdirs();

        nomeClasse = FabricaServelet.objeto.getClass().getSimpleName();
        nomeReferencia = objeto.getObjetoDAO().getTabela().toLowerCase();
        try {
            arquivo = new OutputStreamWriter(new FileOutputStream(
                    diretorio + "\\src\\java\\com\\samho\\servlet\\servlet_"
                    + nomeReferencia + ".java"), encoding);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FabricaViewJSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void criarServlet(InterfaceObj objeto, String descricaoClasse,
            String encoding) throws IOException {
        FabricaServelet.objeto = objeto;
        FabricaServelet.descricaoClasse = descricaoClasse;
        FabricaServelet.encoding = encoding;

        inicializar();
        try {
            montarSerlet();

            arquivo.close();
        } catch (IOException ex) {
            Logger.getLogger(FabricaServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void montarSerlet() throws IOException {
        montarImport();
        montarInfoClasse();

        arquivo.write("@WebServlet(name = \"servlet_" + nomeReferencia.toLowerCase() + "\", urlPatterns = {\"/" + nomeReferencia.toLowerCase() + "\"})\n");
        arquivo.write("public class servlet_" + nomeReferencia.toLowerCase() + " extends HttpServlet {\n");
        arquivo.write("\n");
        arquivo.write("    private final boolean[] acoes;\n");
        arquivo.write("    private " + nomeClasse + " objeto;\n");
        arquivo.write("\n");
        arquivo.write("    public servlet_" + nomeReferencia.toLowerCase() + "() {\n");
        arquivo.write("        this.acoes = Util.getAcoesUsuario(Principal." + nomeReferencia.toUpperCase() + ");\n");
        arquivo.write("        objeto = new " + nomeClasse + "();\n");
        arquivo.write("    }\n");

        montarProcessRequest();
        montarDoGet();

        arquivo.write("    /**\n");
        arquivo.write("     * Returns a short description of the servlet.\n");
        arquivo.write("     *\n");
        arquivo.write("     * @return a String containing servlet description\n");
        arquivo.write("     */\n");
        arquivo.write("    @Override\n");
        arquivo.write("    public String getServletInfo() {\n");
        arquivo.write("        return \"Short description\";\n");
        arquivo.write("    }// </editor-fold>\n");
        arquivo.write("\n");
        arquivo.write("}");
    }

    private static void montarInfoClasse() throws IOException {
        arquivo.write("/**\n");
        arquivo.write(" * To change this license header, choose License Headers in Project Properties.\n");
        arquivo.write(" * To change this template file, choose Tools | Templates\n");
        arquivo.write(" * and open the template in the editor.\n");
        arquivo.write(" *\n");
        arquivo.write(" * Document   : servlet_" + nomeClasse.toLowerCase() + "\n");
        arquivo.write(" * Created on : \"" + Formatacao.getDataAtual(Formatacao.COMPLETA) + "\"\n");
        arquivo.write(" * Author     : \"" + System.getProperty("user.name") + "\"\n");
        arquivo.write("*/\n");
    }

    private static void montarImport() throws IOException {
        arquivo.write("package com.samho.servlet;\n");
        arquivo.write("\n");
        arquivo.write("import com.samho.dao.DadosDAO;\n");
        arquivo.write("import " + FabricaServelet.objeto.getClass().getCanonicalName() + ";\n");
        arquivo.write("import com.samho.necocio.Principal;\n");
        arquivo.write("import com.samho.util.Formatacao;\n");
        arquivo.write("import com.samho.util.Util;\n");
        arquivo.write("import java.io.IOException;\n");
        arquivo.write("import java.io.PrintWriter;\n");
        arquivo.write("import javax.servlet.ServletException;\n");
        arquivo.write("import javax.servlet.annotation.WebServlet;\n");
        arquivo.write("import javax.servlet.http.HttpServlet;\n");
        arquivo.write("import javax.servlet.http.HttpServletRequest;\n");
        arquivo.write("import javax.servlet.http.HttpServletResponse;\n");
    }

    private static void montarProcessRequest() throws IOException {
        arquivo.write("    /**\n");
        arquivo.write("     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>\n");
        arquivo.write("     * methods.\n");
        arquivo.write("     *\n");
        arquivo.write("     * @param request servlet request\n");
        arquivo.write("     * @param response servlet response\n");
        arquivo.write("     * @throws ServletException if a servlet-specific error occurs\n");
        arquivo.write("     * @throws IOException if an I/O error occurs\n");
        arquivo.write("     */\n");
        arquivo.write("    protected void processRequest(HttpServletRequest request, HttpServletResponse response)\n");
        arquivo.write("            throws ServletException, IOException {\n");
        arquivo.write("        response.setContentType(\"text/html;charset=" + encoding + "\");\n");
        arquivo.write("        try (PrintWriter out = response.getWriter()) {\n");
        arquivo.write("           /* TODO output your page here. You may use following sample code. */\n");
        arquivo.write("           out.println(\"<?xml version=\\\"1.0\\\" pageEncoding=\\\"" + encoding + "\\\"?>\");\n");
        arquivo.write("           out.println(\"<!DOCTYPE html PUBLIC \\\"-//W3C//DTD XHTML 1.0 Strict//EN\\\" \\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\\\">\");\n");
        arquivo.write("           out.println(\"<html xmlns=\\\"http://www.w3.org/1999/xhtml\\\">\");\n");

        montarHead("SAMHO - Sistema de Agendamento Médico Hospitalar");
        montarBody();

        arquivo.write("           out.println(\"</html>\");\n");
        arquivo.write("        }\n");
        arquivo.write("    }\n");
    }

    private static void montarBody() throws IOException {
        arquivo.write("out.println(\"<body>\");\n");
        arquivo.write("out.println(\"	<div id=\\\"main\\\">\");\n");

        montarHeader();
        montarSiteContent();
        montarScroll();
        montarFooter();

        arquivo.write("out.println(\"	</div>\");\n");
        arquivo.write("out.println(\"</body>\");\n");
    }

    private static void montarHead(String title) throws IOException {
        arquivo.write("out.println(\"<head>\");\n");
        arquivo.write("out.println(\"	<meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=" + encoding + "\\\"/>\");\n");
        arquivo.write("out.println(\"	<title>" + title + "</title>\");\n");
        arquivo.write("out.println(\"\");");
        arquivo.write("out.println(\"	<!-- Estilos da página --> <link rel=\\\"stylesheet\\\" type=\\\"text/css\\\" href=\\\"css/style.css\\\" />\");\n");
        arquivo.write("out.println(\"	<!-- Datepicker --> <script type=\\\"text/javascript\\\" src=\\\"js/jquery-ui.js\\\"></script>\");\n");
        arquivo.write("out.println(\"	<!-- Datepicker --> <script> $( function() { $( \\\"#datepicker\\\" ).datepicker({ showOn: \\\"button\\\", buttonImage: \\\"images/calendario.png\\\", buttonImageOnly: true, buttonText: \\\"Seleciona uma data.\\\" }); }); </script>\");\n");
        arquivo.write("out.println(\"</head>\");\n");
    }

    private static void montarHeader() throws IOException {
        arquivo.write("out.println(\"<header>\");\n");
        arquivo.write("out.println(\"	<div id=\\\"logo\\\">\");\n");
        arquivo.write("out.println(\"		<div id=\\\"logo_text\\\">\");\n");
        arquivo.write("out.println(\"			<!-- class=\\\"logo_colour\\\", allows you to change the colour of the text -->\");\n");
        arquivo.write("out.println(\"			<h1><a href=\\\"index.html\\\"><span class=\\\"logo_colour\\\">SAMHO</span></a></h1>\");\n");
        arquivo.write("out.println(\"			<h2>Sistema de Agendamento Médico Hospitalar</h2>\");\n");
        arquivo.write("out.println(\"		</div>\");\n");
        arquivo.write("out.println(\"	</div>\");\n");
        arquivo.write("out.println(\"	<nav>\");\n");
        arquivo.write("out.println(\"		<div id=\\\"menu_container\\\">\");\n");
        arquivo.write("out.println(\"			<ul class=\\\"sf-menu\\\" id=\\\"nav\\\">\");\n");
        arquivo.write("out.println(\"				<li><a href=\\\"index.html\\\">Inicio</a></li>\");\n");
        arquivo.write("out.println(\"				<li><a href=\\\"#\\\">Ajuda</a></li>\");\n");
        arquivo.write("out.println(\"			</ul>\");\n");
        arquivo.write("out.println(\"		</div>\");\n");
        arquivo.write("out.println(\"	</nav>\");\n");
        arquivo.write("out.println(\"</header>\");\n");
    }

    private static void montarSiteContent() throws IOException {
        arquivo.write("out.println(\"   <div id=\\\"site_content\\\">\");\n");
        arquivo.write("out.println(\"       <!-- div id=\\\"sidebar_container\\\"> <div class=\\\"sidebar\\\"> </div> </div> -->\");\n");
        arquivo.write("out.println(\"\");\n");
        arquivo.write("out.println(\"       <div class=\\\"content_form\\\">\");\n");
        arquivo.write("out.println(\"           <!-- Formul?o de cadcastro -->\");\n");
        arquivo.write("out.println(\"           <h2>Cadastro \\\"" + descricaoClasse + "\\\"</h2>\");\n");
        arquivo.write("out.println(\"           <form action=\\\"" + nomeClasse.toLowerCase() + "\\\" method=\\\"post\\\">\");\n");
        arquivo.write("out.println(\"               <div class=\\\"form_settings\\\">\");\n");

        /**
         * Formulário da classe
         */
        arquivo.write("out.println(\"" + objeto.getFormServlet() + "\");\n");

        arquivo.write("out.println(\"                   <p class=\\\"separator\\\"/>\");\n");
        arquivo.write("out.println(\"                   <p class=\\\"in_line_right\\\">\");\n");
        arquivo.write("out.println(\"                       <input id=\\\"input_limpar\\\" class=\\\"button_down\\\" type=\\\"reset\\\" value=\\\" Limpar\\\" />\");\n");
        arquivo.write("out.println(\"                       <input id=\\\"input_salvar\\\" class=\\\"button_down\\\" type=\\\"submit\\\" value=\\\" Salvar\\\" />\");\n");
        arquivo.write("out.println(\"                   </p>\");\n");
        arquivo.write("out.println(\"               </div>\");\n");
        arquivo.write("out.println(\"           </form>\");\n");
        arquivo.write("out.println(\"       </div>\");\n");
        arquivo.write("out.println(\"\");\n");
        arquivo.write("out.println(\"   </div>\");\n");
    }

    private static void montarScroll() throws IOException {
        arquivo.write("out.println(\"</div>\");\n");
        arquivo.write("out.println(\"<div id=\\\"scroll\\\">\");\n");
        arquivo.write("out.println(\"	<a title=\\\"Mover para cima\\\" class=\\\"top\\\" href=\\\"#\\\"><img src=\\\"images/up.png\\\" alt=\\\"top\\\" /></a>\");\n");
        arquivo.write("out.println(\"</div>\");\n");
    }

    private static void montarFooter() throws IOException {
        arquivo.write("out.println(\"<footer>\");\n");
        arquivo.write("out.println(\"   <p>\");\n");
        arquivo.write("out.println(\"		<a href=\\\"index.html\\\">Inicio</a> | \");\n");
        arquivo.write("out.println(\"		<a href=\\\"#\\\">Ajuda</a>\");\n");
        arquivo.write("out.println(\"	</p>\");\n");
        arquivo.write("out.println(\"	<p>Copyright &copy; Saibel, Sergio Luis | 2017</p>\");\n");
        arquivo.write("out.println(\"</footer>\");\n");
    }

    private static void montarDoGet() throws IOException {
        String campoId = objeto.getObjetoDAO().getCampoID();
        String montaCampoID = "";
        String c = "";
        for(int i = 2; i < campoId.length(); i++){
            if(campoId.charAt(i) != '_') {
                montaCampoID += String.valueOf(campoId.charAt(i)); 
            } else {
                montaCampoID += String.valueOf(campoId.charAt(i+1)).toUpperCase();
                i++;
            }
        }

        arquivo.write("/**\n");
        arquivo.write(" * Handles the HTTP <code>GET</code> method.\n");
        arquivo.write(" *\n");
        arquivo.write(" * @param request servlet request\n");
        arquivo.write(" * @param response servlet response\n");
        arquivo.write(" * @throws ServletException if a servlet-specific error occurs\n");
        arquivo.write(" * @throws IOException if an I/O error occurs\n");
        arquivo.write(" */\n");
        arquivo.write("@Override\n");
        arquivo.write("protected void doGet(HttpServletRequest request, HttpServletResponse response)\n");
        arquivo.write("		throws ServletException, IOException {\n");
        arquivo.write("     String codigo = request.getParameter(\"codigo\");\n");
        arquivo.write("     String acao = request.getParameter(\"acao\");\n");
        arquivo.write("\n");
        arquivo.write("     if (acao.equals(\"incluir\")) {\n");
        arquivo.write("		if (acoes[Util.INCLUIR]) {\n");
        arquivo.write("             objeto = new " + nomeClasse + "();\n");
        arquivo.write("             objeto.setId" + montaCampoID + "(objeto.getObjetoDAO().getProximoID(\n");
        arquivo.write("             objeto.getObjetoDAO().getCampoID()));\n");
        arquivo.write("\n");
        arquivo.write("             processRequest(request, response);\n");
        arquivo.write("		}\n");
        arquivo.write("     } else {\n");
        arquivo.write("		if (acao.equals(\"alterar\")) {\n");
        arquivo.write("             if (acoes[Util.ALTERAR]) {\n");
        arquivo.write("			objeto = new " + nomeClasse + "();\n");
        arquivo.write("			objeto.removerWhere();\n");
        arquivo.write("			objeto.adicionarWhere(new DadosDAO(\n");
        arquivo.write("                 objeto.getObjetoDAO().getCampoID(), \"\", codigo,\n");
        arquivo.write("                     DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));\n");
        arquivo.write("\n");
        arquivo.write("			Object[] obj = objeto.getObjetoDAO().getDadosObjeto();\n");
        arquivo.write("			objeto = new " + nomeClasse + "(\n");

        // corrigir este trecho
        
        arquivo.write("			Long.parseUnsignedLong(obj[0].toString()),\n");
        arquivo.write("			Integer.parseUnsignedInt(obj[1].toString()),\n");
        arquivo.write("			obj[2].toString(), obj[3].toString());\n");
        
         // ate aqui ...corrigir este trecho
        
        arquivo.write("\n");
        arquivo.write("			processRequest(request, response);\n");
        arquivo.write("             }\n");
        arquivo.write("         } else {\n");
        arquivo.write("             if (acao.equals(\"excluir\")) {\n");
        arquivo.write("                 if (acoes[Util.EXCLUIR]) {\n");
        arquivo.write("                     objeto.getObjetoDAO().excluir(codigo);\n");
        arquivo.write("\n");
        arquivo.write("                     request.getRequestDispatcher(\"/view/view_" + nomeReferencia + ".jsp\").forward(request, response);\n");
        arquivo.write("                 }\n");
        arquivo.write("             }\n");
        arquivo.write("         }\n");
        arquivo.write("     }\n");
        arquivo.write(" }\n");
    }

}
