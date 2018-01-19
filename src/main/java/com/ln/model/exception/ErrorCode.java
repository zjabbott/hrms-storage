package com.ln.model.exception;

public enum ErrorCode {
    INVALID_ARGUMENT(400), NO_HANDLER_FOUND(404), MEDIATYPE_NOT_SUPPORT(415), PWD_ERROR(
            416), ILLEGAL_ACCESS_ERROR
            (417), INTERNAL_ERROR
            (500);

    private final int value;

    private ErrorCode(int value) {
        this.value = value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     * @return null if the value is not found.
     */
    public static ErrorCode findByValue(int value) {
        switch (value) {
            case 400:
                return INVALID_ARGUMENT;
            case 404:
                return NO_HANDLER_FOUND;
            case 415:
                return MEDIATYPE_NOT_SUPPORT;
            case 500:
                return INTERNAL_ERROR;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }
}
