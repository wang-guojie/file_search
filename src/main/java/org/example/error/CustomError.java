package org.example.error;

/**
 * 这个表示的是自定义异常，如果爆出异常的父类是CustomError表示是我们自己定义的异常
 */
public class CustomError extends RuntimeException {
    public CustomError(String errorMessage) {
        super(errorMessage);
    }
}
