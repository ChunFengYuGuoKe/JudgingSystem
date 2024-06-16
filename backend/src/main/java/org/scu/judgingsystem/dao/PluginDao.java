package org.scu.judgingsystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.scu.judgingsystem.pojo.Plugin;

@Mapper
public interface PluginDao extends BaseMapper<Plugin> {
}
