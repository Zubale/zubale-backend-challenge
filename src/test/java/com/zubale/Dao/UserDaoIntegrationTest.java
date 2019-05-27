package com.zubale.Dao;

import com.zubale.models.dao.UserDao;
import com.zubale.models.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // given
        User user = new User();
        user.setEmail("test@test.com");
        user.setUsername("testMan");
        entityManager.persist(user);
        entityManager.flush();

        // when
        User found = userDao.findByUsername(user.getUsername());

        // then
        assertThat(found.getUsername())
                .isEqualTo(user.getName());
    }
}
