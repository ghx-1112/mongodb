package com.jk.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Document(collection="t_bill")
public class BillBean {
    //id、账单名称、消费金额、账单内容、消费时间
    private String id;
    private String name;
    private double price;
    private String info;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date bdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date edate;
}
