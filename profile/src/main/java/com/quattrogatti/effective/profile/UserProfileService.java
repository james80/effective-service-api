package com.quattrogatti.effective.profile;

import com.quattrogatti.effective.security.UserCredentials;

import java.util.Optional;

public interface UserProfileService {

    Optional<UserCredentials> parseCredentials(String basicAuth);

    UserProfileAction with(UserProfile userProfile);

    UserProfileFinder find();

    interface UserProfileAction {
        UserProfile save();

        void delete();

        String createSecurityToken();
    }

    interface UserProfileFinder {
        Optional<UserProfile> withId(String id);

        Optional<UserProfile> withEmail(String email);

        Optional<UserProfile> withSecurityToken(String token);

        Iterable<UserProfile> execute();
    }
}
