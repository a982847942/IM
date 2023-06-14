package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.*;
import edu.nuaa.naive.chat.infrastructure.common.Constants;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.login.LoginRequest;
import edu.nuaa.naive.chat.protocol.login.LoginResponse;
import edu.nuaa.naive.chat.protocol.login.dto.ChatRecordDto;
import edu.nuaa.naive.chat.protocol.login.dto.ChatTalkDto;
import edu.nuaa.naive.chat.protocol.login.dto.GroupsDto;
import edu.nuaa.naive.chat.protocol.login.dto.UserFriendDto;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 9:14
 */
public class LoginHandler extends MyBizHandler<LoginRequest> {
    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        logger.info("登录请求处理: {}", JSON.toJSONString(msg));
        //鉴权
        boolean auth = userService.checkAuth(msg.getUserId(),msg.getUserPassword());
        //1.登录失败
        if (!auth){
            //发送失败消息
            channel.writeAndFlush(new LoginResponse(false));
            return;
        }
        //2.登录成功 服务端记录相关信息 并返回登陆成功结果给客户端
        //2.1保存ID和channel映射
        SocketChannelUtil.addChannel(msg.getUserId(),channel);
        //2.2用户信息
        UserInfo userInfo = userService.queryUserInfo(msg.getUserId());
        LoginResponse loginResponse = new LoginResponse();
        //2.3对话框  查询出的对话框有用户自己发送的类型和别人发送给该用户的类型
        List<TalkBoxInfo> talkBoxInfoList = userService.queryTalkBoxInfoList(msg.getUserId());
        for (TalkBoxInfo talkBoxInfo : talkBoxInfoList) {
            ChatTalkDto chatTalkDto = new ChatTalkDto();
            chatTalkDto.setTalkDate(talkBoxInfo.getTalkDate());
            chatTalkDto.setTalkHead(talkBoxInfo.getTalkHead());
            chatTalkDto.setTalkId(talkBoxInfo.getTalkId());
            chatTalkDto.setTalkName(talkBoxInfo.getTalkName());
            chatTalkDto.setTalkType(talkBoxInfo.getTalkType());
            chatTalkDto.setTalkSketch(talkBoxInfo.getTalkSketch());
            loginResponse.getChatTalkList().add(chatTalkDto);

            if (Constants.TalkType.Friend.getCode().equals(talkBoxInfo.getTalkType())){
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                //好友
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    //自己发的消息
                    if (msgType){
                        chatRecordDto.setUserId(chatRecordInfo.getUserId());
                        chatRecordDto.setMsgUserType(0);
                    }else {
                        chatRecordDto.setUserId(chatRecordInfo.getFriendId());
                        chatRecordDto.setMsgUserType(1); // 消息类型[0自己/1好友]
                    }
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalkDto.setChatRecordList(chatRecordDtoList);
            }
            // 群组；聊天消息
            else if (Constants.TalkType.Group.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Group.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    UserInfo memberInfo = userService.queryUserInfo(chatRecordInfo.getUserId());
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    chatRecordDto.setUserId(memberInfo.getUserId());
                    chatRecordDto.setUserNickName(memberInfo.getUserNickName());
                    chatRecordDto.setUserHead(memberInfo.getUserHead());
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    chatRecordDto.setMsgUserType(msgType ? 0 : 1); // 消息类型[0自己/1好友]
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalkDto.setChatRecordList(chatRecordDtoList);
            }
        }
        //2.4群组
        List<GroupsInfo> groupsInfoList = userService.queryUserGroupInfoList(msg.getUserId());
        for (GroupsInfo groupsInfo : groupsInfoList) {
            GroupsDto groups = new GroupsDto();
            groups.setGroupId(groupsInfo.getGroupId());
            groups.setGroupName(groupsInfo.getGroupName());
            groups.setGroupHead(groupsInfo.getGroupHead());
            loginResponse.getGroupsList().add(groups);
        }
        //2.5好友
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(msg.getUserId());
        for (UserFriendInfo userFriendInfo : userFriendInfoList) {
            UserFriendDto userFriend = new UserFriendDto();
            userFriend.setFriendId(userFriendInfo.getFriendId());
            userFriend.setFriendName(userFriendInfo.getFriendName());
            userFriend.setFriendHead(userFriendInfo.getFriendHead());
            loginResponse.getUserFriendList().add(userFriend);
        }
        //组装回复消息
        loginResponse.setSuccess(true);
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickName());
        loginResponse.setUserHead(userInfo.getUserHead());
        channel.writeAndFlush(loginResponse);
    }
}
