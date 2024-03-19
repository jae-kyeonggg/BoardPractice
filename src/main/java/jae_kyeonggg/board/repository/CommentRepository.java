package jae_kyeonggg.board.repository;

import jae_kyeonggg.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostIdOrderByCreatedAtAsc(Long postId);
}
