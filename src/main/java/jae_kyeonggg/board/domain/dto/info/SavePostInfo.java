package jae_kyeonggg.board.domain.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class SavePostInfo {

    private String title;
    private String content;
    private String writer;
    private Long userId;
}
