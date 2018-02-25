package cms.model;

import javax.persistence.*;
import java.util.List;
import java.sql.Timestamp;

@Entity
@Table(name = "cms_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Category> categories;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Timestamp posted;

    public Post() {
    }

    public Post(User user, List<Category> categories, String title, String content, Timestamp posted) {
        this.user = user;
        this.categories = categories;
        this.title = title;
        this.content = content;
        this.posted = posted;
    }

    public Post(User user, List<Category> categories, String title, String content) {
        this.user = user;
        this.categories = categories;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPosted() {
        return posted;
    }

    public void setPosted(Timestamp posted) {
        this.posted = posted;
    }
}
