package jae_kyeonggg.board.repository;

import jae_kyeonggg.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingOrContentContaining(String keywordTitle, String keywordContent);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.views = p.views + 1 WHERE p.id = :postId")
    void increaseViews(@Param("postId") Long postId);

    List<Post> findAllByOrderByCreatedAtDesc();

    List<Post> findAllByOrderByCreatedAtAsc();

    List<Post> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.likes = p.likes + 1 WHERE p.id = :postId")
    void increaseLikesByPostId(@Param("postId") Long postId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.dislikes = p.dislikes + 1 WHERE p.id = :postId")
    void increaseDislikesByPostId(@Param("postId") Long postId);
}
