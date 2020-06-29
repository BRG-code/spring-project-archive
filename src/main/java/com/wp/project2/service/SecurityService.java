package com.wp.project2.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
