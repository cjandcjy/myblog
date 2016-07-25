package com.blog.service;



import com.blog.model.User;
import com.blog.model.dto.UserDto;
import com.blog.util.Pager;
import java.util.List;

public interface UserService {
    //登录
    public UserDto login(User user);
    //保存用户 或者 添加用户
    public void saveUser(User user);
    //更新用户
    public void updateUser(User user);
    //删除用户
    public void deleteUser(Integer id);
    //获取用户列表
    public List<User> getUsers();
    //获取用户
    public User getUser(Integer id);
    //分页获取用户
    public List<User> getPageUsers(Pager pager);
    //是否存在用户
    public boolean userIsNotEmpty(String name);
}
