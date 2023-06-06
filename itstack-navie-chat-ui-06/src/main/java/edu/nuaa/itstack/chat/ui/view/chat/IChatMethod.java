package edu.nuaa.itstack.chat.ui.view.chat;

import java.util.Date;

/**
 * @author brain
 * @version 1.0
 * @date 2023/6/3 10:03
 */
public interface IChatMethod {
    /**
     * 展示窗口
     */
    void doShow();
    /**
     * 填充对话框列表
     *
     * @param talkIdx    对话框位置；首位0、默认-1
     * @param talkType   对话框类型；好友0、群组1
     * @param talkId     对话框ID，1v1聊天ID、1vn聊天ID
     * @param talkName   对话框名称
     * @param talkHead   对话框头像
     * @param talkSketch 对话框通信简述(聊天内容最后一组信息)
     * @param talkDate   对话框通信时间
     * @param selected   选中[true/false]
     */
    void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected);
}
