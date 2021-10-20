package com.myphotoapp.service;

import com.myphotoapp.model.Comment;
import com.myphotoapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommment(String id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsOfPhotos(String photoId) {
        return commentRepository.findAllByPhotoId(photoId);
    }
}
