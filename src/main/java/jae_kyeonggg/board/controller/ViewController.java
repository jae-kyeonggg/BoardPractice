package jae_kyeonggg.board.controller;

import jae_kyeonggg.board.config.oauth.LoginUser;
import jae_kyeonggg.board.config.oauth.dto.SessionUser;
import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.service.CommentService;
import jae_kyeonggg.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
            return "redirect:/";
        }
        return "home";
    }

    @GetMapping("/")
    public String afterLogin(Model model, @LoginUser SessionUser user) {
        if (user == null) {
            return "home";
        }
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/posts/save")
    public String write(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("post", new Post());
        return "post-save";
    }

    @GetMapping("/posts")
    public String postsListPaged(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "descending", defaultValue = "true") Boolean descending) {
        model.addAttribute("paging", postService.findAllPaged(page, descending));
        return "post-list";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user", user);
        return "mypage";
    }

    @GetMapping("/mypage/edit")
    public String editProfile(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @GetMapping("/posts/{postId}")
    public String postDetail(Model model, @PathVariable("postId") Long postId, @LoginUser SessionUser user) {
        model.addAttribute("postId", postId);
        model.addAttribute("sessionUserId", user.getUserId());
        model.addAttribute("detail", postService.getDetail(postId));
        model.addAttribute("comments", commentService.getAllComments(postId));
        return "post-detail";
    }

    @GetMapping("/posts/filter")
    public String getByWriter(Model model, @RequestParam(value = "writer") String writer, @LoginUser SessionUser user) {
        model.addAttribute("writer", writer);
        model.addAttribute("posts", postService.findByUserId(user.getUserId()));
        return "post-writer";
    }

    @GetMapping("/posts/edit")
    public String editPost(Model model, @RequestParam(value = "postId") Long postId, @LoginUser SessionUser user) {
        model.addAttribute("user", user);
        model.addAttribute("detail", postService.getDetail(postId));
        return "edit-post";
    }
}
