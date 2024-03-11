package jae_kyeonggg.board.service;

import jae_kyeonggg.board.config.oauth.dto.SessionUser;
import jae_kyeonggg.board.domain.User;
import jae_kyeonggg.board.domain.dto.info.EditUserInfo;
import jae_kyeonggg.board.domain.dto.response.EditUserResponse;
import jae_kyeonggg.board.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Transactional
    public EditUserResponse edit(Long userId, EditUserInfo editUserInfo) {
        User foundUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        foundUser.edit(editUserInfo.getName(), editUserInfo.getNickname());
        User savedUser = userRepository.save(foundUser);
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        sessionUser.setNameAndNicknameByDb(savedUser.getName(), savedUser.getNickname());
        EditUserResponse response = EditUserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .nickname(savedUser.getNickname())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
        return response;
    }
}
