package org.example.services;

import org.example.models.Comment;
import org.example.models.Post;
import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private static PostService postService = null;
    List<Post> posts;
    long newPostId;
    long newCommentId;

    private PostService(){
        newPostId = 0;
        newCommentId = 0;
        posts = new ArrayList<>();
    }

    public static PostService PostService(){
        if(postService == null){
            postService = new PostService();
        }
        return postService;
    }
    public void addPost(User user, String postContent){
        ++newPostId;
        Post newPost = new Post(newPostId, postContent, user);
        posts.add(newPost);
        System.out.println("Post has been added.");
    }

    public void commentOnPost(User user, Post post, String commentContent){
        ++newCommentId;
        Comment newComment = new Comment(newPostId, commentContent, user);
        post.commentOnPost(newComment);
    }


}
