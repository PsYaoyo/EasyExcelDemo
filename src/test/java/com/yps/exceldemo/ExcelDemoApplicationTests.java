package com.yps.exceldemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yps.exceldemo.demo.DemoDao;
import com.yps.exceldemo.demo.DemoDataListener;
import com.yps.exceldemo.demo.DemoService;
import com.yps.exceldemo.demo.TestFileUtil;
import com.yps.exceldemo.entry.DemoData;
import com.yps.exceldemo.entry.SQLData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@Slf4j
class ExcelDemoApplicationTests {


    @Autowired
    private DemoService demoService;
    @Test
    void contextLoads() {
    }

    @Test
    public void simpleRead() {
        String fileName = "C:\\Users\\10656\\Desktop\\" + "测试文档导入导出.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener(demoService)).sheet().doRead();
    }
    @Test
    public void bits() {
        DemoData demoData = new DemoData();
        demoData.setId(0);
    }

    @Test
    public void simpleWrite() {
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入

        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        System.out.println(fileName);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class)
                .sheet()
                .doWrite(() -> {
                    // 分页查询数据
                    List<SQLData> sqlDataList = demoService.list(new LambdaQueryWrapper<SQLData>()
                            .isNotNull(SQLData::getCreateTime)
                            .ge(SQLData::getFamilySize, 4)
                    );
                    List<DemoData> demoDataList = sqlDataList.stream().map((data) -> {
                        DemoData demoData = new DemoData();
                        BeanUtils.copyProperties(data, demoData);
                        return demoData;
                    }).collect(Collectors.toList());
                    return demoDataList;
                });

//        // 写法2
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
//
//        // 写法3
//        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build()) {
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        }
    }
}
