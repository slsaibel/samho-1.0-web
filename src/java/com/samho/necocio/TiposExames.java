/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.necocio;

import com.samho.dao.DadosDAO;
import com.samho.dao.ObjetoDAO;
import com.samho.dao.TiposExamesDAO;
import java.util.Objects;

/**
 * Classe de Objeto.
 *
 * @author Saibel, Sergio Luis
 * @since date 31/03/2017
 *
 * @version revision 001.20170331 date 31/03/2017 author Saibel, Sergio Luís
 * reason Instanciar um objeto com métodos e atributos necessários.
 */
public final class TiposExames extends Objeto implements InterfaceObj {

    // Declaração de atributos
    private long idTipoExame;
    private String descricao;
    private String observacoes;
    private boolean ativo;

    private TiposExamesDAO TipoExameDAO;

    // Construtor padrão
    public TiposExames() {
        idTipoExame = -1;
        descricao = "";
        observacoes = "";
        ativo = true;

        TipoExameDAO = new TiposExamesDAO();

        adicionarCampos();
    }

    // Construtor
    public TiposExames(TiposExames situacao) {
        idTipoExame = situacao.getIdTipoExame();
        descricao = situacao.getDescricao();
        observacoes = situacao.getObservacoes();
        ativo = situacao.isAtivo();

        TipoExameDAO = situacao.getTipoExameDAO();

        adicionarCampos();
    }

    // Construtor
    public TiposExames(long idSituacao, String descricao,
            String observacoes, boolean ativo) {
        this.idTipoExame = idSituacao;
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.ativo = ativo;

        this.TipoExameDAO = new TiposExamesDAO();

        adicionarCampos();
    }

    @Override
    public boolean equals(Object object) {
        boolean retorno = false;

        if (object instanceof TiposExames) {
            TiposExames other = (TiposExames) object;
            if (idTipoExame == other.getIdTipoExame()) {
                retorno = true;
            }
        }

        return retorno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.idTipoExame ^ (this.idTipoExame >>> 32));
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.observacoes);
        hash = 59 * hash + (this.ativo ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.TipoExameDAO);
        return hash;
    }

    @Override
    public ObjetoDAO getObjetoDAO() {
        return TipoExameDAO;
    }

    @Override
    public void adicionarCampos() {
        removerCampos();

        getObjetoDAO().addCampo(getDadosCodigo());
        getObjetoDAO().addCampo(getDadosDescricao());
        getObjetoDAO().addCampo(new DadosDAO("observacoes", "Observação",
                observacoes, DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL,
                DadosDAO.NAO_CHAVE));
        getObjetoDAO().addCampo(new DadosDAO("ativo", "Ativo",
                String.valueOf(ativo), DadosDAO.TIPO_BOOLEAN, DadosDAO.IS_IGUAL,
                DadosDAO.NAO_CHAVE));

        // Adicionando where de restrição ao administrador id = 0
        getObjetoDAO().addWhere(new DadosDAO(getObjetoDAO().getCampoID(), "",
                "0", DadosDAO.TIPO_LONG, DadosDAO.IS_DIFERENTE, DadosDAO.IS_CHAVE));
    }

    /**
     * @return the dadosCodigo
     */
    @Override
    public DadosDAO getDadosCodigo() {
        return new DadosDAO("id_tipo_exame", "Cód. Tipo Exame",
                String.valueOf(idTipoExame), DadosDAO.TIPO_LONG, DadosDAO.IS_IGUAL,
                DadosDAO.IS_CHAVE);
    }

    /**
     * @return the dadosDescricao
     */
    @Override
    public DadosDAO getDadosDescricao() {
        return new DadosDAO("descricao", "Descrição", descricao,
                DadosDAO.TIPO_STRING, DadosDAO.IS_IGUAL, DadosDAO.NAO_CHAVE);
    }

    @Override
    public String getFormServlet() {
        return "";
    }

    /**
     * @return the idTipoExame
     */
    public long getIdTipoExame() {
        return idTipoExame;
    }

    /**
     * @param idTipoExame the idTipoExame to set
     */
    public void setIdTipoExame(long idTipoExame) {
        this.idTipoExame = idTipoExame;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the TipoExameDAO
     */
    public TiposExamesDAO getTipoExameDAO() {
        return TipoExameDAO;
    }

    /**
     * @param TipoExameDAO the TipoExameDAO to set
     */
    public void setTipoExameDAO(TiposExamesDAO TipoExameDAO) {
        this.TipoExameDAO = TipoExameDAO;
    }
}
