package com.knowledge_network.circle.service.impl;

import com.knowledge_network.circle.dao.LabelDAO;
import com.knowledge_network.circle.entity.Label;
import com.knowledge_network.circle.service.LabelService;
import com.knowledge_network.support.utils.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wshish000 on 18-3-4
 */
@Service
public class LabelServiceImpl implements LabelService{

    @Autowired
    private LabelDAO labelDAO;

    @Override
    public void delete(Label label) {
        labelDAO.deleteLabel(label);
    }

    @Override
    public Label findById(int id) {
        return labelDAO.findLabelById(id);
    }

    @Override
    public void save(Label label) {
        labelDAO.updateLabel(label);
    }

    @Override
    public Label findByName(String name) {
        return labelDAO.findByName(name);
    }

    @Override
    public List<Label> findByNameLike(String text) {
        return labelDAO.findByNameLike(text);
    }

    @Override
    public List<Label> findAll(int p, int size) {
        Order order = Order.desc("topicCount");
        return labelDAO.findLabelByOrderPage(p, size, order);
    }

    @Override
    public String dealLabels(String labels) {
        if(!StringUtils.isNullOrEmpty(labels) && !labels.equals(",")){
            Date now = new Date();
            String labelId = "";
            String[] labelStr = labels.split(",");
            List<String> newLabelStr = new ArrayList<>();
            for(String s: labelStr){
                if(!newLabelStr.contains(s)){
                    newLabelStr.add(s);
                }
            }
            for(String s : newLabelStr){
                Label label = this.findByName(s);
                if(label == null){
                    label = new Label();
                    label.setInTime(now);
                    label.setName(s);
                    label.setTopicCount(1);
                }
                else{
                    label.setTopicCount(label.getTopicCount() + 1);
                }
                save(label);
                labelId += label.getId() + ",";
            }
            return labelId;
        }
        return null;
    }

    @Override
    public void dealEditTopicOldLabels(String labels) {
        if(!StringUtils.isNullOrEmpty(labels) && !labels.equals(",")){
            String[] labelStr = labels.split(",");
            for(String s: labelStr){
                Label label = this.findById(Integer.parseInt(s));
                if(label != null){
                    label.setTopicCount(label.getTopicCount() - 1);
                    save(label);
                }
            }
        }
    }
}
