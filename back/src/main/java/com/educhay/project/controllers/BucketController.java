package com.educhay.project.controllers;

import java.io.IOException;
import com.educhay.project.classes.AmazonClient;
import com.educhay.project.classes.Unidad;
import com.educhay.project.classes.Usuario;
import com.educhay.project.classes.Video;
import com.educhay.project.errores.OrderNotFoundException;
import com.educhay.project.repository.Profesor_repository;
import com.educhay.project.repository.Unidad_repository;
import com.educhay.project.repository.Usuarios_repository;
import com.educhay.project.repository.Video_repository;
import com.educhay.project.requests.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController

public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @CrossOrigin
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return this.amazonClient.uploadFile(file);
    }
    @CrossOrigin
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}