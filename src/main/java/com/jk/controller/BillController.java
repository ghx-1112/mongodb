package com.jk.controller;

import com.jk.pojo.BillBean;
import com.jk.pojo.TreeBean;
import com.jk.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@RequestMapping("bill")
@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("test")
    public void test(){
        System.out.printf("12345");
    }

    @RequestMapping("findTree")
    @ResponseBody
    public List<TreeBean> findTree(){
        return billService.findTree();
    }
    @RequestMapping("findBill")
    @ResponseBody
    public HashMap<String,Object> findBill(Integer page, Integer rows, BillBean billBean){
        return billService.findBill(page,rows,billBean);
    }

    /**
    2 * @Author: ghx
    3 * @Description: 新增
    4 * @Date: 2021/1/1 21:05
    5  * @param billBean
    6 * @Return: void
    7 **/
    @RequestMapping("addBill")
    @ResponseBody
    public void addBill(BillBean billBean){
        billService.addBill(billBean);
    }

    /**
    2 * @Author: ghx
    3 * @Description: 删除/批删
    4 * @Date: 2021/1/1 21:36
    5  * @param ids
    6 * @Return: void
    7 **/
    @RequestMapping("del")
    @ResponseBody
    public void del(String[] ids){
        billService.del(ids);
    }
    /**
    2 * @Author: ghx
    3 * @Description: 回显
    4 * @Date: 2021/1/1 21:36
    5  * @param id
    6 * @Return: com.jk.pojo.BillBean
    7 **/
    @RequestMapping("findBillById")
    @ResponseBody
    public BillBean findBillById(String id){
        return billService.findBillById(id);
    }
}
