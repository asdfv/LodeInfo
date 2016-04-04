package lodeinfo.model;


import javax.persistence.*;

@Entity
@Table(name = "news_images")
public class NewsImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "id_news")
//    private NewsEntity newsEntity;

    @Column(name = "url")
    private String url;

//    public NewsEntity getNewsEntity() {
//        return newsEntity;
//    }
//
//    public void setNewsEntity(NewsEntity newsEntity) {
//        this.newsEntity = newsEntity;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
