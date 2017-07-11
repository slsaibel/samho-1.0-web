/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.necocio;

import com.samho.dao.DadosDAO;
import com.samho.dao.ObjetoDAO;
import com.samho.dao.PessoasFisicasDAO;
import com.samho.util.Formatacao;
import java.util.Calendar;
import java.util.Objects;

/**
 * Classe de Objeto.
 *
 * @author Saibel, Sergio Luis
 * @since  date 31/03/2017
 *
 * @version  revision 001.20170331 date 31/03/2017 author Saibel, Sergio Luís reason
 * Instanciar um objeto com métodos e atributos necessários.
 *
 * Esta classe é dependente da classe Pessoas
 */
public final class PessoasFisicas extends Objeto implements InterfaceObj {

    // Declaração de constantes
    public static final char MASCULINO = 'M';
    public static final char FEMININO = 'F';

    // Declaração de atributos
    private long codPessoa;
    private long cpf;
    private long rg;
    private String nomeDaMae;
    private Calendar dataDeNascimento;
    private char sexo;

    private PessoasFisicasDAO pessoaFisicaDAO;

    // Construtor padrão
    public PessoasFisicas() {
        codPessoa = -1;
        cpf = 0;
        rg = 0;
        nomeDaMae = "";
        dataDeNascimento = Calendar.getInstance();
        sexo = PessoasFisicas.MASCULINO;

        pessoaFisicaDAO = new PessoasFisicasDAO();

        adicionarCampos();
    }

    // Construtor
    public PessoasFisicas(Pessoas pessoa) {
        codPessoa = pessoa.getIdPessoa();
        cpf = 0;
        rg = 0;
        nomeDaMae = "";
        dataDeNascimento = Calendar.getInstance();
        sexo = PessoasFisicas.MASCULINO;

        pessoaFisicaDAO = new PessoasFisicasDAO();

        adicionarCampos();
    }

    public PessoasFisicas(PessoasFisicas pessoaF) {
        codPessoa = pessoaF.getCodPessoa();
        cpf = pessoaF.getCpf();
        rg = pessoaF.getRg();
        nomeDaMae = pessoaF.getNomeDaMae();
        dataDeNascimento = pessoaF.getDataDeNascimento();
        sexo = pessoaF.getSexo();

        pessoaFisicaDAO = pessoaF.getPessoaFisicaDAO();

        adicionarCampos();
    }

    // Construtor
    public PessoasFisicas(long codPessoa, long cpf, long rg, String nomeDaMae,
            Calendar dataDeNascimento, char sexo) {
        this.codPessoa = codPessoa;
        this.cpf = cpf;
        this.rg = rg;
        this.nomeDaMae = nomeDaMae;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;

        pessoaFisicaDAO = new PessoasFisicasDAO();

        adicionarCampos();
    }

