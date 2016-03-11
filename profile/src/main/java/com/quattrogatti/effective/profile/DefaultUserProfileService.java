package com.quattrogatti.effective.profile;

import com.quattrogatti.effective.security.UserCredentials;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

public class DefaultUserProfileService implements UserProfileService {

    private static final String secret = "secret"; // TODO

    UserProfileRepository userProfileRepository;

    @Override
    public Optional<UserCredentials> parseCredentials(String basicAuth) {
        if (basicAuth != null && basicAuth.startsWith("Basic")) {
            String base64Credentials = basicAuth.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            final String[] values = credentials.split(":", 2);
            if (values.length == 2) {
                return Optional.of(new UserCredentials(values[0], values[1]));
            }
        }
        return Optional.empty();
    }

    @Override
    public UserProfileAction with(UserProfile userProfile) {
        return new UserProfileAction() {
            @Override
            public UserProfile save() {
                return userProfileRepository.save(userProfile);
            }

            @Override
            public void delete() {
                userProfileRepository.delete(userProfile);
            }

            @Override
            public String createSecurityToken() {
                return null; // TODO
//                return Jwts.builder()
//                        .setSubject(userProfile.getEmail())
//                        .signWith(SignatureAlgorithm.HS512, secret)
//                        .compact();
            }
        };
    }

    @Override
    public UserProfileFinder find() {
        return new UserProfileFinder() {
            @Override
            public Optional<UserProfile> withId(String id) {
                return Optional.ofNullable(userProfileRepository.findOne(id));
            }

            @Override
            public Optional<UserProfile> withEmail(String email) {
                return Optional.ofNullable(userProfileRepository.findByEmail(email));
            }

            @Override
            public Optional<UserProfile> withSecurityToken(String token) {
                String username = "user"; // TODO
//                String username = Jwts.parser()
//                        .setSigningKey(secret)
//                        .parseClaimsJws(token)
//                        .getBody()
//                        .getSubject();
                return withEmail(username);
            }

            @Override
            public Iterable<UserProfile> execute() {
                return userProfileRepository.findAll();
            }
        };
    }
}
