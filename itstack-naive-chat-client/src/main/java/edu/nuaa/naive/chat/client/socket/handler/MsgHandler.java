package edu.nuaa.naive.chat.client.socket.handler;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.client.socket.MyBizHandler;
import edu.nuaa.naive.chat.protocol.msg.MsgResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:46
 */
public class MsgHandler extends MyBizHandler<MsgResponse> {
    public MsgHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, MsgResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(()->{
            chat.addTalkMsgUserLeft(msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }
}
