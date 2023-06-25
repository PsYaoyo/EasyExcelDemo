//package com.yps.exceldemo.demo;
//
//import com.alibaba.excel.EasyExcel;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//
//@Controller
//public class WebUpDown {
//    @GetMapping("download")
//    public void download(HttpServletResponse response) throws IOException {
//        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("utf-8");
//        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
//        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//        EasyExcel.write(response.getOutputStream(), DownloadData.class).sheet("模板").doWrite(data());
//    }
//
//    /**
//     * 文件上传
//     * <p>1. 创建excel对应的实体对象 参照{@link UploadData}
//     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
//     * <p>3. 直接读即可
//     */
//    @PostMapping("upload")
//    @ResponseBody
//    public String upload(MultipartFile file) throws IOException {
//        EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener(uploadDAO)).sheet().doRead();
//        return "success";
//    }
//}
