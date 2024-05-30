package com.busanit.article_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// JPA 레포지토리 선언 -> Entity 타입과, ID 의 타입 지정
// 기본적인 CRUD 메서드가 자동 제공
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
