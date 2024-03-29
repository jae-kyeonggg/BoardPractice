package jae_kyeonggg.board.config.oauth.dto;

import jae_kyeonggg.board.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long userId;
    private String name;
    private String email;
    private String nickname;

    public SessionUser(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }

    public void setNameAndNicknameByDb(String dbName, String dbNickname) {
        this.name = dbName;
        this.nickname = dbNickname;
    }
}
