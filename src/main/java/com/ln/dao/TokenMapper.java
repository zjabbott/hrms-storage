package com.ln.dao;

import com.ln.model.Token;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper {
    Token get(@Param("id") Long id);

    void insert(Token token);

    void delete(@Param("id") Long id);
}
