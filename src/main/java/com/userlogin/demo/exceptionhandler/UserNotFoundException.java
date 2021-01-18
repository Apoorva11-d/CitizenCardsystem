package com.userlogin.demo.exceptionhandler;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String uemailid) {
        super("User id not found : " + uemailid);
    }
}
