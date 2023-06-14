package edu.nuaa.naive.chat.infrastructure.po;

import java.util.Date;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:52
 */
public class Groups {
    private Long id;          // 自增ID
    private String groupId;   // 群组ID
    private String groupName; // 群组名称
    private String groupHead; // 群组头像
    private Date createTime;  // 创建时间
    private Date updateTime;  // 更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
