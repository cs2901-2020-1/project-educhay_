package com.educhay.project.login;

import javax.persistence.*;

@Entity
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="usuario")
    public String username;
    @Column(name="pass")
    public String password;

    public users(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public users(){};
    public boolean valid(String pass) {
        if (pass == password) {
            return true;
        } else {
            return false;
        }
    }

}
