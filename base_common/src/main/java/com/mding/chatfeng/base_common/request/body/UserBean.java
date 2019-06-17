package com.mding.chatfeng.base_common.request.body;

/**
 * 演示传递自定义类型
 * @author billy.qi
 * @since 18/5/28 19:43
 */
public class UserBean {
    private String token;
    private String userId;

    public UserBean(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
