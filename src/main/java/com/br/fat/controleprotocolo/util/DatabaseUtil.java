/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.avisa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe Borges
 */
public class DatabaseUtil {

    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public void getCon() {
        if (this.con == null) {
            this.con = ConnectionDb.getConnetion();
        }
    }

    public DatabaseUtil() {
        this.con = ConnectionDb.getConnetion();
    }

    public boolean verificarUsuario(String usuario) {
        boolean flag = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM view_usuarios_agrupados v WHERE v.USUARIO = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public void finalizarConexao() {
        ConnectionDb.closeConnection(con);
    }

    public void finalizarConexaoStmt() {
        ConnectionDb.closeConnection(con, stmt);
    }

    public void finalizarConexaoStmtRs() {
        ConnectionDb.closeConnection(con, stmt, rs);
    }

}
