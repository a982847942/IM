package edu.nuaa.naive.chat.socket;

import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.codec.ObjDecoder;
import edu.nuaa.naive.chat.codec.ObjEncoder;
import edu.nuaa.naive.chat.socket.handler.LoginHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/12 14:43
 */
public class MyChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    private UserService userService;

    public MyChannelInitializer(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        //对象传输处理[解码]
        channel.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
//        channel.pipeline().addLast(new AddFriendHandler(userService));
//        channel.pipeline().addLast(new DelTalkHandler(userService));
        channel.pipeline().addLast(new LoginHandler(userService));
//        channel.pipeline().addLast(new MsgGroupHandler(userService));
//        channel.pipeline().addLast(new MsgHandler(userService));
//        channel.pipeline().addLast(new ReconnectHandler(userService));
//        channel.pipeline().addLast(new SearchFriendHandler(userService));
//        channel.pipeline().addLast(new TalkNoticeHandler(userService));
        //对象传输处理[编码]
        channel.pipeline().addLast(new ObjEncoder());
    }


}
