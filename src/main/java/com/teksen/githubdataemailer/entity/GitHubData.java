package com.teksen.githubdataemailer.entity;


import java.util.List;

public class GitHubData {
    private String username;
    private List<RepoDetails> repos;
    private String apiKey;
    private String email;


    public GitHubData() {
    }

    public GitHubData(String username, List<RepoDetails> repos, String apiKey, String email) {
        this.username = username;
        this.repos = repos;
        this.apiKey = apiKey;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RepoDetails> getRepos() {
        return repos;
    }

    public void setRepos(List<RepoDetails> repos) {
        this.repos = repos;
    }

    public Boolean isEmpty() {
        return username == null && apiKey == null && email == null;
    }
}
