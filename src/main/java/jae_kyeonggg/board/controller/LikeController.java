package jae_kyeonggg.board.controller;

import jae_kyeonggg.board.domain.dto.request.LikeOrDislikePostRequest;
import jae_kyeonggg.board.domain.dto.response.LikeOrDislikePostResponse;
import jae_kyeonggg.board.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/posts/like")
    public ResponseEntity<LikeOrDislikePostResponse> postLike(@Valid @RequestBody LikeOrDislikePostRequest request) throws IllegalAccessException {
        return ResponseEntity.ok().body(likeService.like(request.toDomain()));
    }

    @PostMapping("/posts/dislike")
    public ResponseEntity<LikeOrDislikePostResponse> postDislike(@Valid @RequestBody LikeOrDislikePostRequest request) throws IllegalAccessException {
        return ResponseEntity.ok().body(likeService.dislike(request.toDomain()));
    }
}
