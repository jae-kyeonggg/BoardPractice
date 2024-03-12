package jae_kyeonggg.board.domain.dto.request;

import jae_kyeonggg.board.domain.dto.info.LikeOrDislikePostInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class LikeOrDislikePostRequest {

    private Long userId;
    private Long postId;

    public LikeOrDislikePostInfo toDomain() {
        return new LikeOrDislikePostInfo(userId, postId);
    }
}
