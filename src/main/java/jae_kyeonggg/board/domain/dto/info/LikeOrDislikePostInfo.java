package jae_kyeonggg.board.domain.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class LikeOrDislikePostInfo {

    private Long userId;
    private Long postId;
}
