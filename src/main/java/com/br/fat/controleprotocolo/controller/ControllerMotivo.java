/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.MotivoDAO;
import com.br.fat.controleprotocolo.model.Motivo;
import com.br.fat.controleprotocolo.model.Usuario;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerMotivo extends Controller {

    private final MotivoDAO mdao = new MotivoDAO();

    public ControllerMotivo(Usuario u) {
        super(u);
    }

    public Motivo createMotivo(Motivo m) throws Exception {
        //Verifica se o usuario tem permissao para realizar a acao
        this.verifyPermission(this.user.getPermissao().getWrite());

        if (m.getMotivo().equals("")) {
            throw new Exception();
        } else {
            return mdao.insertMotivo(m);
        }
    }

    public List<Motivo> getAllMotivo() throws Exception {
        //Verifica se o usuario tem permissao para realizar a acao
        this.verifyPermission(this.user.getPermissao().getWrite());
        return mdao.selectAllMotivo();
    }

    public void removeMotivo(int idMotivo) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());

        if (idMotivo <= 0) {
            throw new Exception();
        } else {
            mdao.deleteMotivo(idMotivo);
        }
    }

    public void editMotivo(String jsonMotivo) throws Exception {
        this.verifyPermission(this.user.getPermissao().getUpdate());
        Motivo m = gson.fromJson(jsonMotivo, Motivo.class);
        if (m.getId() <= 0) {
            throw new Exception();
        }
        mdao.updateMotivo(m);
    }
}
