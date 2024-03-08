package jae_kyeonggg.board.handler;

import jae_kyeonggg.board.config.oauth.CustomAuthorityUtils;
import jae_kyeonggg.board.domain.CustomOAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class OAuth2UserSuccessHandler implements AuthenticationSuccessHandler {

    private final CustomAuthorityUtils authorityUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getEmail();
        authorityUtils.createAuthorities(email);

        redirect(response);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
