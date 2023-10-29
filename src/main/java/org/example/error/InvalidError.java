package org.example.error;


/**
 * 这个是无效盘符异常
 */
public class InvalidError extends CustomError{


    public InvalidError(String errorMessage) {
        super(errorMessage);
    }
}
