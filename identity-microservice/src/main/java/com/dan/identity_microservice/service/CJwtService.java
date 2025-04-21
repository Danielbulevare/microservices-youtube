package com.dan.identity_microservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dan.identity_microservice.enums.ERole;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CJwtService {
	
	/*
	 * Se deberia declarar como: private static final, pero por cuestiones didacticas se hizo así.
	 * El secreto es de 32 bits.
	 * No se debe de poner harcodeado en el codigo ni en ningún archivo que este a la vista de todos,
	 * por eso lo declare así para pasarselo como variable de entorno, por cuestiones didacticas.
	 */
	@Value("${secret.key.jwt}")
	private String secret;
	
	public void validateToken(final String token) {
		/*
		 * Este método valida el token
		 */
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    public String generateToken(String userName, ERole role) {
    	/*
    	 * Este método genera el token
    	 */
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", List.of("ROLE_" + role));
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
    	/*
    	 * Este método crea el token.
    	 * Los claims son la carga util de encabezados y firma lo que se de desde la entrada
    	 */
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName) //Nombre del usuaurio
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) //Fecha expiración del token
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); //Tipo de algoritmo utilizado para cifrar el token
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
