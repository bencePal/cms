package cms.controller;

import cms.service.CategoryService;
import cms.service.MenuService;
import cms.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class PostControllerTest {

    private MockMvc mockMvc;

    private PostController postController;

    @Mock
    private PostService postService;
    @Mock
    private MenuService menuService;
    @Mock
    private CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(PostService.class);


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postController = new PostController(postService, menuService, categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void testPostPage() throws Exception {

        mockMvc.perform(get("/post/7/"))
                .andExpect(status().isOk())
                .andExpect(content().encoding("ISO-8859-1"))
                .andExpect(view().name("post"));

        MvcResult result = mockMvc.perform(get("/post/7/")).andReturn();
        logger.debug("view name: " + result.getModelAndView().getViewName());
        logger.debug("model: " + result.getModelAndView().getModel());
        logger.debug("response: " + result.getResponse());
    }

    @Test
    public void testPostPage404() throws Exception {
        mockMvc.perform(get("/post/asd/"))
                .andExpect(status().is4xxClientError());
    }
}