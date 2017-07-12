/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.dao;

/**
 * Classe DAO para Objetos Tipados.
 *
 * @author Sergio
 * @since date 22/04/2017
 *
 * @version revision 001.20170422 date 22/04/2017 author Saibel, Sergio Luís
 * reason permitir as operações com banco de dados para que um objeto possa ser
 * incluído, alterado, consultado ou excluído(inativado).
 *
 * A exclusão não é feita no banco de dados, para isso apenas o atributo
 * "situacao" deve ser setado.
 */
public class ClientesDAO extends ObjetoDAO implements InterfaceDAO {

    public ClientesDAO() {
        super("CLIENTES");
    }

    public int getProximoID() {
        return super.getProximoID("id_cliente");
    }

    public String getSQLSubstiturCampoDescricao(long idCliente) {
        String complemento = "";
        if (idCliente > 0) {
            complemento = " AND clientes.id_cliente = " + idCliente;
        }
        return super.getCampo("clientes.id_cliente||'/'||"
                + "pessoas.id_pessoa||' - '||pessoas.nome||' '||pessoas.sobrenome", "pessoas",
                "pessoas.id_pessoa = clientes.cod_pessoa" + complemento, true);
    }
}
