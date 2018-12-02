/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.LivroRegistroDao;
import com.br.fat.controleprotocolo.model.LivroRegistros;

/**
 *
 * @author Filipe Borges
 */
public class ControllerLivroRegistro {

    private LivroRegistroDao ldao = new LivroRegistroDao();

    public void insertLivro(LivroRegistros l) throws Exception {
        if (l.getNome().equals("")) {
            throw new Exception();
        } else {
            ldao.insertLivro(l);
        }
    }

}
