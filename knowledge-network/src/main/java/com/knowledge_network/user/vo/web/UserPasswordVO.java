package com.knowledge_network.user.vo.web;

/**
 * Created by pentonbin on 17-12-15
 */
public class UserPasswordVO {

    private String oldPassword;
    private String newPassword;

    public UserPasswordVO() {
    }

    public UserPasswordVO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
