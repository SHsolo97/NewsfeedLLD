package org.example.services;

import org.example.models.Comment;
import org.example.models.Post;
import org.example.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService {

    private static PostService postService = null;
    List<Post> posts;
    Map<Long, Post> postMap;
    long newPostId;
    long newCommentId;

    private PostService(){
        newPostId = 0;
        newCommentId = 0;
        posts = new ArrayList<>();
        postMap = new HashMap<>();
    }

    public static PostService PostService(){
        if(postService == null){
            postService = new PostService();
        }
        return postService;
    }
    public Post getPostById(long id){
        if(!postMap.containsKey(id))
            return null;
        return postMap.get(id);
    }
    public void addPost(User user, String postContent){
        ++newPostId;
        Post newPost = new Post(newPostId, postContent, user);
        posts.add(newPost);
        postMap.put(newPostId, newPost);
        System.out.println("Post has been added.");
    }

    public void commentOnPost(User user, Post post, String commentContent){
        ++newCommentId;
        Comment newComment = new Comment(newPostId, commentContent, user);
        post.commentOnPost(newComment);
    }


}
