package com.example.scexcel.model.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class PersonData {
    @ExcelProperty(value = "唯一id", index = 0)
    @ColumnWidth(value = 10)
    private String id;

    @ExcelProperty(value = "姓名", index = 1)
    @ColumnWidth(value = 10)
    private String name;

    @ExcelProperty(value = "年龄", index = 2)
    @ColumnWidth(value = 10)
    private int age;

    @ExcelProperty(value = "性别", index = 3)
    @ColumnWidth(value = 10)
    private int sex;
}
