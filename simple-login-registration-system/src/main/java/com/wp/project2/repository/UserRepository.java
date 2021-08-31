package com.wp.project2.repository;

import com.wp.project2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// dataSource에서 data를 검색해주는 Framework
public interface UserRepository extends JpaRepository<User, Long> {
    // username을 기반으로 찾아주는 메소드
    User findByUsername(String username);
}
