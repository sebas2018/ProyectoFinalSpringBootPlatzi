package com.platzi.proyecto.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlatziUserDetailsService implements UserDetailsService {

    //METODO QUE BUSCA EL USUARIO A PATIR DEL NOMBRE DE USUARIO
    //RETORNA EL USUARIO
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //el ArrayList son los tipos de roles que tendra el usuario por el momentó no se asigna ninguno
        //como el password no se ha pasado por ningun codificador se le añade {noop}
        return new User("juan", "{noop}platzi",new ArrayList<>());
    }
}
