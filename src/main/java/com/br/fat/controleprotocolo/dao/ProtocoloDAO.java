/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.ProtocoloDaoUtil;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import com.br.fat.controleprotocolo.model.Motivo;
import com.br.fat.controleprotocolo.model.Pessoa;
import com.br.fat.controleprotocolo.model.Protocolo;
import com.br.fat.controleprotocolo.model.Situacao;
import com.br.fat.controleprotocolo.model.Usuario;
import com.br.fat.controleprotocolo.util.DatabaseUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe Borges
 */
public class ProtocoloDAO extends DatabaseUtil {

    private Protocolo createObjProtocolo(ResultSet rs) throws SQLException {
        Protocolo p = new Protocolo();
        p.setProtocolo(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_NUM));
        p.setLivro(new LivroRegistros());
        p.getLivro().setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_LIVRO));
        p.setMotivo(new Motivo());
        p.getMotivo().setId(rs.getInt(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_MOTIVO)));
        p.setUsuario(new Usuario());
        p.getUsuario().setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_USUARIO));
        p.setObservacoes(rs.getString(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_OBSERVACOES));
        p.setPaginaLivro(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_PAGINA));
        p.setAssinado(rs.getString(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ASSINADO));
        p.setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ID));
        p.setDestinatario(new Pessoa());
        p.setRemetente(new Pessoa());
        p.getDestinatario().setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DESTINATARIO));
        p.getRemetente().setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_REMETENTE));
        p.setSolicitante(rs.getString(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SOLICITANTE));
        p.setSituacao(new Situacao());
        p.getSituacao().setId(rs.getInt(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SITUACAO));
        p.setDataCadastral(rs.getDate(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_LANCAMENTO));
        p.setDataDevolvido(rs.getDate(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_DEVOLUCAO));
        p.setDataPrevisao(rs.getDate(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_PREVISAO));
        p.setDataRecebido(rs.getDate(ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_RECEBIMENTO));

        return p;
    }

    public Protocolo insertProtocolo(Protocolo p) throws Exception {
        String sql = "INSERT INTO protocolo (" + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_USUARIO + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ASSINADO + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DESTINATARIO + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_LIVRO + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_MOTIVO + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_NUM + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_OBSERVACOES + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_PAGINA + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_REMETENTE + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SITUACAO + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SOLICITANTE + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_LANCAMENTO + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_RECEBIMENTO + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_PREVISAO + "," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_DEVOLUCAO + ","
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_EXCLUIDO + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getUsuario().getId());
            stmt.setString(2, p.getAssinado());
            stmt.setInt(3, p.getDestinatario().getId());
            stmt.setInt(4, p.getLivro().getId());
            stmt.setInt(5, p.getMotivo().getId());
            stmt.setInt(6, p.getProtocolo());
            stmt.setString(7, p.getObservacoes());
            stmt.setInt(8, p.getPaginaLivro());
            stmt.setInt(9, p.getRemetente().getId());
            stmt.setInt(10, p.getSituacao().getId());
            stmt.setString(11, p.getSolicitante());
            stmt.setDate(12, p.getDataCadastral());
            stmt.setDate(13, p.getDataRecebido());
            stmt.setDate(14, p.getDataPrevisao());
            stmt.setDate(15, p.getDataDevolvido());
            stmt.setString(16, "N");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProtocoloDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
        return p;
    }

    public List<Protocolo> selectAllProtocolo() throws Exception {
        String sql = "SELECT * FROM protocolo WHERE " + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_EXCLUIDO + " = 'N'";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Protocolo> listaProtocolo = new ArrayList<>();
            while (rs.next()) {
                listaProtocolo.add(this.createObjProtocolo(rs));
            }
            return listaProtocolo;
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public void deleteProtocolo(int id) throws Exception {
        String sql = "UPDATE protocolo SET " + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_EXCLUIDO + " = 'S' WHERE "
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareCall(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void updateProtocolo(Protocolo p) throws Exception {
        String sql = "UPDATE protocolo SET " + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_USUARIO + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ASSINADO + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DESTINATARIO + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_LIVRO + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_MOTIVO + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_NUM + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_OBSERVACOES + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_PAGINA + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_REMETENTE + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SITUACAO + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_SOLICITANTE + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_LANCAMENTO + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_RECEBIMENTO + "=?,"
                + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_PREVISAO + "=?," + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_DATA_DEVOLUCAO
                + "=? WHERE " + ProtocoloDaoUtil.PROTOCOLO_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getUsuario().getId());
            stmt.setString(2, p.getAssinado());
            stmt.setInt(3, p.getDestinatario().getId());
            stmt.setInt(4, p.getLivro().getId());
            stmt.setInt(5, p.getMotivo().getId());
            stmt.setInt(6, p.getProtocolo());
            stmt.setString(7, p.getObservacoes());
            stmt.setInt(8, p.getPaginaLivro());
            stmt.setInt(9, p.getRemetente().getId());
            stmt.setInt(10, p.getSituacao().getId());
            stmt.setString(11, p.getSolicitante());
            stmt.setDate(12, p.getDataCadastral());
            stmt.setDate(13, p.getDataRecebido());
            stmt.setDate(14, p.getDataPrevisao());
            stmt.setDate(15, p.getDataDevolvido());
            stmt.setInt(16, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

}
