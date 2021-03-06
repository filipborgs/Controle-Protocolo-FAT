/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerToken;
import com.br.fat.controleprotocolo.controller.ControllerUsuario;
import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Filipe Borges
 */
@Path("/usuario")
public class UsuarioRest extends Autenticacao {

    private ControllerUsuario control;

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response login(@FormParam("user") String login, @FormParam("senha") String senha) {
        Gson g = new Gson();
        try {
            control = new ControllerUsuario();
            Usuario user = control.autenticacao(senha, login);
            String u = g.toJson(user);
            ControllerToken ctoken = new ControllerToken();
            String token = ctoken.gerarToken(user);
            return Response.ok(u).header(HttpHeaders.AUTHORIZATION, token).header("Access-Control-Allow-Origin", "*").build();
//            return g.toJson(control.autenticacao(senha, login));
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }

    @POST
    @Secured
    @Path("/cadastrarUsuario")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarUsuario(String json, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            control = new ControllerUsuario(super.criarUser(sc));
            Usuario u = g.fromJson(json, Usuario.class);
            String user = g.toJson(control.createUsuario(u));
            return user;
        } catch (Exception ex) {
            return g.toJson(ex.getMessage());
        }
    }
    
    @GET
    @Secured
    @Path("/listarUsuario")
    @Produces("application/json")
    public String listarUsuario(@Context SecurityContext sc) {
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerUsuario(super.criarUser(sc));
            return control.getAllUsuario();
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            Gson g = new Gson();
            return g.toJson(ex.getMessage());
        }
    }

    @PUT
    @Secured
    @Path("/editarUsuario")
    @Consumes("application/json")
    public String editarUsuario(String json, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerUsuario(super.criarUser(sc));
            control.editUsuario(json);
            return g.toJson("Usuario editado com sucesso");

        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }
    
    @DELETE
    @Secured
    @Path("/deletarUsuario")
    public String deletarSituacao(@QueryParam("idUsuario") int idSituacao, @Context SecurityContext sc) {
        Gson g = new Gson();
        try {
            //cria instancia do controller passando como parametro o usuario
            control = new ControllerUsuario(super.criarUser(sc));
            control.removeUsuario(idSituacao);
            return g.toJson("Usuario deletada com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(LivroRest.class.getName()).log(Level.SEVERE, null, ex);
            return g.toJson("Erro");
        }
    }
}
