package me.kyle.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kyle.springbootdeveloper.domain.Article;
import me.kyle.springbootdeveloper.dto.AddArticleRequest;
import me.kyle.springbootdeveloper.dto.ArticleResponse;
import me.kyle.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    // 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article saveArticle=blogService.save(request);
        // 요청한 자원이 성공적으로 생성되었으며, 저장된 블로그 글 정보를 응답 객체에 담아 전송함
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }
}
