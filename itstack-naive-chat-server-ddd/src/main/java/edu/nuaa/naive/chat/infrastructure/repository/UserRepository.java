package edu.nuaa.naive.chat.infrastructure.repository;

import edu.nuaa.naive.chat.domain.user.model.*;
import edu.nuaa.naive.chat.domain.user.repository.IUserRepository;
import edu.nuaa.naive.chat.infrastructure.dao.IUserDao;
import edu.nuaa.naive.chat.infrastructure.po.User;
import edu.nuaa.naive.chat.infrastructure.po.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 10:03
 */
@Repository("userRepository")
public class UserRepository implements IUserRepository {
    @Autowired
    private IUserDao userDao;
    @Override
    public String queryUserPassword(String userId) {
        return userDao.queryUserPassword(userId);

    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        User user = userDao.queryUserById(userId);
        return new UserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead());
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        return null;
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {

    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        return null;
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        return null;
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        return null;
    }

    @Override
    public void addUserFriend(List<UserFriend> userFriendList) {

    }

    @Override
    public void appendChatRecord(ChatRecordInfo chatRecordInfo) {

    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        return null;
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {

    }

    @Override
    public List<String> queryUserGroupsIdList(String userId) {
        return null;
    }

    @Override
    public List<String> queryTalkBoxGroupsIdList(String userId) {
        return null;
    }
}
