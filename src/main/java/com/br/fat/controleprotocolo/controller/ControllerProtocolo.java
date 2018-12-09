/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.ProtocoloDAO;
import com.br.fat.controleprotocolo.model.Protocolo;
import com.br.fat.controleprotocolo.model.Usuario;
import java.util.List;

/**
 *
 * @author Filipe Borges
 */
public class ControllerProtocolo extends Controller {

    private final ProtocoloDAO pdao = new ProtocoloDAO();

    public ControllerProtocolo(Usuario u) {
        super(u);
    }

    public Protocolo createProtocolo(Protocolo p) throws Exception {
        this.verifyPermission(this.user.getPermissao().getWrite());

        if (p.getLivro() == null
                || p.getDestinatario() == null
                || p.getMotivo() == null
                || p.getRemetente() == null
                || p.getSituacao() == null
                || p.getUsuario() == null) {
            throw new Exception();
        } else if (p.getAssinado().isEmpty()
                || p.getObservacoes().isEmpty()
                || p.getPaginaLivro() <= 0
                || p.getProtocolo() <= 0
                || p.getSolicitante().isEmpty()
                || p.getLivro().getId() <= 0
                || p.getMotivo().getId() <= 0
                || p.getRemetente().getId() <= 0
                || p.getSituacao().getId() <= 0
                || p.getUsuario().getId() <= 0
                || p.getDestinatario().getId() <= 0) {
            throw new Exception();
        } else {
            return pdao.insertProtocolo(p);
        }
    }

    public List<Protocolo> getAllMotivo() throws Exception {
        this.verifyPermission(this.user.getPermissao().getRead());

        return pdao.selectAllProtocolo();
    }

    public void removeProtocolo(int idProtocolo) throws Exception {
        this.verifyPermission(this.user.getPermissao().getDelete());

        if (idProtocolo <= 0) {
            throw new Exception();
        } else {
            pdao.deleteProtocolo(idProtocolo);
        }
    }

    public void editProtocolo(String jsonProtocolo) throws Exception {
        this.verifyPermission(this.user.getPermissao().getUpdate());
        Protocolo p = gson.fromJson(jsonProtocolo, Protocolo.class);
        if(p.getId() <= 0){
            throw new Exception();
        }

        pdao.updateProtocolo(p);
    }
}
