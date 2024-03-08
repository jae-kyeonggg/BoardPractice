package jae_kyeonggg.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jae_kyeonggg.board.domain.Post;
import jae_kyeonggg.board.domain.dto.request.EditPostRequest;
import jae_kyeonggg.board.domain.dto.request.SavePostRequest;
import jae_kyeonggg.board.domain.dto.response.EditPostResponse;
import jae_kyeonggg.board.domain.dto.response.GetPostResponse;
import jae_kyeonggg.board.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 등록")
    @PostMapping("/posts/write")
    public Long save(@RequestBody SavePostRequest request) {
        return postService.save(request.toDomain());
    }

    @Operation(summary = "게시글 수정")
    @PatchMapping("/posts/edit/{id}")
    public ResponseEntity<EditPostResponse> edit(@PathVariable(name = "id") Long postId, @RequestBody EditPostRequest request) {
        return ResponseEntity.ok().body(postService.edit(postId, request.toDomain()));
    }

    @Operation(summary = "게시글 단건 조회")
    @GetMapping("/posts/{id}")
    public ResponseEntity<GetPostResponse> getDetail(@PathVariable(name = "id") Long postId) {
        return ResponseEntity.ok().body(postService.getDetail(postId));
    }

    @Operation(summary = "키워드 게시글 검색")
    @GetMapping("/posts/search")
    public ResponseEntity<Result<List<Post>>> search(@RequestParam(name = "keyword") String keyword) {
        List<Post> findResult = postService.getByKeyword(keyword);
        return ResponseEntity.ok().body(new Result<>(findResult, findResult.size()));
    }

    @Operation(summary = "유저별 게시글 조회")
    @GetMapping("/posts/find")
    public ResponseEntity<Result<List<Post>>> findByUsername(@RequestParam(name = "writer") String name) {
        List<Post> findResult = postService.getByWriter(name);
        return ResponseEntity.ok().body(new Result<>(findResult, findResult.size()));
    }

    @Getter
    @Setter
    public static class Result<T> {
        private T data;
        private int count;

        public Result(T data, int count) {
            this.data = data;
            this.count = count;
        }
    }
}
