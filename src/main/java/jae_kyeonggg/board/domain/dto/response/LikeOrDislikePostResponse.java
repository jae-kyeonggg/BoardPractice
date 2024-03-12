package jae_kyeonggg.board.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeOrDislikePostResponse {

    private Long userId;
    private Long postId;
    private int likes;
    private int dislikes;
}
