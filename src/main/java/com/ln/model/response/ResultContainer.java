/*
 * Copyright (c) 2014 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ln.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultContainer<T> implements Serializable {

    private static final long serialVersionUID = 1395394940396422222L;

    private int code = 0;
    private String message = "success";
    private T result = null;

    public ResultContainer(T result) {
        this.result = result;
    }

    public ResultContainer(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultContainer(int code, String message, T result) {
        this(code, message);
        this.result = result;
    }
}
