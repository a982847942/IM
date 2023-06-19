package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.ChatRecordInfo;
import edu.nuaa.naive.chat.infrastructure.common.Constants;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.msg.MsgRequest;
import edu.nuaa.naive.chat.protocol.msg.MsgResponse;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/14 15:39
 */
public class MsgHandler extends MyBizHandler<MsgRequest> {
    public MsgHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, MsgRequest msg) {
        logger.info("好友聊天信息处理;{}", JSON.toJSONString(msg));
        //保存到数据库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate()));
        //添加对话框(在数据库中给对方添加对话框)
        userService.addTalkBoxInfo(msg.getFriendId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
        //获取接收方通信管道
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (null == friendChannel){
            logger.info("用户id：{}未登录！", msg.getFriendId());
            return;
        }
        //发送给接受者
        friendChannel.writeAndFlush(new MsgResponse(msg.getUserId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate()));
    }
}
