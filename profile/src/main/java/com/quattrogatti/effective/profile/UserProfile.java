package com.quattrogatti.effective.profile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(builder = UserProfile.UserProfileBuilder.class)
public final class UserProfile {

    private final String id;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final boolean credentialsExpired;
    private final boolean accountExpired;
    private final boolean accountLocked;
    private final boolean accountActive;
    private final Set<String> securityRoles;

    private UserProfile(UserProfileBuilder userProfileBuilder) {
        this.id = userProfileBuilder.id;
        this.email = userProfileBuilder.email;
        this.firstName = userProfileBuilder.firstName;
        this.lastName = userProfileBuilder.lastName;
        this.credentialsExpired = userProfileBuilder.credentialsExpired;
        this.accountExpired = userProfileBuilder.accountExpired;
        this.accountLocked = userProfileBuilder.accountLocked;
        this.accountActive = userProfileBuilder.accountActive;
        this.securityRoles = new HashSet<>(userProfileBuilder.securityRoles);
    }

    public static UserProfileBuilder userProfile() {
        return new UserProfileBuilder();
    }

    public static UserProfileBuilder from(UserProfile userProfile) {
        return new UserProfileBuilder(userProfile);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public Set<String> getSecurityRoles() {
        return new HashSet<>(securityRoles);
    }

    @JsonIgnoreProperties({"id"})
    public static final class UserProfileBuilder {

        private String id;
        private String email;
        private String firstName;
        private String lastName;
        private boolean credentialsExpired;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean accountActive;
        private Set<String> securityRoles;

        @JsonCreator
        private UserProfileBuilder() {
        }

        private UserProfileBuilder(UserProfile userProfile) {
            this.id = userProfile.id;
            this.email = userProfile.email;
            this.firstName = userProfile.firstName;
            this.lastName = userProfile.lastName;
            this.credentialsExpired = userProfile.credentialsExpired;
            this.accountExpired = userProfile.accountExpired;
            this.accountLocked = userProfile.accountLocked;
            this.accountActive = userProfile.accountActive;
            this.securityRoles = new HashSet<>(userProfile.securityRoles);
        }

        public UserProfileBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserProfileBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserProfileBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserProfileBuilder withCredentialsExpired(boolean expired) {
            this.credentialsExpired = expired;
            return this;
        }

        public UserProfileBuilder withAccountExpired(boolean expired) {
            this.accountExpired = expired;
            return this;
        }

        public UserProfileBuilder withAccountLocked(boolean locked) {
            this.accountLocked = locked;
            return this;
        }

        public UserProfileBuilder withAccountActive(boolean active) {
            this.accountActive = active;
            return this;
        }

        public UserProfileBuilder clearSecurityRoles() {
            securityRoles.clear();
            return this;
        }

        public UserProfileBuilder addSecurityRole(String role) {
            securityRoles.add(role);
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
