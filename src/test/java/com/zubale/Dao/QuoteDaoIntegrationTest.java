package com.zubale.Dao;

import com.zubale.models.dao.QuoteDao;
import com.zubale.models.dao.UserDao;
import com.zubale.models.entity.Quote;
import com.zubale.models.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuoteDaoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuoteDao quoteDao;

    @Test
    public void whenFindAll_thenReturnQuote() {
        // given
        Quote quote = new Quote();
        quote.setAutor("George R.R. Martin");
        quote.setBook("Song of Ice and Fire");
        entityManager.persist(quote);
        entityManager.flush();

        // when
        List<Quote> found = quoteDao.findAll();

        // then
        assertThat(found.get(0).getAutor())
                .isEqualTo(quote.getAutor());
    }
}
