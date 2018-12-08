/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.MotivoDAO;
import com.br.fat.controleprotocolo.model.Motivo;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerMotivo extends Controller {

    private final MotivoDAO mdao = new MotivoDAO();

    public Motivo createMotivo(Motivo m) throws Exception {
        if (m.getMotivo().equals("")) {
            throw new Exception();
        } else {
            return mdao.insertMotivo(m);
        }
    }

    public List<Motivo> getAllMotivo() throws Exception {
        return mdao.selectAllMotivo();
    }
    
    public void removeMotivo(int idMotivo) throws Exception {
        if (idMotivo <= 0) {
            throw new Exception();
        } else {
            mdao.deleteMotivo(idMotivo);
        }
    }

    public void editMotivo(String jsonMotivo) throws Exception {
        Motivo m = gson.fromJson(jsonMotivo, Motivo.class);
        mdao.updateMotivo(m);
    }
}
