<%-- 
    Document   : view_usuarios.jsp
    Created on : "Terça-feira, 11 de Julho de 2017"
    Author     : "Sergio"
    Path:      : com.samho.necocio.Usuarios
--%>


<%@page import="com.samho.necocio.Objeto"%>
<%@page import="com.samho.necocio.Usuarios"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<?xml version="1.0" pageEncoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
     <title>SAMHO - Sistema de Agendamento Médico Hospitalar</title>

     <!-- Estilos da página -->
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
     <!-- Fonte de icones -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
     <script language="javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
     <script>
         // Função javascript para incluir um registro
         function incluirRegistro() {
             var acao = "incluir";
             window.location.href = "<%=request.getContextPath()%>/usuarios?acao=" + acao;
         }

         // Função javascript para chamar edição de registro
         function alterarRegistro(codigo) {
             var acao = "alterar";
             window.location.href = "<%=request.getContextPath()%>/usuarios?codigo=" + codigo + "&acao=" + acao;
         }

         // Função javascript para chamar Srvlet de exclusão de registro
         function excluirRegistro(codigo) {
             if (confirm('Confirma exclusão do objeto do tipo "Usuário" ' + codigo + '?')) {
                 var acao = "excluir";
                 window.location.href = "<%=request.getContextPath()%>/usuarios?codigo=" + codigo + "&acao=" + acao;
             } else {
                 alert('Exclusão cancelada.');
             }
         }
     </script> 
     <!-- Exibir ID -->
     <script type="text/javascript">
         $(document).ready(function () {
             $("#ocultar").click(function (event) {
                 event.preventDefault();
                 $("#pesquisa").hide("slow");
             });
             $("#mostrar").click(function (event) {
                 event.preventDefault();
                 $("#pesquisa").show(900);
             });
         });
     </script><!-- Exibie ID --> </head>
<body>
 <div id="main">
 <header>
     <div id="logo">
         <div id="logo_text">
             <!-- class="logo_colour", allows you to change the colour of the text -->
             <h1><a href="<%=request.getContextPath()%>/index.html"><span class="logo_colour">SAMHO</span></a></h1>
             <h2>Sistema de Agendamento Médico Hospitalar</h2>
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
         out.print("<h1>Alteração OK</h1>Registro alterado com Sucesso.
     -->
     <div>
         <!-- Formulário de cadcastro -->
         <h2>Consulta de "Usuário"  <a href="#" id="ocultar"> << </a> | <a href="#" id="mostrar"> >> </a></h2>
         <form action="#" method="get">
             <div id="form_settings" class="form_settings">
                 <div id="pesquisa">
                     <p class="in_line">
                         <span class="required" for="input_codigo">Código</span>
                         <input id="input_codigo" class="en_50" data-type="search"></input>
                     </p>
                     <p class="in_line">
                         <span class="required" for="input_descricao">Descrição</span>
                         <input id="input_descricao" class="en_450" data-type="search"></input>
                     </p>
                 </div>
                 <%
                 Objeto objeto = new Usuarios();
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
                                     && (nomeCabecalho.substring(0, nomeCabecalho.indexOf('.')).equals("Cód"))) {
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
