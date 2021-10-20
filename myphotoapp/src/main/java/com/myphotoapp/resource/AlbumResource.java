package com.myphotoapp.resource;

import com.google.firebase.auth.FirebaseAuthException;
import com.myphotoapp.model.Album;
import com.myphotoapp.model.FirebaseUser;
import com.myphotoapp.model.Photo;
import com.myphotoapp.service.AlbumService;
import com.myphotoapp.service.FirebaseService;
import com.myphotoapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/album")
public class AlbumResource {

    @Autowired
    public AlbumService albumService;

    @Autowired
    public PhotoService photoService;

    @Autowired
    public FirebaseService firebaseService;

    // Get All Albums
    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    // Get photos by AlbumID
    @GetMapping("{albumId}/photos")
    @CrossOrigin
    public List<Photo> getPhotosOfAlbum(@PathVariable("albumId") String albumId, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return photoService.getPhotosOfAlbum(albumId);
        }
        return null;
    }

    // Add new album
    @PostMapping
    @CrossOrigin
    public Album saveAlbum(@RequestBody @Valid Album album) {
        return albumService.saveAlbum(album);
    }

    // Update album
    @PutMapping
    @CrossOrigin
    public Album updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }

    // Update CoverPhoto
    @PutMapping("/{albumId}/coverPhoto")
    @CrossOrigin
    public Album updateCoverPhoto(@PathVariable("albumId") String albumId,@RequestParam("coverPhotoUrl") String coverPhotoUrl, @RequestHeader("idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        return albumService.updateCoverPhoto(coverPhotoUrl, albumId);
    }

    // Delete album
    @DeleteMapping
    public void deleteAlbum(@RequestParam("id") String id){
        albumService.deleteAlbum(id);
    }

    @GetMapping("/find-by-id")
    public Album getAlbumById(@RequestParam("id") String id)   {
        return albumService.getAlbumById(id);
    }




}
