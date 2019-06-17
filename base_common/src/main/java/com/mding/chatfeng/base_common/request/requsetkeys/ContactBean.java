package com.mding.chatfeng.base_common.request.requsetkeys;

import com.google.gson.annotations.SerializedName;

public class ContactBean {
    /**
     * controllers_name : Contact
     * searchInfo : {"method_name":"searchInfo","method_type":"2"}
     * searchDetailInfo : {"method_name":"searchDetailInfo","method_type":"2"}
     * addFriend : {"method_name":"addFriend","method_type":"2"}
     * agreeFriend : {"method_name":"agreeFriend","method_type":"2"}
     * refuseFriend : {"method_name":"refuseFriend","method_type":"2"}
     * getFriendList : {"method_name":"getFriendList","method_type":"2"}
     * getGroupManage : {"method_name":"getGroupManage","method_type":"2"}
     * getGroupWebInfo : {"method_name":"getGroupWebInfo","method_type":"2"}
     * createdUserGroup : {"method_name":"createdUserGroup","method_type":"2"}
     * addGroupOf : {"method_name":"addGroupOf","method_type":"2"}
     * messageList : {"method_name":"messageList","method_type":"2"}
     * messageDetail : {"method_name":"messageDetail","method_type":"2"}
     * messageReply : {"method_name":"messageReply","method_type":"2"}
     * groupManageInfo : {"method_name":"groupManageInfo","method_type":"2"}
     * addFriendGroup : {"method_name":"addFriendGroup","method_type":"2"}
     * groupNoticeInfo : {"method_name":"groupNoticeInfo","method_type":"2"}
     * editGroupNotice : {"method_name":"editGroupNotice","method_type":"2"}
     * moveGroupSort : {"method_name":"moveGroupSort","method_type":"2"}
     * friendGroupList : {"method_name":"friendGroupList","method_type":"2"}
     * friendGroupModify : {"method_name":"friendGroupModify","method_type":"2"}
     * groupOfGroupList : {"method_name":"groupOfGroupList","method_type":"2"}
     * groupOfGroupModify : {"method_name":"groupOfGroupModify","method_type":"2"}
     * modifyUserGroup : {"method_name":"modifyUserGroup","method_type":"2"}
     * setGroupCarteModify : {"method_name":"setGroupCarteModify","method_type":"2"}
     * getFriendInfo : {"method_name":"getFriendInfo","method_type":"2"}
     * getGroupMemberList : {"method_name":"getGroupMemberList","method_type":"2"}
     * getGroupMemberInfo : {"method_name":"getGroupMemberInfo","method_type":"2"}
     * setUserGroupDisturb : {"method_name":"setUserGroupDisturb","method_type":"2"}
     * setUserGroupAssistant : {"method_name":"setUserGroupAssistant","method_type":"2"}
     * outGroupChat : {"method_name":"outGroupChat","method_type":"2"}
     * getTransterGroupMemberInfo : {"method_name":"getTransterGroupMemberInfo","method_type":"2"}
     * transferGroupOf : {"method_name":"transferGroupOf","method_type":"2"}
     * delGroupMember : {"method_name":"delGroupMember","method_type":"2"}
     * addFriendQrCode : {"method_name":"addFriendQrCode","method_type":"2"}
     * addGroupOfQrCode : {"method_name":"addGroupOfQrCode","method_type":"2"}
     * addFriendSend : {"method_name":"addFriendSend","method_type":"2"}
     * refuseFriendSend : {"method_name":"refuseFriendSend","method_type":"2"}
     * agreeFriendSend : {"method_name":"agreeFriendSend","method_type":"2"}
     * agreeFriendListSend : {"method_name":"agreeFriendListSend","method_type":"2"}
     * deleteFriendSend : {"method_name":"deleteFriendSend","method_type":"2"}
     * modifyFriendListSend : {"method_name":"modifyFriendListSend","method_type":"2"}
     * agreeGroupSend : {"method_name":"agreeGroupSend","method_type":"2"}
     * agreeGroupListSend : {"method_name":"agreeGroupListSend","method_type":"2"}
     * dissolutionGroupListSend : {"method_name":"dissolutionGroupListSend","method_type":"2"}
     * removeGroupListSend : {"method_name":"removeGroupListSend","method_type":"2"}
     * outGroupListSend : {"method_name":"outGroupListSend","method_type":"2"}
     * joinGroupListSend : {"method_name":"joinGroupListSend","method_type":"2"}
     * invitationGroupListSend : {"method_name":"invitationGroupListSend","method_type":"2"}
     * invitationQrCodeGroupListSend : {"method_name":"invitationQrCodeGroupListSend","method_type":"2"}
     * modifyGroupListSend : {"method_name":"modifyGroupListSend","method_type":"2"}
     * modifyGroupOfListSend : {"method_name":"modifyGroupOfListSend","method_type":"2"}
     * agreeGroupingListSend : {"method_name":"agreeGroupingListSend","method_type":"2"}
     * deleteGroupingListSend : {"method_name":"deleteGroupingListSend","method_type":"2"}
     * modifyGroupingListSend : {"method_name":"modifyGroupingListSend","method_type":"2"}
     * modifyGroupingSortSend : {"method_name":"modifyGroupingSortSend","method_type":"2"}
     * addGroupOfSend : {"method_name":"addGroupOfSend","method_type":"2"}
     * createdGroupSend : {"method_name":"createdGroupSend","method_type":"2"}
     * agreeGroupMemberSend : {"method_name":"agreeGroupMemberSend","method_type":"2"}
     * invitationGroupMemberSend : {"method_name":"invitationGroupMemberSend","method_type":"2"}
     * invitationfGroupMemberSend : {"method_name":"invitationfGroupMemberSend","method_type":"2"}
     * outGroupMemberSend : {"method_name":"outGroupMemberSend","method_type":"2"}
     * deleteGroupMemberSend : {"method_name":"deleteGroupMemberSend","method_type":"2"}
     * modifyGroupMemberSend : {"method_name":"modifyGroupMemberSend","method_type":"2"}
     * deleteFriend : {"method_name":"deleteFriend","method_type":"2"}
     * bwlistFriend : {"method_name":"bwlistFriend","method_type":"2"}
     * shieldFriend : {"method_name":"shieldFriend","method_type":"2"}
     * friendRemarkName : {"method_name":"friendRemarkName","method_type":"2"}
     * groupInvitationfFriend : {"method_name":"groupInvitationfFriend","method_type":"2"}
     * groupInvitationf : {"method_name":"groupInvitationf","method_type":"2"}
     * delGroupMemberList : {"method_name":"delGroupMemberList","method_type":"2"}
     * groupSend : {"method_name":"groupSend","method_type":"2"}
     * sendGroupChat : {"method_name":"sendGroupChat","method_type":"2"}
     * shareBusinessList : {"method_name":"shareBusinessList","method_type":"2"}
     * shareBusinessOperation : {"method_name":"shareBusinessOperation","method_type":"2"}
     * shareBusinessDetails : {"method_name":"shareBusinessDetails","method_type":"2"}
     * upGroupName : {"method_name":"upGroupName","method_type":"2"}
     * upGroupHeadImg : {"method_name":"upGroupHeadImg","method_type":"2"}
     * disturbFriend : {"method_name":"disturbFriend","method_type":"2"}
     * topFriend : {"method_name":"topFriend","method_type":"2"}
     * messageReplySend : {"method_name":"messageReplySend","method_type":"2"}
     * privateSendInterface : {"method_name":"privateSendInterface","method_type":"2"}
     * groupSendInterface : {"method_name":"groupSendInterface","method_type":"2"}
     * contactsList : {"method_name":"contactsList","method_type":"2"}
     */

