package jae_kyeonggg.board.domain.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class WriteCommentInfo {

    private Long userId;
    private Long postId;
    private String content;
}
