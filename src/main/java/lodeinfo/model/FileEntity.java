package lodeinfo.model;

import javax.persistence.*;

@Entity
@Table(name = "news_files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mime_type")
    private String mimeType;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "news_id")
    private Long newsId;

    @Column(name = "size")
    private Long size;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public FileEntity(String name, String mimeType, byte[] file, Long newsId, Long size) {
        this.name = name;
        this.mimeType = mimeType;
        this.file = file;
        this.newsId = newsId;
        this.size = size;
    }

    public FileEntity() {
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
