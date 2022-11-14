/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 * <p>
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package junit5.demo.service.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yongqi.pan
 * @since 2022/11/8 16:02
 */
public class ResultUtils {

    public static String getResult(String type){
        if (StringUtils.equalsIgnoreCase(type, "技术")){
            return "Clean Code";
        } else if (StringUtils.equalsIgnoreCase(type, "历史")){
            return "中华上下五千年";
        } else if(StringUtils.equalsIgnoreCase(type, "小说")){
            return "呐喊";
        }
        return "idk";
    }
}
