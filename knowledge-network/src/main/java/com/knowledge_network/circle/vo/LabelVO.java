package com.knowledge_network.circle.vo;

import com.knowledge_network.circle.entity.Label;

import java.util.Date;

/**
 * Created by wshish000 on 18-3-8
 */
public class LabelVO {

    private int id;

    private String name;

    private String intro;

    private int topicCount;

    private Date inTime;

    private Date modifyTime;

    private String image;

    public LabelVO(){}

    public LabelVO(Label label){
        if(label != null){
            id = label.getId();
            name = label.getName();
            intro = label.getIntro();
            topicCount = label.getTopicCount();
            inTime = label.getInTime();
            modifyTime = label.getModifyTime();
            image = label.getImage();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
