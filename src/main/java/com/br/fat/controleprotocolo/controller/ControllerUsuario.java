/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.UsuarioDAO;
import com.br.fat.controleprotocolo.model.Usuario;

/**
 *
 * @author Filipe Borges
 */
public class ControllerUsuario {

    private UsuarioDAO udao = new UsuarioDAO();

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
        }
        return user;
    }
}
