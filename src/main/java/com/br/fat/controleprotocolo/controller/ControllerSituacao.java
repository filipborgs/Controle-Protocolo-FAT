/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.SituacaoDAO;
import com.br.fat.controleprotocolo.model.Pessoa;
import com.br.fat.controleprotocolo.model.Situacao;
import com.br.fat.controleprotocolo.model.Usuario;
import java.util.List;

/**
 *
 * @author lippy
 */
public class ControllerSituacao extends Controller{
     private final SituacaoDAO sdao = new SituacaoDAO();

    public ControllerSituacao(Usuario u) {
        super(u);
    }

    public String createSituacao(String jsonSituacao) throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        Situacao s = gson.fromJson(jsonSituacao, Situacao.class);

        if (s.getSituacao().equals("")) {
            throw new Exception();
        } else {
            s = sdao.insertSituacao(s);
            return gson.toJson(s);
        }
    }

    public String getAllSituacao() throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        return gson.toJson(sdao.selectAllSituacao());
    }

    public void removeSituacao(int idSituacao) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());

        if (idSituacao <= 0) {
            throw new Exception();
        } else {
            sdao.deleteSituacao(idSituacao);
        }
    }

    public void editSituacao(String jsonSituacao) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());
        Situacao s = gson.fromJson(jsonSituacao, Situacao.class);
        if (s.getId() <= 0) {
            throw new Exception();
        }
        sdao.updateSituacao(s);
    }
}
