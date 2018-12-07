/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.UsuarioDAO;
import com.br.fat.controleprotocolo.model.Usuario;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

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

//            FileInputStream serviceAccount = new FileInputStream("D:\\Users\\Filipe Borges\\Google Drive\\Documentos\\NetBeansProjects\\ControleProtocolo\\src\\main\\resources\\controleprotocolo-39b32-firebase-adminsdk-rer4f-cb86bfa396.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//            String uid = Integer.toString(user.getId());
//            Map<String, Object> additionalClaims = new HashMap<>();
//            additionalClaims.put("permission", user.getPermissao());
//
//            String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
//            return customToken;
return user;
        }
    }

    public Usuario createUsuario(Usuario u) throws Exception {
        if (u.getNome() == null || u.getNome().isEmpty() || u.getUser() == null || u.getUser().isEmpty()
                || u.getSenha() == null || u.getSenha().isEmpty() || u.getPermissao() == null) {
            throw new Exception();
        } else {
            return udao.createUsuario(u);
        }
    }
}
