/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.necocio;

import com.samho.util.FabricaServelet;
import com.samho.util.FabricaViewJSP;
import java.io.IOException;

/**
 *
 * @author Sergio
 */
public class FabricaAPP {

    public static void main(String[] args) throws IOException {
        FabricaViewJSP.criarJSP(new PessoasFisicas(), "Pessoa Física", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new PessoasJuridicas(), "Pessoa Jurídica", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Pessoas(), "Pessoa", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new PessoasEspecializacoes(), "Pessoa - Especialização", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new SituacoesPessoas(), "Pessoa - Situação", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Especializacoes(), "Especialização", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Funcionarios(), "Funcionário", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new SituacoesFuncionarios(), "Funcionário - Situação", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Funcoes(), "Função", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Profissionais(), "Profissional", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new ProfissionaisTurnos(), "Profissional - Turnos", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Clientes(), "Cliente", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new SituacoesClientes(), "Cliente - Situação", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Usuarios(), "Usuário", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new AcoesUsuarios(), "Usuário - Ação", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Acoes(), "Ação", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Enderecos(), "Endereço", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new TiposEnderecos(), "Endereço - Tipo", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Bairros(), "Bairro", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Cidades(), "Cidade", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Estados(), "Estado", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Paises(), "Pais", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Telefones(), "Telefone", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new TiposTelefones(), "Telefone - Tipo", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new PlanosSaude(), "Plano de Saúde - Tipo", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new ClientesTiposPlanos(), "Cliente - Plano de Saúde", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new TiposPlanosSaude(), "Plano de Saúde", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new ProfissionaisPlanosSaude(), "Profissional - Plano de Saúde", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Prontuarios(), "Prontuário", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new ProntuariosSintomasApresentados(), "Prontuário - Sintoma", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new ProntuariosMedicamentos(), "Prontuário - Medicamento", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new SintomasApresentados(), "Sintoma", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new GlossarioDoencasSintomasApresentados(), "Glossário - Sintoma", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new GlossarioDoencas(), "Glossário", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Medicamentos(), "Medicamento", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new GlossarioDoencasMedicamentos(), "Glossário - Medicamento", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new MotivosAgendas(), "Agenda - Motivo", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Agendas(), "Agenda", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new TiposExames(), "Exame - Tipo", "ISO-8859-1");
        FabricaViewJSP.criarJSP(new Exames(), "Exame", "ISO-8859-1");

        //FabricaServelet.criarServlet(new AcoesUsuarios(), "Ações de Usuarios", "ISO-8859-1");
    }

}
