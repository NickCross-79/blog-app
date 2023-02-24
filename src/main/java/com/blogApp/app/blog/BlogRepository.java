package com.blogApp.app.blog;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer> {
    public Blog findById(int id);

    public List<Blog> findByCategory(String category);
}
