package com.blog.model;

/**
 * Created by cj on 16/6/10.
 * 对应   t_category表
 * 分类
 * id   分类id
 * name 分类名称
 */
public class Category {
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
