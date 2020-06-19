package com.educhay.project.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    public String username;
    public String nombre, apellido;
    @Convert(converter = AttributeEncryptor.class)
    public String password;
    @Column(unique = true)
    public String email;
    @OneToMany
    public List<Video> vids_vistos;
    @ElementCollection
    public List<String> notifs;


    public Usuario(String username, String password, String nombre, String apellido, String email) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Usuario() {
    }

    ;

    public boolean valid(String pass) {
        if (pass == password) {
            return true;
        } else {
            return false;
        }
    }

}
