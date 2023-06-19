package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.ChatRecordInfo;
import edu.nuaa.naive.chat.domain.user.model.UserInfo;
import edu.nuaa.naive.chat.infrastructure.common.Constants;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.msg.MsgGroupRequest;
import edu.nuaa.naive.chat.protocol.msg.MsgGroupResponse;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/14 19:53
 */
public class MsgGroupHandler extends MyBizHandler<MsgGroupRequest> {
    public MsgGroupHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgGroupRequest msg) {
        logger.info("接收群组消息:{}", JSON.toJSONString(msg));
        //获取群组通信Channel
        ChannelGroup channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        if (null == channelGroup) {
            //如果群组为空 创建新的群组channel 并初始化
            SocketChannelUtil.addChannelGroup(msg.getTalkId(), channel);
            channelGroup = SocketChannelUtil.getChannelGroup(msg.getTalkId());
        }else {
            channelGroup.add(channel);
        }
        // 异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getTalkId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), Constants.TalkType.Group.getCode()));
        // 群发消息
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse = new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);//整个群组发送该消息
    }
}
