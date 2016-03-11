package com.quattrogatti.effective.profile;

import java.util.Set;

public interface UserProfileRepository {
    UserProfile findByEmail(String email);

    UserProfile save(UserProfile userProfile);

    UserProfile delete(UserProfile userProfile);

    UserProfile findOne(String id);

    Set<UserProfile> findAll();
}
