package com.teksen.githubdataemailer.exception.custom;

import com.teksen.githubdataemailer.exception.CustomException;
import org.springframework.http.HttpStatusCode;

public class GitHubApiRequestException extends CustomException {
    public GitHubApiRequestException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
