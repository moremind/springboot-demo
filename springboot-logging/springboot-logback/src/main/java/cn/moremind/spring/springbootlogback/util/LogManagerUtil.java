package cn.moremind.spring.springbootlogback.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogManagerUtil {
    /**
     * debug级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void debug(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.debug(msg, params);
    }

    /**
     * trace级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void trace(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.trace(msg, params);
    }

    /**
     * info级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void info(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.info(msg, params);
    }

    /**
     * warn级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void warn(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.warn(msg, params);
    }

    /**
     * error级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void error(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.error(msg, params);
    }

    /**
     * fatal日志
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void fatal(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.fatal(msg, params);
    }
}
