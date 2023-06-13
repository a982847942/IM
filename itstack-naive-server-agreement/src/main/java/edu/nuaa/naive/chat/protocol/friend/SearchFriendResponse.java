package edu.nuaa.naive.chat.protocol.friend;



import edu.nuaa.naive.chat.protocol.Command;
import edu.nuaa.naive.chat.protocol.Packet;
import edu.nuaa.naive.chat.protocol.friend.dto.UserDto;

import java.util.List;

/**

 * 搜索好友应答
 */
public class SearchFriendResponse extends Packet {

    private List<UserDto> list;

    public List<UserDto> getList() {
        return list;
    }

    public void setList(List<UserDto> list) {
        this.list = list;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
