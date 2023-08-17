package com.teksen.githubdataemailer.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GitHubDataRequest {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Api key is mandatory")
    private String apiKey;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    public GitHubDataRequest() {
    }

    public GitHubDataRequest(String username, String apiKey, String email) {
        this.username = username;
        this.apiKey = apiKey;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isEmpty() {
        return username == null && apiKey == null && email == null;
    }
}
