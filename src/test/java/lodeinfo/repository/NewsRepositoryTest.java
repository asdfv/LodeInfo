package lodeinfo.repository;

import lodeinfo.config.StartApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= StartApp.class)
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository repository;

    @Test
    @Transactional
    public void getEntity() {

        assertThat(repository.findAll().size(), is(4));
        repository.findAll().forEach(System.out::println);
    }

//    @Test
//    @Transactional
//    public void getSingle() {
//        NewsEntity entity = repository.findOne(4l);
//
//        entity.getNewsImages().forEach(System.out::println);
//        assertThat(entity.getNewsImages().size(), is(4));
//    }

}
