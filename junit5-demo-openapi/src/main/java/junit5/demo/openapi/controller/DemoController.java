/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 * <p>
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
package junit5.demo.openapi.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import junit5.demo.openapi.pojo.DemoRequest;
import junit5.demo.openapi.pojo.DemoResponse;
import junit5.demo.service.DemoService;

/**
 * @author yongqi.pan
 * @since 2022/11/8 10:43
 */
@RestController
@RequestMapping("/junit5/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/test")
    public DemoResponse getDemo(@RequestParam String type) {
        DemoResponse response = new DemoResponse();
        response.setApplicationName(demoService.getResponse(type));
        return response;
    }

    @PostMapping("/test")
    public DemoResponse postDemo(@RequestBody DemoRequest request) {
        DemoResponse response = new DemoResponse();
        response.setApplicationName(demoService.getResponse(request.getReq()));
        return response;
    }
}
