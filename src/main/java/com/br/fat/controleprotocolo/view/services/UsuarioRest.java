/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerUsuario;
import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Filipe Borges
 */
@Path("/usuario")
public class UsuarioRest {

    private ControllerUsuario control = new ControllerUsuario();

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public String login(@FormParam("user") String login, @FormParam("senha") String senha) {
        Gson g = new Gson();
        try {
            Usuario user = control.autenticacao(senha, login);
            String u = g.toJson(user);

            return u;
        } catch (Exception ex) {
            return g.toJson(ex.getMessage());
        }
    }

    @POST
    @Path("/cadastrarUsuario")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarUsuario(String json) {
        Gson g = new Gson();
        try {
            Usuario u = g.fromJson(json, Usuario.class);
            String user = g.toJson(control.createUsuario(u));

            return user;
        } catch (Exception ex) {
            return g.toJson(ex.getMessage());
        }
    }

//    @GET
//    @Produces("application/json")
//    public String getCursos(@QueryParam("cursoid") int cursoid) {
//        Gson g = new Gson();
//        try {
//            List lista = control.getUsuariosByCurso(cursoid);
//            String jsonLista = g.toJson(lista);
//            return jsonLista;
//        } catch (Exception ex) {
//            return g.toJson(ex.getMessage());
//        }
//    }
}
