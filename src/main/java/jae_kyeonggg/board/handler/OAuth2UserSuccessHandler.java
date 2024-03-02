package jae_kyeonggg.board.handler;

import jae_kyeonggg.board.config.oauth.CustomAuthorityUtils;
import jae_kyeonggg.board.domain.Authority;
import jae_kyeonggg.board.domain.CustomOAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class OAuth2UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final CustomAuthorityUtils authorityUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getEmail();
        List<Authority> authorities = authorityUtils.createAuthorities(email);

        redirect(request, response, email, authorities);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String email, List<Authority> authorities) throws IOException {
        getRedirectStrategy().sendRedirect(request, response, createURI(email).toString());
    }

    private URI createURI(String email) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("email", email);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
