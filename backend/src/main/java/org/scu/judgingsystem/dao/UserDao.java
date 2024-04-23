package org.scu.judgingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.scu.judgingsystem.pojo.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
