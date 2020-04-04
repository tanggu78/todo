package com.kkoix.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(){
    	System.out.print("testest");
        return "todo/todoList";
    }

}
