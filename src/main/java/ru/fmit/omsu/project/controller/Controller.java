package ru.fmit.omsu.project.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String init(){
        return "OK";
    }
}
