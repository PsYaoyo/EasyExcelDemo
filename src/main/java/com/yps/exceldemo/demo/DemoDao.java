package com.yps.exceldemo.demo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yps.exceldemo.entry.DemoData;
import com.yps.exceldemo.entry.SQLData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDao extends BaseMapper<SQLData> {
        public void saveBatch(List<DemoData> entry);
}
