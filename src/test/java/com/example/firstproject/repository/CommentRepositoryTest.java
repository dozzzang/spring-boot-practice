package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {
@Autowired
CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시물의 모든 댓글 조회")
    void findByArticleId() {
        /*Case 1 : 4번 게시글의 모든 댓글 조회*/
        {
            // 1. 입력 데이터 조회
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L,"당신의 인생 영화는?","댓글 고고");
            Comment a = new Comment(1L, article,"Park", "굿 윌 헌팅");
            Comment b = new Comment(2L,article,"Kim","i am san");
            Comment c = new Comment(3L,article,"Choi","쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 4, 비교 및 검증
            assertEquals(expected.toString(),comments.toString());
        }
        {// case 2 : 9번 게시글의 모든 댓글 조회
            Long articleId = 9L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(),article.toString());
        }
        { // case 3 : 999번 게시글의 모든 댓글 조회
            Long articleId = 999L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(),article.toString());
        }
        { // case 4 : -1번 게시글의 모든 댓글 조회
            Long articleId = -1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = null;
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(),article.toString());
        }
    }

    @Test
    void findByNickname() {
        /* Case 1 : "Kim"의 모든 댓글 조회*/
        {
            String nickname = "Kim";
            List<Comment> comments = commentRepository.findByNickname("Kim");
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 고고"),nickname, "i am sam");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고"), nickname,"샤브샤브");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname,"유튜브 시청");
            List<Comment> expected = Arrays.asList(a, b, c);
            assertEquals(expected.toString(), comments.toString());
        }
        {   // Case 2 : null의 모든 댓글 조회
            
            List<Comment> comments = commentRepository.findByNickname(null);
            List<Comment> expected = Arrays.asList();
            assertEquals(comments.toString(),expected.toString());
        }
        {   //Case3 : ""의 모든 댓글 조회
            List<Comment> comments = commentRepository.findByNickname(null);
            List<Comment> expected = Arrays.asList();
            assertEquals(comments.toString(),expected.toString());
        }
    }

}