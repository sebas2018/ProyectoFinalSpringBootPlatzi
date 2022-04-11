package com.platzi.proyecto.web.security.filter;
import com.platzi.proyecto.domain.service.PlatziUserDetailsService;
import com.platzi.proyecto.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//FILTRO
//(OncePerRequestFilter) -->Permite que el filtro se ejecute cada ves que exista una peticion
@Component
public class JwtFilterRequest extends OncePerRequestFilter {
    //INYECTO EL jwtUtil para poder verificar el usuario que viene el token
    @Autowired
    private JWTUtil jwtUtil;
    //INYECTAMOS EL SERVICIO QUE HACE LAS VECES DE AUTENTICACION para obtener el UserDetails
    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    //EN ESTE METODO SE VERIFICA SI LO QUE VIENE EN EL ENCABEZADO DE LA PETICION ES UN TOKEN Y SI ESE TOKEN ES CORRECTO
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //capturo el encabezado de la peticion
        String authorizationHeader = request.getHeader("Authorization");
        //si authorizationHeader es diferente de null y inicia con "Bearer" es por que  viene un JWT el cual tendremos
        //que capturar
        if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
                String jwt = authorizationHeader.substring(7);//apartir del caracter (7) viene el token
                //verifico el usuario de ese jwt
                String username = jwtUtil.extractUserName(jwt);
                //valido que el usurio no venga vacio y que el usuario aun no a ingresado a la aplicacion es decir
                //no se ha logeado, es decir se verifica que en el contexto no existe ninguna autenticacion para este usuario
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    //Obtenemos el UserDetails para obtener el usuario que llega en el JWT y verificar que si existe
                    // dentro del sistema de autentificacion
                    UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username);
                    //Pregunto si el JWT es correcto, si es correcto levanto la sesion para ese usuario
                    if (jwtUtil.validateToken(jwt, userDetails)){
                        //mandamos como parametro null por que no vamos poner credenciales y con el
                        //userDetails.getAuthorities()  le enviamos todos los roles que tenga el usuario
                        //para este caso tenemos los roles vacios por que no los hemos gestionado
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                        //le colocamos a (authenticationToken) los detalles de la conexion que se esta recibiendo
                        //le pasamos como parametro (request) para poder evaluar que navegador se esta usando, en que hora se conecto, que sistema
                        //operativo tenia, etc
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //le asignamos la autenticacion para que la proxima ves no tenga que pasar por toda la validacion del filtro
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
        }
        //para finalizar le decimos que el filtro sea evaluado
        filterChain.doFilter(request,response);
    }
}
