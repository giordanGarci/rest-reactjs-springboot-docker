package com.giordan.apigateway;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World")
            String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/login")
    public String login() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Login Page</title>\n" +
                "<style>\n" +
                "    body {\n" +
                "        font-family: Arial, sans-serif;\n" +
                "        background-color: #f4f4f4;\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "    }\n" +
                "    .login-container {\n" +
                "        width: 300px;\n" +
                "        margin: 100px auto;\n" +
                "        background: #fff;\n" +
                "        padding: 20px;\n" +
                "        border-radius: 5px;\n" +
                "        box-shadow: 0px 0px 10px rgba(0,0,0,0.1);\n" +
                "    }\n" +
                "    h2 {\n" +
                "        text-align: center;\n" +
                "        color: #333;\n" +
                "    }\n" +
                "    form {\n" +
                "        display: flex;\n" +
                "        flex-direction: column;\n" +
                "    }\n" +
                "    input[type=\"text\"], input[type=\"password\"] {\n" +
                "        padding: 10px;\n" +
                "        margin: 10px 0;\n" +
                "        border: 1px solid #ddd;\n" +
                "        border-radius: 3px;\n" +
                "    }\n" +
                "    button {\n" +
                "        padding: 10px;\n" +
                "        margin: 10px 0;\n" +
                "        border: none;\n" +
                "        border-radius: 3px;\n" +
                "        background-color: #5c85d6;\n" +
                "        color: white;\n" +
                "        cursor: pointer;\n" +
                "    }\n" +
                "    button:hover {\n" +
                "        background-color: #5071c2;\n" +
                "    }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"login-container\">\n" +
                "    <h2>Login</h2>\n" +
                "    <form action=\"/login\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"username\" placeholder=\"Username\" required>\n" +
                "        <input type=\"password\" name=\"password\" placeholder=\"Password\" required>\n" +
                "        <button type=\"submit\">Login</button>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable(value = "id") Integer id) {
        return "Test " + id;
    }
}
