package cms.service;

import cms.model.Post;
import cms.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAllByOrderByPostedDesc();
    }

    public void deletePostById(Long id) {
        postRepository.delete(id);
    }

//    public void updatePostById(Long id) {
//        postRepository.save(findPostById(id));
//    }

    public Post findPostById(Long id) {
        return postRepository.findOne(id);
    }
}
