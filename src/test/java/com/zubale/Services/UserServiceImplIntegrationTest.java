package com.zubale.Services;

import com.zubale.models.dao.UserDao;
import com.zubale.models.entity.User;
import com.zubale.models.services.UserDetailsServiceImpl;
import com.zubale.models.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserDetailsServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

    @Before
    public void setUp() {
        User user = new User();
        user.setUsername("testMan");
        user.setEmail("testMan@test.com");

        Mockito.when(userDao.findByUsername(user.getUsername()))
                .thenReturn(user);
    }

    @Test
    public void whenValidUsername_thenUserShouldBeFound() {
        String userName = "testMan";
        User found = userService.findByUsername(userName);

        assertThat(found.getUsername())
                .isEqualTo(userName);
    }
}
