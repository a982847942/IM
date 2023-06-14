package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.UserInfo;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.talk.TalkNoticeRequest;
import edu.nuaa.naive.chat.protocol.talk.TalkNoticeResponse;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.Date;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/14 9:10
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeRequest> {
    public TalkNoticeHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, TalkNoticeRequest msg) {
        logger.info("对话通知应答处理:{}", JSON.toJSONString(msg));
        Integer talkType = msg.getTalkType();
        switch (talkType){
            case 0:
                //好友对话  双方互相添加对话
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 0);
                userService.addTalkBoxInfo(msg.getFriendUserId(),msg.getUserId(),  0);
                UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
                //发送对话框消息给好友
                TalkNoticeResponse response = new TalkNoticeResponse();
                response.setTalkId(userInfo.getUserId());
                response.setTalkName(userInfo.getUserNickName());
                response.setTalkHead(userInfo.getUserHead());
                response.setTalkSketch(null);
                response.setTalkDate(new Date());
                //获取好友通信管道
                Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendUserId());
                if (null == friendChannel){
                    logger.info("用户id：{}未登录！", msg.getFriendUserId());
                    return;
                }
                friendChannel.writeAndFlush(response);
                break;
            case 1:
                userService.addTalkBoxInfo(msg.getUserId(), msg.getFriendUserId(), 1);
                break;
            default:break;
        }
    }
}
