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
//    private NewsEntity id_news;

    @Column(name = "url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public NewsEntity getId_news() {
//        return id_news;
//    }
//
//    public void setId_news(NewsEntity id_news) {
//        this.id_news = id_news;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
