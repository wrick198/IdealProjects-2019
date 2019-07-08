package com.knowledge_network.knowledge_network.vo;

import com.knowledge_network.knowledge_network.entity.Course;
import com.knowledge_network.knowledge_network.entity.KnowledgePoint;
import com.knowledge_network.knowledge_network.entity.Resource;
import com.knowledge_network.knowledge_network.entity.UserMarkResourceScoreRelationship;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.vo.UserInfoVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wshish000 on 17-12-16
 * 资源信息页面数据对象
 * <p>
 * Modified by pentonbin on 18-04-18
 */
public class ResourceInfoVO {

    private Integer id;
    private String name;
    private Integer grade;
    private Integer download;
    private String description;
    private Integer scoreTimes;
    private Double score;
    private Integer rank;
    private UserInfoVO uploader;
    private SubjectInfoVO subject;
    private String url;
    private Timestamp uploadDatetime;
    private ResourceTypeInfoVO resourceType;
    private List<Integer> courses;
    private List<Integer> knowledgePoints;
    private List<Integer> downloadUsers;
    private List<Integer> markedScoreUsers;

    public ResourceInfoVO() {
    }

    public ResourceInfoVO(Resource resource) {
        if (resource != null) {
            id = resource.getId();
            name = resource.getName();
            grade = resource.getGrade();
            download = resource.getDownload();
            description = resource.getDescription();
            scoreTimes = resource.getScoreTimes();
            score = resource.getScore();
            rank = resource.getRank();
            uploader = new UserInfoVO(resource.getUploader());
            subject = new SubjectInfoVO(resource.getSubject());
            url = resource.getUrl();
            uploadDatetime = resource.getUploadDatetime();
            resourceType = new ResourceTypeInfoVO(resource.getResourceType());
            courses = new ArrayList<>();
            if (!resource.getCourses().isEmpty()) {
                for (Course c : resource.getCourses()) {
                    courses.add(c.getId());
                }
            }
            knowledgePoints = new ArrayList<>();
            if (!resource.getKnowledgePoints().isEmpty()) {
                for (KnowledgePoint kp : resource.getKnowledgePoints()) {
                    knowledgePoints.add(kp.getId());
                }
            }
            downloadUsers = new ArrayList<>();
            if (!resource.getDownloadUser().isEmpty()) {
                for (User user : resource.getDownloadUser()) {
                    downloadUsers.add(user.getId());
                }
            }
            markedScoreUsers = new ArrayList<>();
            if (!resource.getMarkedScoreUser().isEmpty()) {
                for (UserMarkResourceScoreRelationship relationship : resource.getMarkedScoreUser()) {
                    markedScoreUsers.add(relationship.getUser().getId());
                }
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScoreTimes() {
        return scoreTimes;
    }

    public void setScoreTimes(Integer scoreTimes) {
        this.scoreTimes = scoreTimes;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public UserInfoVO getUploader() {
        return uploader;
    }

    public void setUploader(UserInfoVO uploader) {
        this.uploader = uploader;
    }

    public SubjectInfoVO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInfoVO subject) {
        this.subject = subject;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getUploadDatetime() {
        return uploadDatetime;
    }

    public void setUploadDatetime(Timestamp uploadDatetime) {
        this.uploadDatetime = uploadDatetime;
    }

    public ResourceTypeInfoVO getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceTypeInfoVO resourceType) {
        this.resourceType = resourceType;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public List<Integer> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(List<Integer> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    public List<Integer> getDownloadUsers() {
        return downloadUsers;
    }

    public void setDownloadUsers(List<Integer> downloadUsers) {
        this.downloadUsers = downloadUsers;
    }

    public List<Integer> getMarkedScoreUsers() {
        return markedScoreUsers;
    }

    public void setMarkedScoreUsers(List<Integer> markedScoreUsers) {
        this.markedScoreUsers = markedScoreUsers;
    }
}
