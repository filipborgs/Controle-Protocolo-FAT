/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
