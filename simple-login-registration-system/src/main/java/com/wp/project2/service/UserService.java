package com.wp.project2.service;

import com.wp.project2.model.User;

// Service가 지원하는 메소드를 기술한 Interface. 실제로는 UserServiceImpl.java에서 실행 됨.
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
