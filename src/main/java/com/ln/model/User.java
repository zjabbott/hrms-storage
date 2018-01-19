package com.ln.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String eid;

    private String name;

    private String pwd;

    private String email;

    private String avatar;

    private String address;

    private String phone;

    private int gender;

    private int status;

    private int permit;

    private long inTime;

    private long outTime;

    private long createTime;

    private long updateTime;
}
