package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.util.LoggerUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName TaskRepositoryTest
 * 原子任务表仓库 测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:14
 */
@SpringBootTest
public class TaskRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRepositoryTest.class);

    //@Autowired
    //TaskDetailRepository taskDetailRepository;

    @Test
    void test() {
        LoggerUtil.info(LOGGER, "test started...");
    }

}
