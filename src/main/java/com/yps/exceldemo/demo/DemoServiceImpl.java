package com.yps.exceldemo.demo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yps.exceldemo.entry.DemoData;
import com.yps.exceldemo.entry.SQLData;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class DemoServiceImpl extends ServiceImpl<DemoDao, SQLData> implements DemoService {

}
