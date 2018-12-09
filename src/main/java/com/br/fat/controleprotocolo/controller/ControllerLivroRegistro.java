/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.LivroRegistroDao;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import com.br.fat.controleprotocolo.model.Usuario;

/**
 *
 * @author Filipe Borges
 */
public class ControllerLivroRegistro extends Controller {

    private final LivroRegistroDao ldao = new LivroRegistroDao();

    public ControllerLivroRegistro(Usuario u) {
        super(u);
    }

    public String insertLivro(String jsonLivro) throws Exception {
        //Verifica se o usuario tem permissao para realizar a acao
        this.verifyPermission(this.user.getPermissao().getWrite());

        LivroRegistros l = gson.fromJson(jsonLivro, LivroRegistros.class);

        if (l.getNome().equals("")) {
            throw new Exception();
        } else {
            return gson.toJson(ldao.insertLivro(l));
        }
    }

    public String getAllLivros() throws Exception {
        this.verifyPermission(this.user.getPermissao().getRead());
        return gson.toJson(ldao.selectAllLivroRegistros());
    }

    public void removeLivro(int idLivro) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());
        if (idLivro <= 0) {
            throw new Exception();
        } else {
            ldao.deleteLivro(idLivro);
        }
    }

    public void editLivro(String jsonLivro) throws Exception {
        this.verifyPermission(this.user.getPermissao().getUpdate());
        LivroRegistros l = gson.fromJson(jsonLivro, LivroRegistros.class);
        if (l.getId() <= 0) {
            throw new Exception();
        }
        ldao.updateLivro(l);
    }

}
