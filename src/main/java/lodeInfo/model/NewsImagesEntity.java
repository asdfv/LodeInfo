package lodeInfo.model;


import javax.persistence.*;

@Entity
@Table(name = "news_images")
public class NewsImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @Column(name = "id_news")
    private Long id_news;

    @Column(name = "url")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_news() {
        return id_news;
    }

    public void setId_news(Long id_news) {
        this.id_news = id_news;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
