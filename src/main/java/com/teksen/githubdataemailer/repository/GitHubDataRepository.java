package com.teksen.githubdataemailer.repository;

import com.teksen.githubdataemailer.entity.GitHubData;
import com.teksen.githubdataemailer.entity.RepoDetails;
import com.teksen.githubdataemailer.exception.custom.GitHubDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GitHubDataRepository {

    private GitHubData gitHubData;

    public GitHubData save(GitHubData data, List<RepoDetails> repos) {
        data.setRepos(repos);
        this.gitHubData = data;
        return data;
    }

    public GitHubData findAll() {
        if(gitHubData == null){
            throw new GitHubDataNotFoundException("Data not found. Please add data first. (POST /api/v1/github)", HttpStatus.NOT_FOUND);
        }
        return gitHubData;
    }
}
