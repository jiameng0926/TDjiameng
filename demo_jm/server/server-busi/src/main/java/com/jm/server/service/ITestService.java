package com.jm.server.service;

import com.baomidou.mybatisplus.service.IService;
import com.jm.server.model.Test;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2018-06-07
 */
public interface ITestService extends IService<Test> {
    Test selectOne(String id);
}
