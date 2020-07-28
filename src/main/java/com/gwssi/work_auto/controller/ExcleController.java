package com.gwssi.work_auto.controller;

import com.alibaba.excel.EasyExcel;

import com.gwssi.work_auto.pojo.Work;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.*;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/7/23 12:47
 */
@RestController
public class ExcleController {

    @GetMapping("/get")
    public void getWorkExcle(HttpServletResponse response,
                             @RequestParam("name") String name,
                             @RequestParam("year") int year,
                             @RequestParam("month") int month,
                             @RequestParam("type") String type,
                             @RequestParam("content") String content) throws IOException {



        OutputStream out = null;
        out = response.getOutputStream();
        //设置ConetentType CharacterEncoding Header,需要在excelWriter.write()之前设置
        response.setContentType("mutipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition","attachment;filename=test.xlsx");



        ArrayList<Work> workArrayList = new ArrayList<>();
        List<String> workDateList = DateController.getWolkdayInMonth(year, month);

        for (int i = 0; i < workDateList.size(); i++) {
            Work work = new Work();
            work.setDate(workDateList.get(i));
            work.setHour(8);
            work.setType(type);
            work.setContent(content);
            workArrayList.add(work);
        }
        String base = "/home/";
        String templateFileName = base + "work_time.xls";
        String fileName = base + name + month + "月工时.xls";
        String downName = name + month + "月工时.xls";

        EasyExcel.write(fileName).withTemplate(templateFileName).sheet(0).doFill(workArrayList);



        // 读到流中
        InputStream inStream = new FileInputStream(fileName);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\""  + new String(downName.getBytes("utf-8"), "ISO-8859-1"));
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
