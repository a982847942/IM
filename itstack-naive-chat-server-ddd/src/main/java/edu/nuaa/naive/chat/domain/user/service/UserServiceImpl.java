package edu.nuaa.naive.chat.domain.user.service;

import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.*;
import edu.nuaa.naive.chat.domain.user.repository.IUserRepository;
import edu.nuaa.naive.chat.infrastructure.po.UserFriend;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 9:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private IUserRepository userRepository;
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    //默认线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);


    @Override
    public boolean checkAuth(String userId, String userPassword) {
        // 简单比对验证
        String authCode = userRepository.queryUserPassword(userId);
        return userPassword.equals(authCode);
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        return userRepository.queryUserInfo(userId);
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        return userRepository.queryTalkBoxInfoList(userId);
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {
        userRepository.addTalkBoxInfo(userId, talkId, talkType);
    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        return userRepository.queryUserFriendInfoList(userId);
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        return userRepository.queryUserGroupInfoList(userId);
    }

    @Override
    public List<LuckUserInfo> queryFuzzyUserInfoList(String userId, String searchKey) {
        return userRepository.queryFuzzyUserInfoList(userId,searchKey);
    }

    @Override
    public void addUserFriendList(List<UserFriend> userFriendList) {
        userRepository.addUserFriendList(userFriendList);
    }

    @Override
    public void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userRepository.appendChatRecord(chatRecordInfo);
            }
        });
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        return userRepository.queryChatRecordInfoList(talkId,userId,talkType);
    }

    @Override
    public void deleteUserTalk(String userId, String talkId) {
        userRepository.deleteUserTalk(userId,talkId);
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
