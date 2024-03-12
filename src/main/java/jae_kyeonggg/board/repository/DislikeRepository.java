package jae_kyeonggg.board.repository;

import jae_kyeonggg.board.domain.Dislike;
import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {

    Optional<Dislike> findByUserAndPost(User user, Post post);
}
