package edu.nuaa.naive.chat.client.application;

import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import edu.nuaa.itstack.chat.ui.view.login.ILoginMethod;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/11 16:02
 */
public class UIService {
    private ILoginMethod login;
    private IChatMethod chat;

    public ILoginMethod getLogin() {
        return login;
    }

    public void setLogin(ILoginMethod login) {
        this.login = login;
    }

    public IChatMethod getChat() {
        return chat;
    }

    public void setChat(IChatMethod chat) {
        this.chat = chat;
    }
}
