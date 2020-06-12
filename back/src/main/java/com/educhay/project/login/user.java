package com.educhay.project.login;

import javax.persistence.*;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    public String username;
    @Column
    public String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public user(){};
    public boolean valid(String pass) {
        if (pass == password) {
            return true;
        } else {
            return false;
        }
    }

}
