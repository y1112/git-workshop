package com.org.admin.domain.posts;

import com.org.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter// 롬복의 어노테이션, 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor// 롬복의 어노테이션, 기본 생성자 자동 추가
@Entity// JPA의 어노테이션, 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {
    @Id// 해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy= GenerationType.IDENTITY)// PK의 생성 규칙을 나타냄, 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length=500, nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    private String author;

    @Builder// 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
