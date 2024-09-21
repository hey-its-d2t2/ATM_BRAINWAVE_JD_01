package com.BRAINWAVE_JD_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ViewController {

    @GetMapping
    public String getResponseBody() {
        return "index"; // Returns the name of the view (e.g., index.html)
    }
}
