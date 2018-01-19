package com.ln.controller;

import com.ln.advice.AccessCheck;
import com.ln.model.User;
import com.ln.model.request.*;
import com.ln.model.response.LoginResponse;
import com.ln.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @AccessCheck(value = 8)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(HttpServletRequest request, @RequestBody @Valid CreateUserRequest createUserRequest) {
        service.create(createUserRequest.toUser());
    }

    @AccessCheck(value = 8)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(HttpServletRequest request, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        service.update(updateUserRequest.toUser());
    }

    @RequestMapping(value = "updateself", method = RequestMethod.POST)
    public void updateself(HttpServletRequest request,
            @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        service.update(updateUserRequest.toUser());
    }

    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<User> getList(HttpServletRequest request, @RequestParam(required = false) Long id,
            @RequestParam(required = false,
                    defaultValue =
                            "10") int limit) {
        return service.getList(id, limit);
    }

    @RequestMapping(value = "getByEid", method = RequestMethod.GET)
    public User getByEid(HttpServletRequest request, @RequestParam String eid) {
        return service.getByEid(eid);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public User get(HttpServletRequest request, @RequestParam Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    @AccessCheck(value = 8)
    @RequestMapping(value = "/addPermit", method = RequestMethod.POST)
    public void addPermit(HttpServletRequest request, @RequestBody @Valid AddPermitRequest addPermitRequest) {
        service.addPermit(addPermitRequest);
    }

    @AccessCheck(value = 8)
    @RequestMapping(value = "/removePermit", method = RequestMethod.POST)
    public void removePermit(HttpServletRequest request, @RequestBody @Valid RemovePermitRequest
            removePermitRequest) {
        service.removePermit(removePermitRequest);
    }
}
