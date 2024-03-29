package me.kyle.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)      // 기본 생성자
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;    // DB 테이블 id 컬럼
    
    @Column(name="name",nullable = false)       // name이라는 not null 컬럼과 매핑
    private String name;    // DB 테이블 name 컬럼
}
