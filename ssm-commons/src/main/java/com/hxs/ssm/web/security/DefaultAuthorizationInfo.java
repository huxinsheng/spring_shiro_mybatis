package com.hxs.ssm.web.security;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huxinsheng on 2016/3/22.
 */
public class DefaultAuthorizationInfo implements AuthorizationInfo {
    protected Set<String> roles;
    protected Set<String> stringPermissions;
    protected Set<Permission> objectPermissions;

    public DefaultAuthorizationInfo() {
    }

    public DefaultAuthorizationInfo(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        if(this.roles == null) {
            this.roles = new HashSet();
        }

        this.roles.add(role);
    }

    public void addRoles(Collection<String> roles) {
        if(this.roles == null) {
            this.roles = new HashSet();
        }

        this.roles.addAll(roles);
    }

    public Set<String> getStringPermissions() {
        return this.stringPermissions;
    }

    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

    public void addStringPermission(String permission) {
        if(this.stringPermissions == null) {
            this.stringPermissions = new HashSet();
        }

        this.stringPermissions.add(permission);
    }

    public void addStringPermissions(Collection<String> permissions) {
        if(this.stringPermissions == null) {
            this.stringPermissions = new HashSet();
        }

        this.stringPermissions.addAll(permissions);
    }

    public Set<Permission> getObjectPermissions() {
        return this.objectPermissions;
    }

    public void setObjectPermissions(Set<Permission> objectPermissions) {
        this.objectPermissions = objectPermissions;
    }

    public void addObjectPermission(Permission permission) {
        if(this.objectPermissions == null) {
            this.objectPermissions = new HashSet();
        }

        this.objectPermissions.add(permission);
    }

    public void addObjectPermissions(Collection<Permission> permissions) {
        if(this.objectPermissions == null) {
            this.objectPermissions = new HashSet();
        }

        this.objectPermissions.addAll(permissions);
    }
}
