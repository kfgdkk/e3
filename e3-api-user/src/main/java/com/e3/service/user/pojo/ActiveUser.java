package com.e3.service.user.pojo;

import java.io.Serializable;
import java.util.List;

public class ActiveUser implements Serializable {
    private String userId;
    private String usercord;
    private String username;
    private List<SysPermission> menus;
    private List<SysPermission> permissions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsercord() {
        return usercord;
    }

    public void setUsercord(String usercord) {
        this.usercord = usercord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SysPermission> getMenus() {
        return menus;
    }

    public void setMenus(List<SysPermission> menus) {
        this.menus = menus;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
}
