package cms.service;

import cms.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService service;


    @Test
    public void getAllTest() {
        List<Post> list = service.getAllPost();
        assertNotNull(list);
        assertEquals("Shake treat bag", list.get(0).getTitle());
        assertEquals(3, list.size());
    }

    @Test
    public void saveTest() {
        Post post = new Post();
        post.setId(8L);
        post.setTitle("alma");
        post.setContent("asdasdasd");

        service.savePost(post);

        assertEquals(post.getTitle(), service.getAllPost().get(0).getTitle());
        assertEquals(post.getContent(), service.getAllPost().get(0).getContent());
    }

    @Test
    public void updateTest() {
        service.findPostById(7L).setTitle("dog");
        assertEquals("dog", service.getAllPost().get(2).getTitle());
    }

    @Test(expected = NullPointerException.class)
    public void removeTest() {
        service.deletePostById(7L);
        System.out.println(service.findPostById(7L).getTitle());
    }

    @Test
    public void findTest() {
        assertEquals("Shake treat bag", service.findPostById(6L).getTitle());
    }

    @Test
    public void getAllByCategoryId() {
        List<Post> list = service.getAllPostByCategoryId(1L);
        assertEquals(3, list.size());
    }

}