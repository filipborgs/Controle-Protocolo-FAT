/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.dao;

import com.br.fat.controleprotocolo.dao.util.LivroDaoUtil;
import com.br.fat.controleprotocolo.model.LivroRegistros;
import com.br.fat.controleprotocolo.util.DatabaseUtil;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filipe Borges
 */
public class LivroRegistroDao extends DatabaseUtil {

    public void insertLivro(LivroRegistros l) throws Exception {
        String sql = "INSERT INTO livro (" + LivroDaoUtil.LIVRO_ATRIBUTO_NOME + "," + LivroDaoUtil.LIVRO_ATRIBUTO_NUMERO + ","
                + LivroDaoUtil.LIVRO_ATRIBUTO_FOLHAS + "," + LivroDaoUtil.LIVRO_ATRIBUTO_DETALHES + ","
                + LivroDaoUtil.LIVRO_ATRIBUTO_COR + "," + LivroDaoUtil.LIVRO_ATRIBUTO_EXCLUIDO
                + ") VALUES (?, ?, ?, ?, ?, ?)";
        super.getCon();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, l.getNome());
            stmt.setInt(2, l.getNumero());
            stmt.setInt(3, l.getFolhas());
            stmt.setString(4, l.getDetalhes());
            stmt.setString(5, l.getCor());
            stmt.setString(6, "N");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception();
        }
    }

}
