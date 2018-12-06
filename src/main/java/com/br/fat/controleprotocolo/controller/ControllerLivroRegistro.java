/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.LivroRegistroDao;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerLivroRegistro extends Controller {

    private final LivroRegistroDao ldao = new LivroRegistroDao();

    public String insertLivro(String jsonLivro) throws Exception {
        LivroRegistros l = gson.fromJson(jsonLivro, LivroRegistros.class);

        if (l.getNome().equals("")) {
            throw new Exception();
        } else {
            return gson.toJson(ldao.insertLivro(l));
        }
    }

    public String getAllLivros() throws Exception {

        return gson.toJson(ldao.selectAllLivroRegistros());
    }

    public void removeLivro(int idLivro) throws Exception {
        if (idLivro <= 0) {
            throw new Exception();
        } else {
            ldao.deleteLivro(idLivro);
        }
    }

}
