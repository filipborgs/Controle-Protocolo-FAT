/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.PessoaDaoUtil;
import com.br.fat.controleprotocolo.model.Pessoa;
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
public class PessoaDAO extends DatabaseUtil {

    private Pessoa createObjPessoa(ResultSet rs) throws SQLException {
        Pessoa p = new Pessoa();
        p.setId(rs.getInt(PessoaDaoUtil.PESSOA_ATRIBUTO_ID));
        p.setNome(rs.getString(PessoaDaoUtil.PESSOA_ATRIBUTO_NOME));
        p.setDescricao(rs.getString(PessoaDaoUtil.PESSOA_ATRIBUTO_DESCRICAO));
        p.setSetor(rs.getString(PessoaDaoUtil.PESSOA_ATRIBUTO_SETOR));
        return p;
    }

    public Pessoa insertPessoa(Pessoa p) throws Exception {
        String sql = "INSERT INTO pessoa (" + PessoaDaoUtil.PESSOA_ATRIBUTO_NOME + "," + PessoaDaoUtil.PESSOA_ATRIBUTO_DESCRICAO
                + "," + PessoaDaoUtil.PESSOA_ATRIBUTO_SETOR + "," + PessoaDaoUtil.USUARIO_ATRIBUTO_EXCLUIDO
                + ") VALUES (?,?,?,?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getSetor());
            stmt.setString(4, "N");
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public List<Pessoa> selectAllPessoa() throws Exception {
        String sql = "SELECT * FROM view_table_pessoa WHERE " + PessoaDaoUtil.USUARIO_ATRIBUTO_EXCLUIDO + "='N'";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Pessoa> listaPessoa = new ArrayList<>();
            while (rs.next()) {
                listaPessoa.add(this.createObjPessoa(rs));
            }
            return listaPessoa;
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public void updatePessoa(Pessoa p) throws Exception {
        String sql = "UPDATE pessoa SET " + PessoaDaoUtil.PESSOA_ATRIBUTO_NOME + "= ?,"
                + PessoaDaoUtil.PESSOA_ATRIBUTO_DESCRICAO + "=?," + PessoaDaoUtil.PESSOA_ATRIBUTO_SETOR + "=? WHERE "
                + PessoaDaoUtil.PESSOA_ATRIBUTO_ID + "= ?";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setString(3, p.getSetor());
            stmt.setInt(4, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    public void deletePessoa(int id) throws Exception {
        String sql = "UPDATE pessoa SET " + PessoaDaoUtil.USUARIO_ATRIBUTO_EXCLUIDO + " = 'S' WHERE "
                + PessoaDaoUtil.PESSOA_ATRIBUTO_ID + "= ?";
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
