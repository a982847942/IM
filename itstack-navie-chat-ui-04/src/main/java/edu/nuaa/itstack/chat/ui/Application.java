package edu.nuaa.itstack.chat.ui;

import edu.nuaa.itstack.chat.ui.view.login.ILoginMethod;
import edu.nuaa.itstack.chat.ui.view.login.LoginController;
import javafx.stage.Stage;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/2 22:04
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ILoginMethod login = new LoginController((userId, userPassword) -> {
            System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
        });

        login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
