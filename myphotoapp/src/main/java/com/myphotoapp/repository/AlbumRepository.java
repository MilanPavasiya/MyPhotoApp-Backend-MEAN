package com.myphotoapp.repository;

import com.myphotoapp.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {

    Album findAlbumById(String id);
}
