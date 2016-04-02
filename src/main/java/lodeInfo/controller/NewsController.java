package lodeInfo.controller;

import lodeInfo.model.NewsEntity;
import lodeInfo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @RequestMapping("findAll")
    List<NewsEntity> getNews() {
        return newsRepository.findAll();
    }

}
