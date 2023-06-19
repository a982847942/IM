package edu.nuaa.naive.chat.infrastructure.dao;

import edu.nuaa.naive.chat.domain.inet.model.ChannelUserReq;
import edu.nuaa.naive.chat.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:51
 */
@Mapper
public interface IUserDao {
    String queryUserPassword(String userId);

    User queryUserById(String userId);

    List<User> queryFuzzyUserList(@Param("userId") String userId, @Param("searchKey") String searchKey);

    Long queryChannelUserCount(ChannelUserReq req);
    List<User> queryChannelUserList(ChannelUserReq req);
}
