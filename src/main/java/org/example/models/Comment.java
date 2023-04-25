package org.example.models;


import java.util.HashSet;
import java.util.Set;


public class Comment {
    private long id;
    private String content;
    Set<User> upVotedUsers;
    Set<User> downVotedUsers;
    private User commentedBy;

    public Comment(long id, String content, User commentedBy){
        this.id = id;
        this.content = content;
        upVotedUsers = new HashSet<>();
        downVotedUsers = new HashSet<>();
        this.commentedBy = commentedBy;
    }
    public void showComment(){
        System.out.println(commentedBy+": "+content);
    }
    public void upVoteComment(User user){
        if(downVotedUsers.contains(user))
            downVotedUsers.remove(user);
        upVotedUsers.add(user);
    }
    public void downVoteComment(User user){
        if(upVotedUsers.contains(user))
            upVotedUsers.remove(user);
        downVotedUsers.add(user);
    }

}
