/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.PessoaDAO;
import com.br.fat.controleprotocolo.model.Pessoa;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerPessoa {

    private PessoaDAO pdao = new PessoaDAO();

    public Pessoa createPessoa(Pessoa p) throws Exception {
        if (p.getNome().equals("") || p.getSetor().equals("")) {
            throw new Exception();
        } else {
            return pdao.insertPessoa(p);
        }
    }

    public List<Pessoa> getAllPessoa() throws Exception {
        return pdao.selectAllPessoa();
    }
}
