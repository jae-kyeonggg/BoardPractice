package jae_kyeonggg.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jae_kyeonggg.board.domain.dto.request.EditUserRequest;
import jae_kyeonggg.board.domain.dto.response.EditUserResponse;
import jae_kyeonggg.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 정보 수정")
    @PatchMapping("/user/edit/{id}")
    public ResponseEntity<EditUserResponse> edit(@PathVariable(name = "id") Long userId, @RequestBody EditUserRequest request) {
        return ResponseEntity.ok().body(userService.edit(userId, request.toDomain()));
    }
}
