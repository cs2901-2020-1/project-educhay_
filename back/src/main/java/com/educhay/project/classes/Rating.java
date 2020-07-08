package com.educhay.project.classes;

import javax.persistence.*;

@Entity
public class Rating {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    public Integer score;
    @ManyToOne(cascade = CascadeType.ALL)
    public Video vid;
    @ManyToOne(cascade = CascadeType.ALL)
    public Usuario user;
}
