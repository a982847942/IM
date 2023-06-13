package edu.nuaa.naive.chat.client.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.naive.chat.client.application.UIService;
import edu.nuaa.naive.chat.protocol.login.LoginResponse;
import edu.nuaa.naive.chat.protocol.login.dto.ChatRecordDto;
import edu.nuaa.naive.chat.protocol.login.dto.ChatTalkDto;
import edu.nuaa.naive.chat.protocol.login.dto.GroupsDto;
import edu.nuaa.naive.chat.protocol.login.dto.UserFriendDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:46
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {
    private UIService uiService;

    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println("消息内容：" + JSON.toJSONString(msg));
        if (!msg.isSuccess()) {
            System.out.println("登陆失败");
            return;
        }
        Platform.runLater(() -> {
            uiService.getLogin().doLoginSuccess();
            IChatMethod chat = uiService.getChat();
            // 设置用户信息
            chat.setUserInfo(msg.getUserId(), msg.getUserNickName(), msg.getUserHead());
            // 对话框
            List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
            if (null != chatTalkList) {
                chatTalkList.forEach(talk -> {
                            chat.addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), true);
                            switch (talk.getTalkType()) {
                                // 好友
                                case 0:
                                    List<ChatRecordDto> userRecordList = talk.getChatRecordList();
                                    if (null == userRecordList || userRecordList.isEmpty()) return;
                                    for (int i = userRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = userRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 好友的消息
                                        if (1 == chatRecord.getMsgType()) {
                                            chat.addTalkMsgUserLeft(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                // 群组
                                case 1:
                                    List<ChatRecordDto> groupRecordList = talk.getChatRecordList();
                                    if (null == groupRecordList || groupRecordList.isEmpty()) return;
                                    for (int i = groupRecordList.size() - 1; i >= 0; i--) {
                                        ChatRecordDto chatRecord = groupRecordList.get(i);
                                        //  自己的消息
                                        if (0 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgRight(chatRecord.getTalkId(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                            continue;
                                        }
                                        // 他人的消息
                                        if (1 == chatRecord.getMsgUserType()) {
                                            chat.addTalkMsgGroupLeft(chatRecord.getTalkId(), chatRecord.getUserId(), chatRecord.getUserNickName(), chatRecord.getUserHead(), chatRecord.getMsgContent(), chatRecord.getMsgType(), chatRecord.getMsgDate(), true, false, false);
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }

                        }
                );

            }
            // 群组
            List<GroupsDto> groupsList = msg.getGroupsList();
            if (null != groupsList) {
                groupsList.forEach(groups -> chat.addFriendGroup(groups.getGroupId(), groups.getGroupName(), groups.getGroupHead()));
            }
            // 好友
            List<UserFriendDto> userFriendList = msg.getUserFriendList();
            if (null != userFriendList) {
                userFriendList.forEach(friend -> chat.addFriendUser(false, friend.getFriendId(), friend.getFriendName(), friend.getFriendHead()));
            }
        });
    }
}
