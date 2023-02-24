***NOTE***
For the step that required the view count API to be on all pages, I decided to make two seperate types 
of view counts; one for the main page, and one for each individual blog post, so users will instead 
see how many times each specific blog has been visited. It would have been possible to just simply include 
the same API call in the blog view, but I thought it was a neat idea, and that the individual view 
counters would look better/make more sense in the case of my specific project concept.



***README***
Each section of this read me includes a brief description of the class/view, its features, as well 
as which requirments from the instructions are satisfied. This project also includes a unit test class 
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

Requirements Met:
    - contents from the form should be persisted if it passes validation (saved into a database)
    - must take an optional get param to filter the list by an attribute
    - There must be an API that returns the number of page hits since the server was online
    - This api should be called asynchronously every 3 seconds and the results displayed on every page
    - There must be at-least 1 dependency injected into two different locations in the project



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

Requirments Met:
    - form must be validated on the server side (for every field)
    - use of lombok in data classes



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

Requirements Met:
    - A page that users can go to that lists the items created from the form in requirement 1 using Templates/Thymeleaf
    - There must be an API that returns the number of page hits since the server was online
    - This api should be called asynchronously every 3 seconds and the results displayed on every page
    - must take an optional get param to filter the list by an attribute
    - Aesthetically pleasing website (e.x using css or frameworks)



***Blog.html***
This an HTML page that displays the details of a blog post.

Features:
    Back button: allows the user to navigate to the previous page.
    View counter: shows the number of views for the blog post.
    The title, category, author, and body of the blog post.
    Responsive design using Bootstrap.
    The page uses Thymeleaf syntax to render the values of the blog post dynamically.

Requirments Met:
    - A page that users can go to that lists the items created from the form in requirement 1 using Templates/Thymeleaf
    - Aesthetically pleasing website (e.x using css or frameworks)
    - There must be an API that returns the number of page hits since the server was online
    - This api should be called asynchronously every 3 seconds and the results displayed on every page

Note: In order to follow the API requirements, the view count is reset during every initialization of the server, so 
      that the views are counted per server session. This can be modified by simply removing the initialization of 
      the 'views' attribute from the BlogController constructor. 



***createBlog.html***
This is an HTML form for creating a blog

Features:
    Fields for the title, category, author, and body of the blog 
    Bootstrap for styling and layout
    Thymeleaf for form handling and validation
    A form that submits to the createBlog action, and binds the fields to a blog object 
    Error messages for each field if there are any validation errors.

Requirements Met:
    - A page with a form where users have to input information
    - must have atleast 3 fields for the user to fill out
    - form must be validated on the server side (for every field)
    - contents from the form should be persisted if it passes validation (saved into a database)
    - Aesthetically pleasing website (e.x using css or frameworks)