package com.busanit.article_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 표현 계층임을 선언
@RequestMapping("api/article")  // 기본 경로
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // CREATE
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // 데이터베이스 접근은 서비스 계층에 위임
        Article createArticle = articleService.createArticle(article);

        return ResponseEntity.status(HttpStatus.CREATED).body(createArticle);
    }

    // READ
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // READ one
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        // ID 로 특정 기사 조회
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return ResponseEntity.notFound().build(); // 기사가 없으면 404 Not Found 응답
        } else {
            return ResponseEntity.ok(article);  // 기사가 있으면 200 ok 응답과 함께 책 객체를 리턴
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updateArticle) {
        Article article = articleService.updateArticle(id,updateArticle);
        if (article == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(article);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean isDeleted = articleService.deleteArticle(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}
