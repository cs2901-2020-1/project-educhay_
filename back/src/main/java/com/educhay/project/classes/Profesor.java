package com.educhay.project.classes;

import com.educhay.project.repository.Video_repository;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Entity
public class Profesor extends Usuario {

    public Boolean is_admin;
    @OneToMany
    public List<Video> videos;

    public boolean addVideoToProfesor(Video _video){
        if (videos.contains(_video)){
            return false;
        }
        videos.add(_video);
        return true;
    }
    public boolean deleteVideo(Video_repository repo , Long video_id ){
        Optional<Video> myVid_o = repo.findById(video_id);
        if (myVid_o.isPresent()){
            Video myVid = myVid_o.get();
            if (myVid.creador == this){
                repo.deleteById(video_id);
                return true;
            }
        }
        return false;
    }
    public boolean moveUnidad(Video video, Unidad unidad){
        if (video.creador == this){
            video.unidad = unidad.nombre;
            return true;
        }
        return false;
    }
    public boolean deleteComment(Video video, int index){
        if (video.creador == this){
            video.comments.remove(index);
            return true;
        }
        return false;
    }

}

