package com.blog.model.dto;

/**
 * Created by SuperS on 16/2/23.
 * id 用户id
 * state 用户类型 0用户 1管理�?
 * nickname 用户昵称
 * email 用户邮箱
 * website 用户网站
 * imageName 用户图片名称
 */
public class UserDto {
    private Integer id;
    private Integer state;
    private String nickname;
    private String email;
    private String website;
    private String imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", state=" + state +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
