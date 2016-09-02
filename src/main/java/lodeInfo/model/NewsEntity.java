package lodeinfo.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "news_for_all")
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

    @OneToMany(fetch = FetchType.LAZY /*not work =(*/, cascade = CascadeType.ALL)
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    @Fetch(FetchMode.SELECT)
        @BatchSize(size = 1)
    private Set<FileEntity> files;

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


}
