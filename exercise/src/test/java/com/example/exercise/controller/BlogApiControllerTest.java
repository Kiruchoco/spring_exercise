package com.example.exercise.controller;

import ch.qos.logback.core.net.ObjectWriter;
import com.example.exercise.domain.Article;
import com.example.exercise.dto.AddArticleRequest;
import com.example.exercise.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        blogRepository.deleteAll();
    }

    @DisplayName("add Article: 블로그 글 추가에 성공한다.")
    @Test
    void addArticle() throws Exception{
        // given
        final String url="/api/articles";
        final String title="title";
        final String content="content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 직렬화 JSON
        final String requestBody=objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> article=blogRepository.findAll();

        Assertions.assertThat(article.size()).isEqualTo(1); // 크기가 1인지 검증
        Assertions.assertThat(article.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(article.get(0).getContent()).isEqualTo(content);

    }
}