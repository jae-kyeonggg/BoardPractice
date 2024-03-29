package jae_kyeonggg.board.service;

import jae_kyeonggg.board.config.oauth.CustomAuthorityUtils;
import jae_kyeonggg.board.config.oauth.OAuthAttributes;
import jae_kyeonggg.board.config.oauth.dto.SessionUser;
import jae_kyeonggg.board.domain.Authority;
import jae_kyeonggg.board.domain.CustomOAuth2User;
import jae_kyeonggg.board.domain.User;
import jae_kyeonggg.board.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> originAttributes = oAuth2User.getAttributes();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, originAttributes);
        User user = signUp(attributes);
        String email = user.getEmail();
        List<Authority> authorities = authorityUtils.createAuthorities(email);
        user.setRoles(authorities);
        userRepository.save(user);
        session.setAttribute("user", new SessionUser(user));

        return new CustomOAuth2User(registrationId, originAttributes, authorities, email);
    }

    private User signUp(OAuthAttributes attributes) {
        Optional<String> optionalEmail = Optional.ofNullable(attributes.getEmail());
        if (optionalEmail.isPresent()) {
            String email = optionalEmail.get();
            User user = userRepository.findByEmail(email)
                    .orElse(User.builder()
                            .email(email)
                            .name(attributes.getName())
                            .nickname(attributes.getNickname() == null ? attributes.getEmail() : attributes.getNickname())  //닉네임이 없는 사용자는 가입 시 닉네임이 이메일로 대체되게 함
                            .build());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("이메일 정보가 존재하지 않습니다.");
        }
    }
}
