package com.bla.imagefetch.util;

import org.slf4j.Logger;

/**
 * 自定义Logger类，静态类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
public class LoggerUtil {

    private static String mergeString(String... strings){
        StringBuilder stringBuilder = new StringBuilder();
        for(String s : strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static void info(Logger _Logger, String... strings){
        if (_Logger.isInfoEnabled()){
            _Logger.info(mergeString(strings));
        }
    }

    public static void trace(Logger _Logger, String... strings){
        if (_Logger.isTraceEnabled()){
            _Logger.trace(mergeString(strings));
        }
    }

    public static void debug(Logger _Logger, String... strings){
        if (_Logger.isDebugEnabled()){
            _Logger.debug(mergeString(strings));
        }
    }

    public static void warn(Logger _Logger, String... strings){
        if (_Logger.isWarnEnabled()){
            _Logger.warn(mergeString(strings));
        }
    }

    public static void error(Logger _Logger, String... strings){
        if (_Logger.isErrorEnabled()){
            _Logger.error(mergeString(strings));
        }
    }

    public static void error(Logger _Logger, Throwable throwable, String... strings){
        if (_Logger.isErrorEnabled()){
            _Logger.error(mergeString(strings), throwable);
        }
    }
}
