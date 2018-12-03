/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerLivroRegistro;
import com.br.fat.controleprotocolo.model.LivroRegistros;
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
@Path("/livro")
public class LivroRest {

    private ControllerLivroRegistro control = new ControllerLivroRegistro();

    @POST
    @Path("/cadastrarLivro")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarUsuario(String json) {
        Gson g = new Gson();
        try {
            LivroRegistros l = g.fromJson(json, LivroRegistros.class);
            control.insertLivro(l);
            return g.toJson(l);

            //return g.toJson("Cadastrado com sucesso");
        } catch (Exception ex) {
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Path("/listarLivros")
    @Produces("application/json")
    public String listarLivros() {
        Gson g = new Gson();

        try {
            List lista = control.getAllLivros();
            return g.toJson(lista);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson(ex.getMessage());
        }
    }
}
