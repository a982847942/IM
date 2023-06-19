package edu.nuaa.naive.chat.socket.handler;

import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.login.ReconnectRequest;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import edu.nuaa.naive.chat.socket.MyChannelInitializer;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/16 9:10
 */
public class ReconnectHandler extends MyBizHandler<ReconnectRequest> {

    public ReconnectHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, ReconnectRequest msg) {
        logger.info("客户端断线重连处理。userId：{}", msg.getUserId());
        // 添加用户Channel
//        SocketChannelUtil.removeUserChannelByUserId(msg.getUserId());
        SocketChannelUtil.addChannel(msg.getUserId(), channel);
        // 添加群组Channel
        List<String> groupsIdList = userService.queryTalkBoxGroupsIdList(msg.getUserId());
        for (String groupsId : groupsIdList) {
            SocketChannelUtil.addChannelGroup(groupsId, channel);
        }
    }
}
