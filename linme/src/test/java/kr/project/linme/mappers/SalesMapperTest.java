package kr.project.linme.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SalesMapperTest {
    @Autowired
    private SalesMapper salesMapper;
    
    @Test
    @DisplayName("매출 집계 추가 테스트")
    void insertSales() {
        int output = salesMapper.insert();
        log.debug("output: " + output);
    }

}
