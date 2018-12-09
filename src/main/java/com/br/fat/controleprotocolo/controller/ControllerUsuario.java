/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.fat.controleprotocolo.controller;

import com.br.fat.controleprotocolo.dao.UsuarioDAO;
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
 * @author Filipe Borges
 */
public class ControllerUsuario {

    private UsuarioDAO udao = new UsuarioDAO();

    public Usuario autenticacao(String senha, String login) throws Exception {
        Usuario user = null;

        //verifica se o login ou senha vieram vazios ou incorretos
        if (login.equals("") || senha.equals("") || senha.length() > 8 || login.length() > 60) {
            throw new Exception("Usuario ou senha invalida");
        } else {
            //envia os dados para fazer a verificacao no banco de dados
            udao = new UsuarioDAO();

            //se o retorno for nulo o login nao foi feito com sucesso
            user = udao.validarUsuario(login, senha);
//            FileInputStream serviceAccount = new FileInputStream("C:\\controleprotocolo-39b32-firebase-adminsdk-rer4f-cb86bfa396.json");
//
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//            String uid = Integer.toString(123);
//            Map<String, Object> additionalClaims = new HashMap<>();
////        additionalClaims.put("permission", user.getPermissao());
//
//            String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
//            return customToken;
//            gerarToken(user);
            return user;
        }
    }

    public String gerarToken(Usuario u) {

        Gson g = new Gson();
        String o = g.toJson(u);
        byte[] keyBytes = DatatypeConverter.parseBase64Binary("asdjaskdasjdaasdjaskdasjdaasdjaskdasjdaasdjaskdasjda");
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        JwtBuilder token = Jwts.builder().claim("obj", o).setIssuer(u.getUser()).signWith(key);
        String stoken = token.compact();

        Jws<Claims> jws;

        try {
            jws = Jwts.parser() // (1)
                    .setSigningKey(key) // (2)
                    .parseClaimsJws(stoken); // (3)
            System.out.println(jws.toString());
            String a = (String) jws.getBody().get("obj");
            Usuario as = g.fromJson(a, Usuario.class);
            System.out.println("");
        } catch (JwtException ex) {       // (4)

            // we *cannot* use the JWT as intended by its creator
        }

        return stoken;

//Defini qual vai ser o algoritmo da assinatura no caso vai ser o HMAC SHA512
//        SignatureAlgorithm algoritimoAssinatura = SignatureAlgorithm.HS256;
//
//        //Data atual que data que o token foi gerado
//        Date agora = new Date();
//
//        //Define até que data o token é pelo quantidade de dias que foi passo pelo parâmetro expiraEmDias
//        Calendar expira = Calendar.getInstance();
//
//        expira.add(Calendar.DAY_OF_MONTH, 2);
//
//        //Encoda a frase segredo pra base64 pra ser usada na geração do token 
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("testetestetestetestetestetestetestetestetestetestetestetestetest");
//
//        SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, algoritimoAssinatura.getJcaName());
//
//        //E finalmente utiliza o JWT builder pra gerar o token
//        JwtBuilder construtor = Jwts.builder()
//                .setIssuedAt(agora)//Data que o token foi gerado
//
//                .setIssuer(u.getUser())//Coloca o login do usuário mais podia qualquer outra informação
//
//                .signWith(algoritimoAssinatura, key)//coloca o algoritmo de assinatura e frase segredo já encodada
//
//                .setExpiration(expira.getTime());// coloca até que data que o token é valido
//
//        return construtor.compact();//Constrói o token retornando ele como uma String
    }

    public Usuario createUsuario(Usuario u) throws Exception {
        if (u.getNome() == null || u.getNome().isEmpty() || u.getUser() == null || u.getUser().isEmpty()
                || u.getSenha() == null || u.getSenha().isEmpty() || u.getPermissao() == null) {
            throw new Exception();
        } else {
            return udao.createUsuario(u);
        }
    }
}
