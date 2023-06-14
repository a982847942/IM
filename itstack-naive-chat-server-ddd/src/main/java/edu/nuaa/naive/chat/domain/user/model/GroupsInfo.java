package edu.nuaa.naive.chat.domain.user.model;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 9:36
 */
public class GroupsInfo {
    private String groupId;     // 群组ID
    private String groupName;   // 群组名称
    private String groupHead;   // 群组头像

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }
}
