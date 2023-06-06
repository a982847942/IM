package edu.nuaa.itstack.chat.ui;

import edu.nuaa.itstack.chat.ui.view.chat.ChatController;
import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import javafx.stage.Stage;

import java.util.Date;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/3 11:14
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        IChatMethod chat = new ChatController();
        chat.doShow();

        // 模拟测试
        chat.addTalkBox(-1, 0, "1000001", "brain", "01_50", "无聊ing", new Date(), true);
        chat.addTalkBox(-1, 0, "1000002", "戏命师", "02_50", "死是必然，但杀戮并不一定丑陋。", new Date(), false);
        chat.addTalkBox(-1, 0, "1000003", "恶魔小丑", "03_50", "来次魔术戏法，怎么样？", new Date(), false);
        chat.addTalkBox(-1, 0, "1000004", "放逐之刃", "04_50", "前车之鉴，后车之师。", new Date(), false);
        chat.addTalkBox(0, 1, "5307397", "刀锋之影", "group_1", "刀下生，刀下死!", new Date(), false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
