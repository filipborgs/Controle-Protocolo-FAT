/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Filipe Borges
 */
public abstract class Controller {

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    protected Usuario user;

    public Controller(Usuario u) {
        this.user = u;
    }

    public Controller() {
    }

    protected void verifyPermission(char permission) throws Exception {
        switch (permission) {
            case 'N':
                throw new Exception();
            case 'S':
                break;
            default:
                throw new Exception();
        }
    }

}
