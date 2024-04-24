package org.example.commerceback.user;

public class UserResponse {
    public record LoginDTO(String accessToken, String role, Long id) {}
}
