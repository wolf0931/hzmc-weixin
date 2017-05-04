package com.hzmc.weixin.admin.dao.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    /**
     * 账号
     *
     * @mbg.generated
     */
    private String username;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 用户类型：0-普通用户、1-超级管理员
     *
     * @mbg.generated
     */
    private int type;

    /**
     * 真实姓名
     *
     * @mbg.generated
     */
    private String realname;

    private String salt;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Long ctime;


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", type=").append(type);
        sb.append(", realname=").append(realname);
        sb.append(", salt=").append(salt);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}