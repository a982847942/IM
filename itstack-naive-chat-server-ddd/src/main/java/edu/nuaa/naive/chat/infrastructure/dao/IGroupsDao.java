package edu.nuaa.naive.chat.infrastructure.dao;

import edu.nuaa.naive.chat.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:50
 */
@Mapper
public interface IGroupsDao {
    Groups queryGroupsById(String groupsId);
}
