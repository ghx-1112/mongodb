package com.jk.service;

import com.jk.pojo.BillBean;
import com.jk.pojo.TreeBean;

import java.util.HashMap;
import java.util.List;

public interface BillService {
    List<TreeBean> findTree();

    HashMap<String, Object> findBill(Integer page, Integer rows, BillBean billBean);

    void addBill(BillBean bill);

    void del(String[] ids);

    BillBean findBillById(String id);
}
