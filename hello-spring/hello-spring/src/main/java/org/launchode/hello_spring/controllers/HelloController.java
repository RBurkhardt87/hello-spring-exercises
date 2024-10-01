package org.launchode.hello_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/*
Every new controller we make, we must use an annotation @Controller before the Controller class.
There are methods in this class that are set up to deal with http requests. If you leave off annotation, Spring boot
won't recognize the methods as such-- even if they are written correctly.


To make your method "wired up" to be a request handler, you much add the annotation

---> Think of it as plugging in a controller to a console. W/o it the console can't read it. No wireless controllers here :)

 You also have to add annotations before you methods inside the controller class. The fist one we are going to use is...
 @ResponseBody, we will only be using in until we start getting into using templates in Spring Boot. Essentially is just
 tells Spring Boot that this method will return a plain text http response (No templates).

 @GetMapping : specifies to SpringBoot that this method should handle http get requests, and it will ONLY handle get requests.

 "Hello, Spring" was returned by the application because we made a request-- in particularly, I made a get request, which matched up
 with the @GetMapping and I made a request to the root path

 Routes are important in determining which requests get sent to which controller. This first @GetMapping controller method sends to the
 root path. It is the same as localhost:8080/   Saying there is no other path after it. REMEMBER: "/" is the ROOT PATH

 Since there is no particular routing information included in the controller class or method, it defaults to just living at the root path

 I created another method hello() that handles get requests, but this time I specified it to be handled at the hello path
            "localhost:8080/hello

 Below that is another @GetMapping method goodbye() that handles request at the goodbye path

 THESE ARE ALL STATIC RESPONSES IN SPRING BOOT
 */

@ResponseBody
@Controller
@RequestMapping("hello")    //<--- this means that everything begins at /hello/ & everything that comes after that begins there as well. Also, just means we can remove "hello" from other handlers
public class HelloController {

    //TODO: Handles request at the root path
    @GetMapping
//    @ResponseBody
    public String helloRoot() {
        return "Hello, Spring";
    }


    //TODO: Handles request at path/hello
//    @ResponseBody
//    @GetMapping("hello")
//    public String hello() {
//        return "Hello, Spring @ hello path";
//    }

    //TODO: Handles get request at path/goodbye     <--- so this NOW lives at /hello/goodbye   (due to @RequestMapping("hello") declared above the class declaration
//    @ResponseBody
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring";
    }

    //TODO: Responds to post requests at "/goodbye"   <---- also at /hello/goodbye
    @PostMapping("goodbye")
    public String goodbyePost() {
        return "Post Goodbye";
    }



    /*
    Below we will look at how to create handlers for dynamic responses-- meaning that it will return something based on data
    /hello?name=LaunchCode

    it takes a query parameter called name with a given value and that value should be used in the response

    Inside the parameter of the method, we are going to use the @RequestParam before the data type and variable name
    It signifies to Spring that this particular method expects there to be a query param called name. If the query URL
    doesn't match the query param, then it wont work. If they are the same, if it does, it will take "LaunchCode" from the
    query URL and populate the method param name with that exact same value because the query param name and method param name
    match up


    I got this message when I tried to run the program

    "Caused by: java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'helloController' method "

    That is because I have 2 different controller methods at the same path, and to resolve I just have to comment
    out the other hello path method for now


    So, in the URL I enter :  localhost:8080/hello?name=LaunchCode
                                   Hello, LaunchCode!
                               localhost:8080/hello?name=Rachel
                                    Hello, Rachel!
    Whatever value in the URL I am giving the query name param is what is being passed into the method

    The controller method looks for the query string in the URL that matches its parameter, name,
    and puts the paired value of that name key into the response text.
     */


    //TODO: Handles request of the form /hello?name=LaunchCode
//    @GetMapping("hello")
//    @ResponseBody
//    public String helloWithQueryParam(@RequestParam String name){
//        return "Hello, " + name +"!";
//    }




    /*
    Controller handler methods that respond dynamically
    Difference here is that, the piece of data/variable "LaunchCode" is actually a part of the URL
    @GetMapping("hello/{name}")

    You would think these paths below and right above "hello" would cause an error, but because the variable name
    is actually making up part of the URL. It is distinguished as different paths

    @PathVariable is the annotation you use in this method, when the variable is making up a piece of the URL

    This particular request is coming in with a piece of dynamic data that's part of the path itself. That piece of
    data is also being used to create a customized response.

    NOTE:
    Heart of Web Applications : to take a request that contains some type of data (usually more complicated than one word)
    and creates and returns response based on that specific data.
     */


    //TODO: Handles request of the form /hello/LaunchCode   <--- we can remove the "hello" from the GetMapping("hello/{name}")
    @GetMapping("{name}")
//    @ResponseBody
    public String helloWithPathParam(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    /*
    How we can create a form that will allow a user to type in their name and then greet them accordingly
    We are going to be returning a form, so we are going to use <html>

    NOTE: If you keep your cursor inside the "" when writing your html tags, when you hit enter, it will add a +
            and drop down to the next line for more <html tags> to be entered.

    FORM: input= 'type' is text. You will see single quotes used, that's because Java needs " " itself
          name= 'name' is the parameter identifier that will be used to submit whatever is in this form input. Just
                 what we are calling it.
          ---> where submitted: it is decided using...
                        action= 'hello'
                which is just dropped inside the <form> html tag
                This tells it to submit a request to /hello
     We already have a request handler at the /hello path that takes a query parameter already. So, we don't
     have to make anything new.

     Sometimes form will also have a method inside the tag, but it defaults to GET when there isn't one set
     */

    //TODO: Create a form for user to enter name and create a path
//    @GetMapping("form")
//    @ResponseBody
//    public String helloForm(){
//        return "<html>" +
//                "<body>" +
//                "<form action='hello'>" +
//                "<input type='text' name='name'>" +
//                "<input type='submit' value= 'Greet me!'>" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

    //TODO: Create form that submits via post request     <--- lives at /hello/form
    //We must add a method = post inside the form tag
    //We also need a controller handler method can do GET and POST requests
    //We are going to comment out the GET handler method listed above at the /hello path
    //and make a handler method that will take care of GET and POST below this
    @GetMapping("form")
//    @ResponseBody
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='hello' method='post'>" +
                "<input type='text' name='name'>" +
                "<input type='submit' value= 'Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    //TODO: Handler will do GET and POST request at the /hello path     <---- we can remove value="hello" in the RequestMapping param
    //You will see that the annotation changes to @RequestMapping for GET and POST combined
    //The RequestMapping is just more general and you assign what you want it to do in its params
    //You also have to set the method to be both GET and POST as you see below
    //@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public String helloWithQueryParam(@RequestParam String name) {
        return "Hello " + name + "!";
    }


    /*
    Each method has a cpl annotations, GET/POST Mapping and ResponseBody.
    To DRY code, you can remove all the @ResponseBody above the methods and just declare it above the class declaration
    I personally just commented it out above the methods for educational purposes-- I want to remember how it is set up if
    using for just a method.

     */
}
