package edu.nuaa.naive.chat.domain.user.model;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 9:35
 */
public class UserFriendInfo {
    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHead() {
        return friendHead;
    }

    public void setFriendHead(String friendHead) {
        this.friendHead = friendHead;
    }
}
