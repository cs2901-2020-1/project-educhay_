package com.educhay.project.classes;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="usuario")
    public String username;
    @Column(name="pass")
    public String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Usuario(){};
    public boolean valid(String pass) {
        if (pass == password) {
            return true;
        } else {
            return false;
        }
    }

}
