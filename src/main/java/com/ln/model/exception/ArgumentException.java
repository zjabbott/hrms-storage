package com.ln.model.exception;

import lombok.Data;

import static com.ln.model.exception.ErrorCode.INVALID_ARGUMENT;

@Data
public class ArgumentException extends RuntimeException {
    public static final ErrorCode code = INVALID_ARGUMENT;
    private String reason;
    private Exception exception;

    public ArgumentException(String reason, Exception exception) {
        super(reason, exception);
        this.reason = reason;
        this.exception = exception;
    }

    public ArgumentException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
