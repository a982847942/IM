package edu.nuaa.naive.chat.applicaiton;

import edu.nuaa.naive.chat.domain.inet.model.ChannelUserInfo;
import edu.nuaa.naive.chat.domain.inet.model.ChannelUserReq;
import edu.nuaa.naive.chat.domain.inet.model.InetServerInfo;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:39
 */
public interface InetService {
    InetServerInfo queryNettyServerInfo();

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);
}
