/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.model;

/**
 *
 * @author Filipe Borges
 */
public class Permissao {

    private char read, whrite, delete, update;

    public char getRead() {
        return read;
    }

    public void setRead(char read) {
        this.read = read;
    }

    public char getWhrite() {
        return whrite;
    }

    public void setWhrite(char whrite) {
        this.whrite = whrite;
    }

    public char getDelete() {
        return delete;
    }

    public void setDelete(char delete) {
        this.delete = delete;
    }

    public char getUpdate() {
        return update;
    }

    public void setUpdate(char update) {
        this.update = update;
    }

}
