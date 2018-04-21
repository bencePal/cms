package cms.service;

import cms.model.Page;
import cms.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private PageRepository pageRepository;

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<Page> getAllMenuItems() {
        return pageRepository.findAllByMenuIsTrue();
    }
}
