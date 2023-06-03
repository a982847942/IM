package edu.nuaa.itstack.chat.ui.view.login;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/2 22:06
 */
public interface ILoginEvent {
    /**
     * 登陆验证
     * @param userId        用户ID
     * @param userPassword  用户密码
     *实际业务开发过程还会需要传递IP地址，设备信息，请求时间等信息来协助判断是否正常登陆。
     */
    void doLoginCheck(String userId, String userPassword);
}
