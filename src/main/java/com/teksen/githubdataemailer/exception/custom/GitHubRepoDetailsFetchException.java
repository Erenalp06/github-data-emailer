package com.teksen.githubdataemailer.exception.custom;

import com.teksen.githubdataemailer.exception.CustomException;
import org.springframework.http.HttpStatusCode;

public class GitHubRepoDetailsFetchException extends CustomException {

    public GitHubRepoDetailsFetchException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
