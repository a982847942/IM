package edu.nuaa.itstack.chat.ui;

import edu.nuaa.itstack.chat.ui.view.chat.ChatController;
import edu.nuaa.itstack.chat.ui.view.chat.IChatEvent;
import edu.nuaa.itstack.chat.ui.view.chat.IChatMethod;
import javafx.stage.Stage;


import java.util.Date;

/**
 * 窗口          Stage
 * -场景       Scene
 * -布局     stackPane
 * -控件   Button
 */
public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        IChatMethod chat = new ChatController(new IChatEvent() {
        });

        chat.doShow();
        //设置登录用户信息
        chat.setUserInfo("1000001", "拎包冲", "02_50");

        // 好友 - 对话框
        chat.addTalkBox(-1, 0, "1000004", "哈尼克兔", "04_50", null, null, false);
        chat.addTalkMsgUserLeft("1000004", "沉淀、分享、成长，让自己和他人都有所收获！", new Date(), true, false, true);
        chat.addTalkMsgRight("1000004", "今年过年是放假时间最长的了！", new Date(), true, true, false);

        chat.addTalkBox(-1, 0, "1000002", "铁锤", "03_50", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), false);
        chat.addTalkMsgUserLeft("1000002", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), true, false, true);
        chat.addTalkMsgRight("1000002", "我Q，传说中的老头杀？", new Date(), true, true, false);

        // 群组 - 对话框
        chat.addTalkBox(0, 1, "5307397", "虫洞 · 技术栈(1区)", "group_1", "", new Date(), true);
        chat.addTalkMsgRight("5307397", "你炸了我的山", new Date(), true, true, false);
        chat.addTalkMsgGroupLeft("5307397", "1000002", "拎包冲", "01_50", "推我过忘川", new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000003", "铁锤", "03_50", "奈河桥边的姑娘", new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "哈尼克兔", "04_50", "等我回头看", new Date(), true, false, true);

        /**
         * 问题
         * 这个版本中消息提醒不完善，去掉群组的代码运行程序  第一个消息框还是有消息提醒,第一个是群组消息没有提醒，是因为群组的talkType为1直接返回了，没有设置remind
         * 大致逻辑：
         * talkList这个ListView有很多个ElementTalk有一个pane pane包含了联系人列表的信息
         * ElementTalk也有一个ListView保存了 消息界面  left会++remind right不会增加
         * 所有消息共有info_pane_box这个聊天框  选中具体聊天对象时 会切换为对应的ElementTalk的ListView进行展示
         */
    }

    public static void main(String[] args) {
        launch(args);
    }

}
