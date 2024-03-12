package jae_kyeonggg.board.domain.dto.response;

import lombok.*;

import java.util.Date;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int views;
    private int likes;
    private int dislikes;
    private Date createdAt;
    private Date updatedAt;
}
