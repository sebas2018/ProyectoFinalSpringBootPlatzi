package com.platzi.proyecto.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "platzi2022";//Constante (Clave requerida para la firma del metodo)
    //metodo que genera el JWT(JSON Web Token)
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())//incluimos el usuario en el JWT
                .setIssuedAt(new Date())//incluimos la fecha de creacion del JWT
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))//incluimos la fecha de expiracion del JWT de 10 horas
                .signWith(SignatureAlgorithm.HS256,KEY).compact();//firma del metodo con el algoritmo HS256
    }
    public boolean validateToken(String token, UserDetails userDetails){
        //verificamos que el token este creado para el usuario que hace la peticion y que el token no alla experido
        //validamos que el usuario que llega de la peticion sea el mismo que el usuario que viene el token y que no haya expirado el token
        return userDetails.getUsername().equals(extractUserName(token)) && !isTokenExpired(token);
    }
    //Metodo auxiliar que recibe el token y si la firma no es valida el proceso no continua y devuelve un 403(forbidden)
    private Claims getClaims(String token){
        // paso el token del cual quiero obtener los Claims y obtengo el cuerpo(body) de ese token
        //se tiene un parser al cual se le añade la llave de la firma y cuando verifique que la firma es correcta procede
        // a obtener los Claims o cuerpo del JWT separadop por cada uno de los objetos
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
    //METODO QUE EXTRAE EL USUARIO DEL TOKEN
    public String extractUserName(String token){
        return getClaims(token).getSubject();//Recuerde que en el Subject() es donde esta el usuario de la peticion
    }
    //METODO QUE VERIFICA SI EL TOKEN YA EXPIRO , RETORNA UN BOOLEANO
    public boolean isTokenExpired(String token){
        //preguntamos si la fecha de expitacion se encuentra antes de la fecha actual
        //si la fecha de expiracion esta antes de la fecha actual retorna un (tue) es decir ya expiro el token
        //si la fecha de expiracion esta despues de la fecha actual retorna un (false) es decir el token no a expirado y se encuentra vigente
        return getClaims(token).getExpiration().before(new Date());
    }

}
