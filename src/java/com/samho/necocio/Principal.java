/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.necocio;

import com.samho.dao.DadosDAO;
import java.util.ArrayList;

/**
 * Classe de Principal da aplicação.
 *
 * @author Saibel, Sergio Luis
 * @since date 31/03/2017
 *
 * @version revision 001.20170331 date 31/03/2017 author Saibel, Sergio Luís
 * reason Clase principal da aplicação,
 */
public class Principal {

    public static Usuarios usuario = null;

    public static final int SITUACOES_PESSOAS = 1;
    public static final int PESSOAS_FISICAS = 2;
    public static final int PAISES = 3;
    public static final int ESTADOS = 4;
    public static final int CIDADES = 5;
    public static final int TIPOS_ENDERECOS = 6;
    public static final int BAIRROS = 7;
    public static final int ENDERECOS = 8;
    public static final int FUNCOES = 9;
    public static final int TIPOS_TELEFONES = 10;
    public static final int TELEFONES = 11;
    public static final int SITUACOES_FUNCIONARIOS = 12;
    public static final int ACOES = 13;
    public static final int USUARIOS = 14;
    public static final int ACOES_USUARIOS = 15;
    public static final int FUNCIONARIOS = 16;
    public static final int ESPECIALIZACOES = 17;
    public static final int PESSOAS_ESPECIALIZACOES = 18;
    public static final int SITUACOES_CLIENTES = 19;
    public static final int PESSOAS = 20;
    public static final int CLIENTES = 21;
    public static final int TIPOS_PLANOS_SAUDE = 22;
    public static final int CLIENTES_TIPOS_PLANOS = 23;
    public static final int PLANOS_SAUDE = 24;
    public static final int PROFISSIONAIS_PLANOS_SAUDE = 25;
    public static final int PROFISSIONAIS = 26;
    public static final int PESSOAS_JURIDICAS = 27;
    public static final int PRONTUARIOS_SINTOMAS_APRESENTADOS = 28;
    public static final int PRONTUARIOS = 29;
    public static final int PRONTUARIOS_MEDICAMENTOS = 30;
    public static final int SINTOMAS_APRESENTADOS = 31;
    public static final int GLOSSARIO_DOENCAS_SINTOMAS_APRESENTADOS = 32;
    public static final int GLOSSARIO_DOENCAS = 33;
    public static final int MEDICAMENTOS = 34;
    public static final int GLOSSARIO_DOENCA_MEDICAMENTOS = 35;
    public static final int PROFISSIONAIS_TURNOS = 36;
    public static final int MOTIVOS_AGENDAS = 37;
    public static final int AGENDAS = 38;
    public static final int TIPOS_EXAMES = 39;
    public static final int EXAMES = 40;

