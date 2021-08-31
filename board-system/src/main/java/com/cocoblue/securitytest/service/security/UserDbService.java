package com.cocoblue.securitytest.service.security;

import java.util.List;

public interface UserDbService {
    UserEntity getUser(String loginUserId);
    List<UserRoleEntity> getUserRoles(String loginUserId);
}