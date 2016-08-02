package com.yihukurama.springbootdemo.framework.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(@RequestParam(value="name", required=false, defaultValue="yihukurama") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

}