package edu.nuaa.naive.chat.socket.handler;

import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.UserInfo;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.infrastructure.po.UserFriend;
import edu.nuaa.naive.chat.protocol.friend.AddFriendRequest;
import edu.nuaa.naive.chat.protocol.friend.AddFriendResponse;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 17:29
 */
public class AddFriendHandler extends MyBizHandler<AddFriendRequest> {
    public AddFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendRequest msg) {
        //双方互相添加好友
        List<UserFriend> userFriendList = new ArrayList<>();
        userFriendList.add(new UserFriend(msg.getUserId(),msg.getFriendId()));
        userFriendList.add(new UserFriend(msg.getFriendId(),msg.getUserId()));
        userService.addUserFriendList(userFriendList);

        // 2. 推送好友添加完成 A添加B
        UserInfo userInfo = userService.queryUserInfo(msg.getFriendId());
        channel.writeAndFlush(new AddFriendResponse(userInfo.getUserId(), userInfo.getUserNickName(), userInfo.getUserHead()));
        // 3. 推送好友添加完成 B也添加了A
        Channel friendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (null == friendChannel) return;
        UserInfo friendInfo = userService.queryUserInfo(msg.getUserId());
        friendChannel.writeAndFlush(new AddFriendResponse(friendInfo.getUserId(), friendInfo.getUserNickName(), friendInfo.getUserHead()));
    }
}
