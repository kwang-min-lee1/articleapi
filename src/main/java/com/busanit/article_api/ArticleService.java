package com.busanit.article_api;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스 계층 선언
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    // 모두 조회 메서드
    List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // 하나만 조회 메서드
    Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 생성 메서드(INSERT)
    @Transactional
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    // UPDATE
    @Transactional
    public Article updateArticle(Long id, Article updateArticle) {
        // 조회하여 내용 변경 및 저장
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {

            if (updateArticle.getTitle() != null) {
                article.setTitle(updateArticle.getTitle());
            }

            if (updateArticle.getAuthor() != null) {
                article.setAuthor(updateArticle.getAuthor());
            }

            if (updateArticle.getContent() != null) {
                article.setContent(updateArticle.getContent());
            }
            return articleRepository.save(article);
        } else  {
            return null;
        }

    }

    // DELETE
    @Transactional
    public boolean deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.delete(article);
            return true;
        } else  {
            return false;
        }
    }

}
