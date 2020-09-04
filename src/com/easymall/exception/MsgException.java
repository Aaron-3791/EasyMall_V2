package com.easymall.exception;

/**
 * 自定义异常
 * Runtime Exception
 */
public class MsgException extends RuntimeException {
    public MsgException() {
    }

    public MsgException(String message) {
        super(message);
    }
}
