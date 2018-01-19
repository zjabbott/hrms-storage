package com.ln.dao;

import com.ln.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    void insert(User user);

    void update(User user);

    List<User> getList(@Param("id") Long id, @Param("limit") int limit);

    User getByEid(@Param("eid") String eid);

    User get(@Param("id") Long id);

    void addPermit(@Param("id") Long id, @Param("permit") int permit);

    void removePermit(@Param("id") Long id, @Param("permit") int permit);

}
