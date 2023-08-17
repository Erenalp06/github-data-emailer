package com.teksen.githubdataemailer.util;

import com.teksen.githubdataemailer.entity.GitHubData;
import com.teksen.githubdataemailer.entity.GitHubDataRequest;

public class GitHubDataMapper {

    public static GitHubData mapToGitHubData(GitHubDataRequest gitHubDataRequest) {
        return new GitHubData(gitHubDataRequest.getUsername(), null, gitHubDataRequest.getApiKey(), gitHubDataRequest.getEmail());
    }
}
