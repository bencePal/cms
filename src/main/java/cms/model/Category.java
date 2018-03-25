package cms.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cms_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Page> pages = new HashSet<>();

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PreRemove
    public void removePostAndPageCategory() {
        for (Post post : posts ) {
            post.getCategories().remove(this);
        }
        for (Page page : pages) {
            page.getCategories().remove(this);
        }
    }
}
