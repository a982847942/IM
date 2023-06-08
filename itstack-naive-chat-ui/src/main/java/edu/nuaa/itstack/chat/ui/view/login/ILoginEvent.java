package edu.nuaa.itstack.chat.ui.view.login;


public interface ILoginEvent {

    /**
     * 登陆验证
     * @param userId        用户ID
     * @param userPassword  用户密码
     */
    void doLoginCheck(String userId, String userPassword);

}
