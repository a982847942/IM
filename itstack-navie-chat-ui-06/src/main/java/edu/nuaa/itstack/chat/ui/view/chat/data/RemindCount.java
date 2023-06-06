package edu.nuaa.itstack.chat.ui.view.chat.data;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/3 11:05
 */
public class RemindCount {
    private int count = 0;  // 消息提醒条数

    public RemindCount() {
    }

    public RemindCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
