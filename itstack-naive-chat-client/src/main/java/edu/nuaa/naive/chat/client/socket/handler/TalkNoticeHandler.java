package edu.nuaa.naive.chat.client.socket.handler;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.client.socket.MyBizHandler;
import edu.nuaa.naive.chat.protocol.talk.TalkNoticeResponse;
import io.netty.channel.Channel;
import javafx.application.Platform;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:47
 */
public class TalkNoticeHandler extends MyBizHandler<TalkNoticeResponse> {
    public TalkNoticeHandler(UIService uiService) {
        super(uiService);
    }

    @Override
    public void channelRead(Channel channel, TalkNoticeResponse msg) {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(()->{
            chat.addTalkBox(-1, 0, msg.getTalkId(), msg.getTalkName(), msg.getTalkHead(), msg.getTalkSketch(), msg.getTalkDate(), false);
        });

    }
}
