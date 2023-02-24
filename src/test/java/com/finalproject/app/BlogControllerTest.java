package com.finalproject.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.view.RedirectView;

import com.finalproject.app.blog.Blog;
import com.finalproject.app.blog.BlogController;
import com.finalproject.app.blog.BlogRepository;

public class BlogControllerTest {
    private BlogRepository mockBlogRepository;
    private BlogController blogController;

    @BeforeEach
    public void setUp() {
        mockBlogRepository = Mockito.mock(BlogRepository.class);
        blogController = new BlogController(mockBlogRepository);
    }

    @Test
    public void testDeleteBlog() {
        // Set up test data
        int blogId = 1;
        Blog blog = new Blog();
        blog.setId(blogId);

        // Set up mock repository
        Mockito.when(mockBlogRepository.findById(blogId)).thenReturn(blog);

        // Execute the method under test
        RedirectView result = blogController.deleteBlog(blogId);

        // Verify the results
        assertEquals("/", result.getUrl());
        Mockito.verify(mockBlogRepository).deleteById(blogId);
    }

    @Test
    public void testGetPageVisits() {
        // Set up test data
        int expectedPageVisits = 5;
        blogController.pageVisits = expectedPageVisits;

        // Execute the method under test
        ResponseEntity<String> result = blogController.getPageVisits();

        // Verify the results
        assertEquals(expectedPageVisits, Integer.parseInt(result.getBody()));
    }

    @Test
    public void testGetBlogViews() {
        // Set up test data
        int blogId = 1;
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setViews(5);

        // Set up mock repository
        Mockito.when(mockBlogRepository.findById(blogId)).thenReturn(blog);

        // Execute the method under test
        ResponseEntity<String> result = blogController.getBlogViews(blogId);

        // Verify the results
        assertEquals(blog.getViews(), Integer.parseInt(result.getBody()));
    }

    @Test
    public void testGetBlogs() {
        // Set up test data
        String category = "all";
        int expectedPageVisits = blogController.pageVisits + 1;

        // Execute the method under test
        String result = blogController.getBlogs(category, new ExtendedModelMap());

        // Verify the results
        assertEquals("index", result);
        assertEquals(expectedPageVisits, blogController.pageVisits);
    }

    @Test
    public void testGetCreateBlog() {
        // Execute the method under test
        String result = blogController.getCreateBlog(new ExtendedModelMap());

        // Verify the results
        assertEquals("createBlog", result);
    }

    @Test
    public void testPostCreateBlog() {
        // Set up test data
        Blog blog = new Blog();
        blog.setTitle("Test Blog");
        blog.setCategory("Test Category");
        blog.setAuthor("Test Author");
        blog.setBody("Test Body");
        // Set up mock repository
        Mockito.when(mockBlogRepository.save(blog)).thenReturn(blog);

        // Execute the method under test
        String result = blogController.postCreateBlog(blog, new BeanPropertyBindingResult(blog, "blog"), new ExtendedModelMap());

        // Verify the results
        assertEquals("redirect:/", result);
        Mockito.verify(mockBlogRepository).save(blog);
    }

    @Test
    public void testGetBlog() {
        // Set up test data
        int blogId = 1;
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setTitle("Test Blog");
        blog.setCategory("Test Category");
        blog.setAuthor("Test Author");
        blog.setBody("Test Body");
        blog.setViews(0);

        // Set up mock repository
        Mockito.when(mockBlogRepository.findById(blogId)).thenReturn(blog);

        // Execute the method under test
        String result = blogController.getBlog(blogId, new ExtendedModelMap());

        // Verify the results
        assertEquals("blog", result);
        Mockito.verify(mockBlogRepository).findById(blogId);
        assertEquals(1, blog.getViews());
        Mockito.verify(mockBlogRepository).save(blog);
    }


}

