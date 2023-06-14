package edu.nuaa.naive.chat.socket.handler;

import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.protocol.talk.DelTalkRequest;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/14 9:11
 */
public class DelTalkHandler extends MyBizHandler<DelTalkRequest> {
    public DelTalkHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, DelTalkRequest msg) {
        userService.deleteUserTalk(msg.getUserId(), msg.getTalkId());
    }
}
