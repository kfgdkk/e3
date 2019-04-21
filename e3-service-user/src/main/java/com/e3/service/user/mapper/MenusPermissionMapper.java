package com.e3.service.user.mapper;

import com.e3.service.user.pojo.SysPermission;

import java.util.List;

public interface MenusPermissionMapper {

    List<SysPermission> queryMenusList(String userid);

    List<SysPermission> queryPermissionList(String userid);

}