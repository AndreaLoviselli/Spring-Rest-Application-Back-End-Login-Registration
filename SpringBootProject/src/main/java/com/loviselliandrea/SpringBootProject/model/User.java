package com.loviselliandrea.SpringBootProject.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String username;
    private String password;
    private String email;
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)//se salvo o cancello user aggiungi o cancelli questo
    private Set<Authority> authorities;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        generateDefaultPassword();
    }

    private void generateDefaultPassword(){
        this.password = this.username + "#" + this.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
