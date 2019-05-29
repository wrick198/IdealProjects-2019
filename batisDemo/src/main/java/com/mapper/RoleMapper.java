package com.mapper;
import com.demo.Role;

/*
映射器接口
 */
public interface RoleMapper {

    //根据id获取角色
    public Role getRoleByID(Long id);
    public int insertRole(Role role);
    public int updateRole(Role role);
    public int deleteRole(Long id);

}
