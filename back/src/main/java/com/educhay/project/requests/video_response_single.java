package com.educhay.project.requests;

import com.educhay.project.classes.Comentario;

import java.util.List;

public class video_response_single {

    public Long id;
    public String creador_email, titulo, url_stream, url_download;
    public Float rating;
    public String Unidad;
    public List<Comentario> comments;

}
