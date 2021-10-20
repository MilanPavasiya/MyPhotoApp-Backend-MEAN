package com.myphotoapp.service;

import com.myphotoapp.model.Photo;
import com.myphotoapp.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo updatePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(String id) {
        photoRepository.deleteById(id);
    }

    public List<Photo> getPhotosOfAlbum(String albumId) {
        return photoRepository.findPhotosByAlbumId(albumId);
    }

    public List<Photo> getPhotoById(String id) {
        return photoRepository.findAllById(id);
    }



}
