package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.PluginDao;
import org.scu.judgingsystem.pojo.Plugin;
import org.scu.judgingsystem.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PluginServiceImpl implements PluginService {
    @Autowired
    private PluginDao pluginDao;

    @Override
    public List<Plugin> getAll() {
        QueryWrapper<Plugin> qw = new QueryWrapper<>();
        return pluginDao.selectList(qw);
    }
}
