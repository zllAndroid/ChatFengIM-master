package com.mding.chatfeng.base_common.request.body;

/**
 * 项目：ChatFengIM-master
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/6/19 0019
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class PersonUserBody {

    String userId;
    String token;

    public PersonUserBody(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
