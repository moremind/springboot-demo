package com.javanorth.spring.springbootlog4j2.util;



import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerUtil {

//    /**
//     * debug级别日志输出
//     * @param clazz 类
//     * @param msg 日志
//     * @param params 其他参数
//     */
//    public static void debug(Class clazz, String msg, Object... params) {
//        Logger logger = LoggerFactory.getLogger(clazz.getName());
//        logger.debug(msg, params);
//    }
//
    public static void trace(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.trace(msg, params);
    }

    public static void info(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.info(msg);
//        getLogger().info(msg);
    }

    public static void warn(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn(msg, params);
    }

    public static void error(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg, params);
    }

//    private static Logger getLogger() {
//        return LogManager.getLogger(findCaller().getClassName());
//    }
//
//    private static StackTraceElement findCaller() {
//        // 获取堆栈信息
//        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
//
//        // 最原始被调用的堆栈信息
//        StackTraceElement caller = null;
//
//        // 日志类名称
//
//        String logClassName = LoggerUtil.class.getName();
//        // 循环遍历到日志类标识
//
//        int i = 0;
//        for (int len = callStack.length; i < len; i++) {
//            if (logClassName.equals(callStack[i].getClassName())) {
//                break;
//            }
//        }
//        //是的没有错,这是一个magic number！想知道为什么？开启你的堆栈，来寻找我的宝藏吧！by Luo
//        caller = callStack[i + 3];
//        return caller;
//    }




}
