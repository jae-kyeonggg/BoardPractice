package jae_kyeonggg.board.domain.dto.request;

import jae_kyeonggg.board.domain.dto.info.EditUserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class EditUserRequest {

    private String name;
    private String nickname;

    public EditUserInfo toDomain() {
        return new EditUserInfo(name, nickname);
    }
}
