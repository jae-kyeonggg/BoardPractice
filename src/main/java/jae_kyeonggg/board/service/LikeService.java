package jae_kyeonggg.board.service;

import jae_kyeonggg.board.domain.Dislike;
import jae_kyeonggg.board.domain.Like;
import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.domain.User;
import jae_kyeonggg.board.domain.dto.info.LikeOrDislikePostInfo;
import jae_kyeonggg.board.domain.dto.response.LikeOrDislikePostResponse;
import jae_kyeonggg.board.repository.DislikeRepository;
import jae_kyeonggg.board.repository.LikeRepository;
import jae_kyeonggg.board.repository.PostRepository;
import jae_kyeonggg.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeOrDislikePostResponse like(LikeOrDislikePostInfo likeOrDislikePostInfo) throws IllegalAccessException {
        User user = userRepository.findById(likeOrDislikePostInfo.getUserId()).orElseThrow(IllegalArgumentException::new);
        Post post = postRepository.findById(likeOrDislikePostInfo.getPostId()).orElseThrow(IllegalArgumentException::new);
        Optional<Like> optionalLike = likeRepository.findByUserAndPost(user, post);
        if (optionalLike.isEmpty()) {
            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likeRepository.save(like);
            postRepository.increaseLikesByPostId(likeOrDislikePostInfo.getPostId());
        } else {
            throw new IllegalAccessException("이미 추천한 게시글입니다.");
        }
        LikeOrDislikePostResponse response = LikeOrDislikePostResponse.builder()
                .userId(likeOrDislikePostInfo.getUserId())
                .postId(likeOrDislikePostInfo.getPostId())
                .likes(post.getLikes() + 1)
                .dislikes(post.getDislikes())
                .build();
        return response;
    }

    public LikeOrDislikePostResponse dislike(LikeOrDislikePostInfo likeOrDislikePostInfo) throws IllegalAccessException {
        User user = userRepository.findById(likeOrDislikePostInfo.getUserId()).orElseThrow(IllegalArgumentException::new);
        Post post = postRepository.findById(likeOrDislikePostInfo.getPostId()).orElseThrow(IllegalArgumentException::new);
        Optional<Dislike> optionalDislike = dislikeRepository.findByUserAndPost(user, post);
        if (optionalDislike.isEmpty()) {
            Dislike like = Dislike.builder()
                    .user(user)
                    .post(post)
                    .build();
            dislikeRepository.save(like);
            postRepository.increaseDislikesByPostId(likeOrDislikePostInfo.getPostId());
        } else {
            throw new IllegalAccessException("이미 비추천한 게시글입니다.");
        }
        LikeOrDislikePostResponse response = LikeOrDislikePostResponse.builder()
                .userId(likeOrDislikePostInfo.getUserId())
                .postId(likeOrDislikePostInfo.getPostId())
                .likes(post.getLikes())
                .dislikes(post.getDislikes() + 1)
                .build();
        return response;
    }
}
