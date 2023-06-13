package edu.nuaa.naive.chat.client.socket.handler;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.client.socket.MyBizHandler;
import edu.nuaa.naive.chat.protocol.friend.AddFriendResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javafx.application.Platform;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:46
 */
public class AddFriendHandler extends MyBizHandler<AddFriendResponse> {
    public AddFriendHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, AddFriendResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addFriendUser(true, msg.getFriendId(), msg.getFriendNickName(), msg.getFriendHead());
        });
    }
}
