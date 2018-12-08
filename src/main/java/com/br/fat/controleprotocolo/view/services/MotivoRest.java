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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Filipe Borges
 */
@Path("/motivo")
public class MotivoRest {

    private final ControllerMotivo control = new ControllerMotivo();
    private final Gson g = new Gson();

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

    @DELETE
    @Path("/deletarMotivo")
    public String deletarMotivo(@QueryParam("idMotivo") int idMotivo) {
        Gson g = new Gson();
        try {
            control.removeMotivo(idMotivo);
            return g.toJson("Motivo deletado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

    @PUT
    @Path("/editarLivro")
    @Consumes("application/json")
    public String editarMotivo(String json) {
        Gson g = new Gson();
        try {
            control.editMotivo(json);
            return g.toJson("Motivo editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");

        }
    }

}
