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
public class ControllerMotivo {

    private MotivoDAO mdao = new MotivoDAO();

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
}
