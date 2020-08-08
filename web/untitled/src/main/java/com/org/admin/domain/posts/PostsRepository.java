package com.org.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository
// ibatis/MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// JPA 에서는 Repository라고 부르며 인터페이스로 생성
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long> {//기본적인 CRUD 메소드가 자동 생성
}
