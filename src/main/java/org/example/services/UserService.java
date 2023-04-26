package org.example.services;

import org.example.models.Post;
import org.example.models.User;
import org.example.utils.EmailValidator;

import java.util.*;


public class UserService {
    private static UserService userService = null;
    private PostService postService;
    HashMap<String, User> userLoginAuth;
    List<User> userList;
    List<Post> newsFeed;
    boolean isAuth;
    User currentUser;
    long newUserId = 0;
    private UserService(){
        userLoginAuth = new HashMap<>();
        userList = new ArrayList<>();
        postService = PostService.PostService();
        isAuth = false;
        newUserId = 0;
        currentUser = null;
    }

    public static UserService UserService(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public void start(Scanner sc){
        UserService userService = UserService.UserService();
        while(true){
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            int input = sc.nextInt();
            if(input == 3)
                break;
            if(input!=1 && input!=2)
                continue;
            if(input == 1){
                while(true){
                    System.out.println("Enter email: ");
                    String email = sc.next();
                    if(!EmailValidator.validateEmail(email)){
                        System.out.println("Invalid email !!! Try again.");
                        continue;
                    }
                    System.out.println("Enter username: ");
                    String username = sc.next();
                    System.out.println("Enter password: ");
                    String password = sc.next();
                    userService.signUpUser(email, username, password);
                    break;
                }
            }
            if(input == 2){
                System.out.println("Enter email: ");
                String email = sc.next();
                System.out.println("Enter password: ");
                String password = sc.next();
                userService.signInUser(email, password);
                while(isAuth){
                    userService.setUpNewsFeed();
                    System.out.println("1. Show Newsfeed");
                    System.out.println("2. Add Post");
                    System.out.println("3. Follow a user");
                    System.out.println("4. Log out");
                    int authUserInput = sc.nextInt();
                    if(authUserInput!=1 && authUserInput!=2 &&authUserInput!=3 && authUserInput!=4){
                        System.out.println("Invalid input !!!");
                        continue;
                    }
                    if(authUserInput == 1){
                        userService.showNewsFeed();
                        System.out.println("0. Exit");
                        System.out.print("Select post number: ");
                        int postNumber = sc.nextInt();
                        if(postNumber == 0)
                            continue;
                        if(postNumber<=userService.newsFeed.size() && postNumber>0){
                            Post post = newsFeed.get(postNumber-1);
                            post.showAllComments();
                            System.out.print("1.Upvote    2.Downvote    3.Comment    4.Follow author of post  ");
                            int postInput = sc.nextInt();
                            if(postInput == 1){
                                post.upVotePost(userService.currentUser);
                            }else if(postInput == 2){
                                post.downVotePost(userService.currentUser);
                            }else if(postInput == 3){
                                sc.nextLine();
                                System.out.print("Enter comment for post: ");
                                String commentContent = sc.nextLine();
                                postService.commentOnPost(userService.currentUser, post, commentContent);
                            }else if(postInput == 4){
                                userService.currentUser.follow(post.getPostedBy());
                            }else{
                                System.out.println("Invalid input");
                            }
                        }else{
                            System.out.println("Post with that number does not exist!!!.");
                        }
                    } else if(authUserInput == 2){
                        sc.nextLine();
                        System.out.println(userService.currentUser.getUsername()+" Enter your post: ");
                        String postContent = sc.nextLine();
                        postService.addPost(userService.currentUser, postContent);
                    } else if(authUserInput == 3){
                        String tryEmail = sc.next();
                        if(userService.userLoginAuth.containsKey(tryEmail)){
                            userService.currentUser.follow(userService.userLoginAuth.get(tryEmail));
                        }else{
                            System.out.println("Email does not exist");
                        }
                    } else if(authUserInput == 4){
                        System.out.println("Signing out of account.");
                        userService.signOutUser();
                    }
                }
            }
        }
    }

    private void showNewsFeed(){
        for(int i=0;i<newsFeed.size();i++){
            System.out.print((i+1)+" ");
            newsFeed.get(i).showPost();
        }
    }
    private void setUpNewsFeed(){
        if(!isAuth)
            return;
        newsFeed = new ArrayList<>();
        Collections.sort(postService.posts);
        for(Post post : postService.posts){
            if(currentUser.getFollowing().contains(post.getPostedBy()))
                newsFeed.add(post);
        }
        for(Post post : postService.posts){
            if(!currentUser.getFollowing().contains(post.getPostedBy()))
                newsFeed.add(post);
        }
    }
    public void signUpUser(String email, String username, String password){
        if(userLoginAuth.containsKey(email)) {
            System.out.println("Account with email already exists.");
            return;
        }
        ++newUserId;
        User newUser = new User(newUserId, email, username, password);
        userList.add(newUser);
        userLoginAuth.put(email, newUser);
        System.out.println("New user account has been created");
    }
    public void signOutUser(){
        isAuth = false;
    }

    public void signInUser(String email, String password){
        if(!userLoginAuth.containsKey(email)){
            System.out.println("Account with email does not exist");
            return;
        }else{
            if(userLoginAuth.get(email).getPassword().equals(password)){
                isAuth = true;
                currentUser = userLoginAuth.get(email);
                System.out.println("Log in successful.");
                setUpNewsFeed();
            }else{
                System.out.println("Incorrect password");
            }
        }
    }
}
