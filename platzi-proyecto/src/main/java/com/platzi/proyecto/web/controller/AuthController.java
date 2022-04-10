package com.platzi.proyecto.web.controller;
import com.platzi.proyecto.domain.dto.AuthenticationRequest;
import com.platzi.proyecto.domain.dto.AuthenticationResponse;
import com.platzi.proyecto.domain.service.PlatziUserDetailsService;
import com.platzi.proyecto.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    //INYECTAMOS (AuthenticationManager) EL CUAL TIENE SPRING PARA PODER VALIDAR QUE EL USARIO Y CONTRASEÑA ESTEN CORRECTOS
    @Autowired
    private AuthenticationManager authenticationManager;
    //INYECTAMOS EL SERVICIO QUE SE ENCARGA DE GENERAR LA SEGURIDAD CON USUARIO("juan") Y CONTRASEÑA("platzi")
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    //INYECTAMOS LA CLASE (JWTUtil) que es la que genera el token de seguridad
    @Autowired
    private JWTUtil jwtUtil;
    //METODO QUE RESPONDE UN JWT CUANDO UN USUARIO INICIA SESION
    //ESTE METODO RECIBE LAS PETICIONES A TRAVES DE (POST)
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            // se le dice al gestor de autenticacion de spring que validen el usuario y contraseña para saber si son correctos
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

            //OBTENEMOS LOS DETALLES DEL USUARIO DESDE EL SERVICIO QUE SE CREO  PARA LOGRAR ESTE FIN
            UserDetails userDetails = platziUserDetailsService.loadUserByUsername(request.getUserName());
            String jwt = jwtUtil.generateToken(userDetails);//GENERO EL TOKEN Y LE PASO EL USUARIO
            return new ResponseEntity<>(new AuthenticationResponse(jwt),HttpStatus.OK);

        }catch (BadCredentialsException e){ //EL BadCredentialsException se activa cuando no ocurre la autenticacion con el usuario("juan") y contraseña("platzi")
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }
}
