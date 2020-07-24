package com.gwssi.work_auto.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/7/23 12:54
 */
@Data
@Repository
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    /**
     * 日期
     */
    private String date;
    /**
     * 工作量
     */

    private int hour;
    /**
     * 工作类型
     */

    private String type;
    /**
     * 内容
     */

    private String content;
}
