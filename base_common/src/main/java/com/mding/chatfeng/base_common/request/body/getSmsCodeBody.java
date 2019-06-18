package com.mding.chatfeng.base_common.request.body;

/**
 * 项目：ChatFengIM
 * 文件描述：获取短信
 * 作者：ljj
 * 创建时间：2019/6/13
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class getSmsCodeBody {

    String mobile;
    String type;
    /** 1为登录
     *  2为注册
     *  3为修改登录密码
     *  4修改绑定手机号（旧）
     *  5修改绑定手机号（新） */

    public getSmsCodeBody(String mobile, String type) {
        this.mobile = mobile;
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
