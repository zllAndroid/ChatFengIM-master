package com.mding.chatfeng.base_common.request.body;

/**
 * 项目：ChatFengIM
 * 文件描述：注册设置头像昵称接口
 * 作者：ljj
 * 创建时间：2019/6/13
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class setHeadImgBody {

    String userId;
    String nickName;
    String headImg;

    public setHeadImgBody(String userId, String nickName, String headImg) {
        this.userId = userId;
        this.nickName = nickName;
        this.headImg = headImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

}
