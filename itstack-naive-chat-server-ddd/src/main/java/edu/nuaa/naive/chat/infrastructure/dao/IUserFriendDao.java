package edu.nuaa.naive.chat.infrastructure.dao;

import edu.nuaa.naive.chat.infrastructure.po.UserFriend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:51
 */
@Mapper
public interface IUserFriendDao {
    List<String> queryUserFriendIdList(String userId);
    void addUserFriendList(List<UserFriend> userFriendList);

    UserFriend queryUserFriendById(UserFriend req);
}
