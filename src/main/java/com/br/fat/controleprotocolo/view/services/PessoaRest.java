/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerPessoa;
import com.br.fat.controleprotocolo.model.Pessoa;
import com.google.gson.Gson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Filipe Borges
 */
@Path("/pessoa")
public class PessoaRest {

    private final ControllerPessoa control = new ControllerPessoa();
    private Gson g = new Gson();

    @POST
    @Path("/cadastrarPessoa")
    @Consumes("application/json")
    public String cadastrarPessoa(String json) {
        try {
            return control.createPessoa(json);
        } catch (Exception ex) {
            Logger.getLogger(PessoaRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Path("/listarPessoas")
    @Produces("application/json")
    public String listarLivros() {
        Gson g = new Gson();

        try {
            List lista = control.getAllPessoa();
            return g.toJson(lista);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(ex.getMessage());
        }
    }
}
