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
public class ControllerLivroRegistro {

    private LivroRegistroDao ldao = new LivroRegistroDao();

    public LivroRegistros insertLivro(LivroRegistros l) throws Exception {
        if (l.getNome().equals("")) {
            throw new Exception();
        } else {
            return ldao.insertLivro(l);
        }
    }

    public List<LivroRegistros> getAllLivros() throws Exception {
        return ldao.selectAllLivroRegistros();
    }

}
