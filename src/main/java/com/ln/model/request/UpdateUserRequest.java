package com.ln.model.request;

import com.ln.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {

    @NotNull(message = "系统错误,")
    private Long id;

    private String name;

    @Length(min = 30, message = "系统错误，")
    private String pwd;

    private String email;

    private String avatar;

    private String address;

    @Length(min = 11, max = 11, message = "电话号码只能是11位,")
    private String phone;

    @Max(value = 2, message = "性别格式不对")
    @Min(value = 0, message = "性别格式不对")
    private int gender;

    private int status;

    private int permit;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhone(phone);
        user.setPwd(pwd);
        user.setPermit(permit);
        user.setStatus(status);
        long time = System.currentTimeMillis();
        user.setUpdateTime(time);
        return user;
    }

}
