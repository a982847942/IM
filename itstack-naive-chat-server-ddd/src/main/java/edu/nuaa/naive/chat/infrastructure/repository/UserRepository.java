package edu.nuaa.naive.chat.infrastructure.repository;

import edu.nuaa.naive.chat.domain.user.model.*;
import edu.nuaa.naive.chat.domain.user.repository.IUserRepository;
import edu.nuaa.naive.chat.infrastructure.dao.IUserDao;
import edu.nuaa.naive.chat.infrastructure.dao.IUserFriendDao;
import edu.nuaa.naive.chat.infrastructure.po.User;
import edu.nuaa.naive.chat.infrastructure.po.UserFriend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 10:03
 */
@Repository("userRepository")
public class UserRepository implements IUserRepository {
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserFriendDao userFriendDao;
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
        List<LuckUserInfo> luckUserInfoList = new ArrayList<>();
        List<User> userList = userDao.queryFuzzyUserList(userId, searchKey);
//        logger.info("推荐用户信息:{}",userList);
        for (User user : userList) {
            LuckUserInfo userInfo = new LuckUserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead(), 0);
            UserFriend req = new UserFriend();
            req.setUserId(userId);
            req.setUserFriendId(user.getUserId());
            UserFriend userFriend = userFriendDao.queryUserFriendById(req);
            if (null != userFriend) {
                //用户好友表中已有相关friend 设置状态2
                userInfo.setStatus(2);
            }
            luckUserInfoList.add(userInfo);
        }
        return luckUserInfoList;
    }

    @Override
    public void addUserFriendList(List<UserFriend> userFriendList) {
        try {
            userFriendDao.addUserFriendList(userFriendList);
        }catch (DuplicateKeyException ignored) {
        }
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
