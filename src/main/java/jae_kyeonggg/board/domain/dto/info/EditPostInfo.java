package jae_kyeonggg.board.domain.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class EditPostInfo {

    private Long userId;
    private String title;
    private String content;
}
