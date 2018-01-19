package com.ln.model.exception;

import lombok.Data;

import static com.ln.model.exception.ErrorCode.ILLEGAL_ACCESS_ERROR;

@Data
public class IllegalAccessException extends RuntimeException {
    public static final ErrorCode code = ILLEGAL_ACCESS_ERROR;
    private String reason;
    private Exception exception;

    public IllegalAccessException(String reason, Exception exception) {
        super(reason, exception);
        this.reason = reason;
        this.exception = exception;
    }

    public IllegalAccessException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
