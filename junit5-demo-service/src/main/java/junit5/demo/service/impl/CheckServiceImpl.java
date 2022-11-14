/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 * <p>
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package junit5.demo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import junit5.demo.service.CheckService;

/**
 * @author yongqi.pan
 * @since 2022/11/8 17:03
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Override
    public boolean isLegal(String name) {
        return !StringUtils.contains(name, "呐喊");
    }

}
