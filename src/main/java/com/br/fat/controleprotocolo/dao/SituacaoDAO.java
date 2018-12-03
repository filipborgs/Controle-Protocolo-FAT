/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.SituacaoDaoUtil;
import com.br.fat.controleprotocolo.model.Situacao;
import com.br.fat.controleprotocolo.util.DatabaseUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe Borges
 */
public class SituacaoDAO extends DatabaseUtil {

    private Situacao createObjSituacao(ResultSet rs) throws SQLException {
        Situacao s = new Situacao();
        s.setId(rs.getInt(SituacaoDaoUtil.SITUACAO_ATRIBUTO_ID));
        s.setDescricao(rs.getString(SituacaoDaoUtil.SITUACAO_ATRIBUTO_DESCRICAO));
        s.setSituacao(rs.getString(SituacaoDaoUtil.SITUACAO_ATRIBUTO_SITUACAO));
        return s;
    }

    public Situacao insertSituacao(Situacao s) throws Exception {
        String sql = "INSERT INTO situacao (" + SituacaoDaoUtil.SITUACAO_ATRIBUTO_SITUACAO + "," + SituacaoDaoUtil.SITUACAO_ATRIBUTO_DESCRICAO
                + "," + SituacaoDaoUtil.SITUACAO_ATRIBUTO_EXCLUIDO + ") VALUES (?,?,?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, s.getSituacao());
            stmt.setString(2, s.getDescricao());
            stmt.setString(3, "N");
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                s.setId(rs.getInt(1));
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(SituacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public List<Situacao> selectAllSituacao() throws Exception {
        String sql = "SELECT * FROM situacao";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Situacao> listaSituacao = new ArrayList<>();
            while (rs.next()) {
                listaSituacao.add(this.createObjSituacao(rs));
            }
            return listaSituacao;
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

}
