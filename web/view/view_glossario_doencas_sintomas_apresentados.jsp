<%-- 
    Document   : view_glossario_doencas_sintomas_apresentados.jsp
    Created on : "Sexta-feira, 30 de Junho de 2017"
    Author     : "Sergio"
    Path:      : com.samho.necocio.GlossarioDoencasSintomasApresentados
--%>


<%@page import="com.samho.necocio.Objeto"%>
<%@page import="com.samho.necocio.GlossarioDoencasSintomasApresentados"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<?xml version="1.0" pageEncoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
     <title>SAMHO - Sistema de Agendamento M�dico Hospitalar</title>

     <!-- Estilos da p�gina -->
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
     <!-- Fonte de icones -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

     <script>
         // Fun��o javascript para incluir um registro
         function incluirRegistro() {
             var acao = "incluir";
             window.location.href = "<%=request.getContextPath()%>/glossario_doencas_sintomas_apresentados?acao=" + acao;
         }

         // Fun��o javascript para chamar edi��o de registro
         function alterarRegistro(codigo) {
             var acao = "alterar";
             window.location.href = "<%=request.getContextPath()%>/glossario_doencas_sintomas_apresentados?codigo=" + codigo + "&acao=" + acao;
         }

         // Fun��o javascript para chamar Srvlet de exclus�o de registro
         function excluirRegistro(codigo) {
             if (confirm('Confirma exclus�o do objeto do tipo "Gloss�rio - Sintoma" ' + codigo + '?')) {
                 var acao = "excluir";
                 window.location.href = "<%=request.getContextPath()%>/glossario_doencas_sintomas_apresentados?codigo=" + codigo + "&acao=" + acao;
             } else {
                 alert('Exclus�o cancelada.');
             }
         }
     </script> 
 </head>
<body>
 <div id="main">
 <header>
     <div id="logo">
         <div id="logo_text">
             <!-- class="logo_colour", allows you to change the colour of the text -->
             <h1><a href="<%=request.getContextPath()%>/index.html"><span class="logo_colour">SAMHO</span></a></h1>
             <h2>Sistema de Agendamento M�dico Hospitalar</h2>
         </div>
     </div>
     <nav>
         <div id="menu_container">
             <ul class="sf-menu" id="nav">
                 <li><a href="<%=request.getContextPath()%>/index.html">Inicio</a></li>
                 <li><a href="#">Ajuda</a></li>
             </ul>
         </div>
     </nav>
 </header>
 <div id="site_content">
     <!--
         <div id="sidebar_container">
         <div class="sidebar">
         </div>
         </div>
         out.print("<h1>Altera��o OK</h1>Registro alterado com Sucesso.
     -->
     <div>
         <!-- Formul�rio de cadcastro -->
         <h2>Consulta de "Gloss�rio - Sintoma"</h2>
         <form action="#" method="post">
             <div class="form_settings">
                 <%
                 Objeto objeto = new GlossarioDoencasSintomasApresentados();
                 objeto.getObjetoDAO().setCamposTabelaFormatados();
                 Object[] cabecalho = objeto.getObjetoDAO().getCabecalhoTabela();
                 Object[][] dados = objeto.getObjetoDAO().getDadosTabela();
                 %>
                 <table>
                     <thead>
                         <tr>
                             <th width="100"><%= cabecalho[0].toString()%></th>
                             <%
                             for (int i = 1; i < cabecalho.length; i++) {
                                 String nomeCabecalho = cabecalho[i].toString();
                                 if ((nomeCabecalho.indexOf('.') > 0)
                                     && (nomeCabecalho.substring(0, nomeCabecalho.indexOf('.')).equals("C�d"))) {
                             %>
                             <th width="0"></th>
                             <%
                                 } else {
                             %>
                             <th><%= nomeCabecalho%></th>
                             <%
                                 }
                             }
                             %>
                             <th></th>
                             <th><input type="button" class="button_top" value="Incluir" name="incluir" onclick="incluirRegistro();"/></th>
                         </tr>
                     </thead>
                     <tbody>
                         <!-- Cria nova linha na tabela -->
                         <%
                         int col = 0;
                         int lin = 0;
                         for (lin = 0; lin < dados.length; lin++) {
                         %>
                         <tr>
                             <%
                             for (col = 0; col < dados[lin].length; col++) {
                             %>
                             <td><%= dados[lin][col] == null ? "-" : dados[lin][col].toString()%></td>
                             <%
                             }
                             %>
                             <td class="button"> <input type="button" class="button_top" value="Alterar" name="alterar" onclick="alterarRegistro(<%= Integer.parseUnsignedInt(dados[lin][0].toString())%>);"/> </td>
                             <td class="button"> <input type="button" class="button_top" value="Excluir" name="excluir" onclick="excluirRegistro(<%= Integer.parseUnsignedInt(dados[lin][0].toString())%>);"/> </td>
                         </tr>
                         <%
                         }
                         %>

                     </tbody>
                 </table>
             </div>
         </form>
     </div>
 </div>
 <div id="scroll">
     <a title="Mover para cima" class="top" href="#"><img src="<%=request.getContextPath()%>/images/up.png" alt="top" /></a>
 </div>
 <footer>
     <p>
         <a href="<%=request.getContextPath()%>/index.html">Inicio</a> | 
         <a href="#">Ajuda</a>
     </p>
     <p>Copyright &copy; Saibel, Sergio Luis | 2017</p>
 </footer>
 </div>
</body>
</html>
