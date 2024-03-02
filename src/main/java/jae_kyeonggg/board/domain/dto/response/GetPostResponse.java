package jae_kyeonggg.board.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
