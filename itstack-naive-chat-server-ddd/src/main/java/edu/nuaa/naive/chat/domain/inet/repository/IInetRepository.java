package edu.nuaa.naive.chat.domain.inet.repository;


import edu.nuaa.naive.chat.domain.inet.model.ChannelUserInfo;
import edu.nuaa.naive.chat.domain.inet.model.ChannelUserReq;

import java.util.List;


public interface IInetRepository {

    Long queryChannelUserCount(ChannelUserReq req);

    List<ChannelUserInfo> queryChannelUserList(ChannelUserReq req);

}
