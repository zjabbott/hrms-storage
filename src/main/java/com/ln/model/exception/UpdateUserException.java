package com.ln.model.exception;

import lombok.Data;

import static com.ln.model.exception.ErrorCode.INTERNAL_ERROR;

@Data
public class UpdateUserException extends RuntimeException {
    public static final ErrorCode code = INTERNAL_ERROR;
    private String reason;
    private Exception exception;

    public UpdateUserException(String reason, Exception exception) {
        super(reason, exception);
        this.reason = reason;
        this.exception = exception;
    }

    public UpdateUserException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
