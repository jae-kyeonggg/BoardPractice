package jae_kyeonggg.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jae_kyeonggg.board.domain.Comment;
import jae_kyeonggg.board.domain.dto.request.WriteCommentRequest;
import jae_kyeonggg.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 등록")
    @PostMapping("/write-comment")
    public Comment write(@RequestBody WriteCommentRequest request) {
        return commentService.writeComment(request.toDomain());
    }

}
