/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author lippy
 */
public abstract class Autenticacao {

    protected Usuario criarUser(SecurityContext sc) {
        String s = sc.getUserPrincipal().getName();
        Gson g = new Gson();
        Usuario u = g.fromJson(s, Usuario.class);
        return u;
    }
}
