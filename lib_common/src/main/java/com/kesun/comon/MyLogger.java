package com.kesun.comon;

import android.util.Log;



/**
 * Created by wang on 2016/10/25.
 * 打印log，可定位
 * 修改样式 2017-1-23
 */

public class MyLogger {

    private static final int DEBUG = 3;
    private static final int ERROR = 6;
    private static final int ASSERT = 7;
    private static final int INFO = 4;
    private static final int VERBOSE = 2;
    private static final int WARN = 5;
    private static final String DEFAULT_TAG = "PRETTYLOGGER";
    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private static final int CHUNK_SIZE = 40000;         //可打印的最长字段
    private static final int MIN_STACK_OFFSET = 3;      //调用MyLogger方法的最小层次
//    private static boolean isShow = BuildConfig.DEBUG;
    private static boolean isShow = true;
    private static int logLevel = 2;        //打印几个等级的方法

    public static void d (String tag, String message) {
        log(DEBUG, tag, message);
    }

    public static void d (String message) {
        if(isShow) {
            log(DEBUG, null, message);
        }
    }

    public static void v (String tag, String message) {
        log(VERBOSE, tag, message);
    }

    public static void w (String tag, String message) {
        log(WARN, tag, message);
    }

    public static void i (String tag, String message) {
        log(INFO, tag, message);
    }

    private static void log(int priority, String tag, String message) {
        if(!isShow) {
            return;
        }
        if (isEmpty(message)) {
            message = "Empty/NULL log message";
        }

        logLocation(priority, tag);

        //get bytes of message with system's default charset (which is UTF-8 for Android)
        byte[] bytes = message.getBytes();
        if (bytes.length < CHUNK_SIZE) {
            logContent(priority, tag, message);
        } else {
            for (int i = 0; i < bytes.length; i += CHUNK_SIZE) {
                int count = Math.min(bytes.length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                logContent(priority, tag, new String(bytes, i, count));
            }
        }
    }

    /**
     * 打印内容
     * @param logType   类型
     * @param tag   tag
     * @param chunk 内容
     */
    private static void logContent(int logType, String tag, String chunk) {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        for (String line : lines) {
            StringBuilder builder = new StringBuilder();
            builder.append("║ " + line);
            builder.append(" ");
            builder.append(System.getProperty("line.separator"));
            builder.append("╚════════════════════════════════════════════");
            logChunk(logType, tag,builder);
        }
    }

    /**
     * 打印位置,根据logLevel来打印几层的方法,默认3层
     * @param logType   打印级别
     * @param tag   tag
     */
    private static void logLocation(int logType, String tag) {
        String level = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        int start = getStackOffset(stackTraceElements);
        for (int i = start; i < start + logLevel; i++) {   //除去本身logLocation方法的打印

            if (i == stackTraceElements.length) break;
            StringBuilder builder = new StringBuilder();
            if(i==start){
                builder.append("╔════════════════════════════════════════════");
                builder.append(" ");
                builder.append(System.getProperty("line.separator"));
            }

            builder.append("║").append(level)
                    .append(getSimpleClassName(stackTraceElements[i].getClassName()))
                    .append(".")
                    .append(stackTraceElements[i].getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(stackTraceElements[i].getFileName())
                    .append(":")
                    .append(stackTraceElements[i].getLineNumber())
                    .append(")");
            level += "   ";

            logChunk(logType, tag, builder);

        }
        logLevel = 3;
    }

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    private static void logChunk(int logType, String tag, StringBuilder chunks) {
        String finalTag = formatTag(tag);

        String chunk = chunks.toString();
        switch (logType) {
            case ERROR:
                Log.e(finalTag, chunk);
                break;
            case INFO:
                Log.i(finalTag, chunk);
                break;
            case VERBOSE:
                Log.v(finalTag, chunk);
                break;
            case WARN:
                Log.w(finalTag, chunk);
                break;
            case ASSERT:
                Log.wtf(finalTag, chunk);
                break;
            case DEBUG:
                // Fall through, log debug by default
            default:
                Log.d(finalTag, chunk);
                break;
        }
    }

    private static String formatTag(String tag) {
        if (!isEmpty(tag)) {
            return tag;
        }
        return DEFAULT_TAG;
    }

    private static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     *
     * @return the stack offset
     */
    private static int getStackOffset(StackTraceElement[] trace) {
        //经过debug发现，下标2开始是logLocation方法，0和1是线程方法,这个方法调用的层次是第3层 logLocation - log - d,所以MIN_STACK_OFFSET是2 + 3 = 5
        //但为了可扩展，一般设为3
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(MyLogger.class.getName())) {
                return i;
            }
        }
        return -1;
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int logLevel) {
        MyLogger.logLevel = logLevel;
    }
}