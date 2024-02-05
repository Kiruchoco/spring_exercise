package com.example.exercise.service;

import com.example.exercise.domain.Article;
import com.example.exercise.dto.AddArticleRequest;
import com.example.exercise.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
}
