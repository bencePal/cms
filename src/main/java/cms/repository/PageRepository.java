package cms.repository;

import cms.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findAllByOrderByPostedDesc();

    List<Page> findAllByCategoriesId(Long id);

}
