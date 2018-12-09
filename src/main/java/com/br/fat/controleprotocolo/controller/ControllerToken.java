/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.model.Usuario;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author lippy
 */
public class ControllerToken {

    private final SecretKey key;

    public ControllerToken() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary("*cMze7bgKnb5dWK!&7puK=6ZTPJ4#+QF?hHD-%4eN7kBDRfF$S#Jm4");
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(Usuario u) {

        Gson g = new Gson();
        String o = g.toJson(u);

        JwtBuilder token = Jwts.builder().claim("user", o).setIssuer(u.getUser()).signWith(key);
        String stoken = token.compact();

        return stoken;
    }

    public Claims validarToken(String token) throws Exception {
        Jws<Claims> jws;

        try {
            Claims claim = Jwts.parser() // (1)
                    .setSigningKey(key) // (2)
                    .parseClaimsJws(token).getBody(); // (3)
//            String a = (String) jws.getBody().get("obj");
            if (claim == null) {
                throw new Exception();
            } else {
                return claim;
            }
        } catch (JwtException ex) {       // (4)
            // we *cannot* use the JWT as intended by its creator
            throw new Exception();
        }
    }

}
