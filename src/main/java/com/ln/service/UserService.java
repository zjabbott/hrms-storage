package com.ln.service;

import com.ln.dao.TokenMapper;
import com.ln.dao.UserMapper;
import com.ln.model.Token;
import com.ln.model.User;
import com.ln.model.exception.ArgumentException;
import com.ln.model.exception.PwdErrorException;
import com.ln.model.request.AddPermitRequest;
import com.ln.model.request.LoginRequest;
import com.ln.model.request.RemovePermitRequest;
import com.ln.model.response.LoginResponse;
import com.ln.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ln.util.Constant.EXPIRETIME;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenMapper tokenMapper;

    public void create(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public List<User> getList(Long id, int limit) {
        return userMapper.getList(id, limit);
    }

    public User getByEid(String eid) {
        return userMapper.getByEid(eid);
    }

    public User get(Long id) {
        return userMapper.get(id);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userMapper.getByEid(loginRequest.getEid());
        if (user == null) {
            throw new PwdErrorException("员工号不存在");
        }
        if (!user.getPwd().equals(loginRequest.getPwd())) {
            throw new PwdErrorException("密码错误");
        }
        tokenMapper.delete(user.getId());
        String tokenString = Constant.createToken(user.getEid(), user.getId());
        long expireTime = System.currentTimeMillis() + EXPIRETIME;
        Token token = new Token(user.getId(), tokenString, expireTime);
        tokenMapper.insert(token);
        return new LoginResponse(user, tokenString);
    }

    public boolean checkToken(Long id, String tokenString) {
        Token token = tokenMapper.get(id);
        if (token == null) {
            return false;
        }
        if (token.getToken().equals(tokenString) && System.currentTimeMillis() < token.getExpireTime()) {
            return true;
        }
        return false;
    }

    public void addPermit(AddPermitRequest addPermitRequest) {
        if (Constant.PERMISSIONS.get(addPermitRequest.getFunction()) == null) {
            throw new ArgumentException("功能不存在");
        }
        userMapper
                .addPermit(addPermitRequest.getId(), Constant.PERMISSIONS.get(addPermitRequest.getFunction()));
    }

    public void removePermit(RemovePermitRequest removePermitRequest) {
        if (Constant.PERMISSIONS.get(removePermitRequest.getFunction()) == null) {
            throw new ArgumentException("功能不存在");
        }
        userMapper.removePermit(removePermitRequest.getId(),
                Constant.PERMISSIONS.get(removePermitRequest.getFunction()));
    }
}
