package com.yond.blog.util.comment.channel;

import com.yond.blog.config.properties.BlogProperties;
import com.yond.blog.config.properties.TelegramProperties;
import com.yond.blog.util.comment.CommentUtils;
import com.yond.blog.util.telegram.TelegramUtils;
import com.yond.blog.web.blog.view.dto.Comment;
import com.yond.common.enums.CommentPageEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

/**
 * Telegram提醒方式
 *
 * @author: Naccl
 * @date: 2022-01-22
 */
@Lazy
@Component
public class TelegramChannel implements CommentNotifyChannel {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TelegramChannel.class);
    private final TelegramUtils telegramUtils;

    private final BlogProperties blogProperties;

    private final TelegramProperties telegramProperties;

    private final SimpleDateFormat simpleDateFormat;

    public TelegramChannel(TelegramUtils telegramUtils, BlogProperties blogProperties, TelegramProperties telegramProperties) {
        this.telegramUtils = telegramUtils;
        this.blogProperties = blogProperties;
        this.telegramProperties = telegramProperties;

        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        log.info("TelegramChannel instantiating");
        telegramUtils.setWebhook();
    }

    /**
     * 发送Telegram消息提醒我自己
     *
     * @param comment 当前收到的评论
     */
    @Override
    public void notifyMyself(Comment comment) {
        String url = telegramProperties.getApi() + telegramProperties.getToken() + TelegramUtils.SEND_MESSAGE;
        String content = getContent(comment);
        Map<String, Object> messageBody = telegramUtils.getMessageBody(content);
        telegramUtils.sendByAutoCheckReverseProxy(url, messageBody);
    }

    private String getContent(Comment comment) {
        CommentPageEnum commentPageEnum = CommentUtils.getCommentPageEnum(comment);
        return String.format(
                "<b>您的文章<a href=\"%s\">《%s》</a>有了新的评论~</b>\n" +
                        "\n" +
                        "<b>%s</b> 给您的评论：\n" +
                        "\n" +
                        "<pre>%s</pre>\n" +
                        "\n" +
                        "<b>其他信息：</b>\n" +
                        "评论ID：<code>%d</code>\n" +
                        "IP：%s\n" +
                        "%s" +
                        "时间：<u>%s</u>\n" +
                        "邮箱：<code>%s</code>\n" +
                        "%s" +
                        "状态：%s [<a href=\"%s\">管理评论</a>]\n",
                blogProperties.getView() + commentPageEnum.getPath(),
                commentPageEnum.getTitle(),
                comment.getNickname(),
                comment.getContent(),
                comment.getId(),
                comment.getIp(),
                StringUtils.isBlank(comment.getQq()) ? "" : "QQ：" + comment.getQq() + "\n",
                simpleDateFormat.format(comment.getCreateTime()),
                comment.getEmail(),
                StringUtils.isBlank(comment.getWebsite()) ? "" : "网站：" + comment.getWebsite() + "\n",
                comment.getPublished() ? "公开" : "待审核",
                blogProperties.getCms() + "/blog/comment/list"
        );
    }
}
