package edu.nuaa.naive.chat.applicaiton;

import edu.nuaa.naive.chat.domain.user.model.*;
import edu.nuaa.naive.chat.infrastructure.po.UserFriend;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:40
 */
public interface UserService {

    /**
     *登录鉴权
     * @param userId  用户名
     * @param password  密码
     * @return
     */
    boolean checkAuth(String userId,String password);

    /**
     * 查询相关用户信息
     * @param userId 用户Id
     * @return
     */
    UserInfo  queryUserInfo(String userId);

    /**
     * 查询个人用户对话框列表
     * @param userId 个人用户ID
     * @return
     */
    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    /**
     * 添加对话框
     * @param userId  用户ID
     * @param talkId  好友ID
     * @param talkType 对话框类型 (0 单聊  1 群聊)
     */
    void addTalkBoxInfo(String userId,String talkId,Integer talkType);

    /**
     * 查询个人用户好友列表
     * @param userId 个人用户Id
     * @return
     */
    List<UserFriendInfo> queryUserFriendInfoList(String userId);

    /**
     * 查询个人用户群组列表
     * @param userId 个人用户ID
     * @return
     */
    List<GroupsInfo> queryUserGroupInfoList(String userId);

    /**
     * 模糊查询用户
     * @param userId  用户ID
     * @param searchKey  用户名 、用户ID  用来进行模糊查询
     * @return
     */
    List<LuckUserInfo>  queryFuzzyUserInfoList(String userId,String searchKey);

    /**
     * 添加好友到数据库
     * @param userFriendList 好友集合
     */
    void addUserFriendList(List<UserFriend> userFriendList);
    /**
     * 异步添加聊天记录
     *
     * @param chatRecordInfo 聊天记录信息
     */
    void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo);

    /**
     * 查询聊天记录
     *
     * @param talkId   对话框ID
     * @param userId   好友ID
     * @param talkType 对话类型；0好友、1群组
     * @return 聊天记录(默认10条)
     */
    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);

    /**
     * 删除用户对话框
     *
     * @param userId 用户ID
     * @param talkId 对话框ID
     */
    void deleteUserTalk(String userId, String talkId);

    /**
     * 查询用户群组ID集合
     *
     * @param userId 用户ID
     * @return 用户群组ID集合
     */
    List<String> queryUserGroupsIdList(String userId);

    /**
     * 查询用户群组对话框
     *
     * @param userId 用户Id
     * @return       群组Id
     */
    List<String> queryTalkBoxGroupsIdList(String userId);
}