    /**
     * Verifica as permissões do usuário logado
     *
     * @return Lista de permissões e dependências necessárias.
     */
    public static ArrayList<String> getPermissoesUsuario() {
        ArrayList<String> retorno = new ArrayList();
        boolean isMnuNewAtivo = false;
        boolean isMnuViewAtivo = false;
        String complMenu = "";

        if (Principal.usuario.getIdUsuario() == 0) {
            retorno.add("mnuAdmin");
            complMenu = "Admin";
        } else {
            Funcionarios func = new Funcionarios();
            func.adicionarWhere(new DadosDAO(func.getObjetoDAO().getCampoID(), "",
                    String.valueOf(Principal.usuario.getCodFuncionario()),
                    DadosDAO.TIPO_INTEGER, DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE));
            if (func.getObjetoDAO().getQuantidadeRegistrosTabela() > 0) {
                if ((Principal.usuario.isAdministrador()
                        && (Principal.usuario.getCodFuncionario() > 0))) {
                    retorno.add("mnuOperAdmin");
                    complMenu = "OperAdmin";
                } else {
                    if (Principal.usuario.getCodFuncionario() > 0) {
                        retorno.add("mnuOper");
                        complMenu = "Oper";
                    } else {
                        retorno.add("mnuUser");
                        complMenu = "User";
                    }
                }
            }
        }

        int contador = 0;
        for (AcoesUsuarios permissoe : Principal.usuario.getPermissoes()) {
            contador++;

            AcoesUsuarios acoesUsuario = new AcoesUsuarios(permissoe);

            boolean incluir = acoesUsuario.isIncluir();
            boolean alterar = acoesUsuario.isAlterar();
            boolean excluir = acoesUsuario.isExcluir();
            boolean consultar = acoesUsuario.isConsultar();

            if (incluir && !isMnuNewAtivo) {
                isMnuNewAtivo = true;

                retorno.add("mnuNew" + complMenu);
            }

            if (consultar && !isMnuViewAtivo) {
                isMnuViewAtivo = true;

                retorno.add("mnuView" + complMenu);
            }

            switch (contador) {
                case Principal.PESSOAS_FISICAS:
                    retorno.add(consultar ? "mnuViewPessoasFisicas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewPessoasFisicas" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditPessoaFisica" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.PESSOAS_JURIDICAS:
                    retorno.add(consultar ? "mnuViewPessoasJuridicas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewPessoaJuridica" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditPessoaJuridica" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.PESSOAS:
                    retorno.add(consultar ? "mnuViewPessoas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewPessoas" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditPessoas" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewSituacoesPessoas" + complMenu);
                    }
                    break;
                case Principal.PESSOAS_ESPECIALIZACOES:
                    retorno.add(consultar ? "mnuViewEspecializacoesPessoas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewEspecializacaoPessoa" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditEspecializacaoPessoa" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPessoas" + complMenu);
                        retorno.add("mnuViewEspecializacoes" + complMenu);
                    }
                    break;
                case Principal.SITUACOES_PESSOAS:
                    retorno.add(consultar ? "mnuViewSituacoesPessoas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewSituacaoPessoas" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditSituacaoPessoa" + complMenu : "");
                    break;
                case Principal.ESPECIALIZACOES:
                    retorno.add(consultar ? "mnuViewEspecializacoes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewEspecializacao" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditEspecializacao" + complMenu : "");
                    break;
                case Principal.FUNCIONARIOS:
                    retorno.add(consultar ? "mnuViewFuncionarios" + complMenu : "");
                    retorno.add(incluir ? "mnuNewFuncionario" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditFuncionario" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewSituacoesFuncionarios" + complMenu);
                        retorno.add("mnuViewFuncoes" + complMenu);
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.SITUACOES_FUNCIONARIOS:
                    retorno.add(consultar ? "mnuViewSituacoesFuncionarios" + complMenu : "");
                    retorno.add(incluir ? "mnuNewSituacaoFuncionarios" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditSituacaoFuncionarios" + complMenu : "");
                    break;
                case Principal.FUNCOES:
                    retorno.add(consultar ? "mnuViewFuncoes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewFuncao" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditFuncao" + complMenu : "");
                    break;
                case Principal.PROFISSIONAIS:
                    retorno.add(consultar ? "mnuViewProfissionais" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProfissional" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProfissional" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.PROFISSIONAIS_TURNOS:
                    retorno.add(consultar ? "mnuViewProfissionaisTurnos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProfissionalTurnos" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProfissionalTurnos" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewProfissionais" + complMenu);
                    }
                    break;
                case Principal.CLIENTES:
                    retorno.add(consultar ? "mnuViewClientes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewCliente" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditCliente" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewSituacoesClientes" + complMenu);
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.SITUACOES_CLIENTES:
                    retorno.add(consultar ? "mnuViewSituacoesClientes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewSituacaoCliente" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditSituacaoCliente" + complMenu : "");
                    break;
                case Principal.USUARIOS:
                    retorno.add(consultar ? "mnuViewUsuarios" + complMenu : "");
                    retorno.add(incluir ? "mnuNewUsuario" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditUsuario" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewFuncionarios" + complMenu);
                    }
                    break;
                case Principal.ACOES_USUARIOS:
                    retorno.add(consultar ? "mnuViewUsuariosAcoes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewUsuarioAcoes" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditUsuarioAcoes" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewAcoes" + complMenu);
                        retorno.add("mnuViewUsuarios" + complMenu);
                    }
                    break;
                case Principal.ACOES:
                    retorno.add(consultar ? "mnuViewAcoes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewAcao" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditAcao" + complMenu : "");
                    break;
                case Principal.ENDERECOS:
                    retorno.add(consultar ? "mnuViewEnderecos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewEndereco" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditEndereco" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewEnderecosTipos" + complMenu);
                        retorno.add("mnuViewBairros" + complMenu);
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.TIPOS_ENDERECOS:
                    retorno.add(consultar ? "mnuViewEnderecosTipos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewEnderecoTipo" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditEnderecoTipo" + complMenu : "");
                    break;
                case Principal.BAIRROS:
                    retorno.add(consultar ? "mnuViewBairros" + complMenu : "");
                    retorno.add(incluir ? "mnuNewBairro" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditBairro" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewCidades" + complMenu);
                    }
                    break;
                case Principal.CIDADES:
                    retorno.add(consultar ? "mnuViewCidades" + complMenu : "");
                    retorno.add(incluir ? "mnuNewCidade" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditCidade" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewEstados" + complMenu);
                    }
                    break;
                case Principal.ESTADOS:
                    retorno.add(consultar ? "mnuViewEstados" + complMenu : "");
                    retorno.add(incluir ? "mnuNewEstado" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditEstado" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPaises" + complMenu);
                    }
                    break;
                case Principal.PAISES:
                    retorno.add(consultar ? "mnuViewPaises" + complMenu : "");
                    retorno.add(incluir ? "mnuNewPais" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditPais" + complMenu : "");
                    break;
                case Principal.TELEFONES:
                    retorno.add(consultar ? "mnuViewTelefones" + complMenu : "");
                    retorno.add(incluir ? "mnuNewTelefone" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditTelefone" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewTelefonesTipos" + complMenu);
                        retorno.add("mnuViewPessoas" + complMenu);
                    }
                    break;
                case Principal.TIPOS_TELEFONES:
                    retorno.add(consultar ? "mnuViewTelefonesTipos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewTelefoneTipo" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditTelefoneTipo" + complMenu : "");
                    break;
                case Principal.TIPOS_PLANOS_SAUDE:
                    retorno.add(consultar ? "mnuViewTiposPlanosSaude" + complMenu : "");
                    retorno.add(incluir ? "mnuNewTipoPlanoSaude" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditTipoPlanoSaude" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPlanosSaude" + complMenu);
                    }
                    break;
                case Principal.CLIENTES_TIPOS_PLANOS:
                    retorno.add(consultar ? "mnuViewTiposPlanosSaudeClientes" + complMenu : "");
                    retorno.add(incluir ? "mnuNewTipoPlanoSaudeCliente" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditTipoPlanoSaudeCliente" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewClientes" + complMenu);
                        retorno.add("mnuViewTiposPlanos" + complMenu);
                    }
                    break;
                case Principal.PLANOS_SAUDE:
                    retorno.add(consultar ? "mnuViewPlanosSaude" + complMenu : "");
                    retorno.add(incluir ? "mnuNewPlanoSaude" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditPlanoSaude" + complMenu : "");
                    break;
                case Principal.PROFISSIONAIS_PLANOS_SAUDE:
                    retorno.add(consultar ? "mnuViewProfissionaisPlanosSaude" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProfissionalPlanoSaude" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProfissionalPlanoSaude" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewProfissionais" + complMenu);
                        retorno.add("mnuViewPlanosSaude" + complMenu);
                    }
                    break;
                case Principal.PRONTUARIOS:
                    retorno.add(consultar ? "mnuViewProntuarios" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProntuario" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProntuario" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewClientes" + complMenu);
                    }
                    break;
                case Principal.PRONTUARIOS_SINTOMAS_APRESENTADOS:
                    retorno.add(consultar ? "mnuViewProntuariosSintomasApresentados" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProntuarioSintomaApresentado" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProntuarioSintomaApresentado" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewProntuarios" + complMenu);
                        retorno.add("mnuViewSintomasApresentados" + complMenu);
                    }
                    break;
                case Principal.PRONTUARIOS_MEDICAMENTOS:
                    retorno.add(consultar ? "mnuViewProntuariosMedicamentos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewProntuarioMedicamento" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditProntuarioMedicamento" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewProntuarios" + complMenu);
                        retorno.add("mnuViewMedicamentos" + complMenu);
                    }
                    break;
                case Principal.SINTOMAS_APRESENTADOS:
                    retorno.add(consultar ? "mnuViewSintomasApresentados" + complMenu : "");
                    retorno.add(incluir ? "mnuNewSintomaApresentado" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditSintomaApresentado" + complMenu : "");
                    break;
                case Principal.GLOSSARIO_DOENCAS_SINTOMAS_APRESENTADOS:
                    retorno.add(consultar ? "mnuViewGlossarioDoencasSintomasApresentados" + complMenu : "");
                    retorno.add(incluir ? "mnuNewGlossarioDoencaSintomaApresentado" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditGlossarioDoencaSintomaApresentado" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewGlossarioDoencas" + complMenu);
                        retorno.add("mnuViewSintomasApresentados" + complMenu);
                    }
                    break;
                case Principal.GLOSSARIO_DOENCAS:
                    retorno.add(consultar ? "mnuViewGlossarioDoencas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewGlossarioDoenca" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditGlossarioDoenca" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewProfissionais" + complMenu);
                    }
                    break;
                case Principal.MEDICAMENTOS:
                    retorno.add(consultar ? "mnuViewMedicamentos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewMedicamento" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditMedicamento" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewPessoasJuridicas" + complMenu);
                    }
                    break;
                case Principal.GLOSSARIO_DOENCA_MEDICAMENTOS:
                    retorno.add(consultar ? "mnuViewGlossarioDoencasMedicamentos" + complMenu : "");
                    retorno.add(incluir ? "mnuNewGlossarioDoencaMedicamento" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditGlossarioDoencaMedicamento" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewGlossarioDoencas" + complMenu);
                        retorno.add("mnuViewMedicamentos" + complMenu);
                    }
                    break;
                case Principal.MOTIVOS_AGENDAS:
                    retorno.add(consultar ? "mnuViewMotivosAgendas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewMotivoAgenda" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditMotivoAgenda" + complMenu : "");
                    break;
                case Principal.AGENDAS:
                    retorno.add(consultar ? "mnuViewAgendas" + complMenu : "");
                    retorno.add(incluir ? "mnuNewAgenda" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditAgenda" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewClientes" + complMenu);
                        retorno.add("mnuViewFuncionarios" + complMenu);
                        retorno.add("mnuViewProfissionais" + complMenu);
                        retorno.add("mnuViewMotivosAgendas" + complMenu);
                    }
                    break;
                case Principal.TIPOS_EXAMES:
                    retorno.add(consultar ? "mnuViewTiposExames" + complMenu : "");
                    retorno.add(incluir ? "mnuNewTipoExame" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditTipoExame" + complMenu : "");
                    break;
                case Principal.EXAMES:
                    retorno.add(consultar ? "mnuViewExames" + complMenu : "");
                    retorno.add(incluir ? "mnuNewExame" + complMenu : "");
                    retorno.add((consultar && (alterar || excluir)) ? "mnuEditExame" + complMenu : "");
                    if (incluir || alterar) {
                        retorno.add("mnuViewTiposExames" + complMenu);
                        retorno.add("mnuViewClientes" + complMenu);
                        retorno.add("mnuViewPessoasJuridicas" + complMenu);
                    }
                    break;
                default:
                    break;
            }
        }

        return retorno;
    }
}
