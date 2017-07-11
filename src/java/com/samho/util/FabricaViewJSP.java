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
public class FabricaViewJSP {

    private static File diretorio;
    private static OutputStreamWriter arquivo;
    private static InterfaceObj objeto;
    private static String nomeClasse;
    private static String descricaoClasse;
    private static String nomeReferencia;
    private static String encoding;

    private static void inicializar() throws IOException {
        diretorio = new File(new File(".").getCanonicalPath());
        diretorio.mkdirs();

        nomeClasse = FabricaViewJSP.objeto.getClass().getSimpleName();
        nomeReferencia = objeto.getObjetoDAO().getTabela().toLowerCase();
        try {
            arquivo = new OutputStreamWriter(new FileOutputStream(
                    diretorio + "/web/view/view_" + nomeReferencia + ".jsp"), encoding);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(FabricaViewJSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void criarJSP(InterfaceObj objeto, String descricaoClasse,
            String encoding) throws IOException {
        FabricaViewJSP.objeto = objeto;
        FabricaViewJSP.descricaoClasse = descricaoClasse;
        FabricaViewJSP.encoding = encoding;

        inicializar();
        try {
            montarPaginaJSP();

            arquivo.close();
        } catch (IOException ex) {
            Logger.getLogger(FabricaViewJSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void montarPaginaJSP() throws IOException {
        montarInfoClasse();
        montarImport();

        arquivo.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");

        montarHead("SAMHO - Sistema de Agendamento Médico Hospitalar");
        montarBody();

        arquivo.write("</html>\n");
    }

    private static void montarInfoClasse() throws IOException {
        arquivo.write("<%-- \n");
        arquivo.write("    Document   : view_" + nomeReferencia + ".jsp\n");
        arquivo.write("    Created on : \"" + Formatacao.getDataAtual(Formatacao.COMPLETA) + "\"\n");
        arquivo.write("    Author     : \"" + System.getProperty("user.name") + "\"\n");
        arquivo.write("    Path:      : " + FabricaViewJSP.objeto.getClass().getCanonicalName() + "\n");
        arquivo.write("--%>\n\n\n");
    }

    private static void montarImport() throws IOException {
        arquivo.write("<%@page import=\"com.samho.necocio.Objeto\"%>\n");
        arquivo.write("<%@page import=\"" + objeto.getClass().getName() + "\"%>\n");
        arquivo.write("<%@page contentType=\"text/html\" pageEncoding=\"" + encoding + "\"%>\n");
        arquivo.write("<?xml version=\"1.0\" pageEncoding=\"" + encoding + "\"?>\n");
        arquivo.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n\n");
    }

    private static void montarBody() throws IOException {
        arquivo.write("<body>\n");
        arquivo.write(" <div id=\"main\">\n");

        montarHeader();
        montarSiteContent();
        montarScroll();
        montarFooter();

        arquivo.write(" </div>\n");
        arquivo.write("</body>\n");
    }

    private static void montarHead(String title) throws IOException {
        arquivo.write(" <head>\n");
        arquivo.write("     <meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + encoding + "\"/>\n");
        arquivo.write("     <title>" + title + "</title>\n");
        arquivo.write("\n");
        arquivo.write("     <!-- Estilos da página -->\n");
        arquivo.write("     <link rel=\"stylesheet\" type=\"text/css\" href=\"<%=request.getContextPath()%>/css/style.css\" />\n");
        arquivo.write("     <!-- Fonte de icones -->\n");
        arquivo.write("     <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
        arquivo.write("\n");
        arquivo.write("     <script>\n");
        arquivo.write("         // Função javascript para incluir um registro\n");
        arquivo.write("         function incluirRegistro() {\n");
        arquivo.write("             var acao = \"incluir\";\n");
        arquivo.write("             window.location.href = \"<%=request.getContextPath()%>/" + nomeReferencia + "?acao=\" + acao;\n");
        arquivo.write("         }\n");
        arquivo.write("\n");
        arquivo.write("         // Função javascript para chamar edição de registro\n");
        arquivo.write("         function alterarRegistro(codigo) {\n");
        arquivo.write("             var acao = \"alterar\";\n");
        arquivo.write("             window.location.href = \"<%=request.getContextPath()%>/" + nomeReferencia + "?codigo=\" + codigo + \"&acao=\" + acao;\n");
        arquivo.write("         }\n");
        arquivo.write("\n");
        arquivo.write("         // Função javascript para chamar Srvlet de exclusão de registro\n");
        arquivo.write("         function excluirRegistro(codigo) {\n");
        arquivo.write("             if (confirm(\'Confirma exclusão do objeto do tipo \"" + descricaoClasse + "\" \' + codigo + \'?\')) {\n");
        arquivo.write("                 var acao = \"excluir\";\n");
        arquivo.write("                 window.location.href = \"<%=request.getContextPath()%>/" + nomeReferencia + "?codigo=\" + codigo + \"&acao=\" + acao;\n");
        arquivo.write("             } else {\n");
        arquivo.write("                 alert(\'Exclusão cancelada.\');\n");
        arquivo.write("             }\n");
        arquivo.write("         }\n");
        arquivo.write("     </script> \n");
        arquivo.write(" </head>\n");
    }

    private static void montarHeader() throws IOException {
        arquivo.write(" <header>\n");
        arquivo.write("     <div id=\"logo\">\n");
        arquivo.write("         <div id=\"logo_text\">\n");
        arquivo.write("             <!-- class=\"logo_colour\", allows you to change the colour of the text -->\n");
        arquivo.write("             <h1><a href=\"<%=request.getContextPath()%>/index.html\"><span class=\"logo_colour\">SAMHO</span></a></h1>\n");
        arquivo.write("             <h2>Sistema de Agendamento Médico Hospitalar</h2>\n");
        arquivo.write("         </div>\n");
        arquivo.write("     </div>\n");
        arquivo.write("     <nav>\n");
        arquivo.write("         <div id=\"menu_container\">\n");
        arquivo.write("             <ul class=\"sf-menu\" id=\"nav\">\n");
        arquivo.write("                 <li><a href=\"<%=request.getContextPath()%>/index.html\">Inicio</a></li>\n");
        arquivo.write("                 <li><a href=\"#\">Ajuda</a></li>\n");
        arquivo.write("             </ul>\n");
        arquivo.write("         </div>\n");
        arquivo.write("     </nav>\n");
        arquivo.write(" </header>\n");
    }

    private static void montarSiteContent() throws IOException {
        arquivo.write(" <div id=\"site_content\">\n");
        arquivo.write("     <!--\n");
        arquivo.write("         <div id=\"sidebar_container\">\n");
        arquivo.write("         <div class=\"sidebar\">\n");
        arquivo.write("         </div>\n");
        arquivo.write("         </div>\n");
        arquivo.write("         out.print(\"<h1>Alteração OK</h1>Registro alterado com Sucesso.\n");
        arquivo.write("     -->\n");
        arquivo.write("     <div>\n");
        arquivo.write("         <!-- Formulário de cadcastro -->\n");
        arquivo.write("         <h2>Consulta de \"" + descricaoClasse + "\"</h2>\n");
        arquivo.write("         <form action=\"#\" method=\"post\">\n");
        arquivo.write("             <div id=\"form_settings\" class=\"form_settings\">\n");
        arquivo.write("                 <%\n");
        arquivo.write("                 Objeto objeto = new " + nomeClasse + "();\n");
        arquivo.write("                 objeto.getObjetoDAO().setCamposTabelaFormatados();\n");
        arquivo.write("                 Object[] cabecalho = objeto.getObjetoDAO().getCabecalhoTabela();\n");
        arquivo.write("                 Object[][] dados = objeto.getObjetoDAO().getDadosTabela();\n");
        arquivo.write("                 %>\n");
        arquivo.write("                 <table>\n");
        arquivo.write("                     <thead>\n");
        arquivo.write("                         <tr>\n");
        arquivo.write("                             <th width=\"100\"><%= cabecalho[0].toString()%></th>\n");
        arquivo.write("                             <%\n");
        arquivo.write("                             for (int i = 1; i < cabecalho.length; i++) {\n");
        arquivo.write("                                 String nomeCabecalho = cabecalho[i].toString();\n");
        arquivo.write("                                 if ((nomeCabecalho.indexOf(\'.\') > 0)\n");
        arquivo.write("                                     && (nomeCabecalho.substring(0, nomeCabecalho.indexOf(\'.\')).equals(\"Cód\"))) {\n");
        arquivo.write("                             %>\n");
        arquivo.write("                             <th width=\"0\"></th>\n");
        arquivo.write("                             <%\n");
        arquivo.write("                                 } else {\n");
        arquivo.write("                             %>\n");
        arquivo.write("                             <th><%= nomeCabecalho%></th>\n");
        arquivo.write("                             <%\n");
        arquivo.write("                                 }\n");
        arquivo.write("                             }\n");
        arquivo.write("                             %>\n");
        arquivo.write("                             <th></th>\n");
        arquivo.write("                             <th><input type=\"button\" class=\"button_top\" value=\"Incluir\" name=\"incluir\" onclick=\"incluirRegistro();\"/></th>\n");
        arquivo.write("                         </tr>\n");
        arquivo.write("                     </thead>\n");
        arquivo.write("                     <tbody>\n");
        arquivo.write("                         <!-- Cria nova linha na tabela -->\n");
        arquivo.write("                         <%\n");
        arquivo.write("                         int col = 0;\n");
        arquivo.write("                         int lin = 0;\n");
        arquivo.write("                         for (lin = 0; lin < dados.length; lin++) {\n");
        arquivo.write("                         %>\n");
        arquivo.write("                         <tr>\n");
        arquivo.write("                             <%\n");
        arquivo.write("                             for (col = 0; col < dados[lin].length; col++) {\n");
        arquivo.write("                             %>\n");
        arquivo.write("                             <td><%= dados[lin][col] == null ? \"-\" : dados[lin][col].toString()%></td>\n");
        arquivo.write("                             <%\n");
        arquivo.write("                             }\n");
        arquivo.write("                             %>\n");
        arquivo.write("                             <td class=\"button\"> <input type=\"button\" class=\"button_top\" value=\"Alterar\" name=\"alterar\" onclick=\"alterarRegistro(<%= Integer.parseUnsignedInt(dados[lin][0].toString())%>);\"/> </td>\n");
        arquivo.write("                             <td class=\"button\"> <input type=\"button\" class=\"button_top\" value=\"Excluir\" name=\"excluir\" onclick=\"excluirRegistro(<%= Integer.parseUnsignedInt(dados[lin][0].toString())%>);\"/> </td>\n");
        arquivo.write("                         </tr>\n");
        arquivo.write("                         <%\n");
        arquivo.write("                         }\n");
        arquivo.write("                         %>\n");
        arquivo.write("\n");
        arquivo.write("                     </tbody>\n");
        arquivo.write("                 </table>\n");
        arquivo.write("             </div>\n");
        arquivo.write("         </form>\n");
        arquivo.write("     </div>\n");
        arquivo.write(" </div>\n");
    }

    private static void montarScroll() throws IOException {
        arquivo.write(" <div id=\"scroll\">\n");
        arquivo.write("     <a title=\"Mover para cima\" class=\"top\" href=\"#\"><img src=\"<%=request.getContextPath()%>/images/up.png\" alt=\"top\" /></a>\n");
        arquivo.write(" </div>\n");
    }

    private static void montarFooter() throws IOException {
        arquivo.write(" <footer>\n");
        arquivo.write("     <p>\n");
        arquivo.write("         <a href=\"<%=request.getContextPath()%>/index.html\">Inicio</a> | \n");
        arquivo.write("         <a href=\"#\">Ajuda</a>\n");
        arquivo.write("     </p>\n");
        arquivo.write("     <p>Copyright &copy; Saibel, Sergio Luis | 2017</p>\n");
        arquivo.write(" </footer>\n");
    }

}
