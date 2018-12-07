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
public class ControllerPessoa extends Controller {

    private final PessoaDAO pdao = new PessoaDAO();

    public String createPessoa(String jsonPessoa) throws Exception {

        Pessoa p = gson.fromJson(jsonPessoa, Pessoa.class);

        if (p.getNome().equals("") || p.getSetor().equals("")) {
            throw new Exception();
        } else {
            p = pdao.insertPessoa(p);
            return gson.toJson(p);
        }
    }

    public List<Pessoa> getAllPessoa() throws Exception {
        return pdao.selectAllPessoa();
    }
}
