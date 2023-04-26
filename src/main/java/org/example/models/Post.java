package org.example.models;


import java.sql.Timestamp;
import java.util.*;


public class Post implements Comparable<Post> {
    private long id;
    private String postContent;
    private Set<User> upVotedUsers;
    private Set<User> downVotedUsers;
    private List<Comment> comments;
    private User postedBy;
    private Timestamp timestamp;

    public Post(long id, String postContent, User postedBy) {
        this.id = id;
        this.postContent = postContent;
        this.postedBy = postedBy;
        comments = new ArrayList<>();
        upVotedUsers = new HashSet<>();
        downVotedUsers = new HashSet<>();
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public int compareTo(Post post2) {
        int score1 = this.getPostScore();
        int score2 = post2.getPostScore();
        if (score1 != score2) {
            return Integer.compare(score2, score1);
        } else if (this.getComments().size() != post2.getComments().size()) {
            return Integer.compare(post2.getComments().size(), this.getComments().size());
        } else {
            return post2.getTimestamp().compareTo(this.getTimestamp());
        }
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public long getId() {
        return id;
    }

    public String getPostContent() {
        return postContent;
    }

    public Set<User> getUpVotedUsers() {
        return upVotedUsers;
    }

    public Set<User> getDownVotedUsers() {
        return downVotedUsers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void showPost(){
        System.out.println("User: "+ postedBy.getUsername() +" "+postContent+" Upvotes: "+getUpVotesCount()+" Downvotes: "+getDownVotesCount() );
    }

    public void showAllComments(){
        for(Comment comment:comments){
            comment.showComment();
        }
    }
    public int getUpVotesCount(){
        return upVotedUsers.size();
    }
    public int getDownVotesCount(){
        return downVotedUsers.size();
    }
    public int getPostScore(){
        int postScore = upVotedUsers.size() - downVotedUsers.size();
        return postScore;
    }

    public void upVotePost(User user){
        if(downVotedUsers.contains(user))
            downVotedUsers.remove(user);
        upVotedUsers.add(user);
    }

    public void downVotePost(User user){
        if(upVotedUsers.contains(user))
            upVotedUsers.remove(user);
        downVotedUsers.add(user);
    }

    public void commentOnPost(Comment comment){
        comments.add(comment);
    }


}
