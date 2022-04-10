package com.platzi.proyecto.domain.dto;

public class AuthenticationResponse {

    private String jwt;//unico atributo que corresponde al JWT(JSON Web Token)

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
