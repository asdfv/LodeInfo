package lodeinfo.controller;

        import lodeinfo.model.NewsEntity;
        import lodeinfo.repository.NewsRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
@RequestMapping(value = "/")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces="application/json")
    public List<NewsEntity> getNews() {
        return newsRepository.findAll();
    }

}