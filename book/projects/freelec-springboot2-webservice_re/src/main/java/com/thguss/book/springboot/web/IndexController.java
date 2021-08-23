package com.thguss.book.springboot.web;

import com.thguss.book.springboot.config.auth.LoginUser;
import com.thguss.book.springboot.config.auth.dto.SessionUser;
import com.thguss.book.springboot.service.posts.PostsService;
import com.thguss.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // @LoginUser로 세션 가져오기
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){ // user 값이 있으면 model에 userName으로 등록
            model.addAttribute("userName", user.getName());
        }
        
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


}
