package com.educhay.project.classes;

import com.educhay.project.repository.Video_repository;

import java.util.ArrayList;
import java.util.Optional;

public class Profesor extends Usuario {
    public Boolean is_admin;
    public ArrayList<Long> videos;

    public boolean addVideoToProfesor(Long _video){
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
            if (myVid.creador == username){
                repo.deleteById(video_id);
                return true;
            }
        }
        return false;
    }
    public boolean moveUnidad(Video video, Unidad unidad){
        if (video.creador == username){
            video.unidad = unidad.nombre;
            return true;
        }
        return false;
    }
    public boolean deleteComment(Video video, int index){
        if (video.creador == username){
            video.comments.remove(index);
            return true;
        }
        return false;
    }

}

