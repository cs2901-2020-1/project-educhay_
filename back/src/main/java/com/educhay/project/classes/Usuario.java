package com.educhay.project.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public String username;
    public String password;
    public String email;
    @OneToMany
    public List<Video> vids_vistos;
    @ElementCollection
    public List<String> notifs;


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
