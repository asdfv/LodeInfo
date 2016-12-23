package lodeinfo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "last_edit")
    private Timestamp lastEdit;

    @Column(name = "for_whom")
    @DefaultValue(value = "all")
    private String forWhom;

    @OneToMany(fetch = FetchType.LAZY /*not work =(*/, cascade = CascadeType.ALL)
    @JoinColumn(name = "news_id", referencedColumnName = "id")
//    @Fetch(FetchMode.SELECT)
//        @BatchSize(size = 1)
    private Set<FileEntity> files;


    public NewsEntity() {
    }

    public Set<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<FileEntity> files) {
        this.files = files;
    }

    public Timestamp getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Timestamp lastEdit) {
        this.lastEdit = lastEdit;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

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

    public String getForWhom() {
        return forWhom;
    }

    public void setForWhom(String forWhom) {
        this.forWhom = forWhom;
    }
}
