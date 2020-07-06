package com.educhay.project.classes;

import javax.persistence.*;

@Entity
public class Rating {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    public Integer score;
    @ManyToOne
    public Video vid;
    @ManyToOne
    public Usuario user;
}
