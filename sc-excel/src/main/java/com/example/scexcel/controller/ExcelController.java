package com.example.scexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.example.scexcel.dao.PersonJpa;
import com.example.scexcel.entity.PersonDo;
import com.example.scexcel.model.data.PersonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api(tags = "Excel相关操作")
@RequestMapping(value = "/excel")
public class ExcelController {
    @Resource
    private PersonJpa personJpa;

    @ApiOperation(value = "Excel文件上传与解析 - EasyExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", example = "admin", dataType = "String", required = true)
    })
    @GetMapping("/excel-upload")
    public void demo(String userId){

    }

    @ApiOperation(value = "Excel文件导出 - EasyExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", example = "admin", dataType = "String", required = true)
    })
    @GetMapping("/excel-export")
    public void exportExcel(String id, HttpServletResponse response) throws Exception {
        List<PersonDo> personDoList = personJpa.findAll();

        List<PersonData> personDataList = personDoList.parallelStream().map(personDo -> {
            PersonData personData = new PersonData();
            BeanUtils.copyProperties(personDo, personData);
            return personData;
        }).collect(Collectors.toList());

        String fileName = "乱码处理dmeo-^&_!@\\///(1).xlsx";

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //文件名乱码处理
        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("utf-8"), "iso-8859-1" ));

        EasyExcel.write(response.getOutputStream(), PersonData.class).sheet("sheet1").doWrite(personDataList);

    }
}
