package com.yps.exceldemo.entry;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("person")
public class SQLData {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("婚姻状况")
    private String maritalStatus;

    @ExcelProperty("健康状况")
    private String healthStatus;

    @ExcelProperty("家庭人口")
    private int familySize;

    @ExcelProperty("家庭住址")
    private String address;

    @ExcelProperty("电话")
    private String phoneNumber;

    @ExcelProperty("微信")
    private String weChat;

    @ExcelProperty("创建时间")
    private String createTime;

    @ExcelProperty("更新时间")
    private String updateTime;
}
