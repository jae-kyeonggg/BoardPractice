package jae_kyeonggg.board.domain;

import jae_kyeonggg.board.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter
@Builder
@AllArgsConstructor
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
