package com.blogApp.app.blog;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepo;
    public int pageVisits = 0;

    @Autowired
    public BlogController(BlogRepository blogRepo){
        this.blogRepo = blogRepo;

        for (Blog blog : this.blogRepo.findAll()) {
            blog.setViews(0);
            blogRepo.save(blog);
        }
    }

    @PostMapping("/blog/{id}")
    public RedirectView deleteBlog(@PathVariable int id) {
        blogRepo.deleteById(id);

        return new RedirectView("/");
    }

    @GetMapping("/pageVisits")
    public ResponseEntity<String> getPageVisits() {
        return ResponseEntity.ok(String.valueOf(pageVisits));
    }

    @GetMapping("/blogViews/{id}")
    public ResponseEntity<String> getBlogViews(@PathVariable("id") int id){
        int blogViews = blogRepo.findById(id).getViews();
        return ResponseEntity.ok(String.valueOf(blogViews));
    }

    @GetMapping("/")
    public String getBlogs(@RequestParam(required = false, defaultValue = "all") String category, Model model){
        pageVisits++;
        List<Blog> posts =  (List<Blog>) this.blogRepo.findAll();

        if (!category.equalsIgnoreCase("all")) {
            posts = posts.stream().filter(p -> p.getCategory()
                .equalsIgnoreCase(category))
                .collect(Collectors.toList());
        }

    model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/createBlog")
    public String getCreateBlog(Model model){
        model.addAttribute("blog", new Blog());
        return "createBlog";
    }

    @PostMapping("/createBlog")
    public String postCreateBlog(@Valid Blog blog, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "createBlog";
        }
        this.blogRepo.save(blog);
        return "redirect:/";
    }

    @GetMapping("/blog")
    public String getBlog(@RequestParam int id, Model model){
        Blog blog = this.blogRepo.findById(id);
        int views = blog.getViews() + 1;
        blog.setViews(views);
        blogRepo.save(blog);

        model.addAttribute("title", blog.getTitle());
        model.addAttribute("category", blog.getCategory());
        model.addAttribute("author", blog.getAuthor());
        model.addAttribute("body", blog.getBody());
        
        return "blog";
    }   
}
