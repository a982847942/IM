package edu.nuaa.naive.chat.client.socket.handler;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.protocol.friend.SearchFriendResponse;
import edu.nuaa.naive.chat.protocol.friend.dto.UserDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:47
 */
public class SearchFriendHandler extends SimpleChannelInboundHandler<SearchFriendResponse> {
    private UIService uiService;
    public SearchFriendHandler(UIService uiService){
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SearchFriendResponse searchFriendResponse) throws Exception {
        List<UserDto> list = searchFriendResponse.getList();
        if (list == null || list.isEmpty())return;
        IChatMethod chat = uiService.getChat();
        Platform.runLater(()->{
            for (UserDto user : list) {
                chat.addLuckFriend(user.getUserId(), user.getUserNickName(), user.getUserHead(), user.getStatus());
            }
        });
    }
}
