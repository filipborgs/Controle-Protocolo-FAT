/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerLivroRegistro;
import com.br.fat.controleprotocolo.controller.ControllerSituacao;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author lippy
 */
@Path("/situacao")
public class SituacaoRest extends Autenticacao {

    private ControllerSituacao control;

    @POST
    @Secured
    @Path("/cadastrarSituacao")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarSituacao(String json, @Context SecurityContext sc) {
        try {
            control = new ControllerSituacao(super.criarUser(sc));
            return control.createSituacao(json);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Secured
    @Path("/listarSituacao")
    @Produces("application/json")
    public String listarSituacao(@Context SecurityContext sc) {
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerSituacao(super.criarUser(sc));
            return control.getAllSituacao();
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @DELETE
    @Secured
    @Path("/deletarSituacao")
    public String deletarSituacao(@QueryParam("idSituacao") int idSituacao, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerSituacao(super.criarUser(sc));
            control.removeSituacao(idSituacao);
            return g.toJson("Situacao deletada com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

    @PUT
    @Secured
    @Path("/editarSituacao")
    @Consumes("application/json")
    public String editarSituacao(String json, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerSituacao(super.criarUser(sc));
            control.editSituacao(json);
            return g.toJson("Situacao editada com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }
}
