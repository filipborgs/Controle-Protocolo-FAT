/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerLivroRegistro;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
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
}
