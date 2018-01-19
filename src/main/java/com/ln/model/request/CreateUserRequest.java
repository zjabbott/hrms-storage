package com.ln.model.request;

import com.ln.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.ln.util.Constant.USER_PERMIT_DEFAULT;
import static com.ln.util.Constant.USER_STATUS_NORMAL;

@Data
public class CreateUserRequest {

    @NotEmpty(message = "工号不能为空")
    private String eid;

    @NotEmpty(message = "名字不能为空，")
    private String name;

    @NotEmpty(message = "系统错误，")
    @Length(min = 30, message = "系统错误，")
    private String pwd;

    @NotEmpty(message = "邮箱不能为空,")
    private String email;

    private String avatar;

    private String address;

    @NotEmpty(message = "电话号码只能是11位,")
    @Length(min = 11, max = 11, message = "电话号码只能是11位,")
    private String phone;

    @NotNull(message = "性别不能为空,")
    @Max(value = 1, message = "性别格式不对")
    @Min(value = 0, message = "性别格式不对")
    private Integer gender;

    @NotNull(message = "入职时间不能为空,")
    private Long inTime;

    public User toUser() {
        User user = new User();
        user.setEid(eid);
        user.setName(name);
        user.setAddress(address);
        user.setAvatar(avatar);
        user.setEmail(email);
        user.setGender(gender);
        user.setInTime(inTime);
        user.setPhone(phone);
        user.setPwd(pwd);
        user.setPermit(USER_PERMIT_DEFAULT);
        user.setStatus(USER_STATUS_NORMAL);
        long time = System.currentTimeMillis();
        user.setCreateTime(time);
        user.setUpdateTime(time);
        return user;
    }
}
