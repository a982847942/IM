package edu.nuaa.naive.chat.client.event;

import edu.nuaa.itstack.chat.ui.view.login.ILoginEvent;
import edu.nuaa.naive.chat.client.infrastructure.util.BeanUtil;
import edu.nuaa.naive.chat.client.infrastructure.util.CacheUtil;
import edu.nuaa.naive.chat.protocol.login.LoginRequest;
import io.netty.channel.Channel;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:03
 */
public class LoginEvent implements ILoginEvent {
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId,userPassword));
        CacheUtil.userId = userId;
    }
}
