package jad.farmacy.configurations;

import jad.farmacy.Entity.Store;

import java.util.List;

public class GlobalResponse {
    private long statusCode;
    private String status;
    private String message;
    private Object reply;

    public GlobalResponse(long statusCode, String status, String message, Object reply) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.reply = reply;
    }

    public GlobalResponse() {
    }



    public long getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(long statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }
}
