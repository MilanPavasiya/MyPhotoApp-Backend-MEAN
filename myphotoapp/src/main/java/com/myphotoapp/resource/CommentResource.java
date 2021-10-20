package com.myphotoapp.resource;

import com.myphotoapp.model.Comment;
import com.myphotoapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/comment")
public class CommentResource {

    @Autowired
    public CommentService commentService;

    @GetMapping("/allComments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    public Comment saveComment(@RequestBody @Valid Comment comment){
        return commentService.saveComment(comment);
    }

    @PutMapping
    public Comment updateComment(@RequestBody Comment comment){
        return commentService.updateComment(comment);
    }

    @DeleteMapping
    public void deleteCommment(@RequestParam("id") String id){
        commentService.deleteCommment(id);
    }
}
