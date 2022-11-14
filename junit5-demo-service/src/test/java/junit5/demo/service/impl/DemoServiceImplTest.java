package junit5.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import junit5.demo.service.CheckService;
import junit5.demo.service.utils.ResultUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author yongqi.pan
 * @since 2022/11/8 11:07
 */
@ExtendWith(MockitoExtension.class)
class DemoServiceImplTest {

    @InjectMocks
    private DemoServiceImpl demoService;

    @Mock
    private CheckService checkService;

    private final String applicationName = "junit5-demo";

    @BeforeEach
    public void init() {
        //参数注入
        ReflectionTestUtils.setField(demoService, "applicationName", applicationName);
    }

    @Test
    void getResponseTooLate() {
        String type = "历史";
        //mock静态方法, 真实执行
        try (MockedStatic<LocalDateTime> dateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parse = LocalDateTime.parse("2022-09-10 10:10:10", formatter);
            dateTimeMockedStatic.when(LocalDateTime::now).thenReturn(parse);
            Mockito.when(checkService.isLegal(Mockito.any())).thenReturn(true);
            assertEquals("Too Late", demoService.getResponse(type));
        }
    }

    @Test
    void mockStatic(){
        String type = "历史";
        //mock静态方法, mock结果
        try (MockedStatic<ResultUtils> mock = Mockito.mockStatic(ResultUtils.class)) {
            mock.when(() -> ResultUtils.getResult(type)).thenReturn("Others");
            //Mockito.any()
            Mockito.when(checkService.isLegal(Mockito.any())).thenReturn(true);
            assertEquals("Others", demoService.getResponse(type));
        }
    }

    @Test
    void testException(){
        //异常测试
        String type = "小说";
        assertThrows(RuntimeException.class, () -> demoService.getResponse(type), "Not legal");
    }

    @ParameterizedTest
    @CsvSource({
            "name, junit5-demo",
            "历史, 中华上下五千年",
            "技术, Clean Code",
            "小说, 呐喊",
            "other, idk"
    })
    void testResponse(String type, String result){
        //参数化测试
        Mockito.when(checkService.isLegal(Mockito.any())).thenReturn(true);
        assertEquals(result, demoService.getResponse(type));
    }
}