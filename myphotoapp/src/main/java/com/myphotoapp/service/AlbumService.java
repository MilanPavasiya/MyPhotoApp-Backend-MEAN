package com.myphotoapp.service;


import com.myphotoapp.model.Album;
import com.myphotoapp.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    public AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @CrossOrigin
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    public void deleteAlbum(String id) {
        albumRepository.deleteById(id);
    }

    public Album getAlbumById(String id) {
        return albumRepository.findAlbumById(id);
    }

    public Album updateCoverPhoto(String coverPhotoUrl, String albumId) {
       Album album;
        if (albumRepository.findById(albumId).isPresent()) {
            album = albumRepository.findById(albumId).get();
            album.setCoverPhotoUrl(coverPhotoUrl);
            albumRepository.save(album);
            return album;
        }
        return null;
    }
}