    @SerializedName("controllers_name")
    public String controllersName;
    @SerializedName("searchInfo")
    public SearchInfoBean searchInfo;
    @SerializedName("searchDetailInfo")
    public SearchInfoBean searchDetailInfo;
    @SerializedName("addFriend")
    public SearchInfoBean addFriend;
    @SerializedName("agreeFriend")
    public SearchInfoBean agreeFriend;
    @SerializedName("refuseFriend")
    public SearchInfoBean refuseFriend;
    @SerializedName("getFriendList")
    public SearchInfoBean getFriendList;
    @SerializedName("getGroupManage")
    public SearchInfoBean getGroupManage;
    @SerializedName("getGroupWebInfo")
    public SearchInfoBean getGroupWebInfo;
    @SerializedName("createdUserGroup")
    public SearchInfoBean createdUserGroup;
    @SerializedName("addGroupOf")
    public SearchInfoBean addGroupOf;
    @SerializedName("messageList")
    public SearchInfoBean messageList;
    @SerializedName("messageDetail")
    public SearchInfoBean messageDetail;
    @SerializedName("messageReply")
    public SearchInfoBean messageReply;
    @SerializedName("groupManageInfo")
    public SearchInfoBean groupManageInfo;
    @SerializedName("addFriendGroup")
    public SearchInfoBean addFriendGroup;
    @SerializedName("groupNoticeInfo")
    public SearchInfoBean groupNoticeInfo;
    @SerializedName("editGroupNotice")
    public SearchInfoBean editGroupNotice;
    @SerializedName("moveGroupSort")
    public SearchInfoBean moveGroupSort;
    @SerializedName("friendGroupList")
    public SearchInfoBean friendGroupList;
    @SerializedName("friendGroupModify")
    public SearchInfoBean friendGroupModify;
    @SerializedName("groupOfGroupList")
    public SearchInfoBean groupOfGroupList;
    @SerializedName("groupOfGroupModify")
    public SearchInfoBean groupOfGroupModify;
    @SerializedName("modifyUserGroup")
    public SearchInfoBean modifyUserGroup;
    @SerializedName("setGroupCarteModify")
    public SearchInfoBean setGroupCarteModify;
    @SerializedName("getFriendInfo")
    public SearchInfoBean getFriendInfo;
    @SerializedName("getGroupMemberList")
    public SearchInfoBean getGroupMemberList;
    @SerializedName("getGroupMemberInfo")
    public SearchInfoBean getGroupMemberInfo;
    @SerializedName("setUserGroupDisturb")
    public SearchInfoBean setUserGroupDisturb;
    @SerializedName("setUserGroupAssistant")
    public SearchInfoBean setUserGroupAssistant;
    @SerializedName("outGroupChat")
    public SearchInfoBean outGroupChat;
    @SerializedName("getTransterGroupMemberInfo")
    public SearchInfoBean getTransterGroupMemberInfo;
    @SerializedName("transferGroupOf")
    public SearchInfoBean transferGroupOf;
    @SerializedName("delGroupMember")
    public SearchInfoBean delGroupMember;
    @SerializedName("addFriendQrCode")
    public SearchInfoBean addFriendQrCode;
    @SerializedName("addGroupOfQrCode")
    public SearchInfoBean addGroupOfQrCode;
    @SerializedName("addFriendSend")
    public SearchInfoBean addFriendSend;
    @SerializedName("refuseFriendSend")
    public SearchInfoBean refuseFriendSend;
    @SerializedName("agreeFriendSend")
    public SearchInfoBean agreeFriendSend;
    @SerializedName("agreeFriendListSend")
    public SearchInfoBean agreeFriendListSend;
    @SerializedName("deleteFriendSend")
    public SearchInfoBean deleteFriendSend;
    @SerializedName("modifyFriendListSend")
    public SearchInfoBean modifyFriendListSend;
    @SerializedName("agreeGroupSend")
    public SearchInfoBean agreeGroupSend;
    @SerializedName("agreeGroupListSend")
    public SearchInfoBean agreeGroupListSend;
    @SerializedName("dissolutionGroupListSend")
    public SearchInfoBean dissolutionGroupListSend;
    @SerializedName("removeGroupListSend")
    public SearchInfoBean removeGroupListSend;
    @SerializedName("outGroupListSend")
    public SearchInfoBean outGroupListSend;
    @SerializedName("joinGroupListSend")
    public SearchInfoBean joinGroupListSend;
    @SerializedName("invitationGroupListSend")
    public SearchInfoBean invitationGroupListSend;
    @SerializedName("invitationQrCodeGroupListSend")
    public SearchInfoBean invitationQrCodeGroupListSend;
    @SerializedName("modifyGroupListSend")
    public SearchInfoBean modifyGroupListSend;
    @SerializedName("modifyGroupOfListSend")
    public SearchInfoBean modifyGroupOfListSend;
    @SerializedName("agreeGroupingListSend")
    public SearchInfoBean agreeGroupingListSend;
    @SerializedName("deleteGroupingListSend")
    public SearchInfoBean deleteGroupingListSend;
    @SerializedName("modifyGroupingListSend")
    public SearchInfoBean modifyGroupingListSend;
    @SerializedName("modifyGroupingSortSend")
    public SearchInfoBean modifyGroupingSortSend;
    @SerializedName("addGroupOfSend")
    public SearchInfoBean addGroupOfSend;
    @SerializedName("createdGroupSend")
    public SearchInfoBean createdGroupSend;
    @SerializedName("agreeGroupMemberSend")
    public SearchInfoBean agreeGroupMemberSend;
    @SerializedName("invitationGroupMemberSend")
    public SearchInfoBean invitationGroupMemberSend;
    @SerializedName("invitationfGroupMemberSend")
    public SearchInfoBean invitationfGroupMemberSend;
    @SerializedName("outGroupMemberSend")
    public SearchInfoBean outGroupMemberSend;
    @SerializedName("deleteGroupMemberSend")
    public SearchInfoBean deleteGroupMemberSend;
    @SerializedName("modifyGroupMemberSend")
    public SearchInfoBean modifyGroupMemberSend;
    @SerializedName("deleteFriend")
    public SearchInfoBean deleteFriend;
    @SerializedName("bwlistFriend")
    public SearchInfoBean bwlistFriend;
    @SerializedName("shieldFriend")
    public SearchInfoBean shieldFriend;
    @SerializedName("friendRemarkName")
    public SearchInfoBean friendRemarkName;
    @SerializedName("groupInvitationfFriend")
    public SearchInfoBean groupInvitationfFriend;
    @SerializedName("groupInvitationf")
    public SearchInfoBean groupInvitationf;
    @SerializedName("delGroupMemberList")
    public SearchInfoBean delGroupMemberList;
    @SerializedName("groupSend")
    public SearchInfoBean groupSend;
    @SerializedName("sendGroupChat")
    public SearchInfoBean sendGroupChat;
    @SerializedName("shareBusinessList")
    public SearchInfoBean shareBusinessList;
    @SerializedName("shareBusinessOperation")
    public SearchInfoBean shareBusinessOperation;
    @SerializedName("shareBusinessDetails")
    public SearchInfoBean shareBusinessDetails;
    @SerializedName("upGroupName")
    public SearchInfoBean upGroupName;
    @SerializedName("upGroupHeadImg")
    public SearchInfoBean upGroupHeadImg;
    @SerializedName("disturbFriend")
    public SearchInfoBean disturbFriend;
    @SerializedName("topFriend")
    public SearchInfoBean topFriend;
    @SerializedName("messageReplySend")
    public SearchInfoBean messageReplySend;
    @SerializedName("privateSendInterface")
    public SearchInfoBean privateSendInterface;
    @SerializedName("groupSendInterface")
    public SearchInfoBean groupSendInterface;
    @SerializedName("contactsList")
    public SearchInfoBean contactsList;
}
