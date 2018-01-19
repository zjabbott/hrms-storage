package com.ln.service;

import com.ln.dao.PermissionsMapper;
import com.ln.dao.RolesMapper;
import com.ln.model.Permissions;
import com.ln.model.Roles;
import com.ln.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PermissionsService {
    @Autowired
    private PermissionsMapper permissionsMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @PostConstruct
    public void init() {
        Constant.PERMISSIONS_LIST = permissionsMapper.get();
        Constant.ROLES_LIST = rolesMapper.get();
        for (Permissions permissions : Constant.PERMISSIONS_LIST) {
            Constant.PERMISSIONS.put(permissions.getFunction(), permissions.getPermit());
        }
        for (Roles roles : Constant.ROLES_LIST) {
            Constant.ROLES.put(roles.getRole(), roles.getPermit());
        }
    }

}
