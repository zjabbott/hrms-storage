package com.ln.model.exception;

import lombok.Data;

import static com.ln.model.exception.ErrorCode.PWD_ERROR;

@Data
public class PwdErrorException extends RuntimeException {
    public static final ErrorCode code = PWD_ERROR;
    private String reason;
    private Exception exception;

    public PwdErrorException(String reason, Exception exception) {
        super(reason, exception);
        this.reason = reason;
        this.exception = exception;
    }

    public PwdErrorException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
