package com.loviselliandrea.SpringBootProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority {
    @Id
    private String username;
    @Id
    private String authority;

    /*@JsonIgnore indica a Spring di non serializzare User. Questo per evitare uno Stack Over Flow error. A Spring è
    infatti indicato di serializzare già User e poi di conseguenza Authority. Senza l'annotazione Spring effettuerebbe
    di nuovo la serializzazione di User generando un loop e quindi un errore. */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false ,nullable = false)
    private User user;

    public Authority() {
    }

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
