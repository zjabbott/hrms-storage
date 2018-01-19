package com.ln.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginRequest {
    @NotEmpty(message = "工号不能为空")
    private String eid;

    @NotEmpty(message = "密码错误，")
    @Length(min = 30, message = "密码错误")
    private String pwd;
}
