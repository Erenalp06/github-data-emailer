package com.teksen.githubdataemailer.exception;

import org.springframework.http.HttpStatusCode;

public class CustomException extends RuntimeException {

    private HttpStatusCode httpStatusCode;

    public CustomException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
