package com.jk.service.impl;

import com.jk.pojo.BillBean;
import com.jk.pojo.TreeBean;
import com.jk.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class BillServiceimpl implements BillService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<TreeBean> findTree() {
        String pid = "0";
        List<TreeBean> list = fieldnode(pid);
        return list;
    }

    private List<TreeBean> fieldnode(String pid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("pid").in(pid));
        List<TreeBean> list = mongoTemplate.find(query , TreeBean.class);
        for (TreeBean tree : list) {
            String id = tree.getId();
            List<TreeBean> nodelist = fieldnode(id);
            tree.setChildren(nodelist);
        }
        return list;
    }

    @Override
    public HashMap<String, Object> findBill(Integer page, Integer rows, BillBean bill) {
        int s=(page-1)*rows;
        Query query = new Query();
        if(!StringUtils.isEmpty(bill.getName())){
            query.addCriteria(Criteria.where("name").regex(bill.getName()));
        }
        Criteria where = Criteria.where("bdate");
        if(bill.getSdate()!=null){
            where.gte(bill.getSdate());
        }
        if(bill.getEdate()!=null){
            where.lte(bill.getEdate());
        }
        if(bill.getSdate()!=null ||bill.getEdate()!=null){
            query.addCriteria(where);
        }

        long total = mongoTemplate.count(query,BillBean.class);
        Sort orders =Sort.by(new Sort.Order(Sort.Direction.DESC,"price"));
        query.with(orders);
        query.skip(s).limit(rows);
        List<BillBean> list = mongoTemplate.find(query, BillBean.class);
        HashMap<String, Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void addBill(BillBean bill) {
        mongoTemplate.save(bill);
    }

    @Override
    public void del(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(ids[i]));
            mongoTemplate.remove(query,BillBean.class);
        }

    }

    @Override
    public BillBean findBillById(String id) {
        return mongoTemplate.findById(id,BillBean.class);
    }

    private List<TreeBean> getTreeBeans(Query query) {
        List<TreeBean> list = mongoTemplate.find(query, TreeBean.class);
        for (TreeBean tree:list) {
            String id = tree.getId();
            Query query2 = new Query();
            query2.addCriteria(Criteria.where("pid").is(id));
            List<TreeBean> list2 = getTreeBeans(query2);
            tree.setChildren(list2);
        }
        return list;
    }

}
