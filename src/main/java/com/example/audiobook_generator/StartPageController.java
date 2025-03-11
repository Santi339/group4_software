package com.example.audiobook_generator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    @GetMapping("/")
    public String start() {
        return "start_page"; // refer to src/main/resources/templates/
    }
}