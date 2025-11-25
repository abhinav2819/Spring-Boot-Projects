package com.springboot.hospitalmanagement.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.hospitalmanagement.dto.LoginResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String registrationId = token.getAuthorizedClientRegistrationId();

        ResponseEntity<LoginResponseDto> loginResponse = authService
                .handleOAuth2LoginRequest(oAuth2User, registrationId);

        //Hear we have modified our response(Appending)

        //It will give status code in response that we have set in handleOAuth2LoginRequest method
        response.setStatus(loginResponse.getStatusCode().value());
        //Set content type to json value as we will return json value to the frontend
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //Then we have to modify our body and the object(loginResponseDto) will be converted in string and forward to
        // frontend in the form of json
        response.getWriter().write(objectMapper.writeValueAsString(loginResponse.getBody()));
    }
}
