package edu.nuaa.naive.chat.protocol.login;


import edu.nuaa.naive.chat.protocol.Command;
import edu.nuaa.naive.chat.protocol.Packet;

public class ReconnectRequest extends Packet {

    private String userId;

    public ReconnectRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.ReconnectRequest;
    }
}
