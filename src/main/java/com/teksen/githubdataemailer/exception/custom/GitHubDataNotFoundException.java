package com.teksen.githubdataemailer.exception.custom;

import com.teksen.githubdataemailer.exception.CustomException;
import org.springframework.http.HttpStatusCode;

public class GitHubDataNotFoundException extends CustomException {
    public GitHubDataNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
