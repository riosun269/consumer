package de.kreuzwerker.cdc.messagingapp;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String name;
    private String role;
    private LocalDateTime lastLogin;
    private List<Friend> friends;

    public User(String name, String role, LocalDateTime lastLogin, List<Friend> friends) {
        this.name = name;
        this.role = role;
        this.lastLogin = lastLogin;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
