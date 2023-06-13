package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.GroupsInfo;
import edu.nuaa.naive.chat.domain.user.model.TalkBoxInfo;
import edu.nuaa.naive.chat.domain.user.model.UserFriendInfo;
import edu.nuaa.naive.chat.domain.user.model.UserInfo;
import edu.nuaa.naive.chat.infrastructure.common.SocketChannelUtil;
import edu.nuaa.naive.chat.protocol.login.LoginRequest;
import edu.nuaa.naive.chat.protocol.login.LoginResponse;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

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
        //2.3对话框
        List<TalkBoxInfo> talkBoxInfoList = userService.queryTalkBoxInfoList(msg.getUserId());
        //2.4群组
        List<GroupsInfo> groupsInfoList = userService.queryUserGroupInfoList(msg.getUserId());
        //2.5好友
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(msg.getUserId());
        //组装回复消息
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickName());
        loginResponse.setUserHead(userInfo.getUserHead());
        channel.writeAndFlush(loginResponse);
    }
}
