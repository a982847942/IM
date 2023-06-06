package edu.nuaa.itstack.chat.ui.view.login;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/2 22:08
 */
public class LoginView {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
    }
}
