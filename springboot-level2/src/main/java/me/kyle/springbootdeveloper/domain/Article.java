package me.kyle.springbootdeveloper.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter     // Getter 함수 대용
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 생성자 대신 사용
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder        // 빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
