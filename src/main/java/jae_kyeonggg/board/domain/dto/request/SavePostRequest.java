package jae_kyeonggg.board.domain.dto.request;

import jae_kyeonggg.board.domain.dto.info.SavePostInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class SavePostRequest {

    private String title;
    private String content;
    private String writer;

    public SavePostInfo toDomain() {
        return new SavePostInfo(title, content, writer);
    }

}
