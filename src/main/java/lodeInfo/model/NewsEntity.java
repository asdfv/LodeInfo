package lodeinfo.model;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @OneToMany(mappedBy = "news")
//    private List<NewsImagesEntity> newsImages;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

//    public List<NewsImagesEntity> getNewsImages() {
//        return newsImages;
//    }
//
//    public void setNewsImages(List<NewsImagesEntity> newsImages) {
//        this.newsImages = newsImages;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
