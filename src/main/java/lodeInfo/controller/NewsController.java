package lodeinfo.controller;

import lodeinfo.model.NewsEntity;
import lodeinfo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@RequestMapping(
        value = "/news")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    // Get page for someone
    @RequestMapping(
            value = "/findForSomeone",
            method = RequestMethod.GET
    )
    public Page<NewsEntity> getNewsForSomeone(@RequestParam int offset, @RequestParam String someone) {
        return newsRepository.findNewsForSomeone(someone, new PageRequest(offset, 10));
    }

    // Get page with 10 news
//    @RequestMapping(
//            value = "/findAll",
//            method = RequestMethod.GET)
//    public Page<NewsEntity> getNews(@RequestParam int offset) {
//        return newsRepository.findNewsForAll(new PageRequest(offset, 10));
//    }

    // Save
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST)
    public NewsEntity addNews(@RequestBody NewsEntity news) {
        return newsRepository.save(news);
    }

    // Delete
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.DELETE)
    public void delNews(@PathVariable Long id) {
        newsRepository.delete(id);
    }

    // Get one
    @RequestMapping(
            value = "/get/{id}",
            method = RequestMethod.GET)
    public NewsEntity getNews(@PathVariable Long id) {
        return newsRepository.findById(id);
    }

    // Update
    @RequestMapping(
            value = "/save/{id}",
            method = RequestMethod.PUT)
    public NewsEntity updateNews(@PathVariable Long id, @RequestBody NewsEntity news) {
        NewsEntity newsForEdit = newsRepository.findById(id);

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new java.sql.Timestamp(calendar.getTime().getTime());

        newsForEdit.setText(news.getText());
        newsForEdit.setTitle(news.getTitle());
        newsForEdit.setLastEdit(now);
        newsRepository.save(newsForEdit);

        return newsForEdit;
    }
}