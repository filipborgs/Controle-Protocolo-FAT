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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Filipe Borges
 */
@Path("/motivo")
public class MotivoRest extends Autenticacao {

    private ControllerMotivo control;
    private final Gson g = new Gson();

    @POST
    @Secured
    @Path("/cadastrarMotivo")
    public String cadastrarMotivo(String json, @Context SecurityContext sc) {
        try {
            control = new ControllerMotivo(super.criarUser(sc));
            Motivo m = g.fromJson(json, Motivo.class);
            m = control.createMotivo(m);
            return g.toJson(m);
        } catch (Exception ex) {
            Logger.getLogger(MotivoRest.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @GET
    @Secured
    @Path("/listarMotivo")
    public String listarMotivos(@Context SecurityContext sc) {
        try {
            control = new ControllerMotivo(super.criarUser(sc));
            return g.toJson(control.getAllMotivo());
        } catch (Exception ex) {
            Logger.getLogger(MotivoRest.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @DELETE
    @Secured
    @Path("/deletarMotivo")
    public String deletarMotivo(@QueryParam("idMotivo") int idMotivo, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            control = new ControllerMotivo(super.criarUser(sc));
            control.removeMotivo(idMotivo);
            return g.toJson("Motivo deletado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

    @PUT
    @Secured
    @Path("/editarLivro")
    @Consumes("application/json")
    public String editarMotivo(String json, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            control = new ControllerMotivo(super.criarUser(sc));
            control.editMotivo(json);
            return g.toJson("Motivo editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");

        }
    }

}
