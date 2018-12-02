package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.util.DatabaseUtil;
import com.br.fat.controleprotocolo.dao.util.UsuarioDaoUtil;
import com.br.fat.controleprotocolo.model.Permissao;
import com.br.fat.controleprotocolo.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        user.setSenha(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_SENHA));
        user.setStatus(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_STATUS).charAt(0));
        user.setPermissao(new Permissao());
//        user.getPermissao().setDelete((UsuarioDaoUtil.USUARIO_ATRIBUTO_DELETAR).charAt(0));
        user.getPermissao().setWrite(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_INSERIR).charAt(0));
        user.getPermissao().setUpdate(rs.getString(UsuarioDaoUtil.USUARIO_ATRIBUTO_EDITAR).charAt(0));

        return user;
    }

//    public Usuario createUsuario(Usuario u) {
//        String sql = "INSERT INTO usuarios (nome_usuario, email_usuario, senha_usuario, tipo_usuario, status_usuario)"
//                + " VALUES (?, ?, ?, ?, ?)";
//        try {
//            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, u.getNome());
//            stmt.setString(2, u.getEmail());
//            stmt.setString(3, u.getSenha());
//            stmt.setString(4, u.getTipo().getTipo());
//            stmt.setString(5, u.getStatus().getStatus());
//            stmt.executeUpdate();
//            rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                u.setId(rs.getInt(1));
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showInputDialog(ex.getMessage());
//        }
//
//        return u;
//    }
//
//    public List getListaTiposUsuario() throws SQLException {
//        List<Tipo> listaTipos = null;
//
//        super.getCon();
//        stmt = con.prepareStatement("SELECT * FROM tipos_usuario");
//        rs = stmt.executeQuery();
//        while (rs.next()) {
//            Tipo t = new Tipo();
//            listaTipos = new ArrayList<>();
//            t.setDescricao(rs.getString(UsuarioDaoUtil.TIPO_ATRIBUTO_DESCRICAO));
//            t.setTipo(rs.getString(UsuarioDaoUtil.TIPO_ATRIBUTO_TIPO));
//            listaTipos.add(t);
//        }
//        return listaTipos;
//    }
//
//    public List selectUsuariosByCurso(int idCurso) {
//        String sql = "SELECT * FROM view_usuario_curso WHERE " + CursoDaoUtil.CURSO_ATRIBUTO_ID + " = ?";
//        List<Usuario> listaUser = new ArrayList<>();
//        super.getCon();
//        try {
//            stmt = con.prepareStatement(sql);
//            stmt.setInt(1, idCurso);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                listaUser.add(createObjUsuarioDao(rs, 'U'));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listaUser;
//    }

}
