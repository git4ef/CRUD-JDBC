package ef.model;

import java.sql.Timestamp;

public class Post {
    private Long id;
    private String content;
    private Long created;
    private Long updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Post() {
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(Long id, String content, Long created, Long updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }


    @Override
    public String toString() {
        return "\n* ********************************************************" +
                "\n*  Post id: " + id +
                "\n* ********************************************************" +
                "\n*   content: " + content +
                "\n*   created: " + new Timestamp(created) +
                "\n*   updated: " + new Timestamp(updated) +
                "\n* ********************************************************";
    }
}
