***README***
Each section of this read me includes a brief description of the class/view, its features. This project also includes a unit test class 
called BlogControllerTest.java that contains unit tests from each aspect of the controller class.

Dependencies:
    Spring MVC
    javax.validation



***BlogController.java***
This is a Spring MVC controller that handles HTTP requests for a blog application.

Features:
    GET /: Returns a list of all blogs, or a list of blogs that match the specified category if a category query parameter is provided.
    POST /blog/{id}: Deletes the blog with the specified ID.
    GET /pageVisits: Returns the number of times the / route has been visited.
    GET /blogViews/{id}: Returns the number of times the blog with the specified ID has been viewed.
    GET /createBlog: Renders a form for creating a new blog.
    POST /createBlog: Creates a new blog using the data from the submitted form.
    GET /blog: Renders a page for the specified blog.


***Blog.java***
This is a Spring entity model for a blog:

Fields:
    id: The unique identifier for the blog, generated automatically using the AUTO generation strategy.
    views: The number of views for the blog, with a default value of 0.
    title: The title of the blog, which must not be empty and must be less than 100 characters.
    category: The category of the blog, which must not be empty.
    author: The name of the author of the blog, which must not be empty, must not include numbers or 
            symbols, and must be less than 50 characters.
    body: The body of the blog, which must not be empty and must be less than 500 characters.
          This entity model uses Lombok's @Data and @NoArgsConstructor annotations to automatically generate 
          getters, setters, and a no-args constructor. It also uses JSR-303 Bean Validation constraints to 
          validate the fields.


***index.html***
This is an HTML page for displaying a list of blogs.

Features:
    A title banner at the top with the text "Blogs" in white on a dark background.
    A counter that shows the number of page visits, starting from 1.
    A button to create a new blog post.
    A dropdown list to filter the list of blogs by category. The categories are: Science, Food, Pets, and Sports. The default is "All".
    A list of blog posts, each displaying the title, author, and category. The category is shown as a badge on the right side of the card.
    A button to read the full blog post.
    A button to delete a blog post by ID.
    Uses Bootstrap for styling and layout, jQuery for the page visit counter, and Thymeleaf for displaying dynamic data.


***Blog.html***
This an HTML page that displays the details of a blog post.

Features:
    Back button: allows the user to navigate to the previous page.
    View counter: shows the number of views for the blog post.
    The title, category, author, and body of the blog post.
    Responsive design using Bootstrap.
    The page uses Thymeleaf syntax to render the values of the blog post dynamically.


***createBlog.html***
This is an HTML form for creating a blog

Features:
    Fields for the title, category, author, and body of the blog 
    Bootstrap for styling and layout
    Thymeleaf for form handling and validation
    A form that submits to the createBlog action, and binds the fields to a blog object 
    Error messages for each field if there are any validation errors.
