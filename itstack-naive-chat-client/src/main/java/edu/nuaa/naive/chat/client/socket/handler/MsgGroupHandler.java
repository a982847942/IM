package edu.nuaa.naive.chat.client.socket.handler;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.client.socket.MyBizHandler;
import edu.nuaa.naive.chat.protocol.msg.MsgGroupResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:46
 */
public class MsgGroupHandler extends SimpleChannelInboundHandler<MsgGroupResponse> {
    private UIService uiService;

    public MsgGroupHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgGroupResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkMsgGroupLeft(msg.getTalkId(), msg.getUserId(), msg.getUserNickName(), msg.getUserHead(), msg.getMsg(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }
}
