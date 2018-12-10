/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.UsuarioDAO;
import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Filipe Borges
 */
public class ControllerUsuario extends Controller {

    private UsuarioDAO udao = new UsuarioDAO();

    public ControllerUsuario(Usuario u) {
        super(u);
    }

    public ControllerUsuario() {
    }

    public Usuario autenticacao(String senha, String login) throws Exception {
        Usuario user = null;

        //verifica se o login ou senha vieram vazios ou incorretos
        if (login.equals("") || senha.equals("") || senha.length() > 8 || login.length() > 60) {
            throw new Exception("Usuario ou senha invalida");
        } else {
            //envia os dados para fazer a verificacao no banco de dados
            udao = new UsuarioDAO();

            //se o retorno for nulo o login nao foi feito com sucesso
            user = udao.validarUsuario(login, senha);

            return user;
        }
    }

    public Usuario createUsuario(Usuario u) throws Exception {
        super.verifyPermission(u.getPermissao().getWrite());
        if (u.getNome() == null || u.getNome().isEmpty() || u.getUser() == null || u.getUser().isEmpty()
                || u.getSenha() == null || u.getSenha().isEmpty() || u.getPermissao() == null) {
            throw new Exception();
        } else {
            return udao.createUsuario(u);
        }
    }

    public String getAllUsuario() throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        return gson.toJson(udao.selectAllUsuarios());
    }

    public void editUsuario(String jsonUsuario) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());
        Usuario u = gson.fromJson(jsonUsuario, Usuario.class);
        if (u.getId() <= 0) {
            throw new Exception();
        }
        udao.updateUsuario(u);
    }

    public void removeUsuario(int idUsuario) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());

        if (idUsuario <= 0) {
            throw new Exception();
        } else {
            udao.deleteUsuario(idUsuario);
        }
    }
}
