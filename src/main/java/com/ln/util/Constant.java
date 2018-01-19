package com.ln.util;

import com.ln.model.Permissions;
import com.ln.model.Roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static final int USER_STATUS_NORMAL = 0;

    public static final int USER_PERMIT_DEFAULT = 0;

    public static final long EXPIRETIME = 24 * 60 * 60 * 1000;

    public static Map<String, Integer> PERMISSIONS = new HashMap<>();

    public static List<Permissions> PERMISSIONS_LIST = new ArrayList<>();

    public static Map<String, Integer> ROLES = new HashMap<>();

    public static List<Roles> ROLES_LIST = new ArrayList<>();

    public static String createToken(String eid, Long id) {
        String s = eid + id + System.nanoTime();
        return MD5Util.getMD5(s);
    }

}
