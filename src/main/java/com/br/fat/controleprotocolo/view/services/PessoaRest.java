/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerPessoa;
import com.google.gson.Gson;
import java.util.List;
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
 * @author Filipe Borges
 */
@Path("/pessoa")
public class PessoaRest extends Autenticacao {

    private ControllerPessoa control;
    private Gson g = new Gson();

    @POST
    @Secured
    @Path("/cadastrarPessoa")
    @Consumes("application/json")
    public String cadastrarPessoa(String json, @Context SecurityContext sc) {
        try {
            control = new ControllerPessoa(super.criarUser(sc));
            return control.createPessoa(json);
        } catch (Exception ex) {
            Logger.getLogger(PessoaRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Secured
    @Path("/listarPessoas")
    @Produces("application/json")
    public String listarLivros(@Context SecurityContext sc) {

        try {
            control = new ControllerPessoa(super.criarUser(sc));
            List lista = control.getAllPessoa();
            return g.toJson(lista);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(ex.getMessage());
        }
    }

    @DELETE
    @Secured
    @Path("/deletarPessoa")
    public String deletarPessoa(@QueryParam("idPessoa") int idPessoa, @Context SecurityContext sc) {
        try {
            control = new ControllerPessoa(super.criarUser(sc));
            control.removePessoa(idPessoa);
            return g.toJson("Pessoa deletado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

    @PUT
    @Secured
    @Path("/editarPessoa")
    @Consumes("application/json")
    public String editarLivro(String json) {
        try {
            control.editPessoa(json);
            return g.toJson("Pessoa editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

}
