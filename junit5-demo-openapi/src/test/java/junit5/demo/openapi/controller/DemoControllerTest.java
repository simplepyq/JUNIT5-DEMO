package junit5.demo.openapi.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import junit5.demo.openapi.pojo.DemoResponse;
import junit5.demo.service.DemoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yongqi.pan
 * @since 2022/11/8 11:01
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(DemoController.class)
class DemoControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private DemoService demoService;

    @Test
    void getDemo() throws Exception {
        String uri = "/junit5/demo/test";
        String appName = "junit5-demo";
        RequestBuilder builder = MockMvcRequestBuilders.get(uri)
                .param("type", "name");

        Mockito.when(demoService.getResponse("name")).thenReturn(appName);
        mockMvc.perform(builder).andExpect(status().is2xxSuccessful()).andExpect(result -> {
            DemoResponse response = JSON.parseObject(result.getResponse().getContentAsString(), DemoResponse.class);
            Assertions.assertEquals(appName, response.getApplicationName());
        }).andDo(print());
    }

}