package cms.service;

import cms.model.Page;
import cms.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    private PageRepository pageRepository;

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public void savePage(Page page) {
        pageRepository.save(page);
    }

    public List<Page> getAllPage() {
        return pageRepository.findAllByOrderByPostedDesc();
    }

    public void deletePageById(Long id) {
        pageRepository.delete(id);
    }

    public Page findPageById(Long id) {
        return pageRepository.findOne(id);
    }

    public List<Page> getAllPageByCategoryId(Long id) {
        return pageRepository.findAllByCategoriesId(id);
    }
}
