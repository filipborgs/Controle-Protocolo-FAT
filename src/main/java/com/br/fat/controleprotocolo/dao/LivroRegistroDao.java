/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.LivroDaoUtil;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import com.br.fat.controleprotocolo.util.DatabaseUtil;
import java.sql.Date;
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
public class LivroRegistroDao extends DatabaseUtil {

    public LivroRegistros insertLivro(LivroRegistros l) throws Exception {
        String sql = "INSERT INTO livro (" + LivroDaoUtil.LIVRO_ATRIBUTO_NOME + "," + LivroDaoUtil.LIVRO_ATRIBUTO_NUMERO + ","
                + LivroDaoUtil.LIVRO_ATRIBUTO_FOLHAS + "," + LivroDaoUtil.LIVRO_ATRIBUTO_DETALHES + ","
                + LivroDaoUtil.LIVRO_ATRIBUTO_COR + "," + LivroDaoUtil.LIVRO_ATRIBUTO_EXCLUIDO + "," 
                + LivroDaoUtil.LIVRO_ATRIBUTO_DATA_INICIO + "," + LivroDaoUtil.LIVRO_ATRIBUTO_DATA_FIM
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, l.getNome());
            stmt.setInt(2, l.getNumero());
            stmt.setInt(3, l.getFolhas());
            stmt.setString(4, l.getDetalhes());
            stmt.setString(5, l.getCor());
            stmt.setString(6, "N");
            stmt.setDate(7, (Date) l.getDataInicio());
            stmt.setDate(8, (Date) l.getDataFim());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                l.setId(rs.getInt(1));
                return l;
            } else {
                throw new Exception();
            }
        } catch (SQLException ex) {
            throw new Exception();
        }
    }

    public List<LivroRegistros> selectAllLivroRegistros() throws Exception {
        String sql = "SELECT * FROM livro";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<LivroRegistros> listaLivros = new ArrayList<>();
            while (rs.next()) {
                listaLivros.add(this.createObjLivro(rs));
            }
            return listaLivros;
        } catch (SQLException ex) {
            Logger.getLogger(LivroRegistroDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }

    }

    private LivroRegistros createObjLivro(ResultSet rs) throws SQLException {
        LivroRegistros l = new LivroRegistros();
        l.setId(rs.getInt(LivroDaoUtil.LIVRO_ATRIBUTO_ID));
        l.setNome(rs.getString(LivroDaoUtil.LIVRO_ATRIBUTO_NOME));
        l.setNumero(rs.getInt(LivroDaoUtil.LIVRO_ATRIBUTO_NUMERO));
        l.setFolhas(rs.getInt(LivroDaoUtil.LIVRO_ATRIBUTO_FOLHAS));
        l.setCor(rs.getString(LivroDaoUtil.LIVRO_ATRIBUTO_COR));
        l.setDetalhes(rs.getString(LivroDaoUtil.LIVRO_ATRIBUTO_DETALHES));
        l.setDataInicio(rs.getDate("data_inicio"));
        return l;
    }
}
