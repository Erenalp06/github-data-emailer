package com.teksen.githubdataemailer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoDetails {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    @JsonProperty("forks_count")
    private int forksCount;

    @JsonIgnore
    private ViewData viewData;

    public RepoDetails(String fullName, int stargazersCount, int forksCount, ViewData viewData) {
        this.fullName = fullName;
        this.stargazersCount = stargazersCount;
        this.forksCount = forksCount;
        this.viewData = viewData;
    }

    public RepoDetails() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public void setViewData(ViewData viewData) {
        this.viewData = viewData;
    }
}
