package com.dmz.service.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.contrib.json.JsonLayoutBase;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/9/5.
 */
public class JsonLayoutExt extends JsonLayoutBase<ILoggingEvent> {

    public static final String TIMESTAMP_ATTR_NAME = "timestamp";
    public static final String LEVEL_ATTR_NAME = "level";
    public static final String THREAD_ATTR_NAME = "thread";
    public static final String MDC_ATTR_NAME = "mdc";
    public static final String LOGGER_ATTR_NAME = "logger";
    public static final String FORMATTED_MESSAGE_ATTR_NAME = "message";
    public static final String MESSAGE_ATTR_NAME = "raw-message";
    public static final String EXCEPTION_ATTR_NAME = "exception";
    public static final String CONTEXT_ATTR_NAME = "context";
    protected boolean includeLevel = true;
    protected boolean includeThreadName = true;
    protected boolean includeMDC = true;
    protected boolean includeLoggerName = true;
    protected boolean includeFormattedMessage = true;
    protected boolean includeMessage;
    protected boolean includeException = true;
    protected boolean includeContextName = true;
    private final ThrowableProxyConverter throwableProxyConverter = new ThrowableProxyConverter();

    public JsonLayoutExt() {
    }

    public void start() {
        this.throwableProxyConverter.start();
        super.start();
    }

    public void stop() {
        super.stop();
        this.throwableProxyConverter.stop();
    }

    protected Map toJsonMap(ILoggingEvent event) {
        LinkedHashMap map = new LinkedHashMap();
        if (this.includeTimestamp) {
            long throwableProxy = event.getTimeStamp();
            String formatted = this.formatTimestamp(throwableProxy);
            if (formatted != null) {
                map.put("timestamp", formatted);
            }
        }

        String ex;
        if (this.includeLevel) {
            Level throwableProxy1 = event.getLevel();
            if (throwableProxy1 != null) {
                ex = String.valueOf(throwableProxy1);
                map.put("level", ex);
            }
        }

        String throwableProxy2;
        if (this.includeThreadName) {
            throwableProxy2 = event.getThreadName();
            if (throwableProxy2 != null) {
                map.put("thread", throwableProxy2);
            }
        }

        if (this.includeMDC) {
            Map throwableProxy3 = event.getMDCPropertyMap();
            if (throwableProxy3 != null && !throwableProxy3.isEmpty()) {
                map.put("mdc", throwableProxy3);
            }
        }

        if (this.includeLoggerName) {
            throwableProxy2 = event.getLoggerName();
            if (throwableProxy2 != null) {
                map.put("logger", throwableProxy2);
            }
        }

        if (this.includeFormattedMessage) {
            throwableProxy2 = event.getFormattedMessage();
            if (throwableProxy2 != null) {
                map.put("message", throwableProxy2);
            }
        }

        if (this.includeMessage) {
            throwableProxy2 = event.getMessage();
            if (throwableProxy2 != null) {
                map.put("raw-message", throwableProxy2);
            }
        }

        if (this.includeContextName) {
            throwableProxy2 = event.getLoggerContextVO().getName();
            if (throwableProxy2 != null) {
                map.put("context", throwableProxy2);
            }
        }

        if (this.includeException) {
//            IThrowableProxy throwableProxy4 = event.getThrowableProxy();
//            if (throwableProxy4 != null) {
//                ex = this.throwableProxyConverter.convert(event);
//                if (ex != null && !ex.equals("")) {
//                    map.put("exception", ex);
//                }
//            }
            IThrowableProxy throwableProxy4 = event.getThrowableProxy();
            if (throwableProxy4 != null) {
                ex = this.throwableProxyConverter.convert(event);
                if (ex != null && !ex.equals("")) {
                    map.put("exception", ex);
                    if (throwableProxy4.getCause() != null) {
                        IThrowableProxy throwableCause1 = throwableProxy4.getCause();
                        if (RuntimeException.class.getName().equals(throwableCause1.getClassName()) && throwableCause1.getCause() != null) {
                            throwableCause1 = throwableCause1.getCause();
                        }

                        map.put("exceptiontype", throwableCause1.getClassName());
                    } else {
                        map.put("exceptiontype", throwableProxy4.getClassName());
                    }
                }
            }
        }

        return map;
    }

    public boolean isIncludeLevel() {
        return this.includeLevel;
    }

    public void setIncludeLevel(boolean includeLevel) {
        this.includeLevel = includeLevel;
    }

    public boolean isIncludeLoggerName() {
        return this.includeLoggerName;
    }

    public void setIncludeLoggerName(boolean includeLoggerName) {
        this.includeLoggerName = includeLoggerName;
    }

    public boolean isIncludeFormattedMessage() {
        return this.includeFormattedMessage;
    }

    public void setIncludeFormattedMessage(boolean includeFormattedMessage) {
        this.includeFormattedMessage = includeFormattedMessage;
    }

    public boolean isIncludeMessage() {
        return this.includeMessage;
    }

    public void setIncludeMessage(boolean includeMessage) {
        this.includeMessage = includeMessage;
    }

    public boolean isIncludeMDC() {
        return this.includeMDC;
    }

    public void setIncludeMDC(boolean includeMDC) {
        this.includeMDC = includeMDC;
    }

    public boolean isIncludeThreadName() {
        return this.includeThreadName;
    }

    public void setIncludeThreadName(boolean includeThreadName) {
        this.includeThreadName = includeThreadName;
    }

    public boolean isIncludeException() {
        return this.includeException;
    }

    public void setIncludeException(boolean includeException) {
        this.includeException = includeException;
    }

    public boolean isIncludeContextName() {
        return this.includeContextName;
    }

    public void setIncludeContextName(boolean includeContextName) {
        this.includeContextName = includeContextName;
    }
}
