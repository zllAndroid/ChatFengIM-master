package com.mding.chatfeng.base_common.request.body;

/**
 * 项目：ChatFengIM
 * 文件描述：短信验证码登录
 * 作者：ljj
 * 创建时间：2019/6/13
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class smsLoginBody {
    String mobile;
    String code;

    public smsLoginBody(String mobile, String code) {
        this.mobile = mobile;
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
