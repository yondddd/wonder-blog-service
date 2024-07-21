package com.yond.blog.web.blog.view.controller;

import com.yond.blog.config.properties.TelegramProperties;
import com.yond.blog.util.telegram.TelegramBotMsgHandler;
import com.yond.blog.web.blog.view.dto.TgMessage;
import com.yond.common.constant.CommentConstant;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理TelegramBot接收到的新消息
 * 如果不使用Telegram方式，即comment.notify.channel != tg，则该类不会被实例化，对应的Webhook接口也不会被创建
 *
 * @author: Naccl
 * @date: 2022-01-24
 */
@ConditionalOnProperty(name = "comment.notify.channel", havingValue = CommentConstant.TELEGRAM)
@RestController
public class TelegramBotController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TelegramBotController.class);
    @Autowired
    private TelegramBotMsgHandler msgHandler;
    @Autowired
    private TelegramProperties telegramProperties;

    /**
     * webhook方式监听bot收到的新消息
     *
     * @param message 新消息
     */
    @PostMapping("/view/tg/${tg.bot.token}")
    public void getUpdate(@RequestBody TgMessage message) {
        log.info("Telegram bot receive message: {}", message);
        //判断消息是否是自己发出的
        if (message != null && message.getMessage() != null && message.getMessage().getChat() != null
                && telegramProperties.getChatId().equals(message.getMessage().getChat().getId())) {
            //判断是不是正常的文本消息
            if (message.getMessage().getText() != null) {
                //处理消息
                msgHandler.processCommand(message.getMessage().getText());
            }
        }
    }
}
