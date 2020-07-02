package com.educhay.project.requests;

import com.educhay.project.classes.Comentario;

import java.util.ArrayList;
import java.util.List;

public class Video_response_single {

    public Long id;
    public String creador_email, titulo, url_stream, url_download;
    public Float rating;
    public String unidad,curso,grado;
    public ArrayList<Comment_response> comments;

}
