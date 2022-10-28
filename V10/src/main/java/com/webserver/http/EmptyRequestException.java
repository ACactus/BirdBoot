package com.webserver.http;

/**
 * @author ShangJin
 * @description: TODO
 * @date 2022/10/18 10:28
 */
public class EmptyRequestException extends Exception{
    public EmptyRequestException() {

    }

    public EmptyRequestException(String message) {
        super(message);
    }

    public EmptyRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyRequestException(Throwable cause) {
        super(cause);
    }

    public EmptyRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
