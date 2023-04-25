package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class User {
    private long id;
    private String email;
    private String username;
    private String password;
    private List<User> following;

    public User(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        following = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void follow(User user) {
        if (following.contains(user)) {
            System.out.println("You already follow this user");
            return;
        }
        following.add(user);
        System.out.println("You now follow " + user.getUsername());
    }
}
