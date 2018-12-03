/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerMotivo;
import com.br.fat.controleprotocolo.model.Motivo;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Filipe Borges
 */
@Path("/motivo")
public class MotivoRest {

    private ControllerMotivo control = new ControllerMotivo();
    private Gson g = new Gson();

    @POST
    @Path("/cadastrarMotivo")
    public String cadastrarMotivo(String json) {
        Motivo m = g.fromJson(json, Motivo.class);
        try {
            m = control.createMotivo(m);
            return g.toJson(m);
        } catch (Exception ex) {
            Logger.getLogger(MotivoRest.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @GET
    @Path("/listarMotivo")
    public String listarMotivos() {
        try {
            return g.toJson(control.getAllMotivo());
        } catch (Exception ex) {
            Logger.getLogger(MotivoRest.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

}
