package com.minimarket.security.model;

public class LoginRequest {
    private String username;
    private String password;


    //esta linea recibe el usuario y lo retorna
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Esta linea recibe el password y lo retorna
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
