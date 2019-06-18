package com.mding.chatfeng.base_common.request.body;

/**
 * 项目：ChatFengIM
 * 文件描述：用户注册接口
 * 作者：ljj
 * 创建时间：2019/6/13
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class registerBody {
    String mobile;
    String password;
    String code;

    public registerBody(String mobile, String password, String code) {
        this.mobile = mobile;
        this.password = password;
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
