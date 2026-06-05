package com.minimarket.security.util;

import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {

    private static final String SECRET = "minimarket-clave-secreta-super-segura-2024";
    private static final long EXPIRATION_MS =86400000;

    //convierte el string secreto en un objeto secretKey
    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    //este es el constructor del token, empaqueta la identidad del usuario y sus roles y fecha de vencimiento
    public String generateToken(String username, List<String> roles){
        return Jwts.builder().subject(username).claim("roles", roles).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)).signWith(getSigninKey()).compact();
    }

    //Abre el token y lee el subject
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    //intenta parsear el token, si la firma es invalida o esta expirado
    public boolean validateToken(String token){
        try{
            getClaims(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    //este metodo abre y verifica el token. usa la misma clave secreta para verificar la firma
    private Claims getClaims(String token){
        return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
    }


}
