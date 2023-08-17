package com.teksen.githubdataemailer.exception.response;

import java.util.Date;

public class DefaultResponse {
    private int status;
    private String exceptionType;
    private String message;
    private Date timestamp;

    public DefaultResponse(int status, String exceptionType, String message, Date timestamp) {
        this.status = status;
        this.exceptionType = exceptionType;
        this.message = message;
        this.timestamp = timestamp;
    }

    public DefaultResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
