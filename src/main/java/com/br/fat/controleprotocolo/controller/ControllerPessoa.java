/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.PessoaDAO;
import com.br.fat.controleprotocolo.model.Pessoa;
import com.br.fat.controleprotocolo.model.Usuario;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerPessoa extends Controller {

    private final PessoaDAO pdao = new PessoaDAO();

    public ControllerPessoa(Usuario u) {
        super(u);
    }

    public String createPessoa(String jsonPessoa) throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        Pessoa p = gson.fromJson(jsonPessoa, Pessoa.class);

        if (p.getNome().equals("") || p.getSetor().equals("")) {
            throw new Exception();
        } else {
            p = pdao.insertPessoa(p);
            return gson.toJson(p);
        }
    }

    public List<Pessoa> getAllPessoa() throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        return pdao.selectAllPessoa();
    }

    public void removePessoa(int idPessoa) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());

        if (idPessoa <= 0) {
            throw new Exception();
        } else {
            pdao.deletePessoa(idPessoa);
        }
    }

    public void editPessoa(String jsonPessoa) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());
        Pessoa p = gson.fromJson(jsonPessoa, Pessoa.class);
        if (p.getId() <= 0) {
            throw new Exception();
        }
        pdao.updatePessoa(p);
    }
}
