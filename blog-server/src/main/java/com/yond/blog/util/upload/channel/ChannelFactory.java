package com.yond.blog.util.upload.channel;

import com.yond.blog.util.spring.SpringContextUtils;
import com.yond.common.constant.UploadConstant;

/**
 * 文件上传方式
 *
 * @Author: Yond
 */
public class ChannelFactory {
    /**
     * 创建文件上传方式
     *
     * @param channelName 方式名称
     * @return 文件上传Channel
     */
    public static FileUploadChannel getChannel(String channelName) {
        switch (channelName.toLowerCase()) {
            case UploadConstant.LOCAL:
                return SpringContextUtils.getBean(LocalChannel.class);
            case UploadConstant.GITHUB:
                return SpringContextUtils.getBean(GithubChannel.class);
            case UploadConstant.UPYUN:
                return SpringContextUtils.getBean(UpyunChannel.class);
        }
        throw new RuntimeException("Unsupported value in [application.properties]: [upload.channel]");
    }
}
