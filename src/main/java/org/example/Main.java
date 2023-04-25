package org.example;

import org.example.services.UserService;
import org.example.utils.EmailValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = UserService.UserService();
        userService.start(sc);
    }
}