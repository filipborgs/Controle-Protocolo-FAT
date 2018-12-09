/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.model;

import java.sql.Date;
import java.text.DateFormat;

/**
 *
 * @author Filipe Borges
 */
public class LivroRegistros {

    private int id, folhas, numero;
    private String nome, detalhes, cor, dtIni, dtFim, dataIni, dataFi;
    private Date dataInicio, dataFim;

    public String getDtIni() {
        return dtIni;
    }

    public void setDtIni(String dtIni) {
        this.dtIni = dtIni;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        if (dataFim == null) {
            this.dataFi = "";
        } else {
            this.dataFi = dataFim.toString();
        }
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        if (dataInicio == null) {
            this.dataIni = "";
        } else {
            this.dataIni = dataInicio.toString();
        }
        this.dataInicio = dataInicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFolhas() {
        return folhas;
    }

    public void setFolhas(int folhas) {
        this.folhas = folhas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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
