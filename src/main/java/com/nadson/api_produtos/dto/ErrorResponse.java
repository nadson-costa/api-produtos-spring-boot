package com.nadson.api_produtos.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(Integer status, String error, String message, String path){
        this.timestamp = LocalDateTime.now().toString();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public String getTimestamp(){
        return timestamp;
    }
    public Integer getStatus(){
        return status;
    }
    public String getError(){
        return error;
    }
    public String getMessage(){
        return message;
    }
    public String getPath(){
        return path;
    }

}
