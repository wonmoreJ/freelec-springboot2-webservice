package com.more.book.springboot.web;

import com.more.book.springboot.config.auth.LoginUser;
import com.more.book.springboot.config.auth.dto.SessionUser;
import com.more.book.springboot.service.posts.PostsService;
import com.more.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
        //mustache 스타터 덕분에 컨트롤러에서 문자열을 반화할 때 앞의경로와 뒤의 파일확장자는 자동으로 지정된다.
        //앞의 경로는 src/main/resources/templates/index.mustache 로 전환되어 View Resolver 가 처리하게 됨
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
