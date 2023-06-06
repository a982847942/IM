package edu.nuaa.itstack.chat.ui.view.login;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/2 22:06
 */
public interface ILoginMethod {
    /**
     * 打开登陆窗口
     */
    void doShow();

    /**
     * 登陆失败
     */
    void doLoginError();

    /**
     * 登陆成功；跳转聊天窗口[关闭登陆窗口，打开新窗口]
     */
    void doLoginSuccess();
}
