/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.MotivoDaoUtil;
import com.br.fat.controleprotocolo.model.Motivo;
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
public class MotivoDAO extends DatabaseUtil {

    public Motivo insertMotivo(Motivo m) throws Exception {
        String sql = "INSERT into motivo (" + MotivoDaoUtil.MOTIVO_ATRIBUTO_MOTIVO + "," + MotivoDaoUtil.MOTIVO_ATRIBUTO_DESCRICAO
                + "," + MotivoDaoUtil.MOTIVO_ATRIBUTO_EXCLUIDO + ") VALUES (?,?,?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, m.getMotivo());
            stmt.setString(2, m.getDescricao());
            stmt.setString(3, "N");
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            }
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(MotivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    private Motivo createObjMotivo(ResultSet rs) throws SQLException {
        Motivo m = new Motivo();
        m.setId(rs.getInt(MotivoDaoUtil.MOTIVO_ATRIBUTO_ID));
        m.setMotivo(rs.getString(MotivoDaoUtil.MOTIVO_ATRIBUTO_MOTIVO));
        m.setDescricao(rs.getString(MotivoDaoUtil.MOTIVO_ATRIBUTO_DESCRICAO));
        return m;
    }

    public List<Motivo> selectAllMotivo() throws Exception {
        String sql = "SELECT * FROM motivo WHERE" + MotivoDaoUtil.MOTIVO_ATRIBUTO_EXCLUIDO + " = 'N'";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Motivo> listaMotivo = new ArrayList<>();
            while (rs.next()) {
                listaMotivo.add(this.createObjMotivo(rs));
            }
            return listaMotivo;
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public void updateMotivo(Motivo m) throws Exception {
        String sql = "UPDATE motivo SET " + MotivoDaoUtil.MOTIVO_ATRIBUTO_MOTIVO + "= ?,"
                + MotivoDaoUtil.MOTIVO_ATRIBUTO_DESCRICAO + "=? WHERE "
                + MotivoDaoUtil.MOTIVO_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, m.getMotivo());
            stmt.setString(2, m.getDescricao());
            stmt.setInt(3, m.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void deleteMotivo(int id) throws Exception {
        String sql = "UPDATE motivo SET " + MotivoDaoUtil.MOTIVO_ATRIBUTO_EXCLUIDO + " = 'S' WHERE "
                + MotivoDaoUtil.MOTIVO_ATRIBUTO_ID + "= ?";
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

}
