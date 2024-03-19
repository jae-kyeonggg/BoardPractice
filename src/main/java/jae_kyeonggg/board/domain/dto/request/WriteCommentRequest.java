package jae_kyeonggg.board.domain.dto.request;

import jae_kyeonggg.board.domain.dto.info.WriteCommentInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class WriteCommentRequest {

    private Long userId;
    private Long postId;
    private String content;

    public WriteCommentInfo toDomain() {
        return new WriteCommentInfo(userId, postId, content);
    }
}
