<%-- 
    Document   : home
    Created on : 26/06/2017, 19:27:36
    Author     : Sergio
--%>

<%@page import="com.samho.dao.ConexoesDB"%>
<%@page import="com.samho.util.Registrador"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.samho.necocio.Profissionais"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.samho.necocio.AcoesUsuarios"%>
<%@page import="com.samho.dao.LoginDAO"%>
<%@page import="com.samho.necocio.Principal"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <!-- Refresh na página a cada "content=n; url=principal.jsp" (n=segundos url=página de refresh)-->
        <meta http-equiv="refresh" content="600"/> 

        <title>SAMHO - Sistema de Agendamento Médico Hospitalar</title>

        <!-- Estilos da página -->
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!-- Agenda -->
        <script src="js/js_lembrete.js"></script>
        <!-- Menu dropdown -->
        <script src="js/jquery-2.2.4.js"></script>
        <script src="js/jquery.easing-sooper.js"></script>
        <script src="js/jquery.sooperfish.js"></script>
        <!-- Datepicker -->
        <script src="js/jquery-ui.js"></script>

        <script type = "text/javascript">
            function carregar(divsao, pagina) {
                $("#" + divsao).load(pagina);
            }
        </script>

        <!-- Menu dropdown -->
        <script type="text/javascript">
            $(document).ready(function () {
                $('ul.sf-menu').sooperfish();
                $('.top').click(function (e) {
                    $('html, body').animate({scrollTop: 0}, 'fast');
                    return false;
                });
            });
        </script><!-- Menu dropdown -->

        <!-- Exibe ID -->
        <script>
            function exibe(id) {
                if (document.getElementById(id).style.display === "none") {
                    document.getElementById(id).style.display = "inline";
                }
            }
        </script><!-- Exibe ID -->
        <!-- Exibir ID -->
        <script type="text/javascript">
            $(document).ready(function (id) {
                $("#ocultar").click(function (event) {
                    event.preventDefault();
                    $("#" + id).hide("slow");
                });
                $("#mostrar").click(function (event) {
                    event.preventDefault();
                    $("#" + id).show(3000);
                });
            });
        </script><!-- Exibie ID -->
        <!--
            <p>
                <a href="#" id="ocultar"> << </a> | 
                <a href="#" id="mostrar"> >> </a>  
            </p>
        -->

        <script>
            // Função javascript para incluir um registro
            function terminarSessao() {
                window.location.href = "<%=request.getContextPath()%>/finalizar_sessao.jsp";
            }
        </script>
    </head>
    <body>
        <%
            if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
                String usuario = request.getParameter("name");
                String senha = request.getParameter("pass");

                if (LoginDAO.checkLogin(usuario, senha)) {
                    session.setAttribute("login", Principal.usuario.getIdUsuario() + " - " + Principal.usuario.getLogin());
                } else {
                    session.setAttribute("login", null);
                }
            }
        %>
        <div id="main">
            <header>
                <div id="logo">
                    <div id="logo_text">
                        <!-- class="logo_colour", allows you to change the colour of the text -->
                        <h1><a href="index.html"><span class="logo_colour">SAMHO</span></a></h1>
                        <h2>Sistema de Agendamento Médico Hospitalar</h2>
                    </div>
                </div>
                <nav>
                    <div id="menu_container">
                        <ul class="sf-menu" id="nav">
                            <li><a href="index.html">Inicio</a></li>
                            <li id="mnuUser" style="display:none"><a href="#">Usuário</a>
                                <ul>
                                    <li id="mnuNewUser" style="display:none"><a href="#">Cadastros</a>
                                        <ul>
                                            <li id="mnuNewPessoasFisicasUser" style="display:none"><a href="pessoas_fisicas">Pessoa Física</a></li>
                                            <li id="mnuNewPessoaJuridicaUser" style="display:none"><a href="pessoas_juridicas">Pessoa Jurídica</a></li>
                                            <!-- <li id="mnuNewPessoasUser" style="display:none"><a href="pessoas">Pessoa</a></li> -->
                                            <li id="mnuNewEspecializacaoPessoaUser" style="display:none"><a href="pessoas_especializacoes">Pessoa - Especialização</a></li>
                                            <li id="mnuNewSituacaoPessoasUser" style="display:none"><a href="situacoes_pessoas">Pessoa - Situação</a></li>
                                            <li id="mnuNewEspecializacaoUser" style="display:none"><a href="especializacoes">Especialização</a></li>
                                            <li id="mnuNewFuncionarioUser" style="display:none"><a href="funcionarios">Funcionário</a></li>
                                            <li id="mnuNewSituacaoFuncionariosUser" style="display:none"><a href="situacoes_funcionarios">Funcionário - Situação</a></li>
                                            <li id="mnuNewFuncaoUser" style="display:none"><a href="funcoes">Função</a></li>
                                            <li id="mnuNewProfissionalUser" style="display:none"><a href="profissionais">Profissional</a></li>
                                            <li id="mnuNewProfissionalTurnosUser" style="display:none"><a href="profissionais_turnos">Profissional - Turnos</a></li>
                                            <li id="mnuNewClienteUser" style="display:none"><a href="clientes">Cliente</a></li>
                                            <li id="mnuNewSituacaoClienteUser" style="display:none"><a href="situacoes_clientes">Cliente - Situação</a></li>
                                            <li id="mnuNewUsuarioUser" style="display:none"><a href="usuarios">Usuário</a></li>
                                            <li id="mnuNewUsuarioAcoesUser" style="display:none"><a href="acoes_usuarios">Usuário - Ação</a></li>
                                            <li id="mnuNewAcaoUser" style="display:none"><a href="acoes">Ação</a></li>
                                            <li id="mnuNewEnderecoUser" style="display:none"><a href="enderecos">Endereço</a></li>
                                            <li id="mnuNewEnderecoTipoUser" style="display:none"><a href="tipos_enderecos">Endereço - Tipo</a></li>
                                            <li id="mnuNewBairroUser" style="display:none"><a href="bairros">Bairro</a></li>
                                            <li id="mnuNewCidadeUser" style="display:none"><a href="cidades">Cidade</a></li>
                                            <li id="mnuNewEstadoUser" style="display:none"><a href="estados">Estado</a></li>
                                            <li id="mnuNewPaisUser" style="display:none"><a href="paises">Pais</a></li>
                                            <li id="mnuNewTelefoneUser" style="display:none"><a href="telefones">Telefone</a></li>
                                            <li id="mnuNewTelefoneTipoUser" style="display:none"><a href="tipos_telefones">Telefone - Tipo</a></li>
                                            <li id="mnuNewTipoPlanoSaudeUser" style="display:none"><a href="planos_saude">Plano de Saúde</a></li>
                                            <li id="mnuNewTipoPlanoSaudeClienteUser" style="display:none"><a href="clientes_tipos_planos">Cliente - Plano de Saúde</a></li>
                                            <li id="mnuNewPlanoSaudeUser" style="display:none"><a href="tipos_planos">Plano de Saúde - Tipos</a></li>
                                            <li id="mnuNewProfissionalPlanoSaudeUser" style="display:none"><a href="profissionais_planos_saude">Profissional - Plano de Saúde</a></li>
                                            <li id="mnuNewProntuarioUser" style="display:none"><a href="prontuarios">Prontuário</a></li>
                                            <li id="mnuNewProntuarioSintomaApresentadoUser" style="display:none"><a href="prontuarios_sintomas_apresentados">Prontuário - Sintoma</a></li>
                                            <li id="mnuNewProntuarioMedicamentoUser" style="display:none"><a href="prontuarios_medicamentos">Prontuário - Medicamento</a></li>
                                            <li id="mnuNewSintomaApresentadoUser" style="display:none"><a href="sintomas_apresentados">Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaSintomaApresentadoUser" style="display:none"><a href="glossario_doencas_sintomas_apresentados">Glossário - Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaUser" style="display:none"><a href="glossario_doencas">Glossário</a></li>
                                            <li id="mnuNewMedicamentoUser" style="display:none"><a href="medicamentos">Medicamento</a></li>
                                            <li id="mnuNewGlossarioDoencaMedicamentoUser" style="display:none"><a href="glossario_doenca_medicamentos">Glossário - Medicamento</a></li>
                                            <li id="mnuNewMotivoAgendaUser" style="display:none"><a href="motivos_agendas">Agenda - Motivo</a></li>
                                            <li id="mnuNewAgendaUser" style="display:none"><a href="agendas">Agenda</a></li>
                                            <li id="mnuNewTipoExameUser" style="display:none"><a href="tipos_exames">Exame - Tipo</a></li>
                                            <li id="mnuNewExameUser" style="display:none"><a href="exames">Exame</a></li>
                                        </ul>
                                    </li>
                                    <li id="mnuViewUser" style="display:none"><a href="#">Visualizações</a>
                                        <ul>
                                            <li id="mnuViewPessoasFisicasUser" style="display:none"><a href="view/view_pessoas_fisicas.jsp">Pessoas Físicas</a></li>
                                            <li id="mnuViewPessoasJuridicasUser" style="display:none"><a href="view/view_pessoas_juridicas.jsp">Pessoas Jurídicas</a></li>
                                            <!-- <li id="mnuViewPessoasUser" style="display:none"><a href="view/view_pessoas.jsp">Pessoas</a></li> -->
                                            <li id="mnuViewEspecializacoesPessoasUser" style="display:none"><a href="view/view_pessoas_especializacoes.jsp">Pessoas - Especializações</a></li>
                                            <li id="mnuViewSituacoesPessoasUser" style="display:none"><a href="view/view_situacoes_pessoas.jsp">Pessoas - Situações</a></li>
                                            <li id="mnuViewEspecializacoesUser" style="display:none"><a href="view/view_especializacoes.jsp">Especializações</a></li>
                                            <li id="mnuViewFuncionariosUser" style="display:none"><a href="view/view_funcionarios.jsp">Funcionários</a></li>
                                            <li id="mnuViewSituacoesFuncionariosUser" style="display:none"><a href="view/view_situacoes_funcionarios.jsp">Funcionários - Situações</a></li>
                                            <li id="mnuViewFuncoesUser" style="display:none"><a href="view/view_funcoes.jsp">Funções</a></li>
                                            <li id="mnuViewProfissionaisUser" style="display:none"><a href="view/view_profissionais.jsp">Profissionais</a></li>
                                            <li id="mnuViewProfissionaisTurnosUser" style="display:none"><a href="view/view_profissionais_turnos.jsp">Profissionais - Turnos</a></li>
                                            <li id="mnuViewClientesUser" style="display:none"><a href="view/view_clientes.jsp">Clientes</a></li>
                                            <li id="mnuViewSituacoesClientesUser" style="display:none"><a href="view/view_situacoes_clientes.jsp">Clientes - Situações</a></li>
                                            <li id="mnuViewUsuariosUser" style="display:none"><a href="view/view_usuarios.jsp">Usuários</a></li>
                                            <li id="mnuViewUsuariosAcoesUser" style="display:none"><a href="view/view_acoes_usuarios.jsp">Usuários - Ações</a></li>
                                            <li id="mnuViewAcoesUser" style="display:none"><a href="view/view_acoes.jsp">Ações</a></li>
                                            <li id="mnuViewEnderecosUser" style="display:none"><a href="view/view_enderecos.jsp">Endereços</a></li>
                                            <li id="mnuViewEnderecosTiposUser" style="display:none"><a href="view/view_tipos_enderecos.jsp">Endereços - Tipos</a></li>
                                            <li id="mnuViewBairrosUser" style="display:none"><a href="view/view_bairros.jsp">Bairros</a></li>
                                            <li id="mnuViewCidadesUser" style="display:none"><a href="view/view_cidades.jsp">Cidades</a></li>
                                            <li id="mnuViewEstadosUser" style="display:none"><a href="view/view_estados.jsp">Estados</a></li>
                                            <li id="mnuViewPaisesUser" style="display:none"><a href="view/view_paises.jsp">Paises</a></li>
                                            <li id="mnuViewTelefonesUser" style="display:none"><a href="view/view_telefones.jsp">Telefones</a></li>
                                            <li id="mnuViewTelefonesTiposUser" style="display:none"><a href="view/view_tipos_telefones.jsp">Telefones - Tipos</a></li>
                                            <li id="mnuViewTiposPlanosSaudeUser" style="display:none"><a href="view/view_planos_saude.jsp">Planos de Saúde</a></li>
                                            <li id="mnuViewTiposPlanosSaudeClientesUser" style="display:none"><a href="view/view_clientes_tipos_planos.jsp">Clientes - Planos de Saúde</a></li>
                                            <li id="mnuViewPlanosSaudeUser" style="display:none"><a href="view/view_tipos_planos.jsp">Planos de Saúde - Tipos</a></li>
                                            <li id="mnuViewProfissionaisPlanosSaudeUser" style="display:none"><a href="view/view_profissionais_planos_saude.jsp">Profissionais - Planos de Saúde</a></li>
                                            <li id="mnuViewProntuariosUser" style="display:none"><a href="view/view_prontuarios.jsp">Prontuários</a></li>
                                            <li id="mnuViewProntuariosSintomasApresentadosUser" style="display:none"><a href="view/view_prontuarios_sintomas_apresentados.jsp">Prontuários - Sintomas</a></li>
                                            <li id="mnuViewProntuariosMedicamentosUser" style="display:none"><a href="view/view_prontuarios_medicamentos.jsp">Prontuários - Medicamentos</a></li>
                                            <li id="mnuViewSintomasApresentadosUser" style="display:none"><a href="view/view_sintomas_apresentados.jsp">Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasSintomasApresentadosUser" style="display:none"><a href="view/view_glossario_doencas_sintomas_apresentados.jsp">Glossário - Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasUser" style="display:none"><a href="view/view_glossario_doencas.jsp">Glossário</a></li>
                                            <li id="mnuViewMedicamentosUser" style="display:none"><a href="view/view_medicamentos.jsp">Medicamentos</a></li>
                                            <li id="mnuViewGlossarioDoencasMedicamentosUser" style="display:none"><a href="view/view_glossario_doenca_medicamentos.jsp">Glossário - Medicamentos</a></li>
                                            <li id="mnuViewMotivosAgendasUser" style="display:none"><a href="view/view_motivos_agendas.jsp">Agendas - Motivos</a></li>
                                            <li id="mnuViewAgendasUser" style="display:none"><a href="view/view_agendas.jsp">Agendas</a></li>
                                            <li id="mnuViewTiposExamesUser" style="display:none"><a href="view/view_tipos_exames.jsp">Exames - Tipos</a></li>
                                            <li id="mnuViewExamesUser" style="display:none"><a href="view/view_exames.jsp">Exames</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li id="mnuOper" style="display:none"><a href="#">Operacional</a>
                                <ul>
                                    <li id="mnuNewOper" style="display:none"><a href="#">Cadastros</a>
                                        <ul>
                                            <li id="mnuNewPessoasFisicasOper" style="display:none"><a href="pessoas_fisicas">Pessoa Física</a></li>
                                            <li id="mnuNewPessoaJuridicaOper" style="display:none"><a href="pessoas_juridicas">Pessoa Jurídica</a></li>
                                            <!-- <li id="mnuNewPessoasOper" style="display:none"><a href="pessoas">Pessoa</a></li> -->
                                            <li id="mnuNewEspecializacaoPessoaOper" style="display:none"><a href="pessoas_especializacoes">Pessoa - Especialização</a></li>
                                            <li id="mnuNewSituacaoPessoasOper" style="display:none"><a href="situacoes_pessoas">Pessoa - Situação</a></li>
                                            <li id="mnuNewEspecializacaoOper" style="display:none"><a href="especializacoes">Especialização</a></li>
                                            <li id="mnuNewFuncionarioOper" style="display:none"><a href="funcionarios">Funcionário</a></li>
                                            <li id="mnuNewSituacaoFuncionariosOper" style="display:none"><a href="situacoes_funcionarios">Funcionário - Situação</a></li>
                                            <li id="mnuNewFuncaoOper" style="display:none"><a href="funcoes">Função</a></li>
                                            <li id="mnuNewProfissionalOper" style="display:none"><a href="profissionais">Profissional</a></li>
                                            <li id="mnuNewProfissionalTurnosOper" style="display:none"><a href="profissionais_turnos">Profissional - Turnos</a></li>
                                            <li id="mnuNewClienteOper" style="display:none"><a href="clientes">Cliente</a></li>
                                            <li id="mnuNewSituacaoClienteOper" style="display:none"><a href="situacoes_clientes">Cliente - Situação</a></li>
                                            <li id="mnuNewUsuarioOper" style="display:none"><a href="usuarios">Usuário</a></li>
                                            <li id="mnuNewUsuarioAcoesOper" style="display:none"><a href="acoes_usuarios">Usuário - Ação</a></li>
                                            <li id="mnuNewAcaoOper" style="display:none"><a href="acoes">Ação</a></li>
                                            <li id="mnuNewEnderecoOper" style="display:none"><a href="enderecos">Endereço</a></li>
                                            <li id="mnuNewEnderecoTipoOper" style="display:none"><a href="tipos_enderecos">Endereço - Tipo</a></li>
                                            <li id="mnuNewBairroOper" style="display:none"><a href="bairros">Bairro</a></li>
                                            <li id="mnuNewCidadeOper" style="display:none"><a href="cidades">Cidade</a></li>
                                            <li id="mnuNewEstadoOper" style="display:none"><a href="estados">Estado</a></li>
                                            <li id="mnuNewPaisOper" style="display:none"><a href="paises">Pais</a></li>
                                            <li id="mnuNewTelefoneOper" style="display:none"><a href="telefones">Telefone</a></li>
                                            <li id="mnuNewTelefoneTipoOper" style="display:none"><a href="tipos_telefones">Telefone - Tipo</a></li>
                                            <li id="mnuNewTipoPlanoSaudeOper" style="display:none"><a href="planos_saude">Plano de Saúde</a></li>
                                            <li id="mnuNewTipoPlanoSaudeClienteOper" style="display:none"><a href="clientes_tipos_planos">Cliente - Plano de Saúde</a></li>
                                            <li id="mnuNewPlanoSaudeOper" style="display:none"><a href="tipos_planos">Plano de Saúde - Tipos</a></li>
                                            <li id="mnuNewProfissionalPlanoSaudeOper" style="display:none"><a href="profissionais_planos_saude">Profissional - Plano de Saúde</a></li>
                                            <li id="mnuNewProntuarioOper" style="display:none"><a href="prontuarios">Prontuário</a></li>
                                            <li id="mnuNewProntuarioSintomaApresentadoOper" style="display:none"><a href="prontuarios_sintomas_apresentados">Prontuário - Sintoma</a></li>
                                            <li id="mnuNewProntuarioMedicamentoOper" style="display:none"><a href="prontuarios_medicamentos">Prontuário - Medicamento</a></li>
                                            <li id="mnuNewSintomaApresentadoOper" style="display:none"><a href="sintomas_apresentados">Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaSintomaApresentadoOper" style="display:none"><a href="glossario_doencas_sintomas_apresentados">Glossário - Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaOper" style="display:none"><a href="glossario_doencas">Glossário</a></li>
                                            <li id="mnuNewMedicamentoOper" style="display:none"><a href="medicamentos">Medicamento</a></li>
                                            <li id="mnuNewGlossarioDoencaMedicamentoOper" style="display:none"><a href="glossario_doenca_medicamentos">Glossário - Medicamento</a></li>
                                            <li id="mnuNewMotivoAgendaOper" style="display:none"><a href="motivos_agendas">Agenda - Motivo</a></li>
                                            <li id="mnuNewAgendaOper" style="display:none"><a href="agendas">Agenda</a></li>
                                            <li id="mnuNewTipoExameOper" style="display:none"><a href="tipos_exames">Exame - Tipo</a></li>
                                            <li id="mnuNewExameOper" style="display:none"><a href="exames">Exame</a></li>
                                        </ul>
                                    </li>
                                    <li id="mnuViewOper" style="display:none"><a href="#">Visualizações</a>
                                        <ul>
                                            <li id="mnuViewPessoasFisicasOper" style="display:none"><a href="view/view_pessoas_fisicas.jsp">Pessoas Físicas</a></li>
                                            <li id="mnuViewPessoasJuridicasOper" style="display:none"><a href="view/view_pessoas_juridicas.jsp">Pessoas Jurídicas</a></li>
                                            <!-- <li id="mnuViewPessoasOper" style="display:none"><a href="view/view_pessoas.jsp">Pessoas</a></li> -->
                                            <li id="mnuViewEspecializacoesPessoasOper" style="display:none"><a href="view/view_pessoas_especializacoes.jsp">Pessoas - Especializações</a></li>
                                            <li id="mnuViewSituacoesPessoasOper" style="display:none"><a href="view/view_situacoes_pessoas.jsp">Pessoas - Situações</a></li>
                                            <li id="mnuViewEspecializacoesOper" style="display:none"><a href="view/view_especializacoes.jsp">Especializações</a></li>
                                            <li id="mnuViewFuncionariosOper" style="display:none"><a href="view/view_funcionarios.jsp">Funcionários</a></li>
                                            <li id="mnuViewSituacoesFuncionariosOper" style="display:none"><a href="view/view_situacoes_funcionarios.jsp">Funcionários - Situações</a></li>
                                            <li id="mnuViewFuncoesOper" style="display:none"><a href="view/view_funcoes.jsp">Funções</a></li>
                                            <li id="mnuViewProfissionaisOper" style="display:none"><a href="view/view_profissionais.jsp">Profissionais</a></li>
                                            <li id="mnuViewProfissionaisTurnosOper" style="display:none"><a href="view/view_profissionais_turnos.jsp">Profissionais - Turnos</a></li>
                                            <li id="mnuViewClientesOper" style="display:none"><a href="view/view_clientes.jsp">Clientes</a></li>
                                            <li id="mnuViewSituacoesClientesOper" style="display:none"><a href="view/view_situacoes_clientes.jsp">Clientes - Situações</a></li>
                                            <li id="mnuViewUsuariosOper" style="display:none"><a href="view/view_usuarios.jsp">Usuários</a></li>
                                            <li id="mnuViewUsuariosAcoesOper" style="display:none"><a href="view/view_acoes_usuarios.jsp">Usuários - Ações</a></li>
                                            <li id="mnuViewAcoesOper" style="display:none"><a href="view/view_acoes.jsp">Ações</a></li>
                                            <li id="mnuViewEnderecosOper" style="display:none"><a href="view/view_enderecos.jsp">Endereços</a></li>
                                            <li id="mnuViewEnderecosTiposOper" style="display:none"><a href="view/view_tipos_enderecos.jsp">Endereços - Tipos</a></li>
                                            <li id="mnuViewBairrosOper" style="display:none"><a href="view/view_bairros.jsp">Bairros</a></li>
                                            <li id="mnuViewCidadesOper" style="display:none"><a href="view/view_cidades.jsp">Cidades</a></li>
                                            <li id="mnuViewEstadosOper" style="display:none"><a href="view/view_estados.jsp">Estados</a></li>
                                            <li id="mnuViewPaisesOper" style="display:none"><a href="view/view_paises.jsp">Paises</a></li>
                                            <li id="mnuViewTelefonesOper" style="display:none"><a href="view/view_telefones.jsp">Telefones</a></li>
                                            <li id="mnuViewTelefonesTiposOper" style="display:none"><a href="view/view_tipos_telefones.jsp">Telefones - Tipos</a></li>
                                            <li id="mnuViewTiposPlanosSaudeOper" style="display:none"><a href="view/view_planos_saude.jsp">Planos de Saúde</a></li>
                                            <li id="mnuViewTiposPlanosSaudeClientesOper" style="display:none"><a href="view/view_clientes_tipos_planos.jsp">Clientes - Planos de Saúde</a></li>
                                            <li id="mnuViewPlanosSaudeOper" style="display:none"><a href="view/view_tipos_planos.jsp">Planos de Saúde - Tipos</a></li>
                                            <li id="mnuViewProfissionaisPlanosSaudeOper" style="display:none"><a href="view/view_profissionais_planos_saude.jsp">Profissionais - Planos de Saúde</a></li>
                                            <li id="mnuViewProntuariosOper" style="display:none"><a href="view/view_prontuarios.jsp">Prontuários</a></li>
                                            <li id="mnuViewProntuariosSintomasApresentadosOper" style="display:none"><a href="view/view_prontuarios_sintomas_apresentados.jsp">Prontuários - Sintomas</a></li>
                                            <li id="mnuViewProntuariosMedicamentosOper" style="display:none"><a href="view/view_prontuarios_medicamentos.jsp">Prontuários - Medicamentos</a></li>
                                            <li id="mnuViewSintomasApresentadosOper" style="display:none"><a href="view/view_sintomas_apresentados.jsp">Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasSintomasApresentadosOper" style="display:none"><a href="view/view_glossario_doencas_sintomas_apresentados.jsp">Glossário - Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasOper" style="display:none"><a href="view/view_glossario_doencas.jsp">Glossário</a></li>
                                            <li id="mnuViewMedicamentosOper" style="display:none"><a href="view/view_medicamentos.jsp">Medicamentos</a></li>
                                            <li id="mnuViewGlossarioDoencasMedicamentosOper" style="display:none"><a href="view/view_glossario_doenca_medicamentos.jsp">Glossário - Medicamentos</a></li>
                                            <li id="mnuViewMotivosAgendasOper" style="display:none"><a href="view/view_motivos_agendas.jsp">Agendas - Motivos</a></li>
                                            <li id="mnuViewAgendasOper" style="display:none"><a href="view/view_agendas.jsp">Agendas</a></li>
                                            <li id="mnuViewTiposExamesOper" style="display:none"><a href="view/view_tipos_exames.jsp">Exames - Tipos</a></li>
                                            <li id="mnuViewExamesOper" style="display:none"><a href="view/view_exames.jsp">Exames</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li id="mnuOperAdmin" style="display:none"><a href="#">Operacional - Admin</a>
                                <ul>
                                    <li id="mnuNewOperAdmin" style="display:none"><a href="#">Cadastros</a>
                                        <ul>
                                            <li id="mnuNewPessoasFisicasOperAdmin" style="display:none"><a href="pessoas_fisicas">Pessoa Física</a></li>
                                            <li id="mnuNewPessoaJuridicaOperAdmin" style="display:none"><a href="pessoas_juridicas">Pessoa Jurídica</a></li>
                                            <!-- <li id="mnuNewPessoasOperAdmin" style="display:none"><a href="pessoas">Pessoa</a></li> -->
                                            <li id="mnuNewEspecializacaoPessoaOperAdmin" style="display:none"><a href="pessoas_especializacoes">Pessoa - Especialização</a></li>
                                            <li id="mnuNewSituacaoPessoasOperAdmin" style="display:none"><a href="situacoes_pessoas">Pessoa - Situação</a></li>
                                            <li id="mnuNewEspecializacaoOperAdmin" style="display:none"><a href="especializacoes">Especialização</a></li>
                                            <li id="mnuNewFuncionarioOperAdmin" style="display:none"><a href="funcionarios">Funcionário</a></li>
                                            <li id="mnuNewSituacaoFuncionariosOperAdmin" style="display:none"><a href="situacoes_funcionarios">Funcionário - Situação</a></li>
                                            <li id="mnuNewFuncaoOperAdmin" style="display:none"><a href="funcoes">Função</a></li>
                                            <li id="mnuNewProfissionalOperAdmin" style="display:none"><a href="profissionais">Profissional</a></li>
                                            <li id="mnuNewProfissionalTurnosOperAdmin" style="display:none"><a href="profissionais_turnos">Profissional - Turnos</a></li>
                                            <li id="mnuNewClienteOperAdmin" style="display:none"><a href="clientes">Cliente</a></li>
                                            <li id="mnuNewSituacaoClienteOperAdmin" style="display:none"><a href="situacoes_clientes">Cliente - Situação</a></li>
                                            <li id="mnuNewUsuarioOperAdmin" style="display:none"><a href="usuarios">Usuário</a></li>
                                            <li id="mnuNewUsuarioAcoesOperAdmin" style="display:none"><a href="acoes_usuarios">Usuário - Ação</a></li>
                                            <li id="mnuNewAcaoOperAdmin" style="display:none"><a href="acoes">Ação</a></li>
                                            <li id="mnuNewEnderecoOperAdmin" style="display:none"><a href="enderecos">Endereço</a></li>
                                            <li id="mnuNewEnderecoTipoOperAdmin" style="display:none"><a href="tipos_enderecos">Endereço - Tipo</a></li>
                                            <li id="mnuNewBairroOperAdmin" style="display:none"><a href="bairros">Bairro</a></li>
                                            <li id="mnuNewCidadeOperAdmin" style="display:none"><a href="cidades">Cidade</a></li>
                                            <li id="mnuNewEstadoOperAdmin" style="display:none"><a href="estados">Estado</a></li>
                                            <li id="mnuNewPaisOperAdmin" style="display:none"><a href="paises">Pais</a></li>
                                            <li id="mnuNewTelefoneOperAdmin" style="display:none"><a href="telefones">Telefone</a></li>
                                            <li id="mnuNewTelefoneTipoOperAdmin" style="display:none"><a href="tipos_telefones">Telefone - Tipo</a></li>
                                            <li id="mnuNewTipoPlanoSaudeOperAdmin" style="display:none"><a href="planos_saude">Plano de Saúde</a></li>
                                            <li id="mnuNewTipoPlanoSaudeClienteOperAdmin" style="display:none"><a href="clientes_tipos_planos">Cliente - Plano de Saúde</a></li>
                                            <li id="mnuNewPlanoSaudeOperAdmin" style="display:none"><a href="tipos_planos">Plano de Saúde - Tipos</a></li>
                                            <li id="mnuNewProfissionalPlanoSaudeOperAdmin" style="display:none"><a href="profissionais_planos_saude">Profissional - Plano de Saúde</a></li>
                                            <li id="mnuNewProntuarioOperAdmin" style="display:none"><a href="prontuarios">Prontuário</a></li>
                                            <li id="mnuNewProntuarioSintomaApresentadoOperAdmin" style="display:none"><a href="prontuarios_sintomas_apresentados">Prontuário - Sintoma</a></li>
                                            <li id="mnuNewProntuarioMedicamentoOperAdmin" style="display:none"><a href="prontuarios_medicamentos">Prontuário - Medicamento</a></li>
                                            <li id="mnuNewSintomaApresentadoOperAdmin" style="display:none"><a href="sintomas_apresentados">Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaSintomaApresentadoOperAdmin" style="display:none"><a href="glossario_doencas_sintomas_apresentados">Glossário - Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaOperAdmin" style="display:none"><a href="glossario_doencas">Glossário</a></li>
                                            <li id="mnuNewMedicamentoOperAdmin" style="display:none"><a href="medicamentos">Medicamento</a></li>
                                            <li id="mnuNewGlossarioDoencaMedicamentoOperAdmin" style="display:none"><a href="glossario_doenca_medicamentos">Glossário - Medicamento</a></li>
                                            <li id="mnuNewMotivoAgendaOperAdmin" style="display:none"><a href="motivos_agendas">Agenda - Motivo</a></li>
                                            <li id="mnuNewAgendaOperAdmin" style="display:none"><a href="agendas">Agenda</a></li>
                                            <li id="mnuNewTipoExameOperAdmin" style="display:none"><a href="tipos_exames">Exame - Tipo</a></li>
                                            <li id="mnuNewExameOperAdmin" style="display:none"><a href="exames">Exame</a></li>
                                        </ul>
                                    </li>
                                    <li id="mnuViewOperAdmin" style="display:none"><a href="#">Visualizações</a>
                                        <ul>
                                            <li id="mnuViewPessoasFisicasOperAdmin" style="display:none"><a href="view/view_pessoas_fisicas.jsp">Pessoas Físicas</a></li>
                                            <li id="mnuViewPessoasJuridicasOperAdmin" style="display:none"><a href="view/view_pessoas_juridicas.jsp">Pessoas Jurídicas</a></li>
                                            <!-- <li id="mnuViewPessoasOperAdmin" style="display:none"><a href="view/view_pessoas.jsp">Pessoas</a></li> -->
                                            <li id="mnuViewEspecializacoesPessoasOperAdmin" style="display:none"><a href="view/view_pessoas_especializacoes.jsp">Pessoas - Especializações</a></li>
                                            <li id="mnuViewSituacoesPessoasOperAdmin" style="display:none"><a href="view/view_situacoes_pessoas.jsp">Pessoas - Situações</a></li>
                                            <li id="mnuViewEspecializacoesOperAdmin" style="display:none"><a href="view/view_especializacoes.jsp">Especializações</a></li>
                                            <li id="mnuViewFuncionariosOperAdmin" style="display:none"><a href="view/view_funcionarios.jsp">Funcionários</a></li>
                                            <li id="mnuViewSituacoesFuncionariosOperAdmin" style="display:none"><a href="view/view_situacoes_funcionarios.jsp">Funcionários - Situações</a></li>
                                            <li id="mnuViewFuncoesOperAdmin" style="display:none"><a href="view/view_funcoes.jsp">Funções</a></li>
                                            <li id="mnuViewProfissionaisOperAdmin" style="display:none"><a href="view/view_profissionais.jsp">Profissionais</a></li>
                                            <li id="mnuViewProfissionaisTurnosOperAdmin" style="display:none"><a href="view/view_profissionais_turnos.jsp">Profissionais - Turnos</a></li>
                                            <li id="mnuViewClientesOperAdmin" style="display:none"><a href="view/view_clientes.jsp">Clientes</a></li>
                                            <li id="mnuViewSituacoesClientesOperAdmin" style="display:none"><a href="view/view_situacoes_clientes.jsp">Clientes - Situações</a></li>
                                            <li id="mnuViewUsuariosOperAdmin" style="display:none"><a href="view/view_usuarios.jsp">Usuários</a></li>
                                            <li id="mnuViewUsuariosAcoesOperAdmin" style="display:none"><a href="view/view_acoes_usuarios.jsp">Usuários - Ações</a></li>
                                            <li id="mnuViewAcoesOperAdmin" style="display:none"><a href="view/view_acoes.jsp">Ações</a></li>
                                            <li id="mnuViewEnderecosOperAdmin" style="display:none"><a href="view/view_enderecos.jsp">Endereços</a></li>
                                            <li id="mnuViewEnderecosTiposOperAdmin" style="display:none"><a href="view/view_tipos_enderecos.jsp">Endereços - Tipos</a></li>
                                            <li id="mnuViewBairrosOperAdmin" style="display:none"><a href="view/view_bairros.jsp">Bairros</a></li>
                                            <li id="mnuViewCidadesOperAdmin" style="display:none"><a href="view/view_cidades.jsp">Cidades</a></li>
                                            <li id="mnuViewEstadosOperAdmin" style="display:none"><a href="view/view_estados.jsp">Estados</a></li>
                                            <li id="mnuViewPaisesOperAdmin" style="display:none"><a href="view/view_paises.jsp">Paises</a></li>
                                            <li id="mnuViewTelefonesOperAdmin" style="display:none"><a href="view/view_telefones.jsp">Telefones</a></li>
                                            <li id="mnuViewTelefonesTiposOperAdmin" style="display:none"><a href="view/view_tipos_telefones.jsp">Telefones - Tipos</a></li>
                                            <li id="mnuViewTiposPlanosSaudeOperAdmin" style="display:none"><a href="view/view_planos_saude.jsp">Planos de Saúde</a></li>
                                            <li id="mnuViewTiposPlanosSaudeClientesOperAdmin" style="display:none"><a href="view/view_clientes_tipos_planos.jsp">Clientes - Planos de Saúde</a></li>
                                            <li id="mnuViewPlanosSaudeOperAdmin" style="display:none"><a href="view/view_tipos_planos.jsp">Planos de Saúde - Tipos</a></li>
                                            <li id="mnuViewProfissionaisPlanosSaudeOperAdmin" style="display:none"><a href="view/view_profissionais_planos_saude.jsp">Profissionais - Planos de Saúde</a></li>
                                            <li id="mnuViewProntuariosOperAdmin" style="display:none"><a href="view/view_prontuarios.jsp">Prontuários</a></li>
                                            <li id="mnuViewProntuariosSintomasApresentadosOperAdmin" style="display:none"><a href="view/view_prontuarios_sintomas_apresentados.jsp">Prontuários - Sintomas</a></li>
                                            <li id="mnuViewProntuariosMedicamentosOperAdmin" style="display:none"><a href="view/view_prontuarios_medicamentos.jsp">Prontuários - Medicamentos</a></li>
                                            <li id="mnuViewSintomasApresentadosOperAdmin" style="display:none"><a href="view/view_sintomas_apresentados.jsp">Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasSintomasApresentadosOperAdmin" style="display:none"><a href="view/view_glossario_doencas_sintomas_apresentados.jsp">Glossário - Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasOperAdmin" style="display:none"><a href="view/view_glossario_doencas.jsp">Glossário</a></li>
                                            <li id="mnuViewMedicamentosOperAdmin" style="display:none"><a href="view/view_medicamentos.jsp">Medicamentos</a></li>
                                            <li id="mnuViewGlossarioDoencasMedicamentosOperAdmin" style="display:none"><a href="view/view_glossario_doenca_medicamentos.jsp">Glossário - Medicamentos</a></li>
                                            <li id="mnuViewMotivosAgendasOperAdmin" style="display:none"><a href="view/view_motivos_agendas.jsp">Agendas - Motivos</a></li>
                                            <li id="mnuViewAgendasOperAdmin" style="display:none"><a href="view/view_agendas.jsp">Agendas</a></li>
                                            <li id="mnuViewTiposExamesOperAdmin" style="display:none"><a href="view/view_tipos_exames.jsp">Exames - Tipos</a></li>
                                            <li id="mnuViewExamesOperAdmin" style="display:none"><a href="view/view_exames.jsp">Exames</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li id="mnuAdmin" style="display:none"><a href="#">Administração</a>
                                <ul>
                                    <li id="mnuNewAdmin" style="display:none"><a href="#">Cadastros</a>
                                        <ul>
                                            <li id="mnuNewPessoasFisicasAdmin" style="display:none"><a href="pessoas_fisicas">Pessoa Física</a></li>
                                            <li id="mnuNewPessoaJuridicaAdmin" style="display:none"><a href="pessoas_juridicas">Pessoa Jurídica</a></li>
                                            <li id="mnuNewPessoasAdmin" style="display:none"><a href="pessoas">Pessoa</a></li>
                                            <li id="mnuNewEspecializacaoPessoaAdmin" style="display:none"><a href="pessoas_especializacoes">Pessoa - Especialização</a></li>
                                            <li id="mnuNewSituacaoPessoasAdmin" style="display:none"><a href="situacoes_pessoas">Pessoa - Situação</a></li>
                                            <li id="mnuNewEspecializacaoAdmin" style="display:none"><a href="especializacoes">Especialização</a></li>
                                            <li id="mnuNewFuncionarioAdmin" style="display:none"><a href="funcionarios">Funcionário</a></li>
                                            <li id="mnuNewSituacaoFuncionariosAdmin" style="display:none"><a href="situacoes_funcionarios">Funcionário - Situação</a></li>
                                            <li id="mnuNewFuncaoAdmin" style="display:none"><a href="funcoes">Função</a></li>
                                            <li id="mnuNewProfissionalAdmin" style="display:none"><a href="profissionais">Profissional</a></li>
                                            <li id="mnuNewProfissionalTurnosAdmin" style="display:none"><a href="profissionais_turnos">Profissional - Turnos</a></li>
                                            <li id="mnuNewClienteAdmin" style="display:none"><a href="clientes">Cliente</a></li>
                                            <li id="mnuNewSituacaoClienteAdmin" style="display:none"><a href="situacoes_clientes">Cliente - Situação</a></li>
                                            <li id="mnuNewUsuarioAdmin" style="display:none"><a href="usuarios">Usuário</a></li>
                                            <li id="mnuNewUsuarioAcoesAdmin" style="display:none"><a href="acoes_usuarios">Usuário - Ação</a></li>
                                            <li id="mnuNewAcaoAdmin" style="display:none"><a href="acoes">Ação</a></li>
                                            <li id="mnuNewEnderecoAdmin" style="display:none"><a href="enderecos">Endereço</a></li>
                                            <li id="mnuNewEnderecoTipoAdmin" style="display:none"><a href="tipos_enderecos">Endereço - Tipo</a></li>
                                            <li id="mnuNewBairroAdmin" style="display:none"><a href="bairros">Bairro</a></li>
                                            <li id="mnuNewCidadeAdmin" style="display:none"><a href="cidades">Cidade</a></li>
                                            <li id="mnuNewEstadoAdmin" style="display:none"><a href="estados">Estado</a></li>
                                            <li id="mnuNewPaisAdmin" style="display:none"><a href="paises">Pais</a></li>
                                            <li id="mnuNewTelefoneAdmin" style="display:none"><a href="telefones">Telefone</a></li>
                                            <li id="mnuNewTelefoneTipoAdmin" style="display:none"><a href="tipos_telefones">Telefone - Tipo</a></li>
                                            <li id="mnuNewTipoPlanoSaudeAdmin" style="display:none"><a href="planos_saude">Plano de Saúde</a></li>
                                            <li id="mnuNewTipoPlanoSaudeClienteAdmin" style="display:none"><a href="clientes_tipos_planos">Cliente - Plano de Saúde</a></li>
                                            <li id="mnuNewPlanoSaudeAdmin" style="display:none"><a href="tipos_planos">Plano de Saúde - Tipos</a></li>
                                            <li id="mnuNewProfissionalPlanoSaudeAdmin" style="display:none"><a href="profissionais_planos_saude">Profissional - Plano de Saúde</a></li>
                                            <li id="mnuNewProntuarioAdmin" style="display:none"><a href="prontuarios">Prontuário</a></li>
                                            <li id="mnuNewProntuarioSintomaApresentadoAdmin" style="display:none"><a href="prontuarios_sintomas_apresentados">Prontuário - Sintoma</a></li>
                                            <li id="mnuNewProntuarioMedicamentoAdmin" style="display:none"><a href="prontuarios_medicamentos">Prontuário - Medicamento</a></li>
                                            <li id="mnuNewSintomaApresentadoAdmin" style="display:none"><a href="sintomas_apresentados">Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaSintomaApresentadoAdmin" style="display:none"><a href="glossario_doencas_sintomas_apresentados">Glossário - Sintoma</a></li>
                                            <li id="mnuNewGlossarioDoencaAdmin" style="display:none"><a href="glossario_doencas">Glossário</a></li>
                                            <li id="mnuNewMedicamentoAdmin" style="display:none"><a href="medicamentos">Medicamento</a></li>
                                            <li id="mnuNewGlossarioDoencaMedicamentoAdmin" style="display:none"><a href="glossario_doenca_medicamentos">Glossário - Medicamento</a></li>
                                            <li id="mnuNewMotivoAgendaAdmin" style="display:none"><a href="motivos_agendas">Agenda - Motivo</a></li>
                                            <li id="mnuNewAgendaAdmin" style="display:none"><a href="agendas">Agenda</a></li>
                                            <li id="mnuNewTipoExameAdmin" style="display:none"><a href="tipos_exames">Exame - Tipo</a></li>
                                            <li id="mnuNewExameAdmin" style="display:none"><a href="exames">Exame</a></li>
                                        </ul>
                                    </li>
                                    <li id="mnuViewAdmin" style="display:none"><a href="#">Visualizações</a>
                                        <ul>
                                            <li id="mnuViewPessoasFisicasAdmin" style="display:none"><a href="view/view_pessoas_fisicas.jsp">Pessoas Físicas</a></li>
                                            <li id="mnuViewPessoasJuridicasAdmin" style="display:none"><a href="view/view_pessoas_juridicas.jsp">Pessoas Jurídicas</a></li>
                                            <li id="mnuViewPessoasAdmin" style="display:none"><a href="view/view_pessoas.jsp">Pessoas</a></li>
                                            <li id="mnuViewEspecializacoesPessoasAdmin" style="display:none"><a href="view/view_pessoas_especializacoes.jsp">Pessoas - Especializações</a></li>
                                            <li id="mnuViewSituacoesPessoasAdmin" style="display:none"><a href="view/view_situacoes_pessoas.jsp">Pessoas - Situações</a></li>
                                            <li id="mnuViewEspecializacoesAdmin" style="display:none"><a href="view/view_especializacoes.jsp">Especializações</a></li>
                                            <li id="mnuViewFuncionariosAdmin" style="display:none"><a href="view/view_funcionarios.jsp">Funcionários</a></li>
                                            <li id="mnuViewSituacoesFuncionariosAdmin" style="display:none"><a href="view/view_situacoes_funcionarios.jsp">Funcionários - Situações</a></li>
                                            <li id="mnuViewFuncoesAdmin" style="display:none"><a href="view/view_funcoes.jsp">Funções</a></li>
                                            <li id="mnuViewProfissionaisAdmin" style="display:none"><a href="view/view_profissionais.jsp">Profissionais</a></li>
                                            <li id="mnuViewProfissionaisTurnosAdmin" style="display:none"><a href="view/view_profissionais_turnos.jsp">Profissionais - Turnos</a></li>
                                            <li id="mnuViewClientesAdmin" style="display:none"><a href="view/view_clientes.jsp">Clientes</a></li>
                                            <li id="mnuViewSituacoesClientesAdmin" style="display:none"><a href="view/view_situacoes_clientes.jsp">Clientes - Situações</a></li>
                                            <li id="mnuViewUsuariosAdmin" style="display:none"><a href="view/view_usuarios.jsp">Usuários</a></li>
                                            <li id="mnuViewUsuariosAcoesAdmin" style="display:none"><a href="view/view_acoes_usuarios.jsp">Usuários - Ações</a></li>
                                            <li id="mnuViewAcoesAdmin" style="display:none"><a href="view/view_acoes.jsp">Ações</a></li>
                                            <li id="mnuViewEnderecosAdmin" style="display:none"><a href="view/view_enderecos.jsp">Endereços</a></li>
                                            <li id="mnuViewEnderecosTiposAdmin" style="display:none"><a href="view/view_tipos_enderecos.jsp">Endereços - Tipos</a></li>
                                            <li id="mnuViewBairrosAdmin" style="display:none"><a href="view/view_bairros.jsp">Bairros</a></li>
                                            <li id="mnuViewCidadesAdmin" style="display:none"><a href="view/view_cidades.jsp">Cidades</a></li>
                                            <li id="mnuViewEstadosAdmin" style="display:none"><a href="view/view_estados.jsp">Estados</a></li>
                                            <li id="mnuViewPaisesAdmin" style="display:none"><a href="view/view_paises.jsp">Paises</a></li>
                                            <li id="mnuViewTelefonesAdmin" style="display:none"><a href="view/view_telefones.jsp">Telefones</a></li>
                                            <li id="mnuViewTelefonesTiposAdmin" style="display:none"><a href="view/view_tipos_telefones.jsp">Telefones - Tipos</a></li>
                                            <li id="mnuViewTiposPlanosSaudeAdmin" style="display:none"><a href="view/view_planos_saude.jsp">Planos de Saúde</a></li>
                                            <li id="mnuViewTiposPlanosSaudeClientesAdmin" style="display:none"><a href="view/view_clientes_tipos_planos.jsp">Clientes - Planos de Saúde</a></li>
                                            <li id="mnuViewPlanosSaudeAdmin" style="display:none"><a href="view/view_tipos_planos.jsp">Planos de Saúde - Tipos</a></li>
                                            <li id="mnuViewProfissionaisPlanosSaudeAdmin" style="display:none"><a href="view/view_profissionais_planos_saude.jsp">Profissionais - Planos de Saúde</a></li>
                                            <li id="mnuViewProntuariosAdmin" style="display:none"><a href="view/view_prontuarios.jsp">Prontuários</a></li>
                                            <li id="mnuViewProntuariosSintomasApresentadosAdmin" style="display:none"><a href="view/view_prontuarios_sintomas_apresentados.jsp">Prontuários - Sintomas</a></li>
                                            <li id="mnuViewProntuariosMedicamentosAdmin" style="display:none"><a href="view/view_prontuarios_medicamentos.jsp">Prontuários - Medicamentos</a></li>
                                            <li id="mnuViewSintomasApresentadosAdmin" style="display:none"><a href="view/view_sintomas_apresentados.jsp">Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasSintomasApresentadosAdmin" style="display:none"><a href="view/view_glossario_doencas_sintomas_apresentados.jsp">Glossário - Sintomas</a></li>
                                            <li id="mnuViewGlossarioDoencasAdmin" style="display:none"><a href="view/view_glossario_doencas.jsp">Glossário</a></li>
                                            <li id="mnuViewMedicamentosAdmin" style="display:none"><a href="view/view_medicamentos.jsp">Medicamentos</a></li>
                                            <li id="mnuViewGlossarioDoencasMedicamentosAdmin" style="display:none"><a href="view/view_glossario_doenca_medicamentos.jsp">Glossário - Medicamentos</a></li>
                                            <li id="mnuViewMotivosAgendasAdmin" style="display:none"><a href="view/view_motivos_agendas.jsp">Agendas - Motivos</a></li>
                                            <li id="mnuViewAgendasAdmin" style="display:none"><a href="view/view_agendas.jsp">Agendas</a></li>
                                            <li id="mnuViewTiposExamesAdmin" style="display:none"><a href="view/view_tipos_exames.jsp">Exames - Tipos</a></li>
                                            <li id="mnuViewExamesAdmin" style="display:none"><a href="view/view_exames.jsp">Exames</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="contato">Contato</a></li>
                        </ul>
                    </div>
                </nav>

                <%
                    //Oculta ou mostra os menus de acordo com as permissões do usuário
                    for (String usuarioPermissao : Principal.getPermissoesUsuario()) {
                        String menu = String.valueOf(usuarioPermissao);
                %>
                <script>
                    exibe("<%=menu%>");
                </script>
                <%
                    }
                %>
            </header>
            <div id="site_content">
                <div id="sidebar_container">
                    <div class="sidebar">
                        <form action="home.jsp" method="post">
                            <div class="form_settings">
                                <%
                                    if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
                                %>
                                <p>
                                    <span>Usuário</span>
                                    <input class="en_200" type="text" name="name" value="" placeholder="Infomre o nome de Usuário"/>
                                </p>
                                <p>
                                    <span>Senha</span>
                                    <input class="en_200" type="password" name="pass" value="" placeholder="Infomre a Senha"/>
                                </p>
                                <p>
                                    <input id="input_salvar" class="button_soft" type="submit" value=" Logar no SAMHO"/>
                                </p>
                                <%
                                } else {
                                %>
                                <p>Usuáruio: <%= session.getAttribute("login")%></p>
                                <p>
                                    <input id="input_terminar" class="button_soft" type="button" value=" Sair de SAMHO" onclick="terminarSessao();"/>
                                </p>
                                <%
                                    }
                                %>
                            </div>
                        </form>
                    </div>
                    <div class="sidebar">
                        <p class="separator"/>
                        <h3>Agendamento</h3>
                        <h5>Selecione uma data e agende uma visita com seu médico.</h5>
                        <form action="#" method="post">
                            <div class="form_settings">
                                <script laguage="JavaScript">calendar();</script>
                            </div>
                        </form>
                    </div>
                    <div class="sidebar">
                        <p class="separator"/>
                        <h3>Profissionais</h3>
                        <ul>
                            <%
                                ArrayList listaProfissionais = new ArrayList();
                                Profissionais profissionais = new Profissionais();
                                listaProfissionais = profissionais.getObjetoDAO().getListaCodigoDescricao(profissionais.getObjetoDAO().getCampoID(), "", profissionais.getDadosDescricao().getCampo());
                                for (Iterator it = listaProfissionais.iterator(); it.hasNext();) {
                                    Registrador reg = (Registrador) it.next();
                                    if (reg.getCodigo() > 0) {
                            %>
                            <li><a href=""><%= reg.getDescricao()%></a></li>
                                <%
                                        }
                                    }
                                %>
                        </ul>
                    </div>
                </div>  

                <div class="content">
                    <h3>CID - 10</h3>
                    <h5>Classificação de doenças</h5>
                    <form action="#" method="post">
                        <div class="form_settings">
                            <p>
                                <span>Descrição</span>
                                <input class="en_200" type="text" name="name" value="" placeholder="Infomre o nome da Doença" />
                                <a href="#"><img src="images/localizar.png" alt="Localizar doença..." /></a>
                            </p>
                            <p>
                                <span>CID - 10</span>
                                <input class="en_100" type="text" name="name" value="" placeholder="Infomre o CID - 10"/>
                            </p>
                        </div>

                        <p class="separator"/>

                        <p id="letterAnchor">
                            <a href="http://www.bulas.med.br/cid-10/p/a.html">A</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/b.html">B</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/c.html">C</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/d.html">D</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/e.html">E</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/f.html">F</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/g.html">G</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/h.html">H</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/i.html">I</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/j.html">J</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/k.html">K</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/l.html">L</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/m.html">M</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/n.html">N</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/o.html">O</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/p.html">P</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/q.html">Q</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/r.html">R</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/s.html">S</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/t.html">T</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/u.html">U</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/v.html">V</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/w.html">W</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/x.html">X</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/y.html">Y</a> |
                            <a href="http://www.bulas.med.br/cid-10/p/z.html">Z</a>
                        </p>
                    </form>  
                    <div>
                        <h1>Bem vindos ao SAMHO</h1>
                        <P>O SAMHO é um sistema de agendamento de consultas, controle de clientes e banco de informações que irá auxiliar os profissionais da área de saúde e usuários em geral a obterem informações a cerca de diagnostico e outras informações a respeito de doenças em geral cadastradas no CID-10.</p>
                    </div>
                </div>
            </div>

            <div id="scroll">
                <a title="Mover para cima" class="top" href="#"><img src="images/up.png" alt="top"/></a>
            </div>
            <footer>
                <p>
                    <a href="index.html">Home</a> |
                    <a href="profissionais">Profissionais</a> |
                    <a href="contato">Contatos</a>
                </p>
                <p>Copyright &copy; Saibel, Sergio Luis | 2017</p>
            </footer>
        </div>
    </body>
</html>