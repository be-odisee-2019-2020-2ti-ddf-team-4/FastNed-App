package be.fastned.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String home() {
        return "home";
    }
    @GetMapping("/login-error")
    public String loginerror(Model model) {
        model.addAttribute("error", true);
        return "login";
    }
}
