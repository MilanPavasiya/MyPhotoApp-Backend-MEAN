package com.myphotoapp.repository;


import com.myphotoapp.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotoRepository extends MongoRepository<Photo, String> {

    List<Photo> findPhotosByAlbumId(String albumId);
    List<Photo> findAllById(String id);
}
