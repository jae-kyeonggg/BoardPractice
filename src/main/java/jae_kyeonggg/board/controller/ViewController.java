package jae_kyeonggg.board.controller;

import jae_kyeonggg.board.config.oauth.LoginUser;
import jae_kyeonggg.board.config.oauth.dto.SessionUser;
import jae_kyeonggg.board.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String afterLogin(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "main";
    }

    @GetMapping("/posts/save")
    public String write(Model model) {
        model.addAttribute("post", new Post());
        return "post-save";
    }
}
