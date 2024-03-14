package jae_kyeonggg.board.domain.dto.request;

import jae_kyeonggg.board.domain.dto.info.EditPostInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class EditPostRequest {

    private Long userId;
    private String title;
    private String content;

    public EditPostInfo toDomain() {
        return new EditPostInfo(userId, title, content);
    }
}
