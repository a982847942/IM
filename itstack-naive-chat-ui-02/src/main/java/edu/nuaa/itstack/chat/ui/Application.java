package edu.nuaa.itstack.chat.ui;

import edu.nuaa.itstack.chat.ui.view.Login;
import javafx.stage.Stage;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/2 20:49
 * 窗口 Stage  场景  Scene 布局 Pane  控件
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Login login = new Login();
        login.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
