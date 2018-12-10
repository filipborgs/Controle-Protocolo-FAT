package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.util.DatabaseUtil;
import com.br.fat.controleprotocolo.dao.util.UsuarioDaoUtil;
import com.br.fat.controleprotocolo.model.Permissao;
import com.br.fat.controleprotocolo.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Filipe Borges
 */
public class UsuarioDAO extends DatabaseUtil {

    public Usuario validarUsuario(String login, String senha) throws SQLException, Exception {
        Usuario user = null;
        super.getCon();
        stmt = con.prepareStatement("SELECT * FROM usuario u WHERE u.user = ?");
        stmt.setString(1, login);
        rs = stmt.executeQuery();

        //obtendo resultado da consulta
        if (rs.next()) {
            //verificando se o usuario informou a senha correta
            if (senha.equals(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_SENHA))) {
                user = this.createObjUsuarioDao(rs);
            } else {
                throw new Exception("Usuario ou senha invalida");
            }
        } else {
            throw new Exception("Usuario ou senha invalida");
        }
        return user;
    }

    public Usuario createObjUsuarioDao(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();

        user.setId(rs.getInt(UsuarioDaoUtil.USUARIO_ATRIBUTO_ID));
        user.setNome(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_NOME));
        user.setStatus(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_STATUS).charAt(0));
        user.setPermissao(new Permissao());
        user.getPermissao().setDelete(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_DELETAR).charAt(0));
        user.getPermissao().setWrite(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_INSERIR).charAt(0));
        user.getPermissao().setUpdate(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_EDITAR).charAt(0));
        user.getPermissao().setRead(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_LER).charAt(0));

        return user;
    }

    public Usuario createUsuario(Usuario u) throws Exception {
        String sql = "INSERT INTO usuario (" + UsuarioDaoUtil.USUARIO_ATRIBUTO_NOME + "," + UsuarioDaoUtil.USUARIO_ATRIBUTO_USER + ","
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_SENHA + "," + UsuarioDaoUtil.USUARIO_ATRIBUTO_EDITAR + ","
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_INSERIR + "," + UsuarioDaoUtil.USUARIO_ATRIBUTO_DELETAR + ","
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_LER + "," + UsuarioDaoUtil.USUARIO_ATRIBUTO_STATUS
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getUser());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, String.valueOf(u.getPermissao().getUpdate()));
            stmt.setString(5, String.valueOf(u.getPermissao().getWrite()));
            stmt.setString(6, String.valueOf(u.getPermissao().getDelete()));
            stmt.setString(7, String.valueOf("S"));
            stmt.setString(8, String.valueOf(u.getStatus()));

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                u.setId(rs.getInt(1));
                return u;
            } else {
                throw new Exception();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }

    }

    public List<Usuario> selectAllUsuarios() throws Exception {
        String sql = "SELECT * FROM usuario WHERE " + UsuarioDaoUtil.USUARIO_ATRIBUTO_EXCLUIDO + " = 'S'";
        List<Usuario> listaUser = new ArrayList<>();
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaUser.add(createObjUsuarioDao(rs));
            }
            return listaUser;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void updateUsuario(Usuario u) throws Exception {
        String sql = "UPDATE usuario SET " + UsuarioDaoUtil.USUARIO_ATRIBUTO_NOME + "= ?,"
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_USER + "= ?," + UsuarioDaoUtil.USUARIO_ATRIBUTO_SENHA + "= ?,"
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_EDITAR + "= ?," + UsuarioDaoUtil.USUARIO_ATRIBUTO_INSERIR + "= ?,"
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_DELETAR + "= ?," + UsuarioDaoUtil.USUARIO_ATRIBUTO_LER + "= ?,"
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_STATUS + "= ? WHERE " + UsuarioDaoUtil.USUARIO_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getUser());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, String.valueOf(u.getPermissao().getUpdate()));
            stmt.setString(5, String.valueOf(u.getPermissao().getWrite()));
            stmt.setString(6, String.valueOf(u.getPermissao().getDelete()));
            stmt.setString(7, String.valueOf(u.getPermissao().getRead()));
            stmt.setString(8, String.valueOf(u.getStatus()));
            stmt.setInt(9, u.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void deleteUsuario(int id) throws Exception {
        String sql = "UPDATE usuario SET " + UsuarioDaoUtil.USUARIO_ATRIBUTO_EXCLUIDO + " = 'S' WHERE "
                + UsuarioDaoUtil.USUARIO_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareCall(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }
}
