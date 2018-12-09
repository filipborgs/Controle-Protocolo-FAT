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
public class LivroRest {

    private ControllerLivroRegistro control = new ControllerLivroRegistro();

    @POST
    @Path("/cadastrarLivro")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarUsuario(String json) {
        try {
            return control.insertLivro(json);
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @GET
    @Path("/listarLivros")
    @Produces("application/json")
    public String listarLivros() {
        try {
            return control.getAllLivros();
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }
//    @GET
//    @Secured
//    @Path("/listarLivros")
//    @Produces("application/json")
//    public String listarLivros(@Context SecurityContext sc) {
//        try {
//
//            String s = sc.getUserPrincipal().getName();
//            System.out.println(s);
//            return control.getAllLivros();
//        } catch (Exception ex) {
//            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
//            Gson g = new Gson();
//            return g.toJson(ex.getMessage());
//        }
//    }

    @DELETE
    @Path("/deletarLivro")
    public String deletarLivro(@QueryParam("idLivro") int idLivro) {
        Gson g = new Gson();
        try {
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
    public String editarLivro(String json) {
        Gson g = new Gson();
        try {
            control.editLivro(json);
            return g.toJson("Livro editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");

        }
    }

}
