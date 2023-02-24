package com.blogApp.app.blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Value("$[some.key:0]")
    private int views;

    @NotEmpty(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotEmpty(message = "Category is required")
    private String category;

    @NotEmpty(message = "Author name is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Author name must not include numbers or symbols")
    @Size(max = 50, message = "Author name must be less than 50 characters")
    private String author;

    @NotEmpty(message = "Body is required")
    @Size(max = 500, message = "Body must be less than 500 characters")
    private String body;
}
