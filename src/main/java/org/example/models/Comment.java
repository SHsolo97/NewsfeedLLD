package org.example.models;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class Comment {
    private long id;
    private String content;
    Set<User> upVotedUsers;
    Set<User> downVotedUsers;
    private User commentedBy;
    Timestamp timestamp;

    public Comment(long id, String content, User commentedBy){
        this.id = id;
        this.content = content;
        upVotedUsers = new HashSet<>();
        downVotedUsers = new HashSet<>();
        this.commentedBy = commentedBy;
        timestamp = new Timestamp(System.currentTimeMillis());
    }
    public void showComment(){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        long currentTime = currentTimestamp.getTime();
        long commentTime = timestamp.getTime();
        long timeElapsed = (currentTime- commentTime)/(60*1000000000);
        System.out.println(commentedBy.getUsername()+": "+content+" "+timeElapsed+"minutes ago");
    }
    public int getUpVotesCount(){
        return upVotedUsers.size();
    }
    public int getDownVotesCount(){
        return downVotedUsers.size();
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
