package edu.nuaa.naive.chat.infrastructure.dao;

import edu.nuaa.naive.chat.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:50
 */
@Mapper
public interface ITalkBoxDao {
    List<TalkBox> queryTalkBoxList(String userId);
    void addTalkBox(TalkBox talkBox);

    void deleteUserTalk(@Param("userId") String userId, @Param("talkId") String talkId);

    TalkBox queryTalkBox(@Param("userId") String userId, @Param("talkId") String talkId);
//    List<String> queryTalkBoxGroupsIdList(String userId);
}
