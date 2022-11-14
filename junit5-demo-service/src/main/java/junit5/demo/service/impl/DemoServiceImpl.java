/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 * <p>
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package junit5.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import junit5.demo.service.CheckService;
import junit5.demo.service.DemoService;
import junit5.demo.service.utils.ResultUtils;

/**
 * @author yongqi.pan
 * @since 2022/11/8 10:47
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private CheckService checkService;

    @Override
    public String getResponse(String type) {
        String result = getResult(type);
        if (!checkService.isLegal(result)) {
            throw new RuntimeException("Not legal");
        }
        return result;
    }

    private String getResult(String type) {
        if (StringUtils.equalsIgnoreCase("name", type)) {
            return applicationName;
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse("2022-10-10 10:10:10", formatter);
        if (now.isAfter(parse)) {
            return ResultUtils.getResult(type);
        }
        return "Too Late";
    }
}
