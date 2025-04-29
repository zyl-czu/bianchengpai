package com.github.zylczu.test.dao;

import com.github.zylczu.api.model.vo.user.dto.UserStatisticInfoDTO;
import com.github.zylczu.service.user.service.UserService;
import com.github.zylczu.test.BasicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author YiHui
 * @date 2022/7/20
 */
@Slf4j
public class UserDaoTest extends BasicTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserHome() throws Exception {
        UserStatisticInfoDTO userHomeDTO = userService.queryUserInfoWithStatistic(1L);
        log.info("query userPageDTO: {}", userHomeDTO);
    }
}
