/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Filipe Borges
 */
public abstract class Controller {

    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
}
