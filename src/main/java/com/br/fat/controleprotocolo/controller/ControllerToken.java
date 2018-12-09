/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

    private SecretKey key;

    public ControllerToken() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary("asdjaskdasjdaasdjaskdasjdaasdjaskdasjdaasdjaskdasjda");
        this.key = Keys.hmacShaKeyFor(keyBytes);
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
