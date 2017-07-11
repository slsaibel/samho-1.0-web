/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.necocio;

import com.samho.dao.ExamesDAO;
import com.samho.dao.DadosDAO;
import com.samho.dao.ObjetoDAO;
import com.samho.util.Formatacao;
import java.util.Calendar;
import java.util.Objects;

/**
 * Classe de Funcionários.
 *
 * @author Sergio
 * @since date 18/04/2017
 *
 * @version revision 001.20170418 date 18/04/2017 author Saibel, Sergio Luís
 * reason Instanciar um objeto com métodos e atributos necessários.
 */
public final class Exames extends Objeto implements InterfaceObj {

    // Declaração de atributos
    private long idExame;
    private long codTipoExame;
    private long codCliente;
    private long codPessoaJuridica;
    private Calendar dataAgendamento;
    private String laudo;
    private String observacoes;

    private ExamesDAO exameDAO;

    // Construtor padrão
    public Exames() {
        idExame = -1;
        codTipoExame = 0;
        codCliente = -1;
        codPessoaJuridica = -1;
        dataAgendamento = Calendar.getInstance();
        laudo = "";
        observacoes = "";

        exameDAO = new ExamesDAO();

        adicionarCampos();
    }

    // Construtor
    public Exames(Exames exame) {
        idExame = exame.getIdExame();
        codTipoExame = exame.getCodTipoExame();
        codCliente = exame.getCodCliente();
        codPessoaJuridica = exame.getCodPessoaJuridica();
        dataAgendamento = exame.getDataAgendamento();
        laudo = exame.getLaudo();
        observacoes = exame.getObservacoes();

        exameDAO = exame.getExameDAO();

        adicionarCampos();
    }

    // Construtor
    public Exames(long idExame, long codTipoExame, long codPessoa,
            long codPessoaJuridica, Calendar dataAgendamento, String laudo,
            String observacoes) {
        this.idExame = idExame;
        this.codTipoExame = codTipoExame;
        this.codCliente = codPessoa;
        this.codPessoaJuridica = codPessoaJuridica;
        this.dataAgendamento = dataAgendamento;
        this.laudo = laudo;
        this.observacoes = observacoes;

        exameDAO = new ExamesDAO();

        adicionarCampos();
    }

    @Override
    public boolean equals(Object object) {
        boolean retorno = false;

        if (object instanceof Exames) {
            Exames other = (Exames) object;
            if (idExame == other.getIdExame()) {
                retorno = true;
            }
        }

        return retorno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idExame ^ (this.idExame >>> 32));
        hash = 71 * hash + (int) (this.codTipoExame ^ (this.codTipoExame >>> 32));
        hash = 71 * hash + (int) (this.codCliente ^ (this.codCliente >>> 32));
        hash = 71 * hash + (int) (this.codPessoaJuridica ^ (this.codPessoaJuridica >>> 32));
        hash = 71 * hash + Objects.hashCode(this.dataAgendamento);
        hash = 71 * hash + Objects.hashCode(this.laudo);
        hash = 71 * hash + Objects.hashCode(this.observacoes);
        hash = 71 * hash + Objects.hashCode(this.exameDAO);
        return hash;
    }

    @Override
    public ObjetoDAO getObjetoDAO() {
        return exameDAO;
    }

    @Override
    public void adicionarCampos() {
        removerCampos();

        getObjetoDAO().addCampo(getDadosCodigo());
        getObjetoDAO().addCampo(new DadosDAO("cod_tipo_exame", "Cód. Tip. Exame",
                String.valueOf(codTipoExame), DadosDAO.TIPO_LONG,
                DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("cod_cliente", "Cód. Cliente",
                String.valueOf(codCliente), DadosDAO.TIPO_LONG,
                DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("cod_pessoa_juridica", "Cód. Pessoa",
                String.valueOf(codPessoaJuridica), DadosDAO.TIPO_LONG,
                DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("data_agendamento", "Data Agendamento",
                Formatacao.ajustaDataAMD(dataAgendamento), DadosDAO.TIPO_DATE,
                DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("laudo", "Laudo", laudo,
                DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("observacoes", "Obsercações", observacoes,
                DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE));

        // Adicionando where de restrição ao administrador id = 0
        getObjetoDAO().addWhere(new DadosDAO(getObjetoDAO().getCampoID(), "",
                "0", DadosDAO.TIPO_LONG, DadosDAO.IS_DIFERENTE, DadosDAO.IS_CHAVE));
    }

    /**
     * @return the dadosCodigo
     */
    @Override
    public DadosDAO getDadosCodigo() {
        return new DadosDAO("id_exame", "Cód. Exame",
                String.valueOf(idExame), DadosDAO.TIPO_LONG,
                DadosDAO.IS_IGUAL, DadosDAO.IS_CHAVE);
    }

    /**
     * @return the dadosDescricao
     */
    @Override
    public DadosDAO getDadosDescricao() {
        return new DadosDAO(
                "data_agendamento || '-' || cod_tipo_exame || '/' || id_exame ",
                "Exame", "", DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL,
                DadosDAO.NAO_CHAVE);
    }

    @Override
    public String getFormServlet() {
        return "";
    }

    /**
     * @return the idExame
     */
    public long getIdExame() {
        return idExame;
    }

    /**
     * @param idExame the idExame to set
     */
    public void setIdExame(long idExame) {
        this.idExame = idExame;
    }

    /**
     * @return the codTipoExame
     */
    public long getCodTipoExame() {
        return codTipoExame;
    }

    /**
     * @param codTipoExame the codTipoExame to set
     */
    public void setCodTipoExame(long codTipoExame) {
        this.codTipoExame = codTipoExame;
    }

    /**
     * @return the codCliente
     */
    public long getCodCliente() {
        return codCliente;
    }

    /**
     * @param codCliente the codCliente to set
     */
    public void setCodCliente(long codCliente) {
        this.codCliente = codCliente;
    }

    /**
     * @return the codPessoaJuridica
     */
    public long getCodPessoaJuridica() {
        return codPessoaJuridica;
    }

    /**
     * @param codPessoaJuridica the codPessoaJuridica to set
     */
    public void setCodPessoaJuridica(long codPessoaJuridica) {
        this.codPessoaJuridica = codPessoaJuridica;
    }

    /**
     * @return the dataAgendamento
     */
    public Calendar getDataAgendamento() {
        return dataAgendamento;
    }

    /**
     * @param dataAgendamento the dataAgendamento to set
     */
    public void setDataAgendamento(Calendar dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    /**
     * @return the laudo
     */
    public String getLaudo() {
        return laudo;
    }

    /**
     * @param laudo the laudo to set
     */
    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the exameDAO
     */
    public ExamesDAO getExameDAO() {
        return exameDAO;
    }

    /**
     * @param exameDAO the exameDAO to set
     */
    public void setExameDAO(ExamesDAO exameDAO) {
        this.exameDAO = exameDAO;
    }
}
