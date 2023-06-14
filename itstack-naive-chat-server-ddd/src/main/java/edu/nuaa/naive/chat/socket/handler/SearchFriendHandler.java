package edu.nuaa.naive.chat.socket.handler;

import com.alibaba.fastjson.JSON;
import edu.nuaa.naive.chat.applicaiton.UserService;
import edu.nuaa.naive.chat.domain.user.model.LuckUserInfo;
import edu.nuaa.naive.chat.protocol.friend.SearchFriendRequest;
import edu.nuaa.naive.chat.protocol.friend.SearchFriendResponse;
import edu.nuaa.naive.chat.protocol.friend.dto.UserDto;
import edu.nuaa.naive.chat.socket.MyBizHandler;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/13 17:21
 */
public class SearchFriendHandler extends MyBizHandler<SearchFriendRequest> {

    public SearchFriendHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, SearchFriendRequest msg) {
        logger.info("搜索好友请求处理:{}", JSON.toJSONString(msg));
        List<UserDto> userDtoList = new ArrayList<>();
        List<LuckUserInfo> userInfoList = userService.queryFuzzyUserInfoList(msg.getUserId(), msg.getSearchKey());
        logger.info("好友列表:{}", JSON.toJSONString(userInfoList));
        for (LuckUserInfo userInfo : userInfoList) {
            UserDto userDto = new UserDto();
            userDto.setStatus(userInfo.getStatus());
            userDto.setUserHead(userInfo.getUserHead());
            userDto.setUserId(userInfo.getUserId());
            userDto.setUserNickName(userInfo.getUserNickName());
            userDtoList.add(userDto);
        }
        SearchFriendResponse searchFriendResponse = new SearchFriendResponse();
        searchFriendResponse.setList(userDtoList);
        channel.writeAndFlush(searchFriendResponse);
    }
}
