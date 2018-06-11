package com.jm.server.service.impl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jm.server.mapper.TestMapper;
import com.jm.server.model.Test;
import com.jm.server.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 123
 * @since 2018-06-07
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Autowired
    private TestMapper testMapper;

    public Test selectOne(String id) {
        return testMapper.selectKey(id);
    }
}
