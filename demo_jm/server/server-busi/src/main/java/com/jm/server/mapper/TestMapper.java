package com.jm.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jm.server.model.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper extends BaseMapper<Test> {
    @Select("select * from test where id = #{id}")
    Test selectKey(String id);
}