    @Override
    public boolean equals(Object object) {
        boolean retorno = false;

        if (object instanceof PessoasFisicas) {
            PessoasFisicas other = (PessoasFisicas) object;
            if (codPessoa == other.getCodPessoa()) {
                retorno = true;
            }
        }

        return retorno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.codPessoa ^ (this.codPessoa >>> 32));
        hash = 47 * hash + (int) (this.cpf ^ (this.cpf >>> 32));
        hash = 47 * hash + (int) (this.rg ^ (this.rg >>> 32));
        hash = 47 * hash + Objects.hashCode(this.nomeDaMae);
        hash = 47 * hash + Objects.hashCode(this.dataDeNascimento);
        hash = 47 * hash + this.sexo;
        hash = 47 * hash + Objects.hashCode(this.pessoaFisicaDAO);
        return hash;
    }

    @Override
    public ObjetoDAO getObjetoDAO() {
        return pessoaFisicaDAO;
    }

    @Override
    public void adicionarCampos() {
        removerCampos();

        getObjetoDAO().addCampo(getDadosCodigo());
        getObjetoDAO().addCampo(new DadosDAO("cpf", "CPF", String.valueOf(cpf),
                DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("rg", "RG", String.valueOf(rg),
                DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("nome_mae", "Nome da mãe",
                nomeDaMae, DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, 
                DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("data_nascimento",
                "Data nasc.", Formatacao.ajustaDataAMD(dataDeNascimento),
                DadosDAO.TIPO_DATE, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("sexo", "Sexo",
                String.valueOf(sexo), DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, 
                DadosDAO.NAO_CHAVE));
    }

    /**
     * @return the dadosCodigo
     */
    @Override
    public DadosDAO getDadosCodigo() {
        return new DadosDAO("cod_pessoa", "Cód. Pessoa", String.valueOf(
                codPessoa), DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL, 
                DadosDAO.IS_CHAVE);
    }

    /**
     * @return the dadosDescricao
     */
    @Override
    public DadosDAO getDadosDescricao() {
        return new DadosDAO(pessoaFisicaDAO.getCampo("nome||' - '||sobrenome",
                "pessoas", "id_pessoa = cod_pessoa", false), "Pessoa Física", "",
                DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE);
    }
    
    @Override
    public String getFormServlet() {
        String retorno = null;

        retorno = "            out.println(\"<p>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_codigo\">Código</span>\");\n"
                + "            out.println(\"<input id=\"input_codigo\" name=\"param1\" class=\"di_100\" readonly=\"readonly\" type=\"text\" placeholder=\"Cód. Pessoa\" value=\"\" + objeto.getIdPessoa() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_nome\">Nome</span>\");\n"
                + "            out.println(\"<input id=\"input_nome\" name=\"param2\" class=\"en_450\" required=\"required\" type=\"text\" placeholder=\"Nome.\" value=\"\" + objeto.getNome() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_sobrenome\">Sobrenome</span>\");\n"
                + "            out.println(\"<input id=\"input_sobrenome\" name=\"param3\" class=\"en_450\" required=\"required\" type=\"text\" placeholder=\"Sobrenome.\" value=\"\" + objeto.getSobrenome() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_observacao\">Observações</span>\");\n"
                + "            out.println(\"<textarea id=\"input_observacao\" name=\"param4\" placeholder=\"Utilize este espaço para escrever suas observações.\" value=\"\" + objeto.getObservacoes() + \"\"></textarea>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"\");\n"
                + "            out.println(\"<p class=\"separator\"/>\");\n"
                + "            out.println(\"<h3>Complemento</h3>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_cpf\">CPF</span>\");\n"
                + "            out.println(\"<input id=\"input_cpf\" name=\"param5\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"CPF ___.___.___-__\" value=\"\" + objeto.getPessoaFisica().getCpf() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_rg\">RG</span>\");\n"
                + "            out.println(\"<input id=\"input_rg\" name=\"param6\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"RG __________\" value=\"\" + objeto.getPessoaFisica().getRg() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\" for=\"datepicker\">Data Nascimento</span>\");\n"
                + "            out.println(\"<a><input id=\"datepicker\" name=\"param7\" class=\"en_100\" required=\"required\" type=\"text\" placeholder=\"dd/mm/yyyy\" value=\"\" + Formatacao.ajustaDataDMA(objeto.getPessoaFisica().getDataDeNascimento()) + \"\"/></a>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_nome_mae\">Nome da Mãe</span>\");\n"
                + "            out.println(\"<input id=\"input_nome_mae\" name=\"param8\" class=\"en_450\" required=\"required\" type=\"text\" placeholder=\"Nome da mãe da pessoa.\" value=\"\" + objeto.getPessoaFisica().getNomeDaMae() + \"\"/>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\">Sexo</span>\");\n"
                + "            out.println(\"<input name=\"param9\" class=\"radio\" required=\"required\" type=\"radio\" value=\"M\" value=\"\" + objeto.getPessoaFisica().getSexo() == 'M' + \"\"/>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_sexo\">Masculino</span>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<input name=\"param9\" class=\"radio\" required=\"required\" type=\"radio\" value=\"F\" value=\"\" + objeto.getPessoaFisica().getSexo() == 'F' + \"\"/>\");\n"
                + "            out.println(\"<span class=\"required\" for=\"input_sexo\">Feminino</span>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"<p class=\"in_line\">\");\n"
                + "            out.println(\"<span class=\"required\" for=\"lista_sit_pess_j\">Situação</span>\");\n"
                + "            out.println(\"<select id=\"lista_sit_pess_j\" name=\"param10\" class=\"sel_300\" required=\"required\" value=\"\" + objeto.getCodSituacao() + \"\">\");\n"
                + "            out.println(\"<option value=\"\">Selecione a situação da pessoa.</option>\");\n"
                + "            out.println(\"<option value=\"Ativo\">Ativo</option>\");\n"
                + "            out.println(\"<option value=\"Inativo\">Inativo</option>\");\n"
                + "            out.println(\"<option value=\"Outro\">Outro</option>\");\n"
                + "            out.println(\"</select>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"\");\n"
                + "\n"
                + "            out.println(\"<p class=\"separator\"/>\");\n"
                + "            out.println(\"<div id=\"enderecos\">\");\n"
                + "            out.println(\"</div>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"\");\n"
                + "\n"
                + "            out.println(\"<p class=\"separator\"/>\");\n"
                + "            out.println(\"<div id=\"telefones\">\");\n"
                + "            out.println(\"</div>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"\");\n"
                + "\n"
                + "            out.println(\"<p class=\"separator\"/>\");\n"
                + "            out.println(\"<div id=\"especializacoes\">\");\n"
                + "            out.println(\"</div>\");\n"
                + "            out.println(\"</p>\");\n"
                + "            out.println(\"\");";

        return retorno;
    }

    /**
     * @return the codPessoa
     */
    public long getCodPessoa() {
        return codPessoa;
    }

    /**
     * @param codPessoa the codPessoa to set
     */
    public void setCodPessoa(long codPessoa) {
        this.codPessoa = codPessoa;
    }

    /**
     * @return the cpf
     */
    public long getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public long getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(long rg) {
        this.rg = rg;
    }

    /**
     * @return the nomeDaMae
     */
    public String getNomeDaMae() {
        return nomeDaMae;
    }

    /**
     * @param nomeDaMae the nomeDaMae to set
     */
    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    /**
     * @return the dataDeNascimento
     */
    public Calendar getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * @param dataDeNascimento the dataDeNascimento to set
     */
    public void setDataDeNascimento(Calendar dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the pessoaFisicaDAO
     */
    public PessoasFisicasDAO getPessoaFisicaDAO() {
        return pessoaFisicaDAO;
    }

    /**
     * @param pessoaFisicaDAO the pessoaFisicaDAO to set
     */
    public void setPessoaFisicaDAO(PessoasFisicasDAO pessoaFisicaDAO) {
        this.pessoaFisicaDAO = pessoaFisicaDAO;
    }
}
