package com.knowledge_network.knowledge_network.entity;

import com.knowledge_network.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 17-12-1
 */
@Entity
@Table(name = "course_clicks")
public class CourseClicks implements Serializable {

    /**
     * 用户点击课程列表主键
     */
    private CourseClicksPK primaryKey;
    /**
     * 用户点击课程的次数
     */
    private long count;
    /**
     * 用户
     */
    private User user;
    /**
     * 课程
     */
    private Course course;

    public CourseClicks() {
    }

    @EmbeddedId
    public CourseClicksPK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CourseClicksPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",
            insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "course_id",
            insertable = false, updatable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseClicks that = (CourseClicks) o;

        if (!getPrimaryKey().equals(that.getPrimaryKey())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = primaryKey.hashCode();
        return result;
    }

    /**
     * 主键Primary Key类
     */
    @Embeddable
    public static class CourseClicksPK implements Serializable {

        private Integer userId;
        private Integer courseId;

        @Column(name = "user_id", nullable = false)
        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        @Column(name = "course_id", nullable = false)
        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CourseClicksPK that = (CourseClicksPK) o;

            if (!userId.equals(that.userId)) return false;
            if (!courseId.equals(that.courseId)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + courseId;
            return result;
        }
    }
}
