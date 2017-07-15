/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samho.dao;

import com.samho.necocio.AcoesUsuarios;
import com.samho.necocio.Principal;
import com.samho.necocio.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sergio
 */
public class LoginDAO {

    /**
     *
     * @param login
     * @param senha
     * @return
     */
    public static boolean checkLogin(String login, String senha) {
        boolean check = false;
        String log = login == null ? "" : login;
        String pass = senha == null ? "" : senha;

        Principal.usuario = new Usuarios();

        if (ConexoesDB.getInstance().getConnection() != null) {
            try {
                String sql
                        = " SELECT "
                        + "      id_usuario, cod_funcionario, login, "
                        + "      senha, administrador "
                        + " FROM usuarios "
                        + "WHERE login = '" + log + "'"
                        + "  AND senha = '" + pass + "';";

                PreparedStatement pst
                        = ConexoesDB.getInstance().getConnection().prepareStatement(sql);

                ResultSet rs = pst.executeQuery();
                if (rs.isBeforeFirst()) {
                    if (rs.next()) {
                        Principal.usuario.setIdUsuario(rs.getLong("id_usuario"));
                        Principal.usuario.setCodFuncionario(rs.getLong("cod_funcionario"));
                        Principal.usuario.setLogin(rs.getString("login"));
                        Principal.usuario.setSenha(rs.getString("senha"));
                        Principal.usuario.setAdministrador(rs.getBoolean("administrador"));
                        
                        check = true;
                        ConexoesDB.setIsInvasao(false);
                    }
                } else {
                    if (!log.equals("") || !pass.equals("")) {
                        Principal.usuario.setIdUsuario(0);
                        Principal.usuario.setCodFuncionario(0);
                        Principal.usuario.setLogin(login);
                        Principal.usuario.setSenha(senha);
                        Principal.usuario.setAdministrador(false);
                        
                        check = true;
                        ConexoesDB.setIsInvasao(true);
                    }
                }

                sql
                        = " SELECT "
                        + "     id_acao_usuario, cod_acao, cod_usuario, "
                        + "     incluir, alterar, excluir, consultar "
                        + " FROM acoes_usuarios WHERE cod_usuario = "
                        + Principal.usuario.getIdUsuario() + " ORDER BY cod_acao;";

                pst = ConexoesDB.getInstance().getConnection().prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        AcoesUsuarios acoesUsuario = new AcoesUsuarios();

                        acoesUsuario.setIdAcaoUsuario(rs.getLong("id_acao_usuario"));
                        acoesUsuario.setCodAcao(rs.getLong("cod_acao"));
                        acoesUsuario.setCodUsuario(rs.getLong("cod_usuario"));
                        acoesUsuario.setIncluir(rs.getBoolean("incluir"));
                        acoesUsuario.setAlterar(rs.getBoolean("alterar"));
                        acoesUsuario.setExcluir(rs.getBoolean("excluir"));
                        acoesUsuario.setConsultar(rs.getBoolean("consultar"));

                        Principal.usuario.getPermissoes().add(acoesUsuario);
                    }
                }
            } catch (SQLException eSQL) {
                System.err.println(eSQL);
            }
        }

        return check;
    }
}
