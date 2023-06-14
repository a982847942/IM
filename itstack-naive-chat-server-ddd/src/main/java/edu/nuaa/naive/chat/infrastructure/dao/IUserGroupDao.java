package edu.nuaa.naive.chat.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:51
 */
@Mapper
public interface IUserGroupDao {
    List<String> queryUserGroupsIdList(String userId);
}
