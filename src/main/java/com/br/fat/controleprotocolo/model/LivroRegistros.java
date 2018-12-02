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
public class LivroRegistros {

    private int id, paginaDoRegistro, numeroLivro;
    private String nome, detalhes, cor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaginaDoRegistro() {
        return paginaDoRegistro;
    }

    public void setPaginaDoRegistro(int paginaDoRegistro) {
        this.paginaDoRegistro = paginaDoRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

}
