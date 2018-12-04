/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerProtocolo;
import com.br.fat.controleprotocolo.model.Protocolo;
import com.google.gson.Gson;
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
@Path("/protocolo")
public class ProtocoloRest {

    private Gson g = new Gson();
    private ControllerProtocolo control = new ControllerProtocolo();

    @GET
    @Path("/listarProtocolo")
    @Produces("application/json")
    public String listarProtocolo() {
        try {
            return g.toJson(control.getAllMotivo());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoloRest.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }

    @POST
    @Path("/cadastrarProtocolo")
    @Produces("application/json")
    @Consumes("application/json")
    public String cadastrarProtocolo(String json) {
        Protocolo p = g.fromJson(json, Protocolo.class);
        return "";
        
        
    }

}
