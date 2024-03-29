package me.kyle.springbootdeveloper.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

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

    @Column(name="author", nullable = false)
    private String author;

    @Builder        // 빌더 패턴으로 객체 생성
    public Article(String author, String title, String content){
        this.author=author;
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
