package edu.nuaa.itstack.chat.ui.view.chat;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/3 10:03
 */
public class ChatView {
    private ChatInit chatInit;
    private IChatEvent chatEvent;

    public ChatView(ChatInit chatInit, IChatEvent chatEvent) {
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;
    }
}
