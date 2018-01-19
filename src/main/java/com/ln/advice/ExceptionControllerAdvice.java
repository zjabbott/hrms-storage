package com.ln.advice;

import com.ln.model.exception.ErrorCode;
import com.ln.model.exception.IllegalAccessException;
import com.ln.model.exception.PwdErrorException;
import com.ln.model.exception.UpdateUserException;
import com.ln.model.response.ResultContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.ln.model.exception.ErrorCode.*;

@Slf4j
@RestControllerAdvice("com.ln.controller")
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResultContainer<Void> exceptionHandler(Exception exception) {
        String message = null;
        exception.printStackTrace();
        ErrorCode code;

        if (exception instanceof MethodArgumentNotValidException) {
            StringBuffer messages = new StringBuffer();
            for (FieldError error : ((MethodArgumentNotValidException) exception).getBindingResult()
                    .getFieldErrors()) {
                messages.append(error.getDefaultMessage());
            }
            code = INVALID_ARGUMENT;
            message = messages.substring(0, messages.length() - 1);
        } else if (exception instanceof HttpMessageNotReadableException
                || exception instanceof MissingServletRequestParameterException) {
            code = INVALID_ARGUMENT;
            message = "Request parameter format invalid";
        } else if (exception instanceof NoHandlerFoundException) {
            code = NO_HANDLER_FOUND;
            message = "Request resource does not exist";
        } else if (exception instanceof HttpMediaTypeNotSupportedException) {
            HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException =
                    (HttpMediaTypeNotSupportedException) exception;
            code = MEDIATYPE_NOT_SUPPORT;
            message =
                    "Content type '" + httpMediaTypeNotSupportedException.getContentType() + "' not supported";
        } else if (exception instanceof PwdErrorException) {
            code = PwdErrorException.code;
            message = ((PwdErrorException) exception).getReason();
        } else if (exception instanceof IllegalAccessException) {
            code = PwdErrorException.code;
            message = ((IllegalAccessException) exception).getReason();
        } else if (exception instanceof UpdateUserException) {
            code = PwdErrorException.code;
            message = ((UpdateUserException) exception).getReason();
        } else {
            code = INTERNAL_ERROR;
            message = "other exception";
        }
        return new ResultContainer<>(code.getValue(), message);
    }
}
