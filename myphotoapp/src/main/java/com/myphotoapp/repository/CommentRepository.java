package com.myphotoapp.repository;

import com.myphotoapp.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPhotoId(String photoId);
}
