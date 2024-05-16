package com.yond.log.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import com.yond.extension.ExtensionLoader;
import com.yond.log.LogContextLoader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.List;
import java.util.Map;

/**
 * LogbackContextListener
 **/
public class LogbackContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    private boolean isStarted = false;

    private final List<LogContextLoader> logContextLoaderList = ExtensionLoader.getExtensionList(LogContextLoader.class);

    public LogbackContextListener() {
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext context) {
    }

    @Override
    public void onReset(LoggerContext context) {
    }

    @Override
    public void onStop(LoggerContext context) {
    }

    @Override
    public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
    }

    @Override
    public void start() {
        if (isStarted) {
            return;
        }

        synchronized (LogbackContextListener.class) {
            Context context = getContext();
//            context.putProperty("env", Environment.getEnv());
//            context.putProperty("appkey", Environment.getAppKey());
            if (CollectionUtils.isNotEmpty(logContextLoaderList)) {
                for (LogContextLoader loader : logContextLoaderList) {
                    Map<String, String> map = loader.getContext();
                    if (MapUtils.isNotEmpty(map)) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            context.putProperty(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
            isStarted = true;
        }
    }

    @Override
    public void stop() {
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }
}
