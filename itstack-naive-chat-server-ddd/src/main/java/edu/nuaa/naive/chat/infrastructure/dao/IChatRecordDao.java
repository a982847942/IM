package edu.nuaa.naive.chat.infrastructure.dao;

import edu.nuaa.naive.chat.infrastructure.po.ChatRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:50
 */
@Mapper
public interface IChatRecordDao {
    void appendChatRecord(ChatRecord req);

    List<ChatRecord> queryUserChatRecordList(@Param("talkId") String talkId, @Param("userId") String userId);

    List<ChatRecord> queryGroupsChatRecordList(@Param("talkId") String talkId, @Param("userId") String userId);
}
