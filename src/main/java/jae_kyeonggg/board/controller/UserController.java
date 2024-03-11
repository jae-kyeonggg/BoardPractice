package jae_kyeonggg.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jae_kyeonggg.board.domain.dto.request.EditUserRequest;
import jae_kyeonggg.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 정보 수정")
    @PostMapping("/user/edit/{id}")
    public String edit(@PathVariable(name = "id") Long userId, @RequestBody EditUserRequest request) {
        userService.edit(userId, request.toDomain());
        return "redirect:/user/edit/" + userId;
    }
}
