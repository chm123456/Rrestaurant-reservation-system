package com.example.common;

public class Result {
    private String code;    // Response code
    private String msg;     // Response message
    private Object data;    // Response data

    private Result(Object data) {

        this.data = data;
    }
    // Constructor with data parameter
    public Result() {
    }
    // Default constructor
    // Static method to create a success result without data
    public static Result success() {
        Result tResult = new Result();
        tResult.setCode(ResultCode.SUCCESS.code);
        tResult.setMsg(ResultCode.SUCCESS.msg);
        return tResult;
    }
    // Static method to create a success result with data
    public static Result success(Object data) {
        Result tResult = new Result (data);
        tResult.setCode(ResultCode.SUCCESS.code);
        tResult.setMsg(ResultCode.SUCCESS.msg);
        return tResult;
    }
    // Static method to create an error result without a custom code and message
    public static Result error() {
        Result tResult = new Result();
        tResult.setCode(ResultCode.ERROR.code);
        tResult.setMsg(ResultCode.ERROR.msg);
        return tResult;
    }
    // Static method to create an error result with a custom code and message
    public static Result error(String code, String msg) {
        Result tResult = new Result();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }
    // Static method to create an error result using a predefined ResultCode
    public static Result error(ResultCode resultCode) {
        Result tResult = new Result();
        tResult.setCode(resultCode.code);
        tResult.setMsg(resultCode.msg);
        return tResult;
    }
    // Getter method for the code
    public String getCode() {

        return code;
    }
    // Setter method for the code
    public void setCode(String code) {

        this.code = code;
    }
    // Getter method for the message
    public String getMsg() {

        return msg;
    }
    // Setter method for the message
    public void setMsg(String msg) {

        this.msg = msg;
    }
    // Getter method for the data
    public Object getData() {

        return data;
    }
    // Setter method for the data
    public void setData(Object data) {

        this.data = data;
    }
}