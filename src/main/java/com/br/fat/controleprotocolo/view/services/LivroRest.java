/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerLivroRegistro;
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
 * @author Filipe Borges
 */
@Path("/livro")
public class LivroRest extends Autenticacao {

    private ControllerLivroRegistro control;

    @POST
    @Secured
    @Path("/cadastrarLivro")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarUsuario(String json, @Context SecurityContext sc) {
        try {
            control = new ControllerLivroRegistro(super.criarUser(sc));
            return control.insertLivro(json);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Secured
    @Path("/listarLivros")
    @Produces("application/json")
    public String listarLivros(@Context SecurityContext sc) {
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerLivroRegistro(super.criarUser(sc));
            return control.getAllLivros();
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @DELETE
    @Secured
    @Path("/deletarLivro")
    public String deletarLivro(@QueryParam("idLivro") int idLivro, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerLivroRegistro(super.criarUser(sc));
            control.removeLivro(idLivro);
            return g.toJson("Livro deletado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }

    @PUT
    @Path("/editarLivro")
    @Consumes("application/json")
    public String editarLivro(String json, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerLivroRegistro(super.criarUser(sc));
            control.editLivro(json);
            return g.toJson("Livro editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");

        }
    }

}
