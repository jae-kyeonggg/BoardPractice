package jae_kyeonggg.board.controller;

import jae_kyeonggg.board.domain.dto.request.LikeOrDislikePostRequest;
import jae_kyeonggg.board.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/like")
    public void postLike(@Valid @RequestBody LikeOrDislikePostRequest request) throws IllegalAccessException {
        likeService.like(request.toDomain());
    }

    @PostMapping("/posts/dislike")
    public void postDislike(@Valid @RequestBody LikeOrDislikePostRequest request) throws IllegalAccessException {
        likeService.dislike(request.toDomain());
    }
}
