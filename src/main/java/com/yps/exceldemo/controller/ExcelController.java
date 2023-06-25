package com.yps.exceldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yps.exceldemo.demo.DemoService;
import com.yps.exceldemo.demo.UploadDataListener;
import com.yps.exceldemo.entry.DemoData;
import com.yps.exceldemo.entry.SQLData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private DemoService demoService;

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<SQLData> sqlDataList = demoService.list(new LambdaQueryWrapper<SQLData>()
                .isNotNull(SQLData::getCreateTime));
        List<DemoData> demoDataList = sqlDataList.stream().map((data) -> {
            DemoData demoData = new DemoData();
            BeanUtils.copyProperties(data, demoData);
            return demoData;
        }).collect(Collectors.toList());
        EasyExcel.write(response.getOutputStream(), DemoData.class)
                .sheet("模板")
                .doWrite(demoDataList);
    }

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoData.class, new UploadDataListener(demoService)).sheet().doRead();
        return "success";
    }

}
