package com.myphotoapp.resource;


import com.google.firebase.auth.FirebaseAuthException;
import com.myphotoapp.model.Comment;
import com.myphotoapp.model.FirebaseUser;
import com.myphotoapp.model.Photo;
import com.myphotoapp.service.CommentService;
import com.myphotoapp.service.FirebaseService;
import com.myphotoapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/photo")
public class PhotoResource {

    @Autowired
    public PhotoService photoService;

    @Autowired
    public FirebaseService firebaseService;

    @Autowired
    public CommentService commentService;

    // Get photos
    @GetMapping
    public List<Photo> getAllPhotos(){
        return photoService.getAllPhotos();
    }

    // Post new photos
    @PostMapping
    @CrossOrigin
    public Photo savePhoto(@RequestBody @Valid Photo photo){
        return photoService.savePhoto(photo);
    }

    // update photos
    @PutMapping
    public Photo updatePhoto(@RequestBody @Valid Photo photo){
        return photoService.updatePhoto(photo);
    }

    // Delete Photos
    @DeleteMapping
    public void deletePhoto(@RequestParam("id") String id){
        photoService.deletePhoto(id);
    }

    // Get Photos by Id
    @GetMapping("{id}")
    public List<Photo> getPhotoById(@PathVariable("id") String id, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return photoService.getPhotoById(id);
        }
        return null;
    }

    // Save Comments
    @PostMapping("comments")
    @CrossOrigin
    public Comment saveComment(@RequestBody Comment comment, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return commentService.saveComment(comment);
        }
        return null;
    }

    // get comments by PhotoId
    @GetMapping("{photoId}/comments")
    @CrossOrigin
    public List<Comment> getCommentsOfPhotos(@PathVariable("photoId") String photoId, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            return commentService.getCommentsOfPhotos(photoId);
        }
        return null;
    }
}
