<%-- 
    Document   : view_profissionais.jsp
    Created on : "Sexta-feira, 30 de Junho de 2017"
    Author     : "Sergio"
    Path:      : com.samho.necocio.Profissionais
--%>


<%@page import="com.samho.necocio.Objeto"%>
<%@page import="com.samho.necocio.Profissionais"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div id="main">
            <div id="site_content">
                <div class="form_settings"> 
                    <div class="content">
                        <p class="in_line_right">
                            <input id="input_insert_end_pessoa_f" class="button_top" type="submit" value=" Incluir" formaction="cadastro_profissional.html"/>
                        </p>
                        <h1>Você está na área de Profissionais do SAMHO</h1>
                        <ul>
                            <li>
                                <%
                                    Objeto objeto = new Profissionais();
                                    objeto.getObjetoDAO().setCamposTabelaFormatados();
                                    Object[] cabecalho = objeto.getObjetoDAO().getCabecalhoTabela();
                                    Object[][] dados = objeto.getObjetoDAO().getDadosTabela();
                                %>
                                <p>
                                    <ul>
                                        <%
                                            int col = 0;
                                            int lin = 0;
                                            for (lin = 0; lin < dados.length; lin++) {
                                        %>
                                        <span class="left"><img src="<%=request.getContextPath()%>/images/foto.png" alt="example graphic" /></span>                                        
                                        <%
                                            for (col = 0; col < dados[lin].length; col++) {
                                        %>
                                        <li><%= cabecalho[col].toString() + ": " + dados[lin][col].toString()%></li>
                                            <%
                                                }
                                            %>
                                        <h1>Especializações</h1>
                                        <p>Doença virais, clinico geral</p>
                                        <p class="separator"/>
                                        <%
                                            }
                                        %>
                                    </ul>
                                </p>
                            </li>
                        </ul>
                    </div>

                    <p class="in_line_right">
                        <input id="input_ok" class="button_down" type="submit" value=" Ok" formaction="javascript:window.history.go(-1)"/>
                    </p>
                </div>
            </div>
            <div id="scroll">
                <a title="Mover para cima" class="top" href="#"><img src="images/up.png" alt="top" /></a>
            </div>
            <footer>
                <p>
                    <a href="index.html">Inicio</a> | 
                    <a href="contato.html">Contatos</a>
                </p>
                <p>Copyright &copy; Saibel, Sergio Luis | 2017</p>
            </footer>
        </div>
    </body>
</html>