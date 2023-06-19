package edu.nuaa.naive.chat.infrastructure.repository;

import edu.nuaa.naive.chat.domain.inet.model.ChannelUserInfo;
import edu.nuaa.naive.chat.domain.inet.model.ChannelUserReq;
import edu.nuaa.naive.chat.domain.inet.repository.IInetRepository;
import edu.nuaa.naive.chat.infrastructure.dao.IUserDao;
import edu.nuaa.naive.chat.infrastructure.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository("inetRepository")
public class InetRepository implements IInetRepository {

    @Autowired
    private IUserDao userDao;

    @Override
    public Long queryChannelUserCount(ChannelUserReq req) {
        return userDao.queryChannelUserCount(req);
    }

    @Override
    public List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req) {
        List<ChannelUserInfo> channelUserInfoList = new ArrayList<>();
        List<User> userList = userDao.queryChannelUserList(req);
        for (User user:userList){
            ChannelUserInfo channelUserInfo = new ChannelUserInfo();
            channelUserInfo.setUserId(user.getUserId());
            channelUserInfo.setUserNickName(user.getUserNickName());
            channelUserInfo.setUserHead(user.getUserHead());
            channelUserInfoList.add(channelUserInfo);
        }
        return channelUserInfoList;
    }
}
