package jae_kyeonggg.board.repository;

import jae_kyeonggg.board.domain.Like;
import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
}
