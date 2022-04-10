package com.platzi.proyecto.web.security;
import com.platzi.proyecto.domain.service.PlatziUserDetailsService;
import com.platzi.proyecto.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//Esta etiquete indica que es la clase encargada de la seguridad de la aplicacion
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    @Autowired
    private JwtFilterRequest jwtFilterRequest;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //desabilitamos las peticiones cruzadas y luego autorizamos las peticiones especificandolo como parametro dentro del metodo (.antMatchers)
        //(**) significa que todas las peteciones sin importar lo que hayaa antes que terminen en authenticate las permita
        http.csrf().disable().authorizeRequests().antMatchers("/**//authenticate").permitAll()
                .anyRequest().authenticated()//Ahora especificamos que el resto de peticiones si necesita autenticacion
                .and().sessionManagement()
                //indicamos que la sesion a utilizar en la aplicacion sera(STATELESS), es decir sin sesion
                // por que los JWT controlan cada peticion en particular sin manejar sesion
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //CONCLUSION: LAS PETICIONES QUE TERMINEN EN ("/authenticate") las va a permitir y las peticiones que no terminen
        // en ("/authenticate"), es decir que no cumplan con el patron del (.antMatchers()) si requieren de autenticacion
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);//tipo de filtro(UsernamePasswordAuthenticationFilter,class)
    }

    @Override
    @Bean //le indicamos que este metood es el gestor de autenticacion de la aplicacion
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

