/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.dao;

/**
 * Classe DAO para Objetos Tipados.
 *
 * @author Saibel, Sergio Luis
 * @since date 20/04/2017
 *
 * @version revision 001.20170420 date 20/04/2017 author Saibel, Sergio Luís
 * reason permitir as operações com banco de dados. Esta classe serve para que o
 * objeto possa executar operaçoes de banco aparte. Este tipo de classe extende
 * o ObjetoDAO que é responsável por inserir, alterar ou excluir um objeto do
 * banco, bem como outras funcionalidades comuns a todos os objetos.
 *
 */
public class ProfissionaisDAO extends ObjetoDAO implements InterfaceDAO {

    public ProfissionaisDAO() {
        super("PROFISSIONAIS");
    }

    public int getProximoID() {
        return super.getProximoID("id_profissional");
    }

    public String getSQLSubstiturCampoDescricao(long idProfissional) {
        String complemento = "";
        if (idProfissional > 0) {
            complemento = " AND profissionais.id_profissional = " + idProfissional;
        }
        return super.getCampo("profissionais.id_profissional||'/'||"
                + "pessoas.id_pessoa||' - '||pessoas.nome||' '||pessoas.sobrenome", "pessoas",
                "pessoas.id_pessoa = profissionais.cod_pessoa" + complemento, true);
    }
}
