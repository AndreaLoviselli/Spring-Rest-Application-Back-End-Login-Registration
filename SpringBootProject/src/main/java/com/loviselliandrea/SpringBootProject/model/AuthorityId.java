package com.loviselliandrea.SpringBootProject.model;

import java.io.Serializable;

/*Questa classe esiste per indicare secondo best practice e sintassi che nella classe Authority per la tabella
* "authorities" abbiamo doppia chiave primaria. Tale coppia di valori nella tabella viene indicata a Spring tramite
* questa classe */
public class AuthorityId implements Serializable {
    private String username;
    private String authority;

    public AuthorityId() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


}
