package edu.nuaa.itstack.chat.ui.view.chat;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/3 10:02
 */
public class ChatController extends ChatInit implements IChatMethod{
    private ChatEventDefine chatEventDefine;

    @Override
    public void initView() {

    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this);
    }

    @Override
    public void doShow() {
        super.show();
    }
}
