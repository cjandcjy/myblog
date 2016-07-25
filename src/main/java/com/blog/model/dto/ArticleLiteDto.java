package com.blog.model.dto;


/*
 * 在首页不用显示文章的全部内容（也就是content和markDown），这样可以减少从数据库查询的数据量
 */
public class ArticleLiteDto {
    private Integer id;
    private String title;
    private String pubDate;
    private Integer clicks;
    private String remark;
    private UserDto user;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ArticleLiteDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", clicks=" + clicks +
                ", remark='" + remark + '\'' +
                ", user=" + user +
                '}';
    }
}
