/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.view.services;

import com.br.fat.controleprotocolo.controller.ControllerToken;
import com.br.fat.controleprotocolo.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

@Secured
@Provider
public class FilterAuthentication implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequest)
            throws WebApplicationException {

        String token = containerRequest
                .getHeaderString(HttpHeaders.AUTHORIZATION);

        if (token == null) {
            throw new NotAuthorizedException("Authorization header precisa ser provido");
        } else {
            try {
                ControllerToken control = new ControllerToken();
                Claims claims = control.validarToken(token);
                modificadorRequest(containerRequest, claims.get("user", String.class));
            } catch (Exception ex) {
                Logger.getLogger(FilterAuthentication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void modificadorRequest(ContainerRequestContext requestContext, final String jsonUser) {
        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return jsonUser;
                    }
                };
            }

            @Override
            public boolean isUserInRole(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isSecure() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getAuthenticationScheme() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

}
