package com.educhay.project.classes;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public Float rating;
    public String nombre, titulo , url_download, url_stream;
}
