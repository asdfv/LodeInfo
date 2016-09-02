package lodeinfo.controller;

import lodeinfo.model.ForAdminsEntity;
import lodeinfo.repository.ForAdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@RequestMapping(
        value = "/forAdmins")
public class ForAdminsController {

    @Autowired
    ForAdminsRepository newsRepository;

    // Upload


    // Get page with 10 news
    @RequestMapping(
            value = "/findAll",
            method = RequestMethod.GET)
    public Page<ForAdminsEntity> getNews(@RequestParam int offset) {
        return newsRepository.findAllByOrderByLastEditDesc(new PageRequest(offset, 10));
    }

    // Save
    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST)
    public ForAdminsEntity addNews(@RequestBody ForAdminsEntity news) {
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
    public ForAdminsEntity getNews(@PathVariable Long id) {
        return newsRepository.findById(id);
    }

    // Update
    @RequestMapping(
            value = "/save/{id}",
            method = RequestMethod.PUT)
    public ForAdminsEntity updateNews(@PathVariable Long id, @RequestBody ForAdminsEntity news) {
        ForAdminsEntity newsForEdit = newsRepository.findById(id);

        Calendar calendar = Calendar.getInstance();
        Timestamp now = new java.sql.Timestamp(calendar.getTime().getTime());

        newsForEdit.setText(news.getText());
        newsForEdit.setTitle(news.getTitle());
        newsForEdit.setLastEdit(now);
        newsRepository.save(newsForEdit);

        return newsForEdit;
    }
}