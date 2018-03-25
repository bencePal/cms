package cms.repository;

import cms.model.Category;
import cms.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByPostedDesc();

    List<Post> findAllByCategoriesId(Long id);



}
