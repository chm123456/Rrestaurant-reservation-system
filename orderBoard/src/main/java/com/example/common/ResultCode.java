package com.example.common;

public enum ResultCode {
    SUCCESS("0", "Success"),
    // Successful response code and message
    ERROR("-1", "System Error"),
    // General system error response code and message
    PARAM_ERROR("1001", "Parameter Error"),
    // Parameter error response code and message
    USER_EXIST_ERROR("2001", "The user name already exists"),
    // User name already exists error response code and message
    USER_NOT_LOGIN("2001", "User not logged in"),
    // User not logged in error response code and message
    USER_ACCOUNT_ERROR("2002", "The account or password is incorrect"),
    // Incorrect account or password error response code and message
    USER_NOT_EXIST_ERROR("2003", "The user not exist"),
    // User does not exist error response code and message
    PARAM_LOST_ERROR("2004", "Parameter Missing"),
    // Missing parameter error response code and message
    PARAM_PASSWORD_ERROR("2005", "The origin password was entered incorrectly"),
    // Incorrect original password error response code and message
    ;

    public String code;  // Response code
    public String msg;  // Response message

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}